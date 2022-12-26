package com.study.hunting.controller;

import com.study.hunting.domain.User;
import com.study.hunting.enums.ResponseCode;
import com.study.hunting.service.UserService;
import com.study.hunting.util.TokenUtils;
import com.study.hunting.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
@Api(value = "用户信息管理")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "/global/login")
    @ApiOperation(value = "用户登录")
    public ResultVO<User> login(String email, String password) throws Exception {
        return userService.login(email, password);
    }

    @PostMapping(value = "/global/register")
    @ApiOperation(value = "用户注册")
    public ResultVO<User> register(User user) throws Exception {
        return userService.register(user);
    }

    @PutMapping(value = "/update/base")
    @ApiOperation(value = "更新用户基本信息")
    public ResultVO<User> updateBaseInfo(User user, Integer userId) throws Exception {
        return userService.updateBaseInfo(user, userId);
    }

    @GetMapping(value = "/update/token")
    @ApiOperation(value = "更新token")
    public ResultVO updateToken(Integer userId) {
        ResultVO result = new ResultVO<>();
        result.setResponseCode(ResponseCode.SUCCESS);
        result.setToken(TokenUtils.getToken(userId));
        return result;
    }

    @PutMapping(value = "/update/password")
    @ApiOperation(value = "更新用户密码")
    public ResultVO<User> updatePassword(User user, Integer userId, String newPassword) throws Exception {
        return userService.updatePassword(user, userId, newPassword);
    }

    @PutMapping(value = "/freeze/{targetId}")
    @ApiOperation(value = "冻结用户")
    public ResultVO freeze(Integer userId, @PathVariable Integer targetId) {
        return userService.freeze(userId, targetId);
    }

    @PutMapping(value = "/unfreeze/{targetId}")
    @ApiOperation(value = "账户解冻")
    public ResultVO unfreeze(Integer userId, @PathVariable Integer targetId) {
        return userService.unfreeze(userId, targetId);
    }
}
