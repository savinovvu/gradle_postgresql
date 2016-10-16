package ru.inbox.savinov_vu.config;


import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.inbox.savinov_vu.controller.SavedFileController;

public class ApplicationStartConfig {
    private ApplicationStartConfig() {
    }

    public static void main(String[] args) throws JsonProcessingException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"Spring/config.xml"});
        SavedFileController controller = (SavedFileController) context.getBean("controller");
        controller.start();
    }

}
