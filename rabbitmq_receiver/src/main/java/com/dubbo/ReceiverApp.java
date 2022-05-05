package com.dubbo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 消息接收者
 */
@SpringBootApplication//当前类是springboot启动类
public class ReceiverApp {
    public static void main(String[] args) {
        //启动springboot项目
        SpringApplication.run(ReceiverApp.class,args);
    }
}
