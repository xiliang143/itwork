package com.xiliang.controller.admin;

import com.xiliang.dto.OrderPageQueryDTO;
import com.xiliang.entity.Order;
import com.xiliang.entity.User;
import com.xiliang.result.PageResult;
import com.xiliang.result.Result;
import com.xiliang.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/order")
@Slf4j
@Api(tags = "管理端管理订单功能模块")
public class OrderController {
    @Autowired
    private OrderService orderService;


    //订单分页查询模块
    @GetMapping("/page")
    @ApiOperation("订单分页查询")
    public Result<PageResult> page(OrderPageQueryDTO orderPageQueryDTO) {
        log.info("订单分页查询:{}", orderPageQueryDTO);
        PageResult pageResult = orderService.pageQuery(orderPageQueryDTO);
        return Result.success(pageResult);
    }
    //根据订单id删除订单模块
    @DeleteMapping
    @ApiOperation("根据订单id删除订单")
    public Result deleteOrder(long id){
        log.info("删除订单表中的数据：{}",id);
        orderService.deleteOrder(id);
        return Result.success();
    }











}
