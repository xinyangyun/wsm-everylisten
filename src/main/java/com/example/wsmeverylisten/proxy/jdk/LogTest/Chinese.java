package com.example.wsmeverylisten.proxy.jdk.LogTest;


import org.springframework.stereotype.Component;

@Component
public class Chinese {

    //实现person接口的sayhello方法
    public String sayHello(String name){
        System.out.println("-- 正在执行 sayhello 方法 --");
        //返回简单的字符串
        return name + "hello , Spring Aop";
    }

    //定义一个eat方法
    public void eat(String food){
        System.out.println("我正在吃:" + food);
    }

}
