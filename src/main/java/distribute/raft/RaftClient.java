package distribute.raft;

import com.google.common.collect.Sets;
import distribute.raft.enums.CommandType;
import distribute.raft.leaderstrategy.LeaderChooseAlgorithm;
import distribute.raft.leaderstrategy.VoteComarator;
import distribute.raft.model.Role;
import distribute.raft.net.NettyClient;
import distribute.raft.net.NettyServer;
import distribute.raft.processor.VoteRequestProcessor;
import distribute.raft.processor.VoteResponseProcessor;

import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

public class RaftClient {

    private Role role = Role.FOLLWER;

    private int currentTerm;

    private String candidateId = "A";

    private AtomicBoolean hasLeader = new AtomicBoolean(false);

    private Set<String> clientHostList = Sets.newHashSet("localhost:10086","localhost:10087","localhost:10088");

    private NettyServer nettyServer;

    private LeaderChooseAlgorithm  leaderChooseAlgorithm = new LeaderChooseAlgorithm(candidateId);

    private VoteComarator voteComarator = new VoteComarator(currentTerm);

    private int localPort = 10086;

    private NettyClient nettyClient;

    public RaftClient(int localPort) {
        this.localPort = localPort;
    }

    public void init() {

        //启动服务端
        nettyServer = new NettyServer(localPort,this);
        nettyServer.start();

        //启动客户端
        nettyClient = new NettyClient(candidateId);

        nettyClient.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        registerProcessor();
        nettyClient.createChannels(clientHostList,String.valueOf(localPort));

        //开始发起投票
        nettyClient.startFindLeader(currentTerm,candidateId);
    }

    private void registerProcessor() {

        nettyClient.registerProcessor(CommandType.LEADER_RESPONSE,new VoteResponseProcessor(this));
        nettyServer.registerProcessor(CommandType.LEADER_REQUEST,new VoteRequestProcessor(this));
    }

    public int getCurrentTerm() {
        return currentTerm;
    }

    public void setCurrentTerm(int currentTerm) {
        this.currentTerm = currentTerm;
    }

    public String getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(String candidateId) {
        this.candidateId = candidateId;
    }

    public LeaderChooseAlgorithm getLeaderChooseAlgorithm() {
        return leaderChooseAlgorithm;
    }

    public VoteComarator getVoteComarator() {
        return voteComarator;
    }
}
