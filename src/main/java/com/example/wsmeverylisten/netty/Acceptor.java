package com.example.wsmeverylisten.netty;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;


//代码文章链接https://codingw.blog.csdn.net/article/details/118861171

//Acceptor代码实现比较简单，就是利用NIO提供的API创建ServerSocketChannel，
// 并设置为非阻塞模式，并调用bind方法绑定端口，然后转发到MainReactor中。
public class Acceptor implements Runnable{

        private static ExecutorService mainReactor = Executors.newSingleThreadExecutor(new ThreadFactory() {
                private AtomicInteger num = new AtomicInteger(0);

                @Override
                public Thread newThread(Runnable r) {
                        Thread  t = new Thread(r);
                        t.setName("main-reactor-" + num.incrementAndGet());
                        return t;
                }
        });

        @Override
        public void run() {
                ServerSocketChannel ssc = null;
                try {
                        ssc = ServerSocketChannel.open();
                        ssc.configureBlocking(false);
                        ssc.bind(new InetSocketAddress(222));

                        //转发到mainReactor反应堆
                        dispatch(ssc);
                        System.out.println("-----服务端启动成功-----");
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }

        private void dispatch(ServerSocketChannel ssc) {
                mainReactor.submit(new MainReactor(ssc));
        }

}
