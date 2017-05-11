package ru.inbox.savinov_vu.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jinstagram.auth.InstagramAuthService;
import org.jinstagram.auth.model.Token;
import org.jinstagram.auth.model.Verifier;
import org.jinstagram.auth.oauth.InstagramService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import ru.inbox.savinov_vu.service.PictureService;

import javax.servlet.ServletOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.staticFileLocation;

public class PictureController {
     @Autowired
     private PictureService pictureService;



    private String pictureFolderPath = "src/main/resources/public/filesJpg/";


    public void start() throws IOException {

        staticFileLocation("/public");



        /*get verifier code and url from instagram*/
        get("/getCode", (request, response) -> {
            InstagramService instagramService = new InstagramAuthService()
                    .apiKey("43f2b9f73a2841e7af9dff5712fe29e6")
                    .apiSecret("1bc12cc086c0402f86dce4d20fd86523")
                    .callback("http://localhost:4567/getCode")
                    .scope("public_content")
                    .build();
            Verifier verifier = new Verifier(request.queryParams("code"));
            Token accessToken = instagramService.getAccessToken(verifier);
            BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(String
                    .format("https://api.instagram.com/v1/users/self/media/liked?access_token=%s", accessToken.getToken())).openStream()));

            String jsonString = "";
            String inputLine = "";
            while ((inputLine = reader.readLine()) != null) {
                jsonString += inputLine;
            }
            reader.close();

            JSONObject json = (JSONObject) new JSONParser().parse(jsonString);
            JSONArray jsonArray = (JSONArray) json.get("data");
            ObjectMapper mapper = new ObjectMapper();
            for (int i = 0; i < jsonArray.size(); i++) {
                Map<String, Object> map = mapper.readValue(String.valueOf(jsonArray.get(i)), new TypeReference<Map<String, Object>>() {
                });
                String json2 = String.valueOf(map.get("images"));
                String url = json2.split("standard_resolution=")[1].split("url=")[1].split(",")[0];
                pictureService.put(url);

            }

            return "<a href=\"http://localhost:4567\">выбрать и посмотреть фото</a>";
        });


        get("/getAll", (request, res) ->
             pictureService.read()
        );

        get( "/file/" + pictureFolderPath + ":name", (request, response) -> {


            byte[] bytes = Files.readAllBytes(Paths.get(pictureFolderPath + request.params(":name")));
            ServletOutputStream outputStream = response.raw().getOutputStream();
            outputStream.write(bytes);
            outputStream.flush();
            outputStream.close();
            return response;
        });


    }


}