package com.example.wsmeverylisten.proxy.staticProxy;

public class ProxyMain {

    public static void main(String[] args) {
        //目标对象
        Subject realSubject = new RealSubject();

        //代理对象
        Subject proxySubject = new ProxySubject(realSubject);

        proxySubject.request();
        proxySubject.response();

    }
}
