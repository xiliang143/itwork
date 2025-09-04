package com.xiliang.service.impl;

import com.xiliang.constant.MessageConstant;
import com.xiliang.constant.PasswordConstant;
import com.xiliang.constant.StatusConstant;
import com.xiliang.context.BaseContext;
import com.xiliang.dto.EmployeeDTO;
import com.xiliang.dto.EmployeeLoginDTO;
import com.xiliang.entity.Employee;
import com.xiliang.exception.AccountLockedException;
import com.xiliang.exception.AccountNotFoundException;
import com.xiliang.exception.PasswordErrorException;
import com.xiliang.mapper.EmployeeMapper;
import com.xiliang.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;

    // 员工登录
    public Employee employeeLogin(EmployeeLoginDTO employeeLoginDTO){
        String username = employeeLoginDTO.getUsername();
        String password = employeeLoginDTO.getPassword();

        //1、根据用户名查询数据库中的数据
        Employee employee = employeeMapper.getByUsername(username);

        //2、处理各种异常情况（用户名不存在、密码不对、账号被锁定）
        if (employee == null) {
            //账号不存在
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }

        //密码比对
        // 对前端传来的密码进行md5加密
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!password.equals(employee.getPassword())) {
            //密码错误
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }

        if (employee.getStatus() == StatusConstant.DISABLE) {
            //账号被锁定
            throw new AccountLockedException(MessageConstant.ACCOUNT_LOCKED);
        }

        //3、返回实体对象
        return employee;
    }
    //新增员工

    public void employeeSave(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO, employee);
        //设置员工默认状态
        employee.setStatus(StatusConstant.ENABLE);
        //设置默认密码为123456
        employee.setPassword(DigestUtils.md5DigestAsHex(PasswordConstant.DEFAULT_PASSWORD.getBytes()));
        //设置创建时间和修改时间
        employee.setCreateTime(LocalDateTime.now());
        employee.setUpdateTime(LocalDateTime.now());
        //设置当前记录人的id和修改人的id
        employee.setCreateUser(BaseContext.getCurrentId());
        employee.setUpdateUser(BaseContext.getCurrentId());
        //插入数据
        employeeMapper.insert(employee);

    }

    //删除员工
    public void employeeDelete(Long id) {
        employeeMapper.deleteById(id);
    }
    //根据id查询员工信息
    public Employee getById(Long id) {
        Employee employee = employeeMapper.getById(id);
        //不暴露密码
        employee.setPassword("*****");
        return employee;

    }
    //编辑员工信息
    @Transactional(rollbackFor = Exception.class)
    public void update(EmployeeDTO employeeDTO) {
        // 1. 查询数据库中的原始数据（使用专门的方法，避免密码被修改）
        Employee existingEmployee = employeeMapper.getById(employeeDTO.getId());
        // 2. 创建新的Employee对象用于更新
        Employee employee = new Employee();
        // 3. 将原始数据拷贝到新对象中（保留所有原有字段）
        BeanUtils.copyProperties(existingEmployee, employee);
        // 4. 将DTO中的修改数据拷贝到新对象中（只更新有变化的字段）
        BeanUtils.copyProperties(employeeDTO, employee);
        // 5. 设置更新时间和更新用户
        employee.setUpdateTime(LocalDateTime.now());
        employee.setUpdateUser(BaseContext.getCurrentId());
        // 6. 执行更新操作
        employeeMapper.update(employee);
    }
    //启用禁用员工账号
    public void startOrStop(Integer status, Long id) {
        Employee employee = Employee.builder()
                .status(status)
                .id(id)
                .build();
        employee.setUpdateTime(LocalDateTime.now());
        employee.setUpdateUser(BaseContext.getCurrentId());
        employeeMapper.update(employee);


    }
}
