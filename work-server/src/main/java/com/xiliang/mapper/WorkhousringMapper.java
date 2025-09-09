package com.xiliang.mapper;

import com.xiliang.entity.Goods;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WorkhousringMapper {

    //向仓库中添加货物
    void insert(Goods goods);
}
