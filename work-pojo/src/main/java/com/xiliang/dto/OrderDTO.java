package com.xiliang.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO implements Serializable {


    private Integer goodsType;

    private Integer goodsNum;
}
