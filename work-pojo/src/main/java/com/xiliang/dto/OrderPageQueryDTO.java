package com.xiliang.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data

public class OrderPageQueryDTO implements Serializable {

    private Long orderId;

    //页码
    private int page;

    //每页显示记录数
    private int pageSize;



}
