package com.xiliang.mapper;

import com.github.pagehelper.Page;
import com.xiliang.dto.OrderPageQueryDTO;
import com.xiliang.entity.OrderOut;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface OrderOutMapper {
    //订单分页查询
     Page<OrderOut> pageQuery(OrderPageQueryDTO orderPageQueryDTO);
}
