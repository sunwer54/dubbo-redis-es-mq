package com.dubbo.passport.impl;

import com.dubbo.api.UserServiceApi;
import com.dubbo.passport.UserService;
import com.dubbo.pojo.User;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

@Service//将当前类交给Spring容器管理
public class UserServiceImpl implements UserService {
    @Reference//该注解用于订阅zookeeper中的服务
    private UserServiceApi userServiceApi;
    @Override
    public User loginUser(String userName, String pwd) {
        User user = userServiceApi.loginUser(userName, pwd);
        return user;
    }
}
