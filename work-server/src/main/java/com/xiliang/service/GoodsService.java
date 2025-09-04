package com.xiliang.service;

import com.xiliang.entity.Goods;

public interface GoodsService {
    //货物添加功能
    void addGoods(Goods goods);
    //根据id查询货物功能
    Goods getGoodsById(long id);
}
