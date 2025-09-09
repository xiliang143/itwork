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
public class OrderOut implements Serializable {

    //序列化版本号
    private static final long serialVersionUID = 1L;
    //主键
    private Long id;
    //出库订单发起人
    private String username;
    //出库订单单号
    private String orderId;
    //出库订单创建时间
    private LocalDateTime createTime;
    //出库订单支付状态
    private Integer payStatus;
    //出库订单货物种类
    private Integer goodsType;
    //出库订单货物箱数
    private Integer goodsNum;

}
