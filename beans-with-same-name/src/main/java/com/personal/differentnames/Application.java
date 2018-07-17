package com.personal.differentnames;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;


interface SimpleBean {
    void doSomething();
}

@Configuration
class ConfigurationA {
    @Bean
    public SimpleBean simpleBeanA() {
        return () -> System.out.println("I am from configurationA");
    }
}

@Configuration
class ConfigurationB {
    @Bean
    public SimpleBean simpleBeanB() {
        return () -> System.out.println("I am from configurationB");
    }
}

@Configuration
@ComponentScan
public class Application {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
        context.getBean(SimpleBean.class).doSomething();
    }
}
