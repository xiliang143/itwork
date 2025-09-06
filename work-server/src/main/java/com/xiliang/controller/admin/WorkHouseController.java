package com.xiliang.controller.admin;

import com.xiliang.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@Api(tags = "仓库管理模块")
@RequestMapping("/admin/workHouse")
public class WorkHouseController {
    @Autowired
    private RedisTemplate redisTemplate;
    public static final String KEY = "WORKHOUSE_STATUS";


    //设置仓库状态
    @PutMapping("/{status}")
    @ApiOperation("设置仓库状态")
    public Result setWorkHouseStatus(@PathVariable Integer status){
        log.info("设置仓库状态:{}",status==1?"仓库开启":"仓库关闭");
        redisTemplate.opsForValue().set(KEY,status);
        return Result.success();
    }
    //获取仓库状态
    @GetMapping("/status")
    @ApiOperation("获取仓库状态")
    public Result<Integer> getWorkHouseStatus(){
        Integer status =(Integer)redisTemplate.opsForValue().get(KEY);
        log.info("获取仓库状态：{}",status==1?"仓库开启":"仓库关闭");
        return Result.success(status);
    }


}
