package com.rabbitmq.test;

import com.rabbitmq.RabbitMQApp;
import com.rabbitmq.rabbitMQSend.RabbitMQSend;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//这个注解是基于springboot测试，需要依赖springboot启动类
@SpringBootTest(classes = RabbitMQApp.class)
@RunWith(SpringRunner.class)
public class RabbitMQTest {
    @Autowired//注入消息发送者对象
    private RabbitMQSend rabbitMQSend;
    @Test
    public void testRabbitMQ() {
        int t = 0;
        while (true) {
            rabbitMQSend.sendMsg("您的订单已出库！！！" + t++);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
