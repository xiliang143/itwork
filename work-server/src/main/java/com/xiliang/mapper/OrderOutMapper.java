package com.xiliang.mapper;

import com.github.pagehelper.Page;
import com.xiliang.dto.OrderPageQueryDTO;
import com.xiliang.entity.Order;
import com.xiliang.entity.OrderOut;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDateTime;

@Mapper
public interface OrderOutMapper {
    //订单分页查询
     Page<OrderOut> pageQuery(OrderPageQueryDTO orderPageQueryDTO);
    //新增出库订单
    void insert(OrderOut orderOut);
    //根据id查询订单
    @Select("select * from workhouse_managementshu.order_out where id=#{id}")
    OrderOut getById(Long id);
    @Select("select create_time from workhouse_managementshu.order_out where id=#{id}")
    LocalDateTime getcreateTime(Long id);
    @Update("update workhouse_managementshu.order_out set pay_status=#{payStatus} where id=#{id}")
    void updateOrder(Integer payStatus, Long id);
    @Delete("delete from workhouse_managementshu.order_out where id=#{id}")
    void deleteOrder(Long id);
}
