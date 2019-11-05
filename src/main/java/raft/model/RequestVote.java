package raft.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class RequestVote implements Serializable {

    private int term;  //candidate’s term
    private String candidateId; //candidate requesting vote
    private int lastLogIndex; //index of candidate’s last log entry (§5.4)
    private int lastLogTerm; //term of candidate’s last log entry


    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public String getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(String candidateId) {
        this.candidateId = candidateId;
    }

    public int getLastLogIndex() {
        return lastLogIndex;
    }

    public void setLastLogIndex(int lastLogIndex) {
        this.lastLogIndex = lastLogIndex;
    }

    public int getLastLogTerm() {
        return lastLogTerm;
    }

    public void setLastLogTerm(int lastLogTerm) {
        this.lastLogTerm = lastLogTerm;
    }
}
