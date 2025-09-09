package com.xiliang.service;

import com.xiliang.dto.OrderPageQueryDTO;
import com.xiliang.entity.Order;
import com.xiliang.result.PageResult;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface OrderInService {
    //订单分页查询
    PageResult pageQuery(OrderPageQueryDTO orderPageQueryDTO);
    //根据订单id删除订单
    void deleteOrder(long id);
    //根据id查询订单
    Order getById(long id);
    //打印入库报表
    void exportInData(HttpServletResponse response,Long id) throws IOException;
}
