package raft.processor;

import io.netty.channel.ChannelHandlerContext;
import raft.RaftClient;
import raft.net.NettyClient;
import raft.net.NettyServer;
import raft.net.model.Packet;

public class VoteResponseProcessor extends  PacketProcessor{



    public VoteResponseProcessor(RaftClient raftClient) {
       super(raftClient);
    }

    @Override
    public Object processRequest(ChannelHandlerContext ctx, Packet packet) {

         raftClient.getLeaderChooseAlgorithm().statVote(packet.getRequestVoteReponse());
         return null;
    }
}
