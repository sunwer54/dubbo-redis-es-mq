package com.dubbo.es.controller;

import com.dubbo.pojo.Product;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

//将当前类实现Controller接口并交给spring容器管理
@Controller
public class ESController {

    //注入可以操作es索引库的类ElasticsearchRestTemplate
    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @RequestMapping("searchProduct")
    public String searchData(String searchName, Model model){
        /**
         * 按多字段条件搜索（title字段和sellpoint字段）
         * should，满足任意一个条件（相当于或）
         */
        //1.创建封装多条件的封装器对象
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        List<QueryBuilder> should = boolQueryBuilder.should();
        should.add(QueryBuilders.matchQuery("title",searchName));
        should.add(QueryBuilders.matchQuery("sellpoint",searchName));
        //执行查询
        SearchQuery searchQueryShould = new NativeSearchQuery(boolQueryBuilder);
        List<Product> products = elasticsearchRestTemplate.queryForList(searchQueryShould, Product.class);//执行搜索
        model.addAttribute("products",products);
        return "product_list";
    }

    /*
    接收新增product中商品新增成功后发送过来的消息
     */
    @RequestMapping(value = "/updateProduct",produces = "text/html;charset=utf-8")
    @ResponseBody//方法返回值是响应的到页面的数据而不是页面跳转的uri
    //@RequestBody String jsonMsg表示从请求体中获取参数，接收receiver发送过来的的信息
    //@RequestBody Product product从请求体中获取参数用对象封装并接收
    public String insertData(@RequestBody Product product){
        System.out.println(product);
        /*//将json字符串转化为对象
        Gson gson = new Gson();
        Product product = gson.fromJson(jsonMsg, Product.class);
        //将接收过来的消息用IndexQuery对象进行封装*/
        IndexQuery indexQuery = new IndexQueryBuilder().withObject(product).build();
        //执行数据插入索引库
        elasticsearchRestTemplate.index(indexQuery);
        return "索引库数据更新成功";
    }

    /*
    接收下架product中商品下架成功后发送过来的消息
     */
    @RequestMapping(value = "/downData",produces = "text/html;charset=utf-8")
    @ResponseBody//方法返回值是响应的到页面的数据而不是页面跳转的uri
    public String downData(@RequestBody String pid){
        System.out.println(pid);
        //根据指定id删除
        String delete = elasticsearchRestTemplate.delete(Product.class, pid);
        System.out.println(delete);//删除成功，返回该id
        return "索引库数据更新成功";
    }

    /*
   接收删除product中商品删除成功后发送过来的消息
    */
    @RequestMapping(value = "/upData",produces = "text/html;charset=utf-8")
    @ResponseBody//方法返回值是响应的到页面的数据而不是页面跳转的uri
    public String upData(@RequestBody String pid){
        System.out.println(pid);
        //根据指定id删除
        String delete = elasticsearchRestTemplate.delete(Product.class, pid);
        System.out.println(delete);//删除成功，返回该id
        return "索引库数据更新成功";
    }
}
