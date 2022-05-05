package com.rabbitmq.rabbitMQSend;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 消息发送者（publisher）：向消息队列发送消息
 * 采用topic(主题匹配)交换机，可以将符合路由键匹配规则的消息发送到队列中
 * 比如：消息发送方发送了三条消息，消息一（路由键：com.rabbitmq.one）
 * 消息二（路由键：com.rabbit.two）,消息三(路由键：com.rabbitmq.three)
 * 注意：这三条消息中的路由键都包含com.rabbitmq
 * 所以凡是含有com.rabbitmq.*路由键的队列都可以接收到上面的三条消息
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
        rabbitTemplate.convertAndSend("topicExchange","com.rabbitmq.one","///："+msg);

        rabbitTemplate.convertAndSend("topicExchange","com.rabbitmq.two","***："+msg);

        rabbitTemplate.convertAndSend("topicExchange","com.rabbitmq.three","===："+msg);

        //rabbitTemplate.convertAndSend("fanoutExchange",null,"////："+msg);
    }
}
