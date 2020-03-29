package com.spring.aop.demo.aspect.pointcut;

import com.spring.aop.demo.aspect.MyDemoLoggingAspect;
import com.spring.aop.demo.pojo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingPointcutAspect {

    private static Logger log = LoggerFactory.getLogger(MyDemoLoggingAspect.class);

    @Before("com.spring.aop.demo.aspect.pointcut.AopDeclaration.forCopyDaoPackageNoGetterSetter()")
    public void beforeAnyMethodInPackageAdvice(JoinPoint joinPoint) {
        log.info("Executing @Before advice on any method in com.spring.aop.demo.copydao.* package");

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        log.info("Method signature:{}", methodSignature);

        Object[] args = joinPoint.getArgs();

        for (Object arg : args) {
            log.info("Method argument:{}", arg);

            if (arg instanceof Account) {
                Account account = (Account) arg;
                log.info("Method argument:{}", account.getName());
                log.info("Method argument:{}", account.getLevel());
            }
        }
    }
}
