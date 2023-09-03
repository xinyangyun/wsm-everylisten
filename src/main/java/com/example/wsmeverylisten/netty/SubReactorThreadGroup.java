package com.example.wsmeverylisten.netty;

import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;




//内部持有一个业务线程池，用于执行业务逻辑，可类比Dubbo中的用于执行服务提供这各个Service的业务代码
//通过next()进行负载均衡，选择一个从Reactor。
//这里使用了NioTask，主要是因为Selector虽然是线程安全的，但其内部持有的Key Set是线程不安全的，
// 故这里为保证Selector的现线程安全性，selector的任何方法只会在从Reactor线程中执行，
// 这里只是添加到任务队列中，最终会由从Reactor线程去执行。

public class SubReactorThreadGroup {

        private static final int DEFAULT_NIO_THREAD_COUNT = 4;

        private int businessThreadCount = 0;

        private int ioThreadCount = 0;

        private SubReactorThread[] ioThreads = null;
        private ExecutorService businessExecutePool;

        private AtomicInteger requestCounter ;

        public SubReactorThreadGroup() {
                this(DEFAULT_NIO_THREAD_COUNT);
        }

        public SubReactorThreadGroup(int ioThreadCount) {
                if (ioThreadCount < 1) {
                    ioThreadCount = DEFAULT_NIO_THREAD_COUNT;
                }
                //暂时固定为10
                businessThreadCount = 10;
                businessExecutePool = Executors.newFixedThreadPool(businessThreadCount, new ThreadFactory() {
                        private AtomicInteger num = new AtomicInteger(0);
                        @Override
                        public Thread newThread(Runnable r) {
                                Thread t = new Thread(r);
                                t.setName("bussiness-thread-" + num.incrementAndGet());
                                return null;
                        }
                });
                this.ioThreadCount = ioThreadCount;
                this.ioThreads = new SubReactorThread[ioThreadCount];
                for (int i = 0;i < ioThreadCount;i++) {
                        this.ioThreads[i] = new SubReactorThread(businessExecutePool);
                        this.ioThreads[i].start();//构造方法中启动线程，由于nioThreads不会对外暴露，故不会引起线程逃逸
                }
                System.out.println("NIO 线程数量:" + ioThreadCount);
        }

        public void dispatch(SocketChannel socketChannel) {
                if (socketChannel != null) {
                        next().register(new NioTask());
                }
        }

        protected SubReactorThread next() {
                return this.ioThreads[ requestCounter.getAndIncrement() % ioThreadCount ];
        }

}
