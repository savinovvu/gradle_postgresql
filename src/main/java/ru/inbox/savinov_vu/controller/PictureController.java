package ru.inbox.savinov_vu.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import ru.inbox.savinov_vu.service.PictureService;

import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import static spark.Spark.*;

public class PictureController {
    @Autowired
    private PictureService pictureService;
    private String pictureFolderPath = "src/main/resources/public/filesJpg/";


    public void start() throws IOException {

        staticFileLocation("/public");


        put("/picture", (request, response) -> {
            ObjectMapper mapper = new ObjectMapper();
            System.out.println("до " + request.body());
            Map<String, Object> map = mapper.readValue(request.body(), new TypeReference<Map<String, Object>>() {
            });
            String url = (String) map.get("url");
            pictureService.put(url);
            return pictureService.read();
        });


        get("/readAll", (request, response) -> {
            String s = pictureService.read();
            System.out.println("строка");
            System.out.println(s);

            return s;
        });

        get(String.format("/getPicture/%s:name", pictureFolderPath), (request, response) -> {
            String path = pictureFolderPath + request.params(":name");
            byte[] bytes = Files.readAllBytes(Paths.get(path));
            ServletOutputStream outputStream = response.raw().getOutputStream();
            outputStream.write(bytes);
            outputStream.flush();
            outputStream.close();
            return response;
        });


    }


}




