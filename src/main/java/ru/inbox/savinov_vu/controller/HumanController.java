package ru.inbox.savinov_vu.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import ru.inbox.savinov_vu.service.Service;

import static spark.Spark.post;
import static spark.Spark.staticFileLocation;

public class HumanController {
    @Autowired
    private Service service;

    public void start()  {
    /*   service.read().forEach(System.out::println);
*/

        staticFileLocation("/public");



        post("/readAll", (request, response) -> {
            ObjectMapper mapper = new ObjectMapper();
            String jsonOut = mapper.writeValueAsString(service.read());



            return jsonOut;

                });

        post("/delete", (request, response) -> {
            System.out.println("тест");
            ObjectMapper mapper = new ObjectMapper();
            String jsonOut = mapper.writeValueAsString(service.read());



            return jsonOut;

        });


    }

}