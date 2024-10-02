package com.youngit.office.aop;

import io.swagger.v3.oas.annotations.Operation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

@Aspect
public class Aop {

    private static final Logger logger = LoggerFactory.getLogger(Aop.class);


    @Around("execution(* com.youngit.office.api.*.service..*(..))")
    public Object timeTrace(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START!: " + joinPoint.toString());
        try {
            Object result = joinPoint.proceed();
            return result;
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString() + " " + timeMs + "ms");
        }
    }

    @Around("execution(* com.youngit.office.api.*.controller..*(..))")
    public Object logging(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().toShortString();

        // 메서드에서 @Operation 어노테이션 찾기
        String operationSummary = getOperationSummary(joinPoint);
        logger.info((operationSummary != null ? " - " + operationSummary : " - not provided operation summary"));

        Object result = joinPoint.proceed();
        return result;

    }

    private String getOperationSummary(ProceedingJoinPoint joinPoint) {
        try {
            // 메서드의 이름과 인자에 대한 정보를 가져옵니다.
            Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
            Operation operation = method.getAnnotation(Operation.class);
            if (operation != null) {
                return operation.summary();
            }
        } catch (Exception e) {
            logger.error("Failed to retrieve @Operation summary: ", e);
        }
        return null;
    }

}
