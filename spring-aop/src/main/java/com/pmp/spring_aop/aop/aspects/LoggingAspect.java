package com.pmp.spring_aop.aop.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import com.pmp.spring_aop.constant.AspectConstants;

@Configuration
@Aspect
public class LoggingAspect {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Before(AspectConstants.ALL_ASPECT_CONFIG)
    public void logBeforeExecution(JoinPoint joinPoint) {
        this.logger.trace("{} method is called with {} arguments", joinPoint, joinPoint.getArgs());
    }

    @AfterReturning(pointcut = AspectConstants.ALL_ASPECT_CONFIG, returning = "returnObject")
    public void logAfterReturning(JoinPoint joinPoint, Object returnObject) {
        this.logger.trace("{} method returned {}", joinPoint, returnObject);
    }

    @AfterThrowing(pointcut = AspectConstants.ALL_ASPECT_CONFIG, throwing = "exception")
    public void logAfterThrowingError(JoinPoint joinPoint, Exception exception) {
        this.logger.error("{} method thrown exception {}", joinPoint, exception);
    }
}
