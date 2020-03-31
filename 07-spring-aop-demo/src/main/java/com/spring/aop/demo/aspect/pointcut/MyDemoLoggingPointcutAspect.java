package com.spring.aop.demo.aspect.pointcut;

import com.spring.aop.demo.pojo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingPointcutAspect {

    private static Logger log = LoggerFactory.getLogger(MyDemoLoggingPointcutAspect.class);

    @Around("execution(* com.spring.aop.demo.service.*.getFortune(..))")
    public Object aroundGetFortune(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.info("Executing @Around advice on getFortune(..) method");

        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        log.info("Method signature: {}", methodSignature);

        long begin = System.currentTimeMillis();

        Object result;

        try {
            result = proceedingJoinPoint.proceed();
        } catch (Exception exc) {
            log.warn(exc.getMessage());

            result = "Traffic problem solved!";
        }

        long end = System.currentTimeMillis();

        long duration = end - begin;

        log.info("Method execution duration: {} seconds", duration / 1000.0);

        return result;
    }

    @After("execution(* com.spring.aop.demo.copydao.CopyAccountDAO.findAccounts(..))")
    public void afterFinallyFindAccountsAdvice(JoinPoint joinPoint) {
        log.info("Executing @After advice on findAccounts(..) method " +
                "[IF METHOD EXECUTED WITH OR WITHOUT EXCEPTION]");

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        log.info("Method signature:{}", methodSignature);
    }

    @AfterThrowing(
            pointcut = "execution(* com.spring.aop.demo.copydao.CopyAccountDAO.findAccounts(..))",
            throwing = "exception")
    public void afterThrowingFindAccountsAdvice(JoinPoint joinPoint, Throwable exception) {
        log.info("Executing @AfterThrowing advice on findAccounts(..) method exception");

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        log.info("Method signature:{}", methodSignature);

        log.info("Exception:{}", exception.toString());
    }

    @AfterReturning(
            pointcut = "execution(* com.spring.aop.demo.copydao.CopyAccountDAO.findAccounts(..))",
            returning = "accounts")
    public void afterReturningFindAccountsAdvice(JoinPoint joinPoint, List<Account> accounts) {

        log.info("Executing @AfterReturning advice on any findAccounts(..)" +
                " method with none or many params " +
                "[ONLY IF METHOD EXECUTED WITHOUT EXCEPTION]");

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        log.info("Method signature:{}", methodSignature);

        log.info("findAccounts() result:{}", accounts);

        accounts.forEach(account -> account.setName(account.getName().toUpperCase()));

        log.info("findAccounts() after modifying result:{}", accounts);
    }

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
