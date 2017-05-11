package ru.inbox.savinov_vu.config;


import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.inbox.savinov_vu.controller.PictureController;

import java.io.IOException;

public class ApplicationStartConfig {
    private ApplicationStartConfig() {
    }

    public static void main(String[] args)  {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"context/config.xml"});
        PictureController controller = (PictureController) context.getBean("controller");
        try {
            controller.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
