package com.example.wsmeverylisten.proxy.jdk.LogTest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LogMain {

    public static void main(String[] args)
    {
        // 创建 Spring 容器
        ApplicationContext ctx = new
                ClassPathXmlApplicationContext("bean.xml");
        Chinese p = ctx.getBean("chinese" ,Chinese.class);
        System.out.println(p.sayHello("张三"));
        p.eat("西瓜");
    }

}
