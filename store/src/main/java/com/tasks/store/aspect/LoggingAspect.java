package com.tasks.store.aspect;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger log = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(* com.tasks.store.service.*.*(..))")
    public void logMethodCall(JoinPoint joinPoint) {
        String method = joinPoint.getSignature().getName();
        log.info("Called: {}(), args: {}", method, Arrays.toString(joinPoint.getArgs()));
    }
}
