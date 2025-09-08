package com.xiliang.service;

import com.xiliang.dto.OrderPageQueryDTO;
import com.xiliang.entity.Order;
import com.xiliang.result.PageResult;

public interface OrderService {
    //订单分页查询
    PageResult pageQuery(OrderPageQueryDTO orderPageQueryDTO);
    //根据订单id删除订单
    void deleteOrder(long id);
}
