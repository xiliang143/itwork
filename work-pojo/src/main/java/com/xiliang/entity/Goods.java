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

    private String goodsName;
    //存入时间
    private LocalDateTime CreateTime;
    //存入人员
    private Long CreateUser;
    //更新时间
    private LocalDateTime UpdateTime;
    //更新人员
    private Long UpdateUser;
    //货物图片
    private String Img;


}
