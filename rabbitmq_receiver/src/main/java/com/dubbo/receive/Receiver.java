package com.dubbo.receive;

import com.dubbo.commons.HttpClientUtil;
import com.dubbo.pojo.Message;
import com.dubbo.pojo.Product;
import com.google.gson.Gson;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 消息接收者
 */
@Component
public class Receiver {

    /*
     * 接收消息的方法
     */
    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(value = "elasticSearch"),
                    exchange = @Exchange(value = "topicExchange", type = ExchangeTypes.TOPIC),
                    key = "com.product"
            )
    )
    public void receiveMsgToES(Message msg) {
        System.out.println("队列接收到消息:" + msg.getObject());
        if ("商品上架成功".equals(msg.getMsg())) {
            /*通知es索引库更新消息（将接收到的消息传给es）*/
            //1.将对象转化为json字符串
            Gson gson = new Gson();
            String jsonMsg = gson.toJson(msg.getObject());
            System.out.println(jsonMsg);
        /*2.跨域请求: 使用HttpClientUtil工具类(用来模拟浏览器)发送http请求http://localhost:9094/updateProduct
        把数据通过请求体带到RequestMapping为updateProduct的controller中*/
            HttpClientUtil.doPostJson("http://localhost:9094/updateProduct", jsonMsg);
        }
        if ("商品下架成功".equals(msg.getMsg())) {
            HttpClientUtil.doPostJson("http://localhost:9094/downData",((Product)msg.getObject()).getPid()+"");
        }
    }

    /*
     * 接收消息的方法
     */
    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(value = "redis"),
                    exchange = @Exchange(value = "topicExchange", type = ExchangeTypes.TOPIC),
                    key = "com.product"
            )
    )
    public void receiveMsgToRedis(Message msg) {
        /*2.跨域请求: 使用HttpClientUtil工具类(用来模拟浏览器)发送http请求"http://localhost:9093/showAfterUpdateData"
        把数据通过请求体带到RequestMapping为updateProduct的controller中*/
        HttpClientUtil.doPostJson("http://localhost:9093/showAfterUpdateData", "");
    }
}