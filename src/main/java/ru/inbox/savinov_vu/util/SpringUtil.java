package ru.inbox.savinov_vu.util;


import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.inbox.savinov_vu.controller.HumanController;

public class SpringUtil {
    private SpringUtil(){}

  public  static void main(String[] args)  {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"config.xml"});
      HumanController controller = (HumanController) context.getBean("controller");
      controller.start();
    }

}
