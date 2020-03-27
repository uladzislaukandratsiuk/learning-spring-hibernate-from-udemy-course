package com.spring.aop.demo.aspect.pointcut;

import com.spring.aop.demo.aspect.MyDemoLoggingAspect;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingPointcutAspect {

    private static Logger log = LoggerFactory.getLogger(MyDemoLoggingAspect.class);

    @Pointcut("execution(* com.spring.aop.demo.copydao.*.*(..))")
    private void forCopyDAOPackage() {
    }

    @Pointcut("execution(* com.spring.aop.demo.copydao.*.get*(..))")
    private void getter() {
    }

    @Pointcut("execution(* com.spring.aop.demo.copydao.*.set*(..))")
    private void setter() {
    }

    @Pointcut("forCopyDAOPackage() && !(getter() || setter())")
    private void forCopyDaoPackageNoGetterSetter() {
    }

    @Before("forCopyDaoPackageNoGetterSetter()")
    public void beforeAnyMethodInPackageAdvice() {
        log.info("Executing @Before advice on any method in com.spring.aop.demo.copydao.* package");
    }
}
