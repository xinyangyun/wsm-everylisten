package com.example.wsmeverylisten;


import com.example.wsmeverylisten.proxy.jdk.AOPConfig;
import com.example.wsmeverylisten.proxy.jdk.Human;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

//切面编程测试方法
@SpringBootTest
@RunWith(SpringRunner.class)
public class testLog {


    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AOPConfig.class);

        Human human = context.getBean(Human.class);
        human.display();
        System.out.println(human.getClass().getSuperclass());

    }

    @Test
    public void test() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AOPConfig.class);

        Human human = context.getBean(Human.class);
        human.display();
        System.out.println(human.getClass().getSuperclass());

    }


}
