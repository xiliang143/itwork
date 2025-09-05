package com.xiliang.service.impl;

import com.xiliang.constant.MessageConstant;
import com.xiliang.context.BaseContext;
import com.xiliang.dto.UserLoginDTO;
import com.xiliang.entity.User;
import com.xiliang.exception.AccountNotFoundException;
import com.xiliang.exception.PasswordErrorException;
import com.xiliang.mapper.UserInMapper;
import com.xiliang.service.UserInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInServiceImpl implements UserInService {
    @Autowired
    private UserInMapper userInMapper;


    //用户登录
    public User userLogin(UserLoginDTO userLoginDTO) {
        String username = userLoginDTO.getUsername();
        String password = userLoginDTO.getPassword();
        //1、根据用户名查询数据库中的数据
        User user = userInMapper.getByUsername(username);
        //2、处理各种异常情况（用户名不存在、密码不对、账号被锁定）
        if (user == null) {
            //账号不存在
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }
        //密码比对
        if (!password.equals(user.getPassword())) {
            //密码错误
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }
        return user;
    }
    //用户注册（新增用户）

}
