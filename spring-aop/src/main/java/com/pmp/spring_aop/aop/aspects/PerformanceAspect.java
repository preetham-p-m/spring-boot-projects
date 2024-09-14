package com.pmp.spring_aop.aop.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import com.pmp.spring_aop.constant.AspectConstants;

@Configuration
@Aspect
public class PerformanceAspect {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Around(AspectConstants.ANNOTATION_TIME_TRACK)
    public Object findExecutionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTimeMillis = System.currentTimeMillis();

        Object returnValue = proceedingJoinPoint.proceed();

        long stopTimeMillis = System.currentTimeMillis();
        this.logger.info("{} took {} ms to execute", proceedingJoinPoint, stopTimeMillis - startTimeMillis);

        return returnValue;
    }

}
