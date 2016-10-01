package ru.inbox.savinov_vu.controller;

import ru.inbox.savinov_vu.service.Service;
import ru.inbox.savinov_vu.service.ServiceImpl;

public class HumanController {
    static Service service = new ServiceImpl();


    public static void main(String[] args) throws ClassNotFoundException {
       // service.delete(new Human(1,"Иван45", "356"));


        service.read().forEach(System.out::println);


      /*  staticFileLocation("/public");


        post("/hello", (request, response) -> {

            return "success";
                });*/


    }

}