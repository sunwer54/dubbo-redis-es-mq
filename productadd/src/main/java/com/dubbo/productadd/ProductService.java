package com.dubbo.productadd;

import com.dubbo.pojo.Product;

public interface ProductService {
    //新增商品
    public int addProduct(Product product);

    //根据图片路径查询上架商品的pid（用来发送消息用）
    public int selectByImaPath(String imagepath);

    //下架商品
    public int downProduct(int status,int pid);

    //上架商品
    public int upProduct(int status,int pid);

    public Product getDataByPid(int pid);
}
