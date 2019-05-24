package raft.processor;

import io.netty.channel.ChannelHandlerContext;
import raft.RaftClient;
import raft.net.model.Packet;

public class VoteRequestProcessor extends  PacketProcessor{


    public VoteRequestProcessor(RaftClient raftClient) {
      super(raftClient);
    }

    @Override
    public Object processRequest(ChannelHandlerContext ctx, Packet packet) {
        return raftClient.getVoteComarator().compareVote(packet.getRequestVote());
    }
}
