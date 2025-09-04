package com.xiliang.controller.admin;

import com.xiliang.constant.JwtClaimsConstant;
import com.xiliang.dto.EmployeeDTO;
import com.xiliang.dto.EmployeeLoginDTO;
import com.xiliang.entity.Employee;
import com.xiliang.properties.Jwtproperties;
import com.xiliang.result.Result;
import com.xiliang.service.EmployeeService;
import com.xiliang.utils.JwtUtil;
import com.xiliang.vo.EmployeeLoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@Slf4j
@RequestMapping("/admin/employee")
@Api(tags = "员工管理")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private Jwtproperties jwtproperties;
    /*
    * 员工登录
    * */
    @PostMapping("/login")
    @ApiOperation("员工登录")
    public Result<EmployeeLoginVO> employeeLogin(@RequestBody EmployeeLoginDTO employeeLoginDTO) {
        log.info("员工登录：,{}", employeeLoginDTO);
        Employee employee = employeeService.employeeLogin(employeeLoginDTO);
        //登陆成功后生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.EMP_ID, employee.getId());
        String token = JwtUtil.createJWT(
                jwtproperties.getAdminSecretKey(),
                jwtproperties.getAdminTtl(),
                claims);

        EmployeeLoginVO employeeLoginVO = EmployeeLoginVO.builder()
                .id(employee.getId())
                .userName(employee.getUsername())
                .name(employee.getName())
                .token(token)
                .build();

        return Result.success(employeeLoginVO);
    }
    //退出登录
    @PostMapping("/logout")
    @ApiOperation("员工退出")
    public Result<String> employeeLogout() {
        return Result.success();
    }
    //新增员工
    @PostMapping
    @ApiOperation("新增员工")
    public Result employeeSave(@RequestBody EmployeeDTO employeeDTO) {
        log.info("新增员工,{}", employeeDTO);
        employeeService.employeeSave(employeeDTO);
        return Result.success();
    }
    //删除员工
    @DeleteMapping/*("/{id}")*/
    @ApiOperation("删除员工")
    public Result<String> employeeDelete(Long id) {
        log.info("删除员工,{}", id);
        employeeService.employeeDelete(id);
        return Result.success(id.toString());
    }
    //根据id查询员工信息
    @GetMapping("/{id}")
    @ApiOperation("根据id查询员工信息")
    public Result<Employee> getById(@PathVariable Long id){
        log.info("根据id查询员工信息{}",id);
        Employee employee = employeeService.getById(id);
        return Result.success(employee);
    }
    //编辑员工信息
    @PutMapping
    @ApiOperation("编辑员工信息")
    public Result update(@RequestBody EmployeeDTO employeeDTO) {
        log.info("编辑员工信息{}",employeeDTO);
        employeeService.update(employeeDTO);
        return Result.success();
    }
    //启用禁用员工账号
    @PostMapping("/status/{status}")
    @ApiOperation("启用禁用员工账号")
    public Result startOrStop(@PathVariable Integer status,Long id){
        log.info("启用禁用员工账号:{},{}",status,id);
        employeeService.startOrStop(status,id);
        return Result.success();
    }




}
