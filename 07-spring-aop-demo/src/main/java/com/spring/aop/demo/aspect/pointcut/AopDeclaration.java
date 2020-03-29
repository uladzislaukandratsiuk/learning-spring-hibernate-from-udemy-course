package com.spring.aop.demo.aspect.pointcut;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AopDeclaration {

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
    public void forCopyDaoPackageNoGetterSetter() {
    }
}
