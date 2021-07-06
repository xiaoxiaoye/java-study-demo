package com.example.mybatiscommonmapperspringbootdemo.controller;

import com.example.mybatiscommonmapperspringbootdemo.common.Status;
import com.example.mybatiscommonmapperspringbootdemo.exception.UserNotFoundException;
import com.example.mybatiscommonmapperspringbootdemo.model.ApiResponse;
import com.example.mybatiscommonmapperspringbootdemo.pojo.UserVO;
import com.example.mybatiscommonmapperspringbootdemo.service.UserService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@Api(tags = "UserController", value = "用户管理")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/{id}")
    @ApiOperation(value = "条件查询", notes = "备注")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "用户ID", dataType = "int", paramType = "path", defaultValue = "0")})
    public UserVO getUser(@PathVariable int id) {
        UserVO user = userService.getUserByID(id);
        if (user == null) {
            throw new UserNotFoundException(Status.UNKNOWN);
        }
        log.info("get user:{}", user);
        return user;
    }

    @DeleteMapping("/user/{id}")
    @ApiOperation(value = "删除用户", notes = "按用户id删除用户")
    public ApiResponse deleteUser(@ApiParam(value = "用户id", defaultValue = "0") @PathVariable int id) {
       userService.deleteByID(id);
       return ApiResponse.markSuccess();
    }

    @PostMapping("/user")
    @ApiOperation(value = "创建用户", notes = "创建用户")
    public ApiResponse createUser(@ApiParam(value = "用户实例") @RequestBody UserVO user) {
        UserVO user1 = userService.save(user);
        return ApiResponse.ofSuccess(user1);
    }
}
