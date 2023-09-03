package com.example.wsmeverylisten.proxy.jdk2;

import com.example.wsmeverylisten.proxy.staticProxy.RealSubject;
import com.example.wsmeverylisten.proxy.staticProxy.Subject;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class JdkProxyMain {

    public static void main(String[] args) {


        //获取InvocationHandler对象 在构造方法中注入目标对象
        InvocationHandler handler = new JdkProxySubject(new RealSubject());

        //获取代理对象
        Subject proxySubject = (Subject) Proxy.newProxyInstance(JdkProxyMain.class.getClassLoader(), new Class[]{Subject.class}, handler);

        //调用目标对象
        proxySubject.request();
        proxySubject.response();

    }

}

