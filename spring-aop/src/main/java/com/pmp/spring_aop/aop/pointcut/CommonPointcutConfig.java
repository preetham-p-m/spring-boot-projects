package com.pmp.spring_aop.aop.pointcut;

import org.aspectj.lang.annotation.Pointcut;

public class CommonPointcutConfig {

    @Pointcut("execution(* com.pmp.spring_aop.*..*.*(..))")
    public void allAspectConfig() {
    }

    @Pointcut("bean(*Service*)")
    public void beanContainingService() {
    }

    @Pointcut("@annotation(com.pmp.spring_aop.aop.annotation.TrackTime)")
    public void annotationTimeTrack() {
    }
}
