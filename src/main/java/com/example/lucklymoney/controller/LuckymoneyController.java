package com.example.lucklymoney.controller;

import com.example.lucklymoney.domain.Luckymoney;
import com.example.lucklymoney.domain.Result;
import com.example.lucklymoney.repository.LuckymoneyRepository;
import com.example.lucklymoney.service.LuckymoneyService;
import com.example.lucklymoney.utils.resultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class LuckymoneyController {

    @Autowired
    private LuckymoneyRepository repository;

    @Autowired
    private LuckymoneyService service;

    /**
     * 获取红包列表
     */
    @CrossOrigin
    @GetMapping("/luckymoneys")
    public List<Luckymoney> list() {
        return repository.findAll();
    }

    /**
     * 创建红包
     */
    @PostMapping("/luckymoneys")
    public Result<Luckymoney> create(@Valid Luckymoney luckymoney, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            return resultUtils.error(1, bindingResult.getFieldError().getDefaultMessage());
        }

        luckymoney.setProducer(luckymoney.getProducer());
        luckymoney.setMoney(luckymoney.getMoney());
        return resultUtils.success(repository.save(luckymoney));
    }

    /**
     * 通过id查询红包
     */
    @GetMapping("/luckymoneys/{id}")
    public Luckymoney findById(@PathVariable("id") Integer id) {
        return repository.findById(id).orElse(null);
    }

    /**
     * 更新红包（领红包）
     */
    @PutMapping("/luckymoneys/{id}")
    public Luckymoney update(@PathVariable("id") Integer id,
                             @RequestParam("consumer") String consumer) {
        Optional<Luckymoney> optional = repository.findById(id);
        if(optional.isPresent()) {
            Luckymoney luckymoney = new Luckymoney();
            luckymoney.setId(id);
            luckymoney.setConsumer(consumer);
            return repository.save(luckymoney);
        }
        return null;
    }

    @PostMapping("/luckymoneys/two")
    public void createTwo() {
        service.createTwo();
    }

}
