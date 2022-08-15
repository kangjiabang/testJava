package rpc.netty.factorial;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.math.BigInteger;

/**
 * @Author：zeqi
 * @Date: Created in 23:55 30/1/18.
 * @Description:
 */
/**
 * Handler for a server-side basic.channel.  This handler maintains stateful
 * information which is specific to a certain basic.channel using member variables.
 * Therefore, an instance of this handler can cover only one basic.channel.  You have
 * to create a new handler instance whenever you create a new basic.channel and insert
 * this handler  to avoid a race condition.
 */
public class FactorialServerHandler extends SimpleChannelInboundHandler<BigInteger> {

    private BigInteger lastMultiplier = new BigInteger("1");
    private BigInteger factorial = new BigInteger("1");
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, BigInteger msg) throws Exception {
        lastMultiplier = msg;
        factorial = factorial.multiply(lastMultiplier);
        ctx.writeAndFlush(factorial);
    }


    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.err.printf("Factorial of %,d is: %,d%n", lastMultiplier, factorial);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
