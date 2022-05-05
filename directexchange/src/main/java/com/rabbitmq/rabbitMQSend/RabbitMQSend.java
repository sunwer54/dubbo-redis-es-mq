package com.rabbitmq.rabbitMQSend;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 消息发送者（publisher）：向消息队列发送消息
 */
@Component//将当前类交给spring容器管理
public class RabbitMQSend {
    //注入操作可以操作rabbitmq的RabbitRabbitTemplate类,该类里封装了操作rabbitmq的方法
    @Autowired
    private RabbitTemplate rabbitTemplate;
    //发送消息
    public void sendMsg(String msg){
        //第一个参数：给交换机设置名称
        //第二个参数：设置路由键名称
        //第三个参数：发送到交换机的消息
        rabbitTemplate.convertAndSend("directExchange","com.rabbit.send","send："+msg);

        rabbitTemplate.convertAndSend("directExchange","com.rabbit.sender","sender："+msg);
    }
}
