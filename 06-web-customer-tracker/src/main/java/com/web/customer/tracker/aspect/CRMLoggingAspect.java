package com.web.customer.tracker.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CRMLoggingAspect {

    private static Logger log = LoggerFactory.getLogger(CRMLoggingAspect.class);

    @Before("com.web.customer.tracker.aspect.AopDeclaration.forAppFlow()")
    public void before(JoinPoint joinPoint) {

        String methodSignature = joinPoint.getSignature().toShortString();
        log.info("@Before advice on method: {}", methodSignature);

        Object[] args = joinPoint.getArgs();

        for (Object arg : args) {
            log.info("argument: {}", arg);
        }
    }

    @AfterReturning(
            pointcut = "com.web.customer.tracker.aspect.AopDeclaration.forAppFlow()",
            returning = "result"
    )
    public void afterReturning(JoinPoint joinPoint, Object result) {

        String methodSignature = joinPoint.getSignature().toShortString();
        log.info("@AfterReturning advice on method: {}", methodSignature);

        log.info("Result: {}", result);
    }
}
