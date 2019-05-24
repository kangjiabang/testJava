package raft.model;

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

}
