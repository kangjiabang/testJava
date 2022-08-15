package distribute.raft.leaderstrategy;

import com.google.common.collect.Maps;
import distribute.raft.model.RequestVoteReponse;

import java.util.Map;

public class LeaderChooseAlgorithm {

    private String candidateId;

    public LeaderChooseAlgorithm(String candidateId) {
        this.candidateId = candidateId;
    }

    private Map<String,Integer> candidateIdToVoteCount = Maps.newConcurrentMap();

    /**
     * 统计投票
     * @param requestVoteReponse
     * @return
     */
    public void statVote(RequestVoteReponse requestVoteReponse) {

            if (requestVoteReponse.getVoteGranted()) {

            synchronized (candidateIdToVoteCount) {
                //更新投票列表
                if (candidateIdToVoteCount.get(candidateId) != null) {
                    Integer totalVoteCount = candidateIdToVoteCount.get(candidateId);
                    totalVoteCount++;
                    candidateIdToVoteCount.put(candidateId,totalVoteCount);
                } else {
                    candidateIdToVoteCount.put(candidateId,new Integer(1));
                }
            }
        }
    }
}
