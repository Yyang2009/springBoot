package com.example.lucklymoney.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.lucklymoney.annotation.PassToken;
import com.example.lucklymoney.annotation.UserLoginToken;
import com.example.lucklymoney.domain.UserInfo;
import com.example.lucklymoney.service.UserInfoService;
import org.apache.el.parser.AstFalse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class AuthenticetionInterceptor implements HandlerInterceptor {

    @Autowired
    UserInfoService userInfoService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");   // 从请求头中取出 token

        if(!(handler instanceof HandlerMethod)) {    // 如果不是直接映射到方法则直接通过
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        if(method.isAnnotationPresent(PassToken.class)){      // 检查是否有passtoken注释 有则跳过认证
            PassToken passToken = method.getAnnotation(PassToken.class);
            if(passToken.required()){
                return true;
            }
        }

        if(method.isAnnotationPresent(UserLoginToken.class)){
            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
            if(userLoginToken.required()){
                // 执行认证
                if(token == null) {
                    throw new RuntimeException("无效token，请重新登陆");
                }
                // 获取 token 中 user id
                String userId;
                try {
                    userId = JWT.decode(token).getAudience().get(0);
                }
                catch (JWTDecodeException j){
                    throw new RuntimeException("401");
                }
                UserInfo userInfo = userInfoService.findUserById(userId);
                if(userInfo == null) {
                    throw new RuntimeException("用户不存在，请重新登陆");
                }

                // 验证 token
                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(userInfo.getPassWord())).build();
                try {
                    jwtVerifier.verify(token);
                } catch (JWTVerificationException e) {
                    throw new RuntimeException("401");
                }
                return true;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
