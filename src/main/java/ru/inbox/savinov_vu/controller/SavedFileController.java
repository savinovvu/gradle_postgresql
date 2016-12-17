package ru.inbox.savinov_vu.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import ru.inbox.savinov_vu.service.PictureService;
import ru.inbox.savinov_vu.util.Downloader;

public class SavedFileController {
    @Autowired
    private PictureService pictureService;

    final String location = "files";          // the directory location where files will be stored
    final long maxFileSize = 100000000;       // the maximum size allowed for uploaded files
    final long maxRequestSize = 100000000;    // the maximum size allowed for multipart/form-data requests
    final int fileSizeThreshold = 1024;

    public void start() throws JsonProcessingException {

       // staticFileLocation("/public");

        //(String strURL, String strPath, int buffSize)

        new Downloader().downloadFiles("http://scontent.cdninstagram.com/t51.2885-15/e15/10611081_365696083587824_1388076795_n.jpg?ig_cache_key=Nzk5ODk0MjkyOTkwMjUzOTM2.2",
                "files/1.jpg", 1000);









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