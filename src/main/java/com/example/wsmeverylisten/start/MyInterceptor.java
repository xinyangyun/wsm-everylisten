package com.example.wsmeverylisten.start;

import cn.hutool.core.date.StopWatch;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MyInterceptor implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("------插入前置通知代码-------------");
        // 此处一定要使用proxy的invokeSuper方法来调用目标类的方法
        methodProxy.invokeSuper(o, objects);
        System.out.println("------插入后置处理代码-------------");
        return null;
    }

    public static void main(String[] args) {
//        IntStream.rangeClosed(2,10).forEach(i -> {
//            System.out.println("xx");
//        });
//
//        //模拟需要写入的大量数据
//        String payload = IntStream.rangeClosed(1, 1_000_000)
//                .mapToObj(__ -> "a")
//                .collect(Collectors.joining(""));
//
//        System.out.println(payload);

//        System.out.println(0.1+0.2);

//        int elementCount = 100000;
//
//        int loopCount = 100000;
//
//        StopWatch stopWatch = new StopWatch();
//        stopWatch.start("linkedListGet");
//        linkedListGet(elementCount, loopCount);
//        stopWatch.stop();
//
//        stopWatch.start("arrayListGet");
//        arrayListGet(elementCount, loopCount);
//        stopWatch.stop();
//        System.out.println(stopWatch.prettyPrint());
//
//        StopWatch stopWatch2 = new StopWatch();
//        stopWatch2.start("linkedListAdd");
//        linkedListAdd(elementCount, loopCount);
//        stopWatch2.stop();
//
//        stopWatch2.start("arrayListAdd");
//        arrayListAdd(elementCount, loopCount);
//        stopWatch2.stop();
//
//        System.out.println(stopWatch2.prettyPrint());


        Optional.ofNullable(1).orElse(2);
















    }


    //LinkedList访问
    private static void linkedListGet(int elementCount, int loopCount) {
        List<Integer> list = IntStream.rangeClosed(1, elementCount).boxed().collect(Collectors.toCollection(LinkedList::new));
        IntStream.rangeClosed(1, loopCount).forEach(i -> list.get(ThreadLocalRandom.current().nextInt(elementCount)));
    }

    //ArrayList访问
    private static void arrayListGet(int elementCount, int loopCount) {
        List<Integer> list = IntStream.rangeClosed(1, elementCount).boxed().collect(Collectors.toCollection(ArrayList::new));
        IntStream.rangeClosed(1, loopCount).forEach(i -> list.get(ThreadLocalRandom.current().nextInt(elementCount)));
    }

    //LinkedList插入
    private static void linkedListAdd(int elementCount, int loopCount) {
        List<Integer> list = IntStream.rangeClosed(1, elementCount).boxed().collect(Collectors.toCollection(LinkedList::new));
        IntStream.rangeClosed(1, loopCount).forEach(i -> list.add(ThreadLocalRandom.current().nextInt(elementCount),1));
    }

    //ArrayList插入
    private static void arrayListAdd(int elementCount, int loopCount) {
        List<Integer> list = IntStream.rangeClosed(1, elementCount).boxed().collect(Collectors.toCollection(ArrayList::new));
        IntStream.rangeClosed(1, loopCount).forEach(i -> list.add(ThreadLocalRandom.current().nextInt(elementCount),1));
    }

}