package com.dubbo.mapper;

import com.dubbo.pojo.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
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

    //查询全部
    @Select("select * from product")
    public List<Product> findAllAfterUpdateData();

    //根据pid修改group2
    @Update("update product set group2 = #{param1} where pid = #{param2}")
    public int UpdateData(String group2, int pid);

    //新增商品
    @Insert("insert into product values(null,#{title},#{group1},#{group2},#{group3},#{price},#{sellpoint},#{imagepath},#{detail},#{status})")
    public int addProduct(Product product);

    //根据图片路径查询上架商品的pid
    @Select("select pid from product where imagepath=#{param1}")
    public int selectByImaPath(String imagepath);

    //下架商品
    @Update("update product set status=#{param1} where pid=#{param2}")
    public int downProduct(int status,int pid);

    //上架商品
    @Update("update product set status=#{param1} where pid=#{param2}")
    public int upProduct(int status,int pid);

    @Select("select * from product where pid=#{param1}")
    public Product getDataByPid(int pid);
}
