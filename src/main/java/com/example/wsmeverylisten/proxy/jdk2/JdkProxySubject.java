package com.example.wsmeverylisten.proxy.jdk2;

import com.example.wsmeverylisten.proxy.staticProxy.Subject;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class JdkProxySubject implements InvocationHandler {

    private Subject subject;

    public JdkProxySubject(Subject subject) {
        this.subject = subject;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("before 前置通知");
        Object result = null;

        result = method.invoke(subject, args);

        System.out.println("after 后置通知");

        return result;
    }
}
