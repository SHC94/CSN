package com.csn.csn.comm;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Aspect
@Component
public class LogAspect {

    //@Around("execution(* com.csn.csn..*ServiceImpl.*(..))")
    @Around("execution(* com.csn.csn..*ServiceImpl.select*(..))")
    public Object loggingAround(ProceedingJoinPoint pjp) throws Throwable {
        Object result = pjp.proceed();
        log.info("==> LogAspect Root :: " + pjp.getSignature().getDeclaringTypeName());
        log.info("==> LogAspect Method :: " + pjp.getSignature().getName());
        dateTimeLogging("Around");
        return result;
    }

    @Before("execution(* com.csn.csn..*ServiceImpl.select*(..))")
    public void loggingBefore() throws Throwable {
        dateTimeLogging("Before");
    }


    @After("execution(* com.csn.csn..*ServiceImpl.select*(..))")
    public void loggingAfter() throws Throwable {
        dateTimeLogging("After");
    }

    public void dateTimeLogging(String joinPoint) {
        Date now = new Date();
        StringBuilder print = new StringBuilder();

        if("Around".equals(joinPoint)) {
            print.append("Around ==========================>").append("\n");
        } else if("After".equals(joinPoint)) {
            print.append("After ==========================>").append("\n");
        } else if("Before".equals(joinPoint)) {
            print.append("Before ==========================>").append("\n");
        }//end if()

        print.append(now.toString());
        System.out.println(print.toString());
    }

}
