package com.xiliang.mapper;

import com.xiliang.entity.Goods;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GoodsMapper {
    //货物添加功能
    @Insert("insert into workhouse_managementshu.goods (goods_name,create_time,create_user,update_time,update_user,img) " +
            "values (#{goodsName},#{createTime},#{createUser},#{updateTime},#{updateUser},#{Img})")
    void insert(Goods goods);
}
