package com.xiliang.mapper;

import com.xiliang.entity.Goods;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WorkhousringMapper {

    //向仓库中添加货物
    void insert(Goods goods);
    //根据入库订单号删除货物中的订单
    @Delete("delete from workhouse_managementshu.workhouse where order_id = #{orderId}")
    void deleteByOrderId(String orderId);
}
