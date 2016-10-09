package ru.inbox.savinov_vu.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import ru.inbox.savinov_vu.entity.Human;
import ru.inbox.savinov_vu.service.Service;

import static spark.Spark.post;
import static spark.Spark.staticFileLocation;

public class HumanController {
    @Autowired
    private Service service;

    public void start()  {


        staticFileLocation("/public");



        post("/readAll", (request, response) -> service.read());

        post("/remove", (request, response) -> {
            ObjectMapper mapper = new ObjectMapper();
            Human human = mapper.readValue(request.body(), Human.class);
            System.out.println("human: " + human);

            return service.read();
        });


    }

}