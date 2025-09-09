package com.xiliang.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Goods implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    //货物归属人
    private String username;
    //货物订单号
    private String orderId;
    //货物存入时间
    private LocalDateTime createTime;
    //货物种类
    private Integer goodsType;
    //货物箱数
    private Integer goodsNum;
    //货物图片
    private String img;


}
