package com.xiliang.service;

import com.xiliang.dto.OrderPageQueryDTO;
import com.xiliang.result.PageResult;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface OrderOutService {
    //订单分页查询
    PageResult pageQuery(OrderPageQueryDTO orderPageQueryDTO);
    //接出库单计算出库价格打印报表
    void exportOutData(HttpServletResponse response, Long id) throws IOException;
}
