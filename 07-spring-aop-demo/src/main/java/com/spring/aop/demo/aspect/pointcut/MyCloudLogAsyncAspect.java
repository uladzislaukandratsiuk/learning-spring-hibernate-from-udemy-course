package com.spring.aop.demo.aspect.pointcut;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1)
public class MyCloudLogAsyncAspect {

    private static Logger log = LoggerFactory.getLogger(MyCloudLogAsyncAspect.class);

    @Before("com.spring.aop.demo.aspect.pointcut.AopDeclaration.forCopyDaoPackageNoGetterSetter()")
    public void logToCloudAsync() {
        log.info("Logging to Cloud in async fashion");
    }
}
