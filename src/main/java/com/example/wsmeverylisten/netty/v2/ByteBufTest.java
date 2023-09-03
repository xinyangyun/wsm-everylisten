package com.example.wsmeverylisten.netty.v2;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

public class ByteBufTest {

        public static void main(String[] args) {
                ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer(6, 10);
                printByteBufInfo("ByteBufAllocator.buffer(5, 10)", buffer);
                buffer.writeBytes(new byte[]{1,2});
                printByteBufInfo("write 2 Bytes", buffer);
                buffer.writeInt(100);
                printByteBufInfo("write Int 100", buffer);
                buffer.writeBytes(new byte[]{3,4,5});
                printByteBufInfo("write 3 Bytes", buffer);
                byte[] read = new byte[buffer.readableBytes()];
                buffer.readBytes(read);
                printByteBufInfo("readBytes(" + buffer.readableBytes() + ")", buffer);
                printByteBufInfo("BeforeGetAndSet", buffer);
                System.out.println("getInt(2):" + buffer.getInt(2));
                buffer.setByte(1,0);
                System.out.println("getByte(1):" + buffer.getByte(1));
                printByteBufInfo("AfterGetAndSet", buffer);
        }

        public static void printByteBufInfo(String step, ByteBuf buffer) {
                System.out.println("-----" + step + "------");
                System.out.println("readerIndex():" + buffer.readerIndex());
                System.out.println("writeIndex():" + buffer.writerIndex());
                System.out.println("isReadable():" + buffer.isReadable());
                System.out.println("isWriteable():" + buffer.isWritable());
                System.out.println("readableBytes():" + buffer.readableBytes());
                System.out.println("writeableBytes():" + buffer.writableBytes());
                System.out.println("maxWriteableBytes():" + buffer.maxWritableBytes());
                System.out.println("capacity():" + buffer.capacity());
                System.out.println("maxCapacity():" + buffer.maxCapacity());
        }

}
