package com.example.wsmeverylisten.proxy.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class DaoProxy implements MethodInterceptor {


    @Override
    public Object intercept(Object Object, Method method, Object[] objects, MethodProxy proxy) throws Throwable {

        System.out.println("Before method invoke");

        proxy.invokeSuper( Object, objects);

        System.out.println("After method invoke");

        return null;
    }



}
