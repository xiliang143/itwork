package com.xiliang.controller.admin;

import com.xiliang.dto.OrderPageQueryDTO;
import com.xiliang.result.PageResult;
import com.xiliang.result.Result;
import com.xiliang.service.OrderOutService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/orderout")
@Slf4j
@Api(tags = "管理端管理出库订单模块")
public class OrderOutController {
    @Autowired
    private OrderOutService orderOutService;



    //订单分页查询
    @GetMapping("/page")
    @ApiOperation("订单分页查询")
    public Result<PageResult> page(OrderPageQueryDTO orderPageQueryDTO) {
        log.info("订单分页查询:{}", orderPageQueryDTO);
        PageResult pageResult = orderOutService.pageQuery(orderPageQueryDTO);
        return Result.success(pageResult);
    }
}
