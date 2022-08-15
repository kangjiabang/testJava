package rpc.netty.server.server3;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import rpc.netty.pojo.UnixTime;

/**
 * @Author：zeqi
 * @Date: Created in 00:00 30/1/18.
 * @Description:
 */
public class TimeEncoder extends ChannelOutboundHandlerAdapter {

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {

        UnixTime unixTime = (UnixTime) msg;
        ByteBuf buf = ctx.alloc().buffer(4);
        buf.writeInt((int)unixTime.value());
        ctx.writeAndFlush(buf,promise);

    }
}
