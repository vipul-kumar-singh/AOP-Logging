package com.vkstech.AOPLogging.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.stream.IntStream;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Pointcut("within(com.vkstech.AOPLogging.controller..*) || within(com.vkstech.AOPLogging.service..*)")
    public void logMethodsPointcut() {
    }

    @Before("logMethodsPointcut()")
    public Object profileAllMethods(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        //Get intercepted method details
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();

        //Log class and method
        log.info("{} :: {}", className, methodName);

        //log parameters
        String[] sigParamNames = methodSignature.getParameterNames();
        Object[] signatureArgs = joinPoint.getArgs();

        IntStream.range(0, sigParamNames.length)
                .forEach(i -> log.info("{} : {}", sigParamNames[i], signatureArgs[i]));

        return joinPoint;
    }

    //    @Around("logMethodsPointcut()")
//    public Object profileAllMethods(ProceedingJoinPoint proceedingJoinPoint) throws Throwable
//    {
//        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
//
//        //Get intercepted method details
//        String className = methodSignature.getDeclaringType().getSimpleName();
//        String methodName = methodSignature.getName();
//
//        final StopWatch stopWatch = new StopWatch();
//
//        //Measure method execution time
//        stopWatch.start();
//        Object result = proceedingJoinPoint.proceed();
//        stopWatch.stop();
//
//        //Log method execution time
//        log.info(className + "::" + methodName + " :: " + stopWatch.getTotalTimeMillis() + " ms");
//
//        return result;
//    }
}
