package com.elasticsearch.mapper;
import com.elasticsearch.pojo.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 数据访问层，负责直接与数据库连接更数据库进行数据交互
 */
@Repository//将当前类交给spring容器管理
public interface ProductMapper {
    //查询全部
    @Select("select * from product")
    public List<Product> findAll();
}
