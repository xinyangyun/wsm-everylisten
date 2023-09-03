//package com.example.wsmeverylisten;
//
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.concurrent.TimeUnit;
//
//@SpringBootTest
//@RunWith(SpringRunner.class)
//public class RedisTest {
//
//    @Autowired
//    private StringRedisTemplate stringRedisTemplate;
//
//    @Test
//    public void test() {
//        stringRedisTemplate.opsForValue().set("test1","test1",30, TimeUnit.SECONDS);
//
//        String test = stringRedisTemplate.opsForValue().get("test");
//        System.out.println(test);
//    }
//
//
//
//}
