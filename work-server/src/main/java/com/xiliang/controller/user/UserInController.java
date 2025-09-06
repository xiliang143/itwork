package com.xiliang.controller.user;

import com.xiliang.constant.JwtClaimsConstant;
import com.xiliang.dto.OrderDTO;
import com.xiliang.dto.UserLoginDTO;
import com.xiliang.entity.Order;
import com.xiliang.entity.User;
import com.xiliang.json.JacksonObjectMapper;
import com.xiliang.properties.Jwtproperties;
import com.xiliang.result.Result;
import com.xiliang.service.UserInService;
import com.xiliang.utils.JwtUtil;
import com.xiliang.vo.OrderVO;
import com.xiliang.vo.UserLoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Info;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user/userin")
@Slf4j
@Api(tags = "用户入库模块")
public class UserInController {
    @Autowired
    private UserInService userInService;
    @Autowired
    private Jwtproperties jwtproperties;


    //用户登陆界面
    @PostMapping("/login")
    @ApiOperation("用户登录")
    public Result<UserLoginVO> userLogin(@RequestBody UserLoginDTO userLoginDTO) {
        log.info("用户登录,{}", userLoginDTO);

        User user=userInService.userLogin(userLoginDTO);
        //登陆成功后生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.USER_ID, user.getId());
        String token = JwtUtil.createJWT(
                jwtproperties.getUserSecretKey(),
                jwtproperties.getUserTtl(),
                claims);

        UserLoginVO userLoginVO = UserLoginVO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .name(user.getName())
                .token(token)
                .build();

        return Result.success(userLoginVO);
    }
    //用户退出登录
    @PostMapping("/logout")
    @ApiOperation("用户退出")
    public Result<String> logout() {
        return Result.success();
    }
    //用户注册（新增用户）
    @PostMapping("/register")
    @ApiOperation("用户注册")
    public Result register(@RequestBody User user){
        log.info("用户注册,{}",user);
        userInService.register(user);
        return Result.success();
    }
    //用户查询个人信息
    @GetMapping("/getById/{id}")
    @ApiOperation("用户查询个人信息")
    public Result<User> getById(@PathVariable Long id){
        log.info("用户修改个人信息：{}",id);
        User user=userInService.getById(id);
        return Result.success(user);
    }

    //用户修改个人信息
    @PostMapping("/update")
    @ApiOperation("用户修改个人信息")
    public Result update(@RequestBody User user){
        log.info("用户修改个人信息,{}",user);
        userInService.updateById(user);
        return Result.success();
    }

    //用户提交订单
    @PostMapping("/submit")
    @ApiOperation("用户提交订单")
    public Result submitOrder(@RequestBody OrderDTO orderDTO){
        log.info("用户提交订单,{}",orderDTO);
        userInService.submitOrder(orderDTO);
        return Result.success();
    }





}
