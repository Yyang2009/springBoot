package com.example.lucklymoney.service;

import com.example.lucklymoney.domain.Luckymoney;
import com.example.lucklymoney.repository.LuckymoneyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@Service
public class LuckymoneyService {
    @Autowired
    private LuckymoneyRepository repository;

    /**
     * 事务 指数据库的事务 mysql 有的引擎不支持事务
     */
    @Transactional
    public void createTwo() {
        Luckymoney luckymoney1 = new Luckymoney();
        luckymoney1.setProducer("haha");
        luckymoney1.setMoney(new BigDecimal("520"));
        repository.save(luckymoney1);

        Luckymoney luckymoney2 = new Luckymoney();
        luckymoney2.setProducer("haha1");
        luckymoney2.setMoney(new BigDecimal("131"));
        repository.save(luckymoney2);
    }
}
