package com.example.wsmeverylisten.proxy.aspect;

public class Hello {

    //定义一个简单方法，模拟应用中的业务逻辑方法
    public void sayHello() {
        System.out.println("Hello AspectJ!");
    }

    public static void main(String[] args) {
        Hello hello = new Hello();
        hello.sayHello();
    }

}
