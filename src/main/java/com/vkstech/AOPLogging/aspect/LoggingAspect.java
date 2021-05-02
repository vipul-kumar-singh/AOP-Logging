package com.vkstech.AOPLogging.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Pointcut("(within(com.vkstech.AOPLogging.controller..*) || within(com.vkstech.AOPLogging.service..*)) && !@target(com.vkstech.AOPLogging.annotation.NoLogging)")
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
        List<String> sigParamNames = Arrays.asList(methodSignature.getParameterNames());
        List<Object> signatureArgs = Arrays.asList(joinPoint.getArgs());

        Map<String, Object> requestMap = IntStream.range(0, sigParamNames.size()).boxed()
                .collect(Collectors.toMap(sigParamNames::get, signatureArgs::get));

        requestMap.forEach((key, value) -> log.info("{} : {}", key, value));

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
