package com.xiliang.controller.admin;

import com.xiliang.dto.OrderPageQueryDTO;
import com.xiliang.entity.Order;
import com.xiliang.result.PageResult;
import com.xiliang.result.Result;
import com.xiliang.service.OrderInService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/admin/orderin")
@Slf4j
@Api(tags = "管理端管理入库订单功能模块")
public class OrderInController {
    @Autowired
    private OrderInService orderService;


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
    //根据id查询订单
    @GetMapping("/{id}")
    @ApiOperation("根据id查询订单")
    public Result<Order> getById(@PathVariable long id){
        log.info("根据id查询订单：{}",id);
        Order order = orderService.getById(id);
        return Result.success(order);
    }

    //接单计算入库价格并打印报表
    @GetMapping("/export")
    @ApiOperation("打印入库报表")
    public void export(HttpServletResponse response, Long id) throws IOException {
        orderService.exportInData(response,id);
    }













}
