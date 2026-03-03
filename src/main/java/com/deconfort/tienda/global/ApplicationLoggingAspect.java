package com.deconfort.tienda.global;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class ApplicationLoggingAspect {

    @Pointcut(
            "(within(@org.springframework.stereotype.Service *) || " +
                    "within(@org.springframework.stereotype.Component *)) && " +
                    "!within(@org.springframework.web.bind.annotation.RestController *) && " +
                    "!within(@org.springframework.stereotype.Controller *) && " +
                    "!within(@jakarta.persistence.Entity *) && " +
                    "!within(@org.springframework.boot.context.properties.ConfigurationProperties *) && " +
                    "!within(org.springframework.web.filter.GenericFilterBean+) && " +
                    "!within(jakarta.servlet.Filter+)"
    )
    public void applicationBeans() {}


    @Before("applicationBeans()")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println(">> " +
                joinPoint.getSignature().getDeclaringTypeName() +
                "." +
                joinPoint.getSignature().getName());

        System.out.println("   Args: " +
                Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(pointcut = "applicationBeans()", returning = "result")
    public void logAfter(JoinPoint joinPoint, Object result) {
        System.out.println("<< Finished: " +
                joinPoint.getSignature().getName());
    }

    @AfterThrowing(pointcut = "applicationBeans()", throwing = "ex")
    public void logError(JoinPoint joinPoint, Throwable ex) {
        System.out.println("!! Error in: " +
                joinPoint.getSignature().getName());

        System.out.println("   Exception: " +
                ex.getMessage());
    }
}