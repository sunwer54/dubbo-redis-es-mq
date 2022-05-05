package com.dubbo.passport;

import com.dubbo.pojo.User;

public interface UserService {
    public User loginUser(String userName, String pwd);
}
