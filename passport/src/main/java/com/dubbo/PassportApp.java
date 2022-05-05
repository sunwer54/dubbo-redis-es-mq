package com.dubbo;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.session.FlushMode;
import org.springframework.session.SaveMode;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * 在启动类上,加上注解EnableRedisHttpSession可以对redis的属性配置。不过所有属性都有默认值，也可以省略该注解不配置。
 * a)maxInactiveIntervalInSeconds：单位秒，默认 1800 秒，表示 Session 存活时间。
 * b)redisNamespace：存储在 redis 中 key 的前缀。默认 spring:session
 * c)flushMode:刷新 redis 数据的模式。默认 ON_SAVE，表示在保存时才刷新。
 * 另一个取值FlushMode.IMMEDIATE，表示不刷新。
 * d)cleanupCron:清理过期session的频率，保持默认无需配置，默认 "0 * * * * *"（每秒一次）
 * e)saveMode:保存模式。默认值 SaveMode.ON_SET_ATTRIBUTE，表示设值时保存
 * SaveMode.ALWAYS：实时保存。SaveMode.ON_GET_ATTRIBUTE：获取值时才进行保存到 redis
 */
@SpringBootApplication//当前类是springboot启动类
@EnableDubbo //该注解表示开启dubbo的远程服务
/*@EnableRedisHttpSession(redisNamespace = "userInfo",maxInactiveIntervalInSeconds = 1800,
flushMode = FlushMode.ON_SAVE,saveMode = SaveMode.ON_SET_ATTRIBUTE,cleanupCron = "0 * * * * *")*/
public class PassportApp {//extends SpringBootServletInitializer
    public static void main(String[] args) {
        //启动Springboot项目
        SpringApplication.run(PassportApp.class,args);
    }
    /*//目的是打包成war之后，放在tomcat容器中启动时，tomcat服务器能自动找到当前的这个启动类，启动微服务
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(PassportApp.class);
    }*/
}
