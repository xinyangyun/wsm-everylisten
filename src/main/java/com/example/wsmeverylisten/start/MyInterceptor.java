package com.example.wsmeverylisten.start;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class MyInterceptor implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("------插入前置通知代码-------------");
        // 此处一定要使用proxy的invokeSuper方法来调用目标类的方法
        methodProxy.invokeSuper(o, objects);
        System.out.println("------插入后置处理代码-------------");
        return null;
    }
}