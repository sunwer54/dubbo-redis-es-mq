package com.elasticsearch.mapper;

import com.elasticsearch.pojo.TbItem;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository//将当前的mapper类交给spring容器管理
public interface TB_itemMapper {
    //查询全部
    @Select("select * from tb_item")
    public List<TbItem> findAll();
}
