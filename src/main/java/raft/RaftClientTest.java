package raft;

public class RaftClientTest {

    public static void main(String[] args) {
        RaftClient raftClient = new RaftClient(10087);
        raftClient.init();

    }
}
