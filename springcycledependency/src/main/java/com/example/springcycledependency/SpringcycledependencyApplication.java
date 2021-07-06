package com.example.springcycledependency;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

//@SpringBootApplication
public class SpringcycledependencyApplication {

    public static void main(String[] args) {
//        SpringApplication.run(SpringcycledependencyApplication.class, args);
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
//        applicationContext.register(ServiceA.class);
//        applicationContext.register(ServiceB.class);
//        applicationContext.register(A.class);
//        applicationContext.register(B.class);
//        applicationContext.refresh();

        IUserService userService = applicationContext.getBean(IUserService.class);
        userService.add("yy");
        applicationContext.close();
    }

}
