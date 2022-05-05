package com.dubbo.productadd.impl;

import com.dubbo.api.ProductApi;
import com.dubbo.pojo.Product;
import com.dubbo.productadd.ProductService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

/**
 * 服务消费者，dubbo订阅zookeeper注册中心中的远程服务
 */
//将当前类交给spring容器管理
@Service
public class ProductServiceImpl implements ProductService {
    // dubbo订阅zookeeper注册中心中的远程服务
    @Reference
    private ProductApi productApi;
    @Override
    public int addProduct(Product product) {
        return productApi.addProduct(product);
    }

    @Override
    public int selectByImaPath(String imagepath) {
        return productApi.selectByImaPath(imagepath);
    }

    @Override
    public int downProduct(int status, int pid) {
        return productApi.downProduct(status, pid);
    }

    @Override
    public int upProduct(int status, int pid) {
        return productApi.upProduct(status, pid);
    }

    @Override
    public Product getDataByPid(int pid) {
        return productApi.getDataByPid(pid);
    }
}
