package com.xiliang.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xiliang.dto.OrderPageQueryDTO;
import com.xiliang.entity.OrderOut;
import com.xiliang.mapper.OrderOutMapper;
import com.xiliang.result.PageResult;
import com.xiliang.service.OrderOutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderOutServiceImpl implements OrderOutService {
    @Autowired
    private OrderOutMapper orderOutMapper;


    //订单分页查询
    public PageResult pageQuery(OrderPageQueryDTO orderPageQueryDTO) {
        //开始分页查询
        PageHelper.startPage(orderPageQueryDTO.getPage(),orderPageQueryDTO.getPageSize());
        //查询订单
        Page<OrderOut> page=orderOutMapper.pageQuery(orderPageQueryDTO);
        long total = page.getTotal();
        List<OrderOut> records = page.getResult();
        return new PageResult(total,records);
    }
}
