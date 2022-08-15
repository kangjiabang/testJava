package rpc.netty.server.server5;

import io.netty.channel.*;
import rpc.netty.pojo.UnixTime;

/**
 * @Author：zeqi
 * @Date: Created in 21:19 28/1/18.
 * @Description:
 */
public class TimerServerReadHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ChannelFuture f = ctx.writeAndFlush(new UnixTime());
        f.addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Channel channel = ctx.channel();
        ctx.writeAndFlush("nihao");
        channel.writeAndFlush("nihao");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
