package raft.processor;

import io.netty.channel.ChannelHandlerContext;
import raft.RaftClient;
import raft.net.NettyClient;
import raft.net.NettyServer;
import raft.net.model.Packet;

public abstract class PacketProcessor {

    protected RaftClient raftClient;

    public PacketProcessor(RaftClient raftClient) {
        this.raftClient = raftClient;
    }



    public abstract  Object processRequest(ChannelHandlerContext ctx, Packet packet);
}
