package ru.inbox.savinov_vu.util;


import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.inbox.savinov_vu.controller.HumanController;

public class SpringUtil {
    private SpringUtil(){}

  public  static void main(String[] args) throws JsonProcessingException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"Spring/config.xml"});
      HumanController controller = (HumanController) context.getBean("controller");
      controller.start();
    }

}
