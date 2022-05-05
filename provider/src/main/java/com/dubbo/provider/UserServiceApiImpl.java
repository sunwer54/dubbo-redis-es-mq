package com.dubbo.provider;

import com.dubbo.api.ProductApi;
import com.dubbo.api.UserServiceApi;
import com.dubbo.mapper.UserMapper;
import com.dubbo.pojo.User;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 服务提供者，注册服务到zookeeper中，必须实现api模块中的UserServiceApi接口
 */

@Service//该注解是dubbo下的Service注解，表示将该类提供的服务注册到dubbo指定的zookeeper注册中心
public class UserServiceApiImpl implements UserServiceApi{
    //自动注入mapper
    @Autowired
    private UserMapper userMapper;
    @Override
    public User loginUser(String userName, String pwd) {
        User user = userMapper.loginUser(userName, pwd);
        return user;
    }
}
