package com.xiliang.mapper;

import com.xiliang.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserInAndOutMapper {
    //根据用户名查询用户信息
    @Select("select * from workhouse_managementshu.user where username = #{username}")
    User getByUsername(String username);
    //新增用户
    @Insert("insert into workhouse_managementshu.user (username, password, name, phone, sex, id_number, create_time, update_time) " +
            "values (#{username}, #{password}, #{name}, #{phone}, #{sex}, #{idNumber}, #{createTime}, #{updateTime})")
    void insert(User user);

    void updateById(User user);
    @Select("select * from workhouse_managementshu.user where id = #{id}")
    User getById(Long id);
}
