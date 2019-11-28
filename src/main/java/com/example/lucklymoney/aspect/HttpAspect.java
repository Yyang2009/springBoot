package com.example.lucklymoney.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class HttpAspect {

    private final static Logger logger = LoggerFactory.getLogger(HttpAspect.class);

    @Pointcut("within(com.example.lucklymoney.controller.*)")
    public void log() {
    }

    @Before("log()")
    public void deBefore() {
        logger.info("111111111");
    }

    @After("log()")
    public void deAfter() {
        logger.info("222222222");
    }
}
