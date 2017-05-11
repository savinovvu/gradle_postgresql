package ru.inbox.savinov_vu.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import ru.inbox.savinov_vu.entity.Human;
import ru.inbox.savinov_vu.service.Service;

import static spark.Spark.post;
import static spark.Spark.staticFileLocation;

public class HumanController {
    @Autowired
    private Service service;

    public void start() throws JsonProcessingException {

        staticFileLocation("/public");


        post("/readAll", (request, response) -> service.read());

        post("/remove", (request, response) -> {

            ObjectMapper mapper = new ObjectMapper();
            Human human = mapper.readValue(request.body(), Human.class);
            service.delete(human);

            return service.read();
        });

        post("/update", (request, response) -> {
            ObjectMapper mapper = new ObjectMapper();
            Human human = mapper.readValue(request.body(), Human.class);
            service.update(human);
            return service.read();
        });

        post("/add", (request, response) -> {

            ObjectMapper mapper = new ObjectMapper();
            Human tempHuman = mapper.readValue(request.body(), Human.class);
            Human human = new Human(tempHuman.getName(), tempHuman.getPhoneNumber());
            service.create(human);
            return service.read();


        });


    }

}