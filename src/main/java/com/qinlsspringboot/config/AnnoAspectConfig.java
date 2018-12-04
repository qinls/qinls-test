package com.qinlsspringboot.config;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2018/6/25.
 */

/**
 * 定义切面配置类
 */
@Aspect
@Component
public class AnnoAspectConfig {

    // 标注注解拦截
    @Pointcut("@annotation(com.qinlsspringboot.anno.AdminOnly)")
    public void matchAnno() {
    }

    @Before("matchAnno()")
    public void befor() {
        System.out.println();
        System.out.println("########befor");
    }

    // 根据方法匹配  *所有返回值类型  ..任意参数
    @Pointcut("execution(public * com.qinlsspringboot.service.impl.*Service.*(..))")
    public void mathchEx() {
    }

    // args 传入的参数
    @Before("mathchEx() && args(longId)" )
    public void ex(Long longId) {
        System.out.println();
        System.out.println("方法exexution 拦截:" + longId);
    }


}
