package ru.inbox.savinov_vu.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import ru.inbox.savinov_vu.entity.SavedFile;
import ru.inbox.savinov_vu.service.Service;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import static spark.Spark.*;

public class HumanController {
    @Autowired
    private Service service;

    public void start() throws JsonProcessingException {

        staticFileLocation("/public");

        String location = "image";          // the directory location where files will be stored
        long maxFileSize = 100000000;       // the maximum size allowed for uploaded files
        long maxRequestSize = 100000000;    // the maximum size allowed for multipart/form-data requests
        int fileSizeThreshold = 1024;


        post("/file","multipart/form-data" ,(request, response) -> {
            MultipartConfigElement multipartConfigElement = new MultipartConfigElement(
                    location, maxFileSize, maxRequestSize, fileSizeThreshold);
            request.raw().setAttribute("org.eclipse.jetty.multipartConfig",
                    multipartConfigElement);
            service.saveFile(request.raw().getParts().stream().findFirst().get());
            response.redirect("/readAll");
            System.out.println(response.body());
            return null;
        });

        get("/file/:name", (request, response) -> {

            byte[] bytes = Files.readAllBytes(Paths.get("files/" + request.params(":name")));

            ServletOutputStream outputStream = response.raw().getOutputStream();
            outputStream.write(bytes);
            outputStream.flush();
            outputStream.close();
            return response;
        });

        post("/readAll", (request, response) -> service.read());


        post("/remove", (request, response) -> {

            ObjectMapper mapper = new ObjectMapper();
            SavedFile savedFile = mapper.readValue(request.body(), SavedFile.class);
            service.delete(savedFile);

            return service.read();
        });

        post("/update", (request, response) -> {
            ObjectMapper mapper = new ObjectMapper();
            SavedFile savedFile = mapper.readValue(request.body(), SavedFile.class);
            service.update(savedFile);
            return service.read();
        });

        post("/add", (request, response) -> {

            ObjectMapper mapper = new ObjectMapper();
            SavedFile tempSavedFile = mapper.readValue(request.body(), SavedFile.class);
            SavedFile savedFile = new SavedFile(tempSavedFile.getName(), tempSavedFile.getLoadpath());
            service.create(savedFile);
            return service.read();


        });


    }

}