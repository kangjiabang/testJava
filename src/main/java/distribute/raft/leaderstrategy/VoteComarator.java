package distribute.raft.leaderstrategy;

import com.google.common.collect.Maps;
import distribute.raft.model.RequestVote;
import distribute.raft.model.RequestVoteReponse;

import java.util.Map;

public class VoteComarator {

    private int currentTerm;

    public VoteComarator(int currentTerm) {
        this.currentTerm = currentTerm;
    }

    private Map<String,Integer> candidateIdToVoteCount = Maps.newConcurrentMap();

    /**
     * 比较投票大小
     * @param requestVote
     * @return
     */
    public RequestVoteReponse compareVote(RequestVote requestVote) {
        RequestVoteReponse requestVoteReponse = new RequestVoteReponse();

        requestVoteReponse.setTerm(currentTerm);

        if (requestVote.getTerm() < currentTerm ) {

            requestVoteReponse.setVoteGranted(false);
        } else {
            //接收投票
            requestVoteReponse.setVoteGranted(true);
            currentTerm = requestVote.getTerm();
            synchronized (candidateIdToVoteCount) {
                //更新投票列表
                if (candidateIdToVoteCount.get(requestVote.getCandidateId()) != null) {
                    Integer totalVoteCount = candidateIdToVoteCount.get(requestVote.getCandidateId());
                    totalVoteCount++;
                    candidateIdToVoteCount.put(requestVote.getCandidateId(),totalVoteCount);
                } else {
                    candidateIdToVoteCount.put(requestVote.getCandidateId(),new Integer(1));
                }
            }
        }

        return requestVoteReponse;
    }
}
