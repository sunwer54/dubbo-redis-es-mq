package com.elasticsearch.pojo;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;
import java.util.Date;

/**
 * SpringDataElasticsearch的功能：可以以POJO中中心的模型，用于ElasticSearch文档Document交互
 *
 * @Document 用来将当前实体类与ElasticSearch交互
 * indexName：指定索引名称
 * type：指定类型名称（默认为值为""）
 * shards:指定主分片数量（默认值为5）
 * replicas：指定每个主分片对应的备份副分片数量（默认值为1）
 */
@Data
@Document(indexName = "tb_item",type = "tbitem",shards = 2,replicas = 1)
public class TbItem implements Serializable {
  @Id //这个注解表示将TbItem中的主键id作为作为索引中的_id字段
  private long id;
  /*
  @Field:指定存入到ElasticSearch中索引的字段
  name：给字段设置一个指定的名称，不设置则默认与属性名一样
  type：指定该字段的类型，不指定则由es自动映射
  analyzer：指定分词器，不指定则默认是standard
  fielddata：是否开启正向索引，默认是关闭的（false），默认会为文本类型的字段创建倒排索引，
  改成true之后会格外创建一个正向索引，不推荐开启
   */
  @Field(name = "title",type = FieldType.Text,analyzer = "ik_smart",fielddata = false)
  private String title;//商品标题，作为搜索条件
  @Field(type = FieldType.Text,analyzer = "ik_max_word")
  private String sellPoint;//商品卖点，作为搜索条件
  @Field(type = FieldType.Long)
  private long price;
  /*
  index = false:是否创建默认的反向索引或正向索引（默认为true，即默认创建索引。false时表示不创建任何索引）
  Text类型会默认创建反向索引，其他类型会默认创建正向索引，如果没有索引的话，就不能作为搜索条件
   */
  @Field(type = FieldType.Integer,index = false)
  private int num;//库存
  private String barcode;
  private String image;
  private long cid;
  private byte status;
  private Date created;
  private Date updated;

}
