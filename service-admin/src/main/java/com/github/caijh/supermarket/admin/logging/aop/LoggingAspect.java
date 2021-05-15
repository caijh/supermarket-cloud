package com.github.caijh.supermarket.admin.logging.aop;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void controller() {
        // Do nothing.
    }

    @Pointcut("execution(public * *(..))")
    public void publicMethod() {
        // Do nothing.
    }

    /**
     * logger info before request.
     *
     * @param joinPoint JoinPoint
     */
    @Before(value = "controller() && publicMethod()")
    public void before(JoinPoint joinPoint) {
        LoggingAspect.LOGGER.debug("Entering in Method: {}", joinPoint.getSignature().getName());
        LoggingAspect.LOGGER.debug("Class Name: {}", joinPoint.getSignature().getDeclaringTypeName());
        String reqBody = JSON.toJSONString(joinPoint.getArgs());
        LoggingAspect.LOGGER.debug("Arguments: {}", reqBody);
        LoggingAspect.LOGGER.debug("Target class: {}", joinPoint.getTarget().getClass().getName());

        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        String requestMethod = request.getMethod();
        String contextPath = request.getServletPath();
        LoggingAspect.LOGGER
            .info("Method Type: {}, Request Path info: {}, Request Body: {}", requestMethod, contextPath, reqBody);
    }

    @AfterReturning(pointcut = "controller() && publicMethod()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        String respBody = JSON.toJSONString(result);
        String methodName = joinPoint.getSignature().getName();
        LoggingAspect.LOGGER.info("Method {} Return value : {}", methodName, respBody);
    }

    @AfterThrowing(pointcut = "controller() && publicMethod()", throwing = "exception")
    public void afterThrowing(JoinPoint joinPoint, Throwable exception) {
        LoggingAspect.LOGGER.error("An exception has been thrown in {}()", joinPoint.getSignature().getName());
        LoggingAspect.LOGGER.error("Cause : {}", exception.getCause());
    }

    /**
     * around logging.
     *
     * @param joinPoint ProceedingJoinPoint
     * @return controller method result.
     * @throws Throwable exception, if controller method execute fail.
     */
    @Around("controller() && publicMethod()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        try {
            String className = joinPoint.getSignature().getDeclaringTypeName();
            String methodName = joinPoint.getSignature().getName();
            Object result = joinPoint.proceed();
            long elapsedTime = System.currentTimeMillis() - start;
            LoggingAspect.LOGGER.debug("Method {}.{}() execution time : {}ms", className, methodName, elapsedTime);
            return result;
        } catch (IllegalArgumentException e) {
            LoggingAspect.LOGGER.error("Illegal argument " + Arrays.toString(joinPoint.getArgs()) + " in "
                + joinPoint.getSignature().getName() + "()");
            throw e;
        }
    }

}
