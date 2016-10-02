package ru.inbox.savinov_vu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import ru.inbox.savinov_vu.service.Service;

public class HumanController {
    @Autowired
    private Service service;

    public void start()  {

        service.read().forEach(System.out::println);


      /*  staticFileLocation("/public");


        post("/hello", (request, response) -> {

            return "success";
                });*/


    }

}