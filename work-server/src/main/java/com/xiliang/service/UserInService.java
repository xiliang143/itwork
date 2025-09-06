package com.xiliang.service;

import com.xiliang.dto.OrderDTO;
import com.xiliang.dto.UserLoginDTO;
import com.xiliang.entity.User;

public interface UserInService {
    /**
     * 用户登录
     * @param userLoginDTO
     * @return
     */
    User userLogin(UserLoginDTO userLoginDTO);
    //用户注册(新增用户)
    void register(User user);

    //用户修改个人信息
    void updateById(User user);
    //用户根据id查询个人信息
    User getById(Long id);
    //用户提交订单
    void submitOrder(OrderDTO orderDTO);
}
