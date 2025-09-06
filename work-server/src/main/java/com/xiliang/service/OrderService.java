package com.xiliang.service;

import com.xiliang.dto.OrderPageQueryDTO;
import com.xiliang.result.PageResult;

public interface OrderService {
    //订单分页查询
    PageResult pageQuery(OrderPageQueryDTO orderPageQueryDTO);
}
