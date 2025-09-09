package com.xiliang.mapper;

import com.xiliang.entity.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserOutMapper {
    //根据订单单号查询仓库中的订单
    @Select("select * from workhouse_managementshu.workhouse where order_id=#{orderId};")
    Goods getByOrderId(String orderId);
}
