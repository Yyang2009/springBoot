package com.example.lucklymoney.repository;

import com.example.lucklymoney.domain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {
    UserInfo findByUserName(String userName);
}
