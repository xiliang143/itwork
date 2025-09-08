package com.xiliang.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WorkHouse implements Serializable {

    private static final long serialVersionUID = 1L;
    //主键
    private long id;
    //订单归属人
    private String username;
    //订单号
    private String orderId;
    //存入仓库时间
    private LocalDateTime createTime;
    //货物种类
    private Integer goodsType;
    //货物箱数
    private Integer goodsNum;
    //货物图片
    private String img;
}
