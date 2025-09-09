package com.xiliang.controller.user;

import com.xiliang.entity.Goods;
import com.xiliang.entity.Order;
import com.xiliang.result.Result;
import com.xiliang.service.UserOutService;
import com.xiliang.vo.OrderVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/out")
@Slf4j
@Api(tags = "用户出库模块")
public class UserOutController {
    @Autowired
    private UserOutService userOutService;



    //根据订单单号查询仓库中的订单
    @GetMapping("/getByOrderId")
    @ApiOperation(value = "根据订单单号查询仓库中的订单")
    public Result<Goods> getByOrderId(String orderId) {
        log.info("根据订单单号查询仓库中的订单：{}", orderId);
        Goods goods=userOutService.getByOrderId(orderId);
        return Result.success(goods);
    }







}
