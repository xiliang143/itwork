package com.xiliang.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xiliang.context.BaseContext;
import com.xiliang.dto.OrderPageQueryDTO;
import com.xiliang.entity.Employee;
import com.xiliang.entity.Order;
import com.xiliang.mapper.OrderMapper;
import com.xiliang.result.PageResult;
import com.xiliang.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.SortParameters;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;


    //订单分页查询
    public PageResult pageQuery(OrderPageQueryDTO orderPageQueryDTO) {
        //开始分页查询
        PageHelper.startPage(orderPageQueryDTO.getPage(), orderPageQueryDTO.getPageSize());
        //查询订单
        Page<Order> page=orderMapper.pageQuery(orderPageQueryDTO);
        long total = page.getTotal();
        List<Order> records = page.getResult();
        return new PageResult(total,records);

    }
    //根据id删除订单
    public void deleteOrder(long id) {
        orderMapper.deleteOrder(id);
    }
    //根据id查询订单
    public Order getById(long id) {
        //如果id在数据库表中不存在，则抛出异常
        if (orderMapper.getById(id)==null){
            throw new RuntimeException("订单不存在");
        }
        Order order=orderMapper.getById(id);
        return order;

    }
    /*//打印入库报表
    public void exportInData(HttpServletResponse response) {
        //查询数据库，获取数据

        //将查询到的数据，写入excel文件中

        //通过输出流，将excel下载到客户端浏览器



    }*/
}
