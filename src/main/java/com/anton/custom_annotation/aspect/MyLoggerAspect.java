/**
 * Copyright (c) 2025 nadeeshan_fdz. All rights reserved.
 * <p>
 * This file is part of the Spring Boot Project and may not be
 * copied, modified, or distributed without permission.
 * <p>
 * Author: nadeeshan_fdz
 * Date: 11/05/2025
 */

package com.anton.custom_annotation.aspect;

import com.anton.custom_annotation.annotation.MyLogger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Author by nadeeshan_fdz, Date = "11/05/2025"
 */
@Aspect
@Component
public class MyLoggerAspect {
    private static final Logger log = LoggerFactory.getLogger(MyLoggerAspect.class);
    @Around("@annotation(myLogger)")
    public Object logExecution(ProceedingJoinPoint joinPoint, MyLogger myLogger) throws Throwable {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        log.info("▶️ Started: {}.{} | Args: {}", className, methodName, Arrays.toString(args));

        try {
            Object result = joinPoint.proceed();
            log.info("✅ Finished: {}.{} | Status: SUCCESS", className, methodName);
            return result;
        } catch (Throwable e) {
            log.error("❌ Exception in {}.{} | {}", className, methodName, e.getMessage(), e);
            throw e;
        }
    }
}
