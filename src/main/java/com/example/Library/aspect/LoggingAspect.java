package com.example.Library.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.example.Library.service.*.*(..))")
    public void serviceLayer() {}
    long startTime;

    @Before("serviceLayer()")
    public void logBefore(JoinPoint joinPoint) {
        /*logger.info("Before method: " + joinPoint.getSignature().getName());*/
        startTime = System.currentTimeMillis();
    }

    @After("serviceLayer()")
    public void logAfter(JoinPoint joinPoint) {
        long executionTime = System.currentTimeMillis() - startTime;
        logger.info("Method: " + joinPoint.getSignature().getName() + " " + executionTime + " ms");

    }
}
