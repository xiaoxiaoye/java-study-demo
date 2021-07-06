package org.example.study.io.Netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class TimeClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        ByteBuf m = (ByteBuf) msg;
//        try {
//            long curTimeMillis = (m.readUnsignedInt() - 2208988800L) * 1000L;
//            System.out.println(new Date(curTimeMillis));
//            ctx.close();
//        } finally {
//            ReferenceCountUtil.release(msg);
//        }
        UnixTime time = (UnixTime) msg;
        System.out.println(time);
        ctx.close();
    }
}
