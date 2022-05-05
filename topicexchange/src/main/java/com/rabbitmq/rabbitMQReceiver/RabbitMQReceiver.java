package com.rabbitmq.rabbitMQReceiver;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 消息接收者（consumer）：消息接收者，接收来发送者发送的消息，
 * 采用topicExchange(主题匹配)交换机，可以将符合路由键匹配规则的消息发送到队列中
 * 比如：消息发送方发送了三条消息，消息一（路由键：com.rabbitmq.one）
 * 消息二（路由键：com.rabbit.two）,消息三(路由键：com.rabbitmq.three)
 * 注意：这三条消息中的路由键都包含com.rabbitmq
 * 所以凡是含有com.rabbitmq.*路由键的队列都可以接收到上面的三条消息
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
    //队列一：topicQueue收到了来自com.rabbitmq.one，com.rabbitmq.two，com.rabbitmq.three三条消息
    @RabbitListener(
        //bindings:绑定队列
        bindings = @QueueBinding(//@QueueBinding:绑定队列的详细属性
            // value = "thisDirect"配置队列名称,autoDelete = "false":是否是可删除的临时队列(是否持久化,true用完之后自动从队列中删除)
            value = @Queue(value = "topicSend",autoDelete = "false"),
            //value = "directExchange"配置交换机名称，type = ExchangeTypes.DIRECT配置交换机类型，默认就是这个
            exchange = @Exchange(value = "topicExchange",type = ExchangeTypes.TOPIC),
            //配置路由键
            key = "com.rabbitmq.*"
           //收到了来自com.rabbitmq.one，com.rabbitmq.two，com.rabbitmq.three三条消息
        )
    )
    public void receiveMsgFromSend(String msg){
        System.out.println("fanoutSender队列消息来自他们："+msg);
    }


    //队列二：topicSender收到了来自com.rabbitmq.one，com.rabbitmq.two，com.rabbitmq.three三条消息
    @RabbitListener(
            //bindings:绑定队列
            bindings = @QueueBinding(//@QueueBinding:绑定队列的详细属性
                    // value = "thisDirect"配置队列名称,autoDelete = "false":是否是可删除的临时队列(是否持久化,true用完之后自动从队列中删除)
                    value = @Queue(value = "topicSender",autoDelete = "false"),
                    //value = "directExchange"配置交换机名称，type = ExchangeTypes.DIRECT配置交换机类型，默认就是这个
                    exchange = @Exchange(value = "topicExchange",type = ExchangeTypes.TOPIC),
                    //配置路由键
                    key = "com.rabbitmq.*"
             //收到了来自com.rabbitmq.one，com.rabbitmq.two，com.rabbitmq.three三条消息
            )
    )
    public void receiveMsgFromSender(String msg){
        System.out.println("fanoutSend消息来自"+msg);
    }
}
