package com.xiliang.service.impl;

import com.xiliang.context.BaseContext;
import com.xiliang.controller.admin.CommonController;
import com.xiliang.controller.admin.GoodsController;
import com.xiliang.entity.Goods;
import com.xiliang.mapper.GoodsMapper;
import com.xiliang.service.GoodsService;
import com.xiliang.utils.AliOssUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Slf4j
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;

    //货物添加功能
    public void addGoods(Goods goods) {

        goods.setCreateTime(LocalDateTime.now());
        goods.setUpdateTime(LocalDateTime.now());
        goods.setCreateUser(BaseContext.getCurrentId());
        goods.setUpdateUser(BaseContext.getCurrentId());
        //添加货物名称
        goodsMapper.insert(goods);

    }
}
