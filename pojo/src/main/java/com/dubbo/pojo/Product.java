package com.dubbo.pojo;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;
/**
 * SpringDataElasticsearch的功能：可以以POJO中中心的模型，用于ElasticSearch文档Document交互
 *
 * @Document 用来将当前实体类与ElasticSearch交互
 * indexName：指定索引名称
 * type：指定类型名称（默认为值为""）
 * shards:指定主分片数量（默认值为5）
 * replicas：指定每个主分片对应的备份副分片数量（默认值为1）
 */
@Document(indexName = "product_list",type = "product",shards = 2,replicas = 1)
public class Product implements Serializable {
  @Id //这个注解表示将TbItem中的主键id作为作为索引中的_id字段
  private long pid;
  /*
  @Field:指定存入到ElasticSearch中索引的字段
  name：给字段设置一个指定的名称，不设置则默认与属性名一样
  type：指定该字段的类型，不指定则由es自动映射
  analyzer：指定分词器，不指定则默认是standard
  fielddata：是否开启正向索引，默认是关闭的（false），默认会为文本类型的字段创建倒排索引，
  改成true之后会格外创建一个正向索引，不推荐开启
   */
  @Field(type = FieldType.Text,analyzer = "ik_max_word")
  private String title;
  private String group1;
  private String group2;
  private String group3;
  /*
  index = false:是否创建默认的反向索引或正向索引（默认为true，即默认创建索引。false时表示不创建任何索引）
  Text类型会默认创建反向索引，其他类型会默认创建正向索引，如果没有索引的话，就不能作为搜索条件
   */
  @Field(type = FieldType.Double)
  private double price;
  @Field(type = FieldType.Text,analyzer = "ik_max_word")
  private String sellpoint;
  private String imagepath;
  private String detail;
  private long status;


  public long getPid() {
    return pid;
  }

  public void setPid(long pid) {
    this.pid = pid;
  }


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }


  public String getGroup1() {
    return group1;
  }

  public void setGroup1(String group1) {
    this.group1 = group1;
  }


  public String getGroup2() {
    return group2;
  }

  public void setGroup2(String group2) {
    this.group2 = group2;
  }


  public String getGroup3() {
    return group3;
  }

  public void setGroup3(String group3) {
    this.group3 = group3;
  }


  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }


  public String getSellpoint() {
    return sellpoint;
  }

  public void setSellpoint(String sellpoint) {
    this.sellpoint = sellpoint;
  }


  public String getImagepath() {
    return imagepath;
  }

  public void setImagepath(String imagepath) {
    this.imagepath = imagepath;
  }


  public String getDetail() {
    return detail;
  }

  public void setDetail(String detail) {
    this.detail = detail;
  }


  public long getStatus() {
    return status;
  }

  public void setStatus(long status) {
    this.status = status;
  }

  @Override
  public String toString() {
    return "Product{" +
            "pid=" + pid +
            ", title='" + title + '\'' +
            ", group1='" + group1 + '\'' +
            ", group2='" + group2 + '\'' +
            ", group3='" + group3 + '\'' +
            ", price=" + price +
            ", sellpoint='" + sellpoint + '\'' +
            ", imagepath='" + imagepath + '\'' +
            ", detail='" + detail + '\'' +
            ", status=" + status +
            '}';
  }
}
