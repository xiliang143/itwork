package com.xiliang.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xiliang.context.BaseContext;
import com.xiliang.dto.OrderPageQueryDTO;
import com.xiliang.entity.Employee;
import com.xiliang.entity.Order;
import com.xiliang.mapper.OrderMapper;
import com.xiliang.result.PageResult;
import com.xiliang.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;


    //订单分页查询
    public PageResult pageQuery(OrderPageQueryDTO orderPageQueryDTO) {
        //开始分页查询
        PageHelper.startPage(orderPageQueryDTO.getPage(), orderPageQueryDTO.getPageSize());
        //查询订单
        Page<Order> page=orderMapper.pageQuery(orderPageQueryDTO);
        long total = page.getTotal();
        List<Order> records = page.getResult();
        return new PageResult(total,records);

    }

}
