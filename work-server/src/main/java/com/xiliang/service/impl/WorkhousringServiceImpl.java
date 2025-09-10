package com.xiliang.service.impl;

import com.xiliang.constant.OrderPayStatusConstant;
import com.xiliang.entity.Goods;
import com.xiliang.entity.Order;
import com.xiliang.exception.PayStatusException;
import com.xiliang.mapper.OrderInMapper;
import com.xiliang.mapper.WorkhousringMapper;
import com.xiliang.service.WorkhousringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class WorkhousringServiceImpl implements WorkhousringService {
    @Autowired
    private OrderInMapper orderMapper;
    @Autowired
    private WorkhousringMapper workhousringMapper;
    //向仓库添加货物
    public void addGoods(Goods goods) {
        //查询订单表，并将订单表中的数据存入仓库表中
        Order order = orderMapper.getById(goods.getId());
        //货物持有人
        String username = order.getUsername();
        //货物订单号
        String orderId = order.getOrderId();
        //货物种类
        Integer goodsType = order.getGoodsType();
        //货物箱数
        Integer goodsNum = order.getGoodsNum();

        goods.setUsername(username);
        goods.setOrderId(orderId);
        goods.setGoodsType(goodsType);
        goods.setGoodsNum(goodsNum);
        goods.setCreateTime(LocalDateTime.now());
        Integer payStatus = order.getPayStatus();
        if (payStatus == 1) {
            workhousringMapper.insert(goods);
            //像仓库中添加货物完成后，删除订单表里的数据
            orderMapper.deleteById(goods.getId());
        }else {
            throw new PayStatusException(OrderPayStatusConstant.NOT_PAY);
        }

    }
    //根据入库订单号删除货物中的订单
    public void deleteByOrderId(String orderId) {
        workhousringMapper.deleteByOrderId(orderId);
    }
}
