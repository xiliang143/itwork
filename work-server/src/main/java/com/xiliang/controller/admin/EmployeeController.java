package com.xiliang.controller.admin;

import com.xiliang.constant.JwtClaimsConstant;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/login")
    @ApiOperation("员工登录")
    public Result<EmployeeLoginVO> employeeLogin(@RequestBody EmployeeLoginDTO employeeLoginDTO) {
        log.info("员工登录：,{}", employeeLoginDTO);
        Employee employee = employeeService.employeeLogin(employeeLoginDTO);
        //登陆成功后生成jwt令牌
        //登录成功后，生成jwt令牌
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



}
