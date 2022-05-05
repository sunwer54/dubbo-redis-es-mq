package com.rabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication//当前类是springboot启动类
public class RabbitMQApp {
    public static void main(String[] args) {
        //启动springboot项目
        SpringApplication.run(RabbitMQApp.class,args);
    }
}
