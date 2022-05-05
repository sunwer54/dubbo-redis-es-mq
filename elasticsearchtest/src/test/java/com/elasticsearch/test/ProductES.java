package com.elasticsearch.test;

import com.elasticsearch.ElasticSearchApp;
import com.elasticsearch.mapper.ProductMapper;
import com.elasticsearch.pojo.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

//这个注解是基于springboot测试，需要依赖springboot启动类
@SpringBootTest(classes = {ElasticSearchApp.class})
@RunWith(SpringRunner.class)
public class ProductES {
    @Autowired//注入Java中用来操作es的ElasticsearchRestTemplate类，子类中封装了用来操作es的方法
    private ElasticsearchRestTemplate esrt;
    @Test
    public void createIndex(){
        //据pojo实体类的配置在es中创建索引（相当于创建数据库中创建表）
        //在pojo中指定了Product实体类用于ElasticSearch文档Document交互，所以可以直接传入Product的Class对象
        boolean isIndex = esrt.createIndex(Product.class);
        System.out.println("创建索引结果："+isIndex);

        //据pojo实体类的配置在es中创建创建mapping（相当于数据库中的表结构）
        boolean isMapping = esrt.putMapping(Product.class);
        System.out.println("创建mapping："+isMapping);
    }
    @Autowired
    private ProductMapper productMapper;
    @Test
    public void insertDatas(){
        List<Product> products = productMapper.findAll();
        //创建IndexQuery对象，封装要插入es索引库中的数据
        List<IndexQuery> indexQueries = new ArrayList<>();
        for (Product product:products){
            System.out.println(product);
            IndexQuery indexQuery = new IndexQueryBuilder().withObject(product).build();
            indexQueries.add(indexQuery);
        }
        //执行数据插入
        esrt.bulkIndex(indexQueries);
    }
}
