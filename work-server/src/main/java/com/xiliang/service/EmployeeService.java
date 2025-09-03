package com.xiliang.service;

import com.xiliang.dto.EmployeeLoginDTO;
import com.xiliang.entity.Employee;

public interface EmployeeService {
 Employee employeeLogin(EmployeeLoginDTO employeeLoginDTO);
}
