package com.example.lucklymoney.api;

import com.alibaba.fastjson.JSONObject;
import com.example.lucklymoney.annotation.PassToken;
import com.example.lucklymoney.annotation.UserLoginToken;
import com.example.lucklymoney.domain.UserInfo;
import com.example.lucklymoney.service.TokenService;
import com.example.lucklymoney.service.UserInfoService;
import com.example.lucklymoney.utils.resultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Api("用户测试")
@RestController
@RequestMapping("/api")
public class UserApi {
    @Autowired
    UserInfoService userInfoService;
    @Autowired
    TokenService tokenService;

    // 登陆
    @PostMapping("/loginByJWT")
    public Object login(@RequestBody UserInfo user) {
        JSONObject jsonObject = new JSONObject();
        UserInfo userInfo = userInfoService.findUserByName(user.getUserName());
        if (userInfo == null) {
            return resultUtils.error(1, "登陆失败，用户不存在");
        } else {
            if (!userInfo.getPassWord().equals(user.getPassWord())) {
                return resultUtils.error(1, "登陆失败，密码错误");
            } else {
                String token = tokenService.getToken(userInfo);
                jsonObject.put("token", token);
                jsonObject.put("user", userInfo);
                return jsonObject;
            }
        }
    }

    @ApiOperation("测试登录")
    @UserLoginToken
    @GetMapping("/getMessage")
    public String getMessage() {
        return "你已经通过验证";
    }

    @PassToken
    @PostMapping("/loginBySession")
    public UserInfo login1(@RequestBody UserInfo user,  @ApiIgnore HttpServletRequest request) {
        UserInfo userInfo = userInfoService.findUserByName(user.getUserName());
        if (userInfo != null) {
            HttpSession session = request.getSession();
            session.setAttribute(session.getId(), userInfo);
        }
        return userInfo;
    }

//    @ApiOperation("测试获取redis信息")
//    @PostMapping("/loginBySession")
//    public UserInfo login2(@ApiIgnore HttpSession session) {
//        UserInfo userInfo = (UserInfo) session.getAttribute(session.getId());
//        System.out.println(userInfo);
//        return userInfo;
//    }

    @PassToken
    @GetMapping("/logout")
    public String logout(@ApiIgnore HttpSession session) {
        session.removeAttribute(session.getId());
        return "user logout success";
    }
}
