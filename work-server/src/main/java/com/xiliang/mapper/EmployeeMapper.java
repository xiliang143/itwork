package com.xiliang.mapper;

import com.xiliang.dto.EmployeeLoginDTO;
import com.xiliang.entity.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface EmployeeMapper {
    @Select("select * from workhouse_managementshu.employee where username = #{username}")
    Employee getByUsername(String username);


}
