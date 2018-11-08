package netty.client.fileclient;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import netty.pojo.UnixTime;

import java.util.Scanner;

/**
 * @Author：zeqi
 * @Date: Created in 23:18 29/1/18.
 * @Description:
 */
public class FileClientHandler extends ChannelInboundHandlerAdapter {

    private volatile boolean  isFirstTime = true;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        System.out.println("message:" + (String) msg);

        if (isFirstTime) {
            System.out.println("input:");
            Scanner scanner = new Scanner(System.in);
            String file = scanner.nextLine();

            scanner.close();

            System.out.println("file:" + file);
            ctx.writeAndFlush(file + "\n");
            isFirstTime = false;
        }

    //ctx.writeAndFlush("/Users/zeqi/Documents/code/netty/NOTICE.txt\n");
}


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
