package com.example.app1;

import com.example.app1.config.ConfigApp2;
import com.example.app1.config.ConfigApp3;
import com.example.app2.service.UserService;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class App1Application {

    public static void main(String[] args) {
        //SpringApplication.run(App1Application.class, args);
        parentContext();
    }

    // 兄弟容器
    private static void OneContext() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.register(ConfigApp2.class, ConfigApp3.class);

        applicationContext.setAllowBeanDefinitionOverriding(false);

        applicationContext.refresh();

        System.out.println(applicationContext.getBean(com.example.app2.service.UserService.class));
        System.out.println(applicationContext.getBean(com.example.app3.service.UserService.class));
    }

    // 兄弟容器
    private static void brotherContext() {
        AnnotationConfigApplicationContext parentApplicationContext = new AnnotationConfigApplicationContext();

        AnnotationConfigApplicationContext applicationContext1 = new AnnotationConfigApplicationContext(ConfigApp2.class);
        AnnotationConfigApplicationContext applicationContext2 = new AnnotationConfigApplicationContext(ConfigApp3.class);


        parentApplicationContext.setAllowBeanDefinitionOverriding(false);

        applicationContext1.setParent(parentApplicationContext);
        applicationContext2.setParent(parentApplicationContext);

        parentApplicationContext.refresh();

        System.out.println(applicationContext1.getBean(com.example.app2.service.UserService.class));
        System.out.println(applicationContext2.getBean(com.example.app3.service.UserService.class));
    }

    // 父子容器
    private static void parentContext(){
        AnnotationConfigApplicationContext parentApplicationContext = new AnnotationConfigApplicationContext();
        parentApplicationContext.register(ConfigApp2.class);

        parentApplicationContext.refresh();

        AnnotationConfigApplicationContext childApplicationContext = new AnnotationConfigApplicationContext();
        childApplicationContext.register(ConfigApp3.class);

        childApplicationContext.setParent(parentApplicationContext);

        // 刷新 child
        childApplicationContext.refresh();


        System.out.println(parentApplicationContext.getBean(com.example.app2.service.UserService.class));
        //System.out.println(parentApplicationContext.getBean(com.example.app3.service.UserService.class));

        System.out.println(childApplicationContext.getBean(com.example.app2.service.UserService.class));
        System.out.println(childApplicationContext.getBean(com.example.app3.service.UserService.class));

        // 可以给非 ListableBeanFactory 容器设置父容器，父容器不可以访问子容器的 Bean，但是子容器可以访问父容器的 Bean。
    }

}
