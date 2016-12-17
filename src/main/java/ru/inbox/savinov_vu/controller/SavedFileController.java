package ru.inbox.savinov_vu.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import ru.inbox.savinov_vu.service.Service;

import static spark.Spark.staticFileLocation;

public class SavedFileController {
    @Autowired
    private Service service;

    public void start() throws JsonProcessingException {

        staticFileLocation("/public");

        String location = "files";          // the directory location where files will be stored
        long maxFileSize = 100000000;       // the maximum size allowed for uploaded files
        long maxRequestSize = 100000000;    // the maximum size allowed for multipart/form-data requests
        int fileSizeThreshold = 1024;


        /*Picture picture = new Picture("url1", "loadpath", 5);
        service.save(picture);*/

/*
        post("/file", "multipart/form-data", (request, response) -> {
      *//*      MultipartConfigElement multipartConfigElement = new MultipartConfigElement(
                    location, maxFileSize, maxRequestSize, fileSizeThreshold);
            request.raw().setAttribute("org.eclipse.jetty.multipartConfig",
                    multipartConfigElement);
            service.saveFile(request.raw().getParts().stream().findFirst().get());

            response.redirect("/readAll");*//*
            return "oK";
        });

        get("/file/:name", (request, response) -> {

            byte[] bytes = Files.readAllBytes(Paths.get("files/" + request.params(":name")));

            ServletOutputStream outputStream = response.raw().getOutputStream();
            outputStream.write(bytes);
            outputStream.flush();
            outputStream.close();
            return response;
        });

        post("/readAll", (request, response) -> {

            String s = service.read();

            return s;
        });


        post("/remove", (request, response) -> {

            ObjectMapper mapper = new ObjectMapper();
            Picture savedFile = mapper.readValue(request.body(), Picture.class);
            service.delete(savedFile);

            return service.read();
        });*/


    }

}