package com.xiliang.mapper;

import com.xiliang.dto.EmployeeLoginDTO;
import com.xiliang.entity.Employee;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface EmployeeMapper {
    //根据用户名查询员工
    @Select("select * from workhouse_managementshu.employee where username = #{username}")
    Employee getByUsername(String username);
    //新增员工
    void insert(Employee employee);
}
