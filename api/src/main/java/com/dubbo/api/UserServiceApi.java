package com.dubbo.api;

import com.dubbo.pojo.User;

/**
 * 定义服务提供者(provider)和服务服务消费者(consumer)共同的接口
 */
public interface UserServiceApi {
        public User loginUser(String userName, String pwd);
}
