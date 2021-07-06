package com.remmy;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 */
public final class App {
    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:application.xml");
        System.out.println("启动成功");

//        MessageService messageService = applicationContext.getBean(MessageService.class);
//        System.out.println(messageService.getMessage());

        Person person = applicationContext.getBean(Person.class);
        System.out.println(person);

        System.out.println("现在开始关闭容器！");
        applicationContext.close();
//        ((ClassPathXmlApplicationContext)applicationContext).registerShutdownHook();
    }
}
