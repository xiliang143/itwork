package com.xiliang.mapper;

import com.xiliang.entity.Goods;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface GoodsMapper {
    //货物添加功能
    @Insert("insert into workhouse_managementshu.goods (goods_name,create_time,create_user,update_time,update_user,img) " +
            "values (#{goodsName},#{createTime},#{createUser},#{updateTime},#{updateUser},#{Img})")
    void insert(Goods goods);
    @Select("select * from workhouse_managementshu.goods where id=#{id}")
    Goods getById(long id);
}
