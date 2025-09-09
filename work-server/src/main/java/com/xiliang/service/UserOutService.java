package com.xiliang.service;

import com.xiliang.entity.Goods;
import com.xiliang.vo.OrderVO;

public interface UserOutService {
    //根据订单单号查询仓库中的订单
    Goods getByOrderId(String orderId);
}
