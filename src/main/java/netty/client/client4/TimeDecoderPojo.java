package netty.client.client4;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import netty.pojo.UnixTime;

import java.util.List;

/**
 * @Author：zeqi
 * @Date: Created in 23:44 29/1/18.
 * @Description:
 */
public class TimeDecoderPojo extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (in.readableBytes() < 4) {
            return;
        }
        out.add(new UnixTime(in.readUnsignedInt()));
    }
}
