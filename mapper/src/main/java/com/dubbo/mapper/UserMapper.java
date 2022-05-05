package com.dubbo.mapper;

import com.dubbo.pojo.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
/**
 * 数据访问层，负责直接与数据库连接更数据库进行数据交互
 */
@Repository//将当前类交给spring容器管理
public interface UserMapper {
    //根据userName和password查询
    @Select("select * from loginuser where userName=#{param1} and password=#{param2}")
    public User loginUser(String userName, String pwd);
}
