package com.example.wsmeverylisten.proxy.jdk;


import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class HumanAspect {


    // 为Student这个类的所有方法，配置这个前置通知
    @Before("execution(* com.example.wsmeverylisten.proxy.jdk.Student.*(..))")
    public void before() {
        System.out.println("before student");
    }

}
