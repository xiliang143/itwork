package com.xiliang.mapper;

import com.github.pagehelper.Page;
import com.xiliang.dto.OrderPageQueryDTO;
import com.xiliang.entity.Order;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface OrderMapper {
    //订单分页查询
    Page<Order> pageQuery(OrderPageQueryDTO orderPageQueryDTO);

    //新增订单
    void insert(Order order);
    @Delete("delete from workhouse_managementshu.order where id=#{id}")
    void deleteOrder(long id);
    //根据id查询订单
    @Select("select * from workhouse_managementshu.order where id=#{id}")
    Order getById(long id);
}
