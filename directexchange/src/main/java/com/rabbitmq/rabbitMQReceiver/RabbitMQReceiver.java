package com.rabbitmq.rabbitMQReceiver;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 消息接收者（consumer）：消息接收者，接收来发送者发送的消息
 */
@Component//将当前类交给spring容器管理
public class RabbitMQReceiver {
    /*
    @RabbitListener-->表示配置消息队列监听：
        bindings = @QueueBinding-->绑定队列的详细属性:
            value = @Queue-->指定队列：
                value = "thisDirect"配置队列名称；
                autoDelete = "false":是否是可删除的临时队列(是否持久化,true用完之后自动从队列中删除)；
            exchange = @Exchange-->指定交换机：
                value = "directExchange"配置交换机名称；
                type = ExchangeTypes.DIRECT配置交换机类型，默认就是（DIRECT）
            key = "com.rabbit.send"-->配置路由键
    */
    @RabbitListener(
        //bindings:绑定队列
        bindings = @QueueBinding(//@QueueBinding:绑定队列的详细属性
            // value = "thisDirect"配置队列名称,autoDelete = "false":是否是可删除的临时队列(是否持久化,true用完之后自动从队列中删除)
            value = @Queue(value = "thisDirect",autoDelete = "false"),
            //value = "directExchange"配置交换机名称，type = ExchangeTypes.DIRECT配置交换机类型，默认就是这个
            exchange = @Exchange(value = "directExchange",type = ExchangeTypes.DIRECT),
            //配置路由键
            key = "com.rabbit.send"
        )
    )
    public void receiveMsgFromSend(String msg){
        System.out.println("消息来自"+msg);
    }

    @RabbitListener(
            //bindings:绑定队列
            bindings = @QueueBinding(//@QueueBinding:绑定队列的详细属性
                    // value = "thisDirect"配置队列名称,autoDelete = "false":是否是可删除的临时队列(是否持久化,true用完之后自动从队列中删除)
                    value = @Queue(value = "thisDirect",autoDelete = "false"),
                    //value = "directExchange"配置交换机名称，type = ExchangeTypes.DIRECT配置交换机类型，默认就是这个
                    exchange = @Exchange(value = "directExchange",type = ExchangeTypes.DIRECT),
                    //配置路由键
                    key = "com.rabbit.sender"
            )
    )
    public void receiveMsgFromSender(String msg){
        System.out.println("消息来自"+msg);
    }
}
