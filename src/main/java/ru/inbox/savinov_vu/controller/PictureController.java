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
import ru.inbox.savinov_vu.util.Downloader;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.staticFileLocation;

public class PictureController {
     @Autowired
     private PictureService pictureService;

    private String pictureFolderPath = "src/main/resources/public/filesJpg/";

    ObjectMapper mapper = new ObjectMapper();

    public void start() throws IOException {

        staticFileLocation("/public");


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


            for (int i = 0; i < jsonArray.size(); i++) {
                Map<String, Object> map = mapper.readValue(String.valueOf(jsonArray.get(i)), new TypeReference<Map<String, Object>>() {
                });
                String json2 = String.valueOf(map.get("images"));
                json2 = json2.split("standard_resolution=")[1].split("url=")[1].split(",")[0];
                Downloader.downloadFiles(json2);

            }

            return "<a href=\"http://localhost:4567\">на стартовую</a>";
        });


        get("/start", (request, res) -> {
            try {
                String strURL = "https://api.instagram.com/v1/users/self/media/liked?access_token=2999480870.43f2b9f.d873416ad8ab430bbf4a8b82597a6cd7";

                URL connection = new URL(strURL);

                String strPath = String.format("src/main/resources/public/filesJpg/%s.txt", LocalDateTime.now());
                int buffSize = 1000;

                HttpURLConnection urlconn = (HttpURLConnection) connection.openConnection();
                urlconn.setRequestMethod("GET");
                urlconn.connect();


                InputStream inputStream = urlconn.getInputStream();
                OutputStream writer = new FileOutputStream(strPath);
                byte buffer[] = new byte[buffSize];
                int c = inputStream.read(buffer);
                while (c > 0) {
                    writer.write(buffer, 0, c);
                    c = inputStream.read(buffer);
                }
                writer.flush();
                writer.close();
                inputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }


            System.out.println("все норм");
            return "KO";
        });


    }


}