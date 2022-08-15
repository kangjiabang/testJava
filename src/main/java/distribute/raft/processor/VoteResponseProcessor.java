package distribute.raft.processor;

import io.netty.channel.ChannelHandlerContext;
import distribute.raft.RaftClient;
import distribute.raft.net.model.Packet;

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
