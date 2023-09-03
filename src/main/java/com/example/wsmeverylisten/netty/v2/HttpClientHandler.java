package com.example.wsmeverylisten.netty.v2;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.HttpContent;
import io.netty.util.CharsetUtil;

public class HttpClientHandler extends ChannelInboundHandlerAdapter {

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) {
            if (msg instanceof HttpClient) {
                HttpContent content = (HttpContent) msg;
                ByteBuf buf = content.content();
                System.out.println("test    ---> "+buf.toString(CharsetUtil.UTF_8));
                buf.release();
            }
        }

}
