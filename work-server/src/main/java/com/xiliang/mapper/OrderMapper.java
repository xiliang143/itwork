package com.xiliang.mapper;

import com.github.pagehelper.Page;
import com.xiliang.dto.OrderPageQueryDTO;
import com.xiliang.entity.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {

    //订单分页查询

    Page<Order> pageQuery(OrderPageQueryDTO orderPageQueryDTO);
}
