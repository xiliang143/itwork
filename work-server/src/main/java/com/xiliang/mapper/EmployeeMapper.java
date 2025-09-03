package com.xiliang.mapper;

import com.xiliang.dto.EmployeeDTO;
import com.xiliang.dto.EmployeeLoginDTO;
import com.xiliang.entity.Employee;
import org.apache.ibatis.annotations.*;

@Mapper
public interface EmployeeMapper {
    //根据用户名查询员工
    @Select("select * from workhouse_managementshu.employee where username = #{username}")
    Employee getByUsername(String username);
    //新增员工
    void insert(Employee employee);
   @Delete("delete from workhouse_managementshu.employee where id = #{id}")
    void deleteById(Long id);
    //编辑员工信息
    void update(Employee employee);
}
