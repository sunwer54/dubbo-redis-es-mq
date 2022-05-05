package com.elasticsearch;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication//指定当前类是Springboot启动类
@MapperScan("com.elasticsearch.mapper")//扫描mapper包，整合mybatis注解
public class ElasticSearchApp {
    public static void main(String[] args) {
        //启动springboot项目
        SpringApplication.run(ElasticSearchApp.class,args);
    }
}
