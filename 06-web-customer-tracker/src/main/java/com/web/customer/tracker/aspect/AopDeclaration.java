package com.web.customer.tracker.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AopDeclaration {

    @Pointcut("execution(* com.web.customer.tracker.controller.*.*(..))")
    private void forControllerPackage() {
    }

    @Pointcut("execution(* com.web.customer.tracker.service.*.*(..))")
    private void forServicePackage() {
    }

    @Pointcut("execution(* com.web.customer.tracker.dao.*.*(..))")
    private void forDaoPackage() {
    }

    @Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
    public void forAppFlow() {
    }
}
