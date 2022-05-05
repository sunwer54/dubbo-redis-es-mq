package com.dubbo;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication//当前类是springboot启动类
@MapperScan("com.dubbo.mapper")//扫描mapper包，整合mybatis注解
@EnableDubbo //该注解表示开启dubbo的远程服务
public class ProviderApp {//extends SpringBootServletInitializer
    public static void main(String[] args) {
        //启动springboot项目
        SpringApplication.run(ProviderApp.class,args);
    }

   /* //目的是打包成war之后，放在tomcat容器中启动时，tomcat服务器能自动找到当前的这个启动类，启动微服务
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(ProviderApp.class);
    }*/
}
