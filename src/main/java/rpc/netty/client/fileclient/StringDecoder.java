package rpc.netty.client.fileclient;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.nio.charset.Charset;
import java.util.List;

/**
 * @Author：zeqi
 * @Date: Created in 18:45 31/1/18.
 * @Description:
 */
public class StringDecoder extends MessageToMessageDecoder<ByteBuf> {


    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf buf, List<Object> out) throws Exception {
        out.add(buf.toString(Charset.forName("UTF-8")));
    }
}
