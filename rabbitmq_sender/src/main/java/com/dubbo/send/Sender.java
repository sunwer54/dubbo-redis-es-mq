package com.dubbo.send;

import com.dubbo.pojo.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 消息发送者
 */
@Component
public class Sender {
    @Autowired//注入操作Rabbitmq的类
    private RabbitTemplate rabbitTemplate;

    /*
    发送消息的方法
     */
    public void sendMsg(Message msg){
        rabbitTemplate.convertAndSend("topicExchange","com.product",msg);
        System.out.println("发送消息到队列成功");
    }
}
