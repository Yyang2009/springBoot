package com.example.lucklymoney.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.lucklymoney.annotation.PassToken;
import com.example.lucklymoney.annotation.UserLoginToken;
import com.example.lucklymoney.domain.Result;
import com.example.lucklymoney.domain.UserInfo;
import com.example.lucklymoney.service.TokenService;
import com.example.lucklymoney.service.UserInfoService;
import com.example.lucklymoney.utils.resultUtils;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api")
public class UserApi {
    @Autowired
    UserInfoService userInfoService;
    @Autowired
    TokenService tokenService;
    // 登陆
    @PostMapping("/loginByJWT")
    public Object login(@RequestBody UserInfo user)
    {
        JSONObject jsonObject = new JSONObject();
        UserInfo userInfo = userInfoService.findUserByName(user.getUserName());
        if(userInfo == null){
            return resultUtils.error(1, "登陆失败，用户不存在");
        } else {
            if(!userInfo.getPassWord().equals(user.getPassWord())){
                return resultUtils.error(1, "登陆失败，密码错误");
            } else {
                String token = tokenService.getToken(userInfo);
                jsonObject.put("token", token);
                jsonObject.put("user", userInfo);
                return jsonObject;
            }
        }
    }
    @UserLoginToken
    @GetMapping("/getMessage")
    public String getMessage() {
        return "你已经通过验证";
    }

    @PassToken
    @PostMapping("/loginBySession")
    public UserInfo login1(@RequestBody UserInfo user, HttpSession session) {
        UserInfo userInfo = userInfoService.findUserByName(user.getUserName());
        if(userInfo != null) {
            session.setAttribute(session.getId(), userInfo);
        }
        return userInfo;
    }

    @PassToken
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute(session.getId());
        return "user logout success";
    }
}
