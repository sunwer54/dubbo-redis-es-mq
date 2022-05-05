package com.elasticsearch.test;

import com.elasticsearch.ElasticSearchApp;
import com.elasticsearch.mapper.TB_itemMapper;
import com.elasticsearch.pojo.TbItem;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.test.context.junit4.SpringRunner;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


//这个注解是基于springboot测试，需要依赖springboot启动类
@SpringBootTest(classes = {ElasticSearchApp.class})
@RunWith(SpringRunner.class)
public class ElasticSearchTest {

    @Autowired//注入Java中用来操作es的ElasticsearchRestTemplate类，子类中封装了用来操作es的方法
    private ElasticsearchRestTemplate esrt;
    /*
    根创建索引，设置映射
     */
    @Test
    public void createIndex(){
        //据pojo实体类的配置在es中创建索引（相当于创建数据库中创建表）
        //在pojo中指定了TbItem实体类用于ElasticSearch文档Document交互，所以可以直接传入TbItem的Class对象
        boolean isIndex = esrt.createIndex(TbItem.class);
        System.out.println("创建索引结果："+isIndex);

        //据pojo实体类的配置在es中创建创建mapping（相当于数据库中的表结构）
        boolean isMapping = esrt.putMapping(TbItem.class);
        System.out.println("创建mapping："+isMapping);
    }

    /**
     * 往es中批量插入数据,先从数据库中查询到全部数据，在批量插入到es中
     */
    @Autowired//自动注入
    private TB_itemMapper tb_itemMapper;
    @Test
    public void insertDatas(){
        //1.从数据库中查询到数据
        List<TbItem> all = tb_itemMapper.findAll();

        //2.把查询出来的数据放入es索引库中
        List<IndexQuery> indexQueries = new ArrayList<>();
        for(TbItem tbItem:all){
            //2.1把数据转化成 IndexQuery 对象
            IndexQuery indexQuery = new IndexQueryBuilder().withObject(tbItem).build();
            indexQueries.add(indexQuery);
        }
        //2.2指定批量插入数据到es中
        esrt.bulkIndex(indexQueries);
    }

    /**
     * 往es中插入单条数据
     */
    @Test
    public void insertData(){
        TbItem tbItem = new TbItem();
        //如果索引中已经有对应的这个id，那么会该方法会执行覆盖原来的数据，没有该id则插入新数据
        tbItem.setId(222333);
        tbItem.setTitle("好玩魔方");
        tbItem.setPrice(10);
        tbItem.setNum(1000);
        tbItem.setSellPoint("益智游戏，锻炼脑子和反应能力");

        //将数据转化为IndexQuery对象
        IndexQuery indexQuery = new IndexQueryBuilder().withObject(tbItem).build();
        //执行插入数据到es中
        String index = esrt.index(indexQuery);//返回id值
        System.out.println(index);//222333
    }
    /**
     * 删除单条数据
     */
    @Test
    public void deleteData(){
        //根据指定id删除
        String delete = esrt.delete(TbItem.class, "222333");
        System.out.println(delete);//删除成功，返回该id
    }
    /**
     * 修改数据
     */
    @Test
    public void updateData() throws IOException {

        //1.创建修改请求对象封装要修改的成数据
        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.doc(XContentFactory.jsonBuilder()
                .startObject()
                .field("title","玩转魔方")
                .endObject());
        //2.绑定要修改的那条数据，指定那个数据中的某个条件
        UpdateQuery updateQuery = new UpdateQueryBuilder()
                .withUpdateRequest(updateRequest)
                .withClass(TbItem.class)
                .withId("222333").build();
        //执行修改
        UpdateResponse update = esrt.update(updateQuery);
        System.out.println(update.getResult());//修改成功，返回UPDATE
    }
    /**
     * 全搜索
     * 从es中搜索数据，es会默认分页，每页10条数据
     */
    @Test
    public void matchAll(){
        //搜索所有
        SearchQuery searchQuery = new NativeSearchQuery(QueryBuilders.matchAllQuery());//搜索条件
        List<TbItem> tbItems = esrt.queryForList(searchQuery, TbItem.class);//执行搜索
        //查看搜索结果
        for (TbItem tbItem:tbItems){
            System.out.println(tbItem);
        }
    }
    /**
     * 匹配搜索（模糊搜索）
     * 条件，ik分词器
     */
    @Test
    public void ikMatchData(){
        //第一个参数是要搜索的字段，第二个参数是搜索条件（会先进行分词，再进行匹配）
        SearchQuery searchQuery = new NativeSearchQuery(QueryBuilders.matchQuery("title","魔方"));//搜索条件
        List<TbItem> tbItems = esrt.queryForList(searchQuery, TbItem.class);//执行搜索
        //查看搜索结果
        for (TbItem tbItem:tbItems){
            System.out.println(tbItem);
        }
    }
    /**
     * 短语搜索，完全匹配搜索
     */
    @Test
    public void matchPhraseData(){
        //第一个参数是要搜索的字段，第二个参数是搜索条件
        SearchQuery searchQuery = new NativeSearchQuery(QueryBuilders.matchPhraseQuery("title","翻盖"));
        List<TbItem> tbItems = esrt.queryForList(searchQuery, TbItem.class);//执行搜索
        //查看搜索结果
        for (TbItem tbItem:tbItems){
            System.out.println(tbItem);
        }
    }
    /**
     * 同一个字段中多数据条件搜索，terms
     */
    @Test
    public void termData(){
        //第一个参数是要搜索的字段，第二个参数是搜索条件
        SearchQuery searchQuery = new NativeSearchQuery(QueryBuilders.termsQuery("title","老人手机","红色"));
        List<TbItem> tbItems = esrt.queryForList(searchQuery, TbItem.class);//执行搜索
        //查看搜索结果
        for (TbItem tbItem:tbItems){
            System.out.println(tbItem);
        }
    }
    /**
     * 范围搜索，range
     */
    @Test
    public void rangeData(){
        //搜索条件
        SearchQuery searchQuery = new NativeSearchQuery(QueryBuilders.rangeQuery("price").gte(1000).lte(4000));
        //还可以设置分页
        searchQuery.setPageable(PageRequest.of(1,5));//第一个参数是页面（从0开始），第二个参数是显示数据条数
        //还可以设置排序
        searchQuery.addSort(Sort.by(Sort.Direction.DESC,"price"));//按价格降序排序
        List<TbItem> tbItems = esrt.queryForList(searchQuery, TbItem.class);//执行搜索
        //查看搜索结果
        for (TbItem tbItem:tbItems){
            System.out.println(tbItem);
        }
    }
    /**
     * 多字段中多条件搜索
     * must（相当于&&，且）
     * should（相当于||，或）
     * must not （相当于！= ，非）
     */
    @Test
    public void multiData(){
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        //must,同时满足各条件
        List<QueryBuilder> must = boolQueryBuilder.must();
        must.add(QueryBuilders.matchQuery("title","红色"));
        must.add(QueryBuilders.rangeQuery("price").gte(10000).lte(40000));
        //执行查询
        SearchQuery searchQueryMust = new NativeSearchQuery(boolQueryBuilder);
        //还可以设置分页
        searchQueryMust.setPageable(PageRequest.of(1,5));//第一个参数是页面（从0开始），第二个参数是显示数据条数
        //还可以设置排序
        searchQueryMust.addSort(Sort.by(Sort.Direction.DESC,"price"));//按价格降序排序
        List<TbItem> tbItemsMust = esrt.queryForList(searchQueryMust, TbItem.class);//执行搜索
        //查看搜索结果
        for (TbItem tbItem:tbItemsMust){
            System.out.println(tbItem);
        }

        //should，满足任意一个条件
        List<QueryBuilder> should = boolQueryBuilder.should();
        should.add(QueryBuilders.matchQuery("title","红色"));
        should.add(QueryBuilders.rangeQuery("price").gte(10000).lte(40000));
        //执行查询
        SearchQuery searchQueryShould = new NativeSearchQuery(boolQueryBuilder);
        //还可以设置分页
        searchQueryShould.setPageable(PageRequest.of(1,5));//第一个参数是页面（从0开始），第二个参数是显示数据条数
        //还可以设置排序
        searchQueryShould.addSort(Sort.by(Sort.Direction.DESC,"price"));//按价格降序排序
        List<TbItem> tbItemsShould = esrt.queryForList(searchQueryShould, TbItem.class);//执行搜索
        //查看搜索结果
        for (TbItem tbItem:tbItemsShould){
            System.out.println(tbItem);
        }

        //must not，不能满足其中的任何条件
        //should，满足任意一个条件
        List<QueryBuilder> mustNot = boolQueryBuilder.mustNot();
        mustNot.add(QueryBuilders.matchQuery("title","红色"));
        mustNot.add(QueryBuilders.rangeQuery("price").gte(10000).lte(40000));
        //执行查询
        SearchQuery searchQueryMustNot = new NativeSearchQuery(boolQueryBuilder);
        //还可以设置分页
        searchQueryMustNot.setPageable(PageRequest.of(1,5));//第一个参数是页面（从0开始），第二个参数是显示数据条数
        //还可以设置排序
        searchQueryMustNot.addSort(Sort.by(Sort.Direction.DESC,"price"));//按价格降序排序
        List<TbItem> tbItemsMustNot = esrt.queryForList(searchQueryMustNot, TbItem.class);//执行搜索
        //查看搜索结果
        for (TbItem tbItem:tbItemsMustNot){
            System.out.println(tbItem);
        }
    }
}
