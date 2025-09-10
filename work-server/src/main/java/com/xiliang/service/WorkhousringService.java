package com.xiliang.service;

import com.xiliang.entity.Goods;

public interface WorkhousringService {
    //向仓库添加货物
    void addGoods(Goods goods);
    //根据入库订单号删除货物中的订单
    void deleteByOrderId(String orderId);
}
