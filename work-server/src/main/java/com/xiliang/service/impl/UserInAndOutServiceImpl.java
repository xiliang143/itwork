package com.xiliang.service.impl;

import com.xiliang.constant.MessageConstant;
import com.xiliang.constant.OrderPayStatusConstant;
import com.xiliang.constant.PasswordConstant;
import com.xiliang.context.BaseContext;
import com.xiliang.dto.OrderDTO;
import com.xiliang.dto.UserLoginDTO;
import com.xiliang.entity.Goods;
import com.xiliang.entity.Order;
import com.xiliang.entity.User;
import com.xiliang.exception.AccountNotFoundException;
import com.xiliang.exception.NoTheUserException;
import com.xiliang.exception.PasswordErrorException;
import com.xiliang.mapper.OrderInMapper;
import com.xiliang.mapper.UserInAndOutMapper;
import com.xiliang.service.UserInAndOutService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UserInAndOutServiceImpl implements UserInAndOutService {
    @Autowired
    private UserInAndOutMapper userInAndOutMapper;
    @Autowired
    private OrderInMapper orderMapper;


    //用户登录
    public User userLogin(UserLoginDTO userLoginDTO) {
        String username = userLoginDTO.getUsername();
        String password = userLoginDTO.getPassword();
        //1、根据用户名查询数据库中的数据
        User user = userInAndOutMapper.getByUsername(username);
        //2、处理各种异常情况（用户名不存在、密码不对、账号被锁定）
        if (user == null) {
            //账号不存在
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }
        //密码比对
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!password.equals(user.getPassword())) {
            //密码错误
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }
        return user;
    }
    //用户注册（新增用户）
    public void register(User user) {
        //设置默认密码为123456
        user.setPassword(DigestUtils.md5DigestAsHex(PasswordConstant.DEFAULT_PASSWORD.getBytes()));
        //设置创建时间和修改时间
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        //插入数据
        userInAndOutMapper.insert(user);
    }

    //用户根据id查询个人信息
    public User getById(Long id) {
        User user = userInAndOutMapper.getById(id);
        return user;
    }

    //用户修改个人信息
    public void updateById(User user) {
        //获取当前登录用户的id
        Long userId = BaseContext.getCurrentId();
        //如果用户id和当前登录用户的id不一致，抛出禁止修改异常
        if(!user.getId().equals(userId)) {
            throw new NoTheUserException(MessageConstant.NOT_THE_CURRENT_USER);
        }
        //如果是当前用户，则被允许修改
        user.setUpdateTime(LocalDateTime.now());
        userInAndOutMapper.updateById(user);
    }
    //用户提交订单
    @Transactional(rollbackFor = Exception.class)
    public void submitOrder(OrderDTO orderDTO) {

        //获取当前用户
        Long userId = BaseContext.getCurrentId();
        User user = userInAndOutMapper.getById(userId);
        //获取用户名
        String username = user.getUsername();
        //用uuid设置一个10位数的订单号
        String orderId = UUID.randomUUID().toString().replace("-", "").substring(0, 10);
        Order order = Order.builder()
                .username(username)
                .orderId(orderId)
                .createTime(LocalDateTime.now())
                .payStatus(OrderPayStatusConstant.NOT_PAY)
                .build();
        BeanUtils.copyProperties(orderDTO, order);
        orderMapper.insert(order);

    }
    //根据出库订单单号查询仓库中的订单
    public Goods getByOrderId(String orderId) {
        Goods goods=userInAndOutMapper.getByOrderId(orderId);
        return goods;
    }
}
