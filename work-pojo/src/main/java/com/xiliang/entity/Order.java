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
public class Order implements Serializable {
    //序列化版本号
    private static final long serialVersionUID = 1L;
    //主键
    private Long id;
    //订单发起人
    private String username;
    //订单单号
    private String orderId;
    //订单创建时间
    private LocalDateTime createTime;
    //订单支付状态
    private Integer payStatus;
    //订单货物种类
    private Integer goodsType;
    //订单货物箱数
    private Integer goodsNum;

}
