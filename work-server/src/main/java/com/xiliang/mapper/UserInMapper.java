package com.xiliang.mapper;

import com.xiliang.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserInMapper {
    //根据用户名查询用户信息
    @Select("select * from workhouse_managementshu.user where username = #{username}")
    User getByUsername(String username);
}
