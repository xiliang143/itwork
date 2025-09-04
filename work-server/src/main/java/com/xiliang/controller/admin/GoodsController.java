package com.xiliang.controller.admin;

import com.xiliang.entity.Goods;
import com.xiliang.result.Result;
import com.xiliang.service.GoodsService;
import com.xiliang.utils.AliOssUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@Slf4j
@RequestMapping("/admin/goods")
@Api(tags = "货物管理")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private AliOssUtil aliOssUtil;

    //货物添加功能
    @PostMapping
    @ApiOperation("添加货物")
    public Result addGoods(@ModelAttribute Goods goods, @RequestParam("file") MultipartFile file) {
        log.info("添加货物：{}", goods);
        try {
            // 复用CommonController的上传逻辑
            String originalFilename = file.getOriginalFilename();
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String objectName = UUID.randomUUID().toString() + extension;
            String filePath = aliOssUtil.upload(file.getBytes(), objectName);

            // 设置图片路径到商品对象
            goods.setImg(filePath);
            goodsService.addGoods(goods);
            return Result.success();
        } catch (IOException e) {
            log.error("文件上传失败：{}", e);
            return Result.error("文件上传失败");
        }
    }
    //根据id查询货物功能
    @GetMapping("/{id}")
    @ApiOperation("根据id查询货物")
    public Result<Goods> getGoods(@PathVariable long id) {
        log.info("根据id查询货物：{}", id);
        Goods goods=goodsService.getGoodsById(id);
        return Result.success(goods);

    }

}
