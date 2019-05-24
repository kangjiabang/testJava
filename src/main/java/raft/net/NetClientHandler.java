package raft.net;

import com.google.common.collect.Maps;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import netty.configcenter.client.ListenerContext;
import netty.configcenter.listener.MessageChangedListener;
import raft.enums.CommandType;
import raft.leaderstrategy.LeaderChooseAlgorithm;
import raft.net.model.Packet;
import raft.processor.PacketProcessor;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @Author：zeqi
 * @Date: Created in 23:18 29/1/18.
 * @Description:
 */
public class NetClientHandler extends ChannelInboundHandlerAdapter {


    private  ConcurrentMap<CommandType/* commandType */,PacketProcessor> processorTable =
            Maps.newConcurrentMap();

    /**
     * 监听器上下文
     */
    private ListenerContext listenerContext = null;

    private  AtomicBoolean isRegiesterConfig = new AtomicBoolean(false);

    public void addLister(MessageChangedListener listener) {
        listenerContext.addListener(listener);
    }

    public NetClientHandler(ConcurrentMap<CommandType/* commandType */,PacketProcessor> processorTable) {
        this.processorTable = processorTable;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        System.out.println("message from server:" + msg);


        //如果是packet
        if (msg instanceof Packet) {

            Packet packet = (Packet) msg;
            switch (packet.getHeader()) {
                case HEATBEAT:
                    break;
                case LEADER_RESPONSE:
                    processorTable.get(CommandType.LEADER_RESPONSE).processRequest(ctx,(Packet)msg);
                    break;
            }

        }

}


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
