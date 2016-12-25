package ru.inbox.savinov_vu.controller;

import org.jinstagram.auth.InstagramAuthService;
import org.jinstagram.auth.model.Token;
import org.jinstagram.auth.model.Verifier;
import org.jinstagram.auth.oauth.InstagramService;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;

import static spark.Spark.*;

public class PictureController {
    /* @Autowired
     private PictureService pictureService;*/

    private static final Token EMPTY_TOKEN = null;
    private String pictureFolderPath = "src/main/resources/public/filesJpg/";
    static int b;

    public void start() throws IOException {

        staticFileLocation("/public");


        get("/getCode", (request, response) -> {
            System.out.println(++b);
            String code = request.queryParams("code");
            System.out.println(code);

            InstagramService instagramService = new InstagramAuthService()
                    .apiKey("43f2b9f73a2841e7af9dff5712fe29e6")
                    .apiSecret("1bc12cc086c0402f86dce4d20fd86523")
                    .callback("http://localhost:4567/getCode")
                    .scope("public_content")
                    .build();


            String authorizationUrl = instagramService.getAuthorizationUrl();
            Verifier verifier = new Verifier(code);
            Token accessToken = instagramService.getAccessToken(verifier);
            System.out.println(accessToken);


            return "s";
        });

        post("/getCode", (request, response) -> {
            System.out.println(++b);
            System.out.println(b);
            System.out.println(b);
            System.out.println(b);
            System.out.println("пост");
            System.out.println("пост");
            System.out.println("пост");

            System.out.println(request.body());

          /*  String s = pictureService.read();
            System.out.println("строка");
            System.out.println(s);*/

            return "s";
        });


        String urlString = "https://api.instagram.com/oauth/authorize/?client_id=43f2b9f73a2841e7af9dff5712fe29e6&redirect_uri=http://localhost:4567/getCode&response_type=code";





     /*   URL url = new URL(urlString);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        // By default it is GET request
        con.setRequestMethod("GET");
        //add request header
        int responseCode = con.getResponseCode();
        System.out.println("Sending get request : "+ url);
        System.out.println("Response code : "+ responseCode);
        // Reading response from input Stream
        //printing result from response
        System.out.println("выслали");*/

        /*HttpURLConnection httpURLConnection = (HttpURLConnection)(new URL( urlString ).openConnection());
        httpURLConnection.setInstanceFollowRedirects( true
        );
        httpURLConnection.connect();
        int code = httpURLConnection.getResponseCode();
        System.out.println( code );
        String location = httpURLConnection.getHeaderField( "code");
        System.out.println( location );
*/


      /*  put("/picture", (request, response) -> {
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
        });*/
        get("/start", (request, res) -> {
            try {

                // String strURL = "https://api.instagram.com/v1/users/self/media/liked?access_token=2999480870.43f2b9f.d873416ad8ab430bbf4a8b82597a6cd7";
                String strURL = "https://api.instagram.com/oauth/authorize/?client_id=43f2b9f73a2841e7af9dff5712fe29e6&redirect_uri=http://localhost:4567//getCode&response_type=token";
                //String strURL = "https://api.instagram.com/oauth/authorize/";

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




          /*  get(String.format("/getPicture/%s:name", pictureFolderPath), (request, response) -> {
            String path = pictureFolderPath + request.params(":name");
            byte[] bytes = Files.readAllBytes(Paths.get(path));
            ServletOutputStream outputStream = response.raw().getOutputStream();
            outputStream.write(bytes);
            outputStream.flush();
            outputStream.close();



            return "Ko";
        });*/


    }


}




