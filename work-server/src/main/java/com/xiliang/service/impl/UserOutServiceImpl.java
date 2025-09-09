package com.xiliang.service.impl;

import com.xiliang.entity.Goods;
import com.xiliang.mapper.UserOutMapper;
import com.xiliang.service.UserOutService;
import com.xiliang.service.WorkhousringService;
import com.xiliang.vo.OrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserOutServiceImpl implements UserOutService {

    @Autowired
    private UserOutMapper userOutMapper;



    //根据订单单号查询仓库中的订单
    public Goods getByOrderId(String orderId) {
        Goods goods=userOutMapper.getByOrderId(orderId);
        return goods;
    }
}
