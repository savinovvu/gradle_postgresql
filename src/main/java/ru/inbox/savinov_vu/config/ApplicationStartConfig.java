package ru.inbox.savinov_vu.config;


import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.inbox.savinov_vu.controller.SavedFileController;

public class ApplicationStartConfig {
    private ApplicationStartConfig() {
    }

    public static void main(String[] args) throws JsonProcessingException {
        //ClassPathXmlApplicationContext dbcontext = new ClassPathXmlApplicationContext(new String[]{"db/db.xml"});
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"context/config.xml"});
        //context.setParent(dbcontext);
        SavedFileController controller = (SavedFileController) context.getBean("controller");
        controller.start();
    }

}
