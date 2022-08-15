package distribute.raft.processor;

import io.netty.channel.ChannelHandlerContext;
import distribute.raft.RaftClient;
import distribute.raft.net.model.Packet;

public abstract class PacketProcessor {

    protected RaftClient raftClient;

    public PacketProcessor(RaftClient raftClient) {
        this.raftClient = raftClient;
    }



    public abstract  Object processRequest(ChannelHandlerContext ctx, Packet packet);
}
