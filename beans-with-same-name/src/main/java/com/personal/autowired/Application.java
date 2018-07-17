package com.personal.autowired;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


interface SimpleBean {
    void doSomething();
}

@Component
class ComplexBean {
    @Autowired
    private SimpleBean simpleBean;

    public void doSomething(){
        simpleBean.doSomething();
    }
}

@Configuration
@ComponentScan
public class Application {
    @Bean
    public SimpleBean simpleBean(){
        return () -> System.out.println("I am simple bean");
    }

    @Bean
    public SimpleBean anotherSimpleBean(){
        return () -> System.out.println("I am another simple bean");
    }

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
        context.getBean(ComplexBean.class).doSomething();
    }
}
