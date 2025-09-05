package com.xiliang.service;

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

}
