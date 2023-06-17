package com.example.aop.demoaop.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Pointcut("@annotation(com.example.aop.demoaop.interfaces.Log)")
    public void logPointcut() {
    }

    @Before("logPointcut()")
    public void logAllMethodCallsAdvice() {
        System.out.println("In Aspect");
    }

    @Pointcut("@annotation(com.example.aop.demoaop.interfaces.AfterLog)")
    public void logAfterPointcut(){}

    @After("logAfterPointcut()")
    public void logMethodCallsAfterAdvice(JoinPoint joinPoint) {
        System.out.println("In After Aspect " + joinPoint.getSignature().getName());
    }
}
