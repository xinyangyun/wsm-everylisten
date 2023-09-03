package com.example.wsmeverylisten.netty;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.locks.ReentrantLock;



//主要是提供增加IO读写任务(NioTask)添加到任务队列中，并在执行业务逻辑时转发到业务线程池。
public class SubReactorThread  extends Thread {

        private Selector selector;

        private ExecutorService businessExecutorPool;

        private List<NioTask> taskList = new ArrayList<>(512);

        private ReentrantLock taskMainLock = new ReentrantLock();

        public SubReactorThread(ExecutorService businessExecutorPool) {
                try {
                        this.businessExecutorPool = businessExecutorPool;
                        this.selector = Selector.open();
                }catch (IOException e) {
                        e.printStackTrace();
                }
        }

        /**
         * socket channel
         * @param task
         */
        public void register(NioTask task) {
                if (task != null) {
                        try {
                                taskMainLock.lock();
                                taskList.add(task);
                        } finally {
                                taskMainLock.unlock();
                        }
                }
        }

        private void dispatch(SocketChannel sc, ByteBuffer reqBuffer) {
//                businessExecutorPool.submit(new Thread());
//                businessExecutorPool.submit(new Handler(sc, reqBuffer , this));
                businessExecutorPool.submit((Runnable) new Handler(sc, reqBuffer));
        }

        @Override
        public void run() {
                while (!Thread.interrupted()) {
                        Set<SelectionKey> ops = null;
                        try {
                                selector.select(1000);
                                ops = selector.selectedKeys();
                        } catch (IOException e) {
                                e.printStackTrace();
                                continue;
                        }
                        //处理相关事件
                        for(Iterator<SelectionKey> it = ops.iterator(); it.hasNext();) {
                                SelectionKey key = it.next();
                                it.remove();
                                try {
                                        if (key.isWritable()) { //向客户端发送请求
                                                SocketChannel clientChannel = (SocketChannel) key.channel();
                                                ByteBuffer buf = (ByteBuffer) key.attachment();
                                                clientChannel.write(buf);//这里其实要和taskList的实现一样，需要处理缓存区满的情况
                                                System.out.println("服务端向客户端发送数据。。");
                                                //重新注册读事件
                                                clientChannel.register(selector, SelectionKey.OP_READ);
                                        }else if (key.isReadable()) { //接受客户端请求
                                                System.out.println("服务端接受客户端连接请求。。。");
                                                SocketChannel clientChannel = (SocketChannel) key.channel();
                                                ByteBuffer buf = ByteBuffer.allocate(1024);
                                                System.out.println(buf.capacity());
                                                /**
                                                 * 这里其实实现的非常不优雅
                                                 */
                                                int rc = clientChannel.read(buf);//解析请求完毕
                                                //转发请求到具体的业务线程；当然，这里其实可以向dubbo那样，支持转发策略，如果执行时间短
                                                //比如没有数据库操作等，可以在io线程中执行。本实例，转发到业务线程池
                                                dispatch(clientChannel, buf);
                                        }
                                } catch (Throwable e) {
                                        e.printStackTrace();
                                        System.out.println("客户端主动断开连接。。。。。");
                                }
                        }
                }
        }

}
