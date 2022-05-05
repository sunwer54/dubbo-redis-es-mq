package com.dubbo.api;

import com.dubbo.pojo.Product;
import java.util.List;

/**
 * 定义服务提供者(provider)和服务服务消费者(consumer)共同的接口
 */
public interface ProductApi {
    //查询全部商品（用来list.jsp用）
    public List<Product> findAll();

    //查询全部商品（用来测试redis缓存更新用）
    public List<Product> findAllAfterUpdateData();

    //更新某商品的group2（用来测试清除redis缓存用）
    public int UpdateData(String group2,int pid);

    //商品新增
    public int addProduct(Product product);

    //根据图片路径查询上架商品的pid（用来发送消息用）
    public int selectByImaPath(String imagepath);

    //下架商品
    public int downProduct(int status,int pid);

    //上架商品
    public int upProduct(int status,int pid);

    public Product getDataByPid(int pid);
}
