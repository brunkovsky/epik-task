package org.epik.annotation;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Aspect
@Component
public class LogTimeExecutionAspect {

    @Value("${logging.logTimeExec:false}")
    private boolean logTimeExec;


    @Around("@annotation(LogTimeExecution)")
    public Object showResult(ProceedingJoinPoint joinPoint) throws Throwable {
        if (logTimeExec) {
            long start = new Date().getTime();
            Object result = joinPoint.proceed();
            long end = new Date().getTime();
            log.info(joinPoint.getSignature().getName() + ": " + (end - start) + "ms");
            return result;
        } else {
            return joinPoint.proceed();
        }

    }

}
