package com.example.wsmeverylisten.proxy.jdk.LogTest;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;

//定义一个切面
@Aspect
public class AfterReturningAdviceTest {

    @AfterReturning(returning = "rvt",
    pointcut = "execution(* com.example.wsmeverylisten.proxy.jdk.LogTest.*.*(..))")
    public void log(Object rvt) {
        System.out.println("获取目标方法返回值:" + rvt);
        System.out.println("模拟记录日志功能 ...");
    }

}
