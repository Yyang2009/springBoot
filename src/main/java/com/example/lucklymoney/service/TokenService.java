package com.example.lucklymoney.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.lucklymoney.domain.UserInfo;
import org.springframework.stereotype.Service;

@Service
public class TokenService {
    public String getToken(UserInfo userInfo) {
        String token = "";
        token = JWT.create().withAudience(String.valueOf(userInfo.getId())).sign(Algorithm.HMAC256(userInfo.getPassWord()));
        return token;
    }
}
