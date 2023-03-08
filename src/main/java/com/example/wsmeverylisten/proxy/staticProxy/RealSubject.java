package com.example.wsmeverylisten.proxy.staticProxy;


/**
 * 目标类
 */
public class RealSubject implements Subject{

    @Override
    public void request() {
        System.out.println("执行目标对象的request方法。。。。。。");
    }

    @Override
    public void response() {
        System.out.println("执行目标对象的response方法。。。。。。");
    }

}
