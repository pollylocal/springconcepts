package com.personal.resourcevsautowiringprimary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


interface SimpleBean {
    void doSomething();
}

@Component
class ComplexBeanAutowired {
    @Autowired
    private SimpleBean simpleBean;

    public void doSomething(){
        simpleBean.doSomething();
    }
}

@Component
class ComplexBeanResource {
    @Resource
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
    @Primary
    public SimpleBean anotherSimpleBean(){
        return () -> System.out.println("I am another simple bean");
    }

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
        context.getBean(ComplexBeanAutowired.class).doSomething();
        context.getBean(ComplexBeanResource.class).doSomething();
    }
}
