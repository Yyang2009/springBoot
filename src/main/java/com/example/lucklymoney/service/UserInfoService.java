package com.example.lucklymoney.service;

import com.example.lucklymoney.domain.Result;
import com.example.lucklymoney.domain.UserInfo;
import com.example.lucklymoney.repository.UserInfoRepository;
import com.example.lucklymoney.utils.resultUtils;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserInfoService {

    @Autowired
    private UserInfoRepository repository;

    public Result<UserInfo> list() {
        return resultUtils.success(repository.findAll());
    }

    public Result<UserInfo> create(UserInfo userInfo) {
        userInfo.setUserName(userInfo.getUserName());
        userInfo.setPassWord(userInfo.getPassWord());
        return resultUtils.success(repository.save(userInfo));
    }

//    public Result<UserInfo> update(String id, String password, String username) {
//        Optional<UserInfo> optional = repository.findById(id);
//
//        if(optional.isPresent()){
//            UserInfo userInfo = new UserInfo();
//            userInfo.setId(id);
//            userInfo.setPassword(password);
//            userInfo.setUsername(username);
//            return resultUtils.success(repository.save(userInfo));
//        }
//        return null;
//    }

    public UserInfo findUserById(String userId) {
        System.out.println(userId);
        return repository.findById(Integer.parseInt(userId)).orElse(null);
    }

    public UserInfo findUserByName(String username) {
        return repository.findByUserName(username);
    }
}
