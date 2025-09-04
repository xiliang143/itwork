package com.xiliang.service;

import com.xiliang.dto.EmployeeDTO;
import com.xiliang.dto.EmployeeLoginDTO;
import com.xiliang.entity.Employee;

public interface EmployeeService {
 //员工登录
 Employee employeeLogin(EmployeeLoginDTO employeeLoginDTO);
//新增员工
 void employeeSave(EmployeeDTO employeeDTO);
//删除员工
 void employeeDelete(Long id);
//编辑员工信息
 void update(EmployeeDTO employeeDTO);
//根据id查询员工信息
 Employee getById(Long id);
//启用禁用员工账号
 void startOrStop(Integer status, Long id);
}
