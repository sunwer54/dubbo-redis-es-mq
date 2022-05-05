package com.dubbo;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

//当前类是springboot启动类
@SpringBootApplication
//该注解表示开启dubbo的远程服务
@EnableDubbo
public class ProductAddApp {//extends SpringBootServletInitializer
    public static void main(String[] args) {
        //启动springboot项目
        SpringApplication.run(ProductAddApp.class,args);
    }
    /*//目的是打包成war之后，放在tomcat容器中启动时，tomcat服务器能自动找到当前的这个启动类，启动微服务
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(ProductAddApp.class);
    }*/
}
