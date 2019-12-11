package com.example.lucklymoney.controller;

import com.example.lucklymoney.domain.Result;
import com.example.lucklymoney.domain.UserInfo;
import com.example.lucklymoney.repository.UserInfoRepository;
import com.example.lucklymoney.service.UserInfoService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Api("用户测试")
@RestController
public class UserInfoController {


    @Autowired
    private UserInfoService service;

    /*
    *  获取所有用户
    */
    @GetMapping("/users")
    public Result<UserInfo> list() {
        return service.list();
    }

    /*
    *
    *  创建用户
    * */
    @PostMapping("/users")
    public Result<UserInfo> create(UserInfo userInfo) {
        return service.create(userInfo);
    }

    /*
    *  修改密码
    * */
//    @PutMapping("/users/{id}")
//    public Result<UserInfo> update(@PathVariable("id") Integer id,
//                                   @RequestParam("password") String password,
//                                   @RequestParam("username") String username)
//    {
//        return service.update(id, password, username);
//    }

    /*
    *
    * */

}
