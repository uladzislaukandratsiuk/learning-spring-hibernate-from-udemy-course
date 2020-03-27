package com.spring.aop.demo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {

    private static Logger log = LoggerFactory.getLogger(MyDemoLoggingAspect.class);

    @Before("execution(* add*())")
    public void beforeAddAccountAdvice() {
        log.info("Executing @Before advice on any add*() method without params");
    }

    @Before("execution(* add*(com.spring.aop.demo.pojo.Account))")
    public void beforeAddAccountWithParamsAdvice() {
        log.info("Executing @Before advice on any add*(Account account) method with params");
    }

    @Before("execution(* add*(com.spring.aop.demo.pojo.Account, ..))")
    public void beforeAddAccountsWithFewParamsAdvice() {
        log.info("Executing @Before advice on any add*(Account account, ..) method with few params");
    }
}
