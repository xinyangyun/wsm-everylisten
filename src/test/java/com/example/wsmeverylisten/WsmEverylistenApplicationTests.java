package com.example.wsmeverylisten;

import com.example.wsmeverylisten.proxy.jdk.LogTest.Chinese;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ThreadPoolExecutor;


@SpringBootTest
@RunWith(SpringRunner.class)
class WsmEverylistenApplicationTests {

    @Autowired
    private ThreadPoolExecutor threadPoolExecutor;

    @Autowired
    private Chinese chinese;

    @Test
    public void test() {
        String xx = chinese.sayHello("xx");
        System.out.println(xx);
    }

}
