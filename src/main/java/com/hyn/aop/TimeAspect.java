package com.hyn.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
@Aspect
@Component
public class TimeAspect {
    @Around("execution(* com.hyn.service.*.*(..))")
    public Object TimeAspect(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = pjp.proceed();
        long end = System.currentTimeMillis();
//        System.out.println(end-start+"ms");
        return result;
    }
}
