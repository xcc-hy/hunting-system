package com.study.hunting.controller;

import com.study.hunting.domain.User;
import com.study.hunting.enums.ResponseCode;
import com.study.hunting.service.UserService;
import com.study.hunting.util.TokenUtils;
import com.study.hunting.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

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
    public ResultVO<User> updateBaseInfo(User user, @RequestHeader Integer userId) throws Exception {
        return userService.updateBaseInfo(user, userId);
    }

    @PutMapping("/update/ico")
    @ApiOperation("上传头像")
    public ResultVO<String> updateIco(@RequestHeader Integer userId, MultipartFile multipartFile) {
        return userService.updateIco(userId, multipartFile);
    }

    @GetMapping(value = "/update/token")
    @ApiOperation(value = "更新token")
    public ResultVO updateToken(@RequestHeader Integer userId) {
        ResultVO result = new ResultVO<>();
        result.setResponseCode(ResponseCode.SUCCESS);
        result.setToken(TokenUtils.getToken(userId));
        return result;
    }

    @GetMapping("/getCompanyIdById/{userId}")
    public ResultVO<Integer> getCompanyIdById(@PathVariable Integer userId) {
        return userService.getCompanyIdById(userId);
    }

    @GetMapping("/getNameById/{userId}")
    public ResultVO<String> getNameById(@PathVariable Integer userId) {
        return userService.getNameById(userId);
    }

    @PutMapping(value = "/update/password")
    @ApiOperation(value = "更新用户密码")
    public ResultVO<User> updatePassword(User user, @RequestHeader Integer userId, String newPassword) throws Exception {
        return userService.updatePassword(user, userId, newPassword);
    }

    @PutMapping(value = "/freeze/{targetId}")
    @ApiOperation(value = "冻结用户")
    public ResultVO freeze(@RequestHeader Integer userId, @PathVariable Integer targetId) {
        return userService.freeze(userId, targetId);
    }

    @PutMapping(value = "/unfreeze/{targetId}")
    @ApiOperation(value = "账户解冻")
    public ResultVO unfreeze(@RequestHeader Integer userId, @PathVariable Integer targetId) {
        return userService.unfreeze(userId, targetId);
    }

    @GetMapping("/secret/isManager/{userId}")
    public Boolean isManager(@PathVariable Integer userId) {
        return userService.isManager(userId);
    }

    @GetMapping("/isEnterprise/{userId}")
    public ResultVO<Boolean> isEnterprise(@PathVariable Integer userId) {
        return userService.isEnterprise(userId);
    }

    @PutMapping("/secret/upgrade/enterprise/{id}")
    public Boolean upgradeEnterprise(@PathVariable Integer id, @RequestHeader Integer userId, Boolean isManager, Integer companyId) {
        return userService.upgradeEnterprise(userId, id, isManager, companyId);
    }
}
