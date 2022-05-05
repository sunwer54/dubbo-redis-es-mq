package com.dubbo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//当前类是springboot启动类
@SpringBootApplication
public class ESApp {
    public static void main(String[] args) {
        //启动springboot项目
        SpringApplication.run(ESApp.class,args);
    }
}
