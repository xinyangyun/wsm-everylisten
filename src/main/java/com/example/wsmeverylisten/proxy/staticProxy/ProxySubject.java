package com.example.wsmeverylisten.proxy.staticProxy;

/**
 * 代理类
 */
public class ProxySubject implements Subject{


    private Subject subject;

    public ProxySubject(Subject subject) {
        this.subject = subject;
    }

    @Override
    public void request() {

        System.out.println("request before 增强");
        subject.request();
        System.out.println("request after 增强");

    }

    @Override
    public void response() {
        System.out.println("response before 增强");
        subject.response();
        System.out.println("response after 增强");
    }
}
