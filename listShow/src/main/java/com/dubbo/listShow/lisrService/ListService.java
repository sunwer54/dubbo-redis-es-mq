package com.dubbo.listShow.lisrService;

import com.dubbo.pojo.Product;
import java.util.List;

public interface ListService {
    //查询全部
    public List<Product> findAll();

    //查询全部
    public List<Product> findAllAfterUpdateData();

    //根据pid修改group2
    public int UpdateData(String group2,int pid);
}
