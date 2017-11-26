package com.corp.coooldh.example.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DBConnectionAOP {

//    @Pointcut(value="execution(public * *(..))")
//    public void anyPublicMethod() { }

    @Around("@annotation(connectToDB)")
    public Object proceed(ProceedingJoinPoint pjp, ConnectToDB connectToDB) throws Throwable {
        try {

            if(connectToDB.value().equals("MASTER")) {
                System.out.println("Master DB 설정 입니다");
                DBContextHolder.setDbType(DbType.MASTER);
            } else {
                System.out.println("Slave DB 설정 입니다");
                DBContextHolder.setDbType(DbType.SLAVE);
            }

            Object result = pjp.proceed();
            DBContextHolder.clearDbType();
            return result;
        } finally {
            DBContextHolder.clearDbType();
        }
    }
}
