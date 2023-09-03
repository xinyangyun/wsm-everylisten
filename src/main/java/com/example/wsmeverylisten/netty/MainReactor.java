package com.example.wsmeverylisten.netty;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;


//创建Selector选择器对象，并注册OP_ACCEPT事件，用于监听客户端的链接
//创建从Reactor线程组
//NIO事件选择经典使用方法，通过循环调用selector的select方法，此方法会返回当前就绪的事件,例如读事件就绪，表明此时调用该通道的网络读API，一定会返回有效数据
//如果当前事件是OP_ACCEPT,则调用ServerSocketChannel的accept方法，创建一个SocketChannel对象，并转发到从Reactor线程组中。
public class MainReactor implements Runnable{
        private Selector selector;

        private SubReactorThreadGroup subReactorThreadGroup;

        private static final int DEFAULT_IO_THREAD_COUNT = 4;

        private int ioThreadCount = DEFAULT_IO_THREAD_COUNT;


        public MainReactor(ServerSocketChannel channel) {
                try {
                        selector = Selector.open();
                        channel.register(selector, SelectionKey.OP_ACCEPT);
                }catch (IOException e) {
                        e.printStackTrace();
                }
                subReactorThreadGroup = new SubReactorThreadGroup(ioThreadCount);
        }

        @Override
        public void run() {
                System.out.println("MainReactor is running");
                while (!Thread.interrupted()) {
                        Set<SelectionKey> ops = null;
                        try {
                                selector.select(1000);
                                ops = selector.selectedKeys();
                        }catch (IOException e) {
                                e.printStackTrace();
                        }

                        //处理相关事件
                        for (Iterator<SelectionKey> it = ops.iterator(); it.hasNext();) {
                                SelectionKey key = it.next();
                                it.remove();
                                try {
                                        if (key.isAcceptable()) {
                                                System.out.println("收到客户端的连接请求。。。");
                                                //这里其实，可以直接使用ssl这里变量
                                                ServerSocketChannel serverSc = (ServerSocketChannel) key.channel();
                                                SocketChannel clientChannel = serverSc.accept();
                                                clientChannel.configureBlocking(false);
//                                                subReactorThreadGroup.
                                                subReactorThreadGroup.dispatch(clientChannel); //转发改请求
                                        }
                                } catch (Throwable e) {
                                        e.printStackTrace();
                                        System.out.println("------客户端主动断开连接------");
                                }
                        }
                }
        }

}
