package com.example.wsmeverylisten.start;

import net.sf.cglib.proxy.Enhancer;

import java.util.concurrent.ConcurrentHashMap;

public class CglibProxy {

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        // 设置目标类
        enhancer.setSuperclass(TargetClass.class);
        // 设置拦截器对象
        enhancer.setCallback(new MyInterceptor());
        // 创建子类 即代理
        TargetClass targetClassProxy = (TargetClass) enhancer.create();
        targetClassProxy.targetInfo();

        //创建一个String类

        ConcurrentHashMap<Object, Object> objectObjectConcurrentHashMap = new ConcurrentHashMap<>();



    }

}
