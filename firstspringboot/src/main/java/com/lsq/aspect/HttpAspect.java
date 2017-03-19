package com.lsq.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2017-3-19.
 */
@Aspect
@Component
public class HttpAspect {
    /**
     * org.slf4j.Logger spring 自带的一个log框架
     */
    private final static Logger log= LoggerFactory.getLogger(HttpAspect.class);

    @Pointcut("execution(public * com.lsq.controller.GirlController.*(..))")
    public void log() {

    }

    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
       log.warn("方法之前执行");
        ServletRequestAttributes attributes=(ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        log.info("url={}",request.getRequestURL());
        log.info("method={}",request.getMethod());
        log.info("ip={}",request.getRemoteAddr());
        log.info("class_method={}",joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName());
        log.info("args={}",joinPoint.getArgs());
    }

    @After("log()")
    public void doAfter() {
       log.info("22222");
    }

    @AfterReturning(returning = "object",pointcut = "log()")
    public void getReturning(Object object){
        log.warn("response={}",object);
    }
}
