package distribute.raft.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class RequestVoteReponse implements Serializable {

    /**
     *currentTerm, for candidate to update itself
     */
    private int term;
    /**
     *  true means candidate received vote
     */
    private Boolean voteGranted;

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public Boolean getVoteGranted() {
        return voteGranted;
    }

    public void setVoteGranted(Boolean voteGranted) {
        this.voteGranted = voteGranted;
    }
}
