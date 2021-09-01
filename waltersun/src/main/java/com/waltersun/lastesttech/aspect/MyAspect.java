package com.waltersun.lastesttech.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;

/**
 * @author walter
 * @date 2021-09-01 10:59
 */
@Component
@Aspect
@Slf4j
public class MyAspect {

    @Pointcut("@annotation(com.waltersun.lastesttech.annotation.MyAnnotation)")
    private void pointCut() {
    }

    @Before("pointCut()")
    public void beforeAdvice() {
        log.debug("aspect before");
    }

    @After("pointCut()")
    public void afterAdvice() {
        log.debug("aspect after");
    }

    /**
     * around 的优先级高于 before 和 after
     */
    @Around("pointCut()")
    public void aroundAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.debug("aspect around front");
        Object obj = proceedingJoinPoint.proceed();
        log.debug("aspect around end :{}",JSON.toJSONString(obj));
    }
}
