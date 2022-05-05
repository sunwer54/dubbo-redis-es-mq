package com.dubbo.provider;

import com.dubbo.api.ProductApi;
import com.dubbo.mapper.ProductMapper;
import com.dubbo.pojo.Product;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
 * 服务提供者，注册服务到zookeeper中，必须实现api模块中的ProductApi接口
 */

@Service//该注解是dubbo下的Service注解，表示将该类提供的服务注册到dubbo指定的zookeeper注册中心
public class ProductApiImpl implements ProductApi {

    //注入mapper
    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Product> findAll() {
        return productMapper.findAll();
    }

    @Override
    public List<Product> findAllAfterUpdateData() {
        return productMapper.findAllAfterUpdateData();
    }

    @Override
    public int UpdateData(String group2, int pid) {
        return productMapper.UpdateData(group2, pid);
    }

    @Override
    public int addProduct(Product product) {
        return productMapper.addProduct(product);
    }

    @Override
    public int selectByImaPath(String imagepath) {
        return productMapper.selectByImaPath(imagepath);
    }

    @Override
    public int downProduct(int status, int pid) {
        return productMapper.downProduct(status, pid);
    }

    @Override
    public int upProduct(int status, int pid) {
        return productMapper.downProduct(status, pid);
    }

    @Override
    public Product getDataByPid(int pid) {
        return productMapper.getDataByPid(pid);
    }
}
