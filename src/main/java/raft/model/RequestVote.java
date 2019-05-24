package raft.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class RequestVote implements Serializable {

    private int term;  //candidate’s term
    private String candidateId; //candidate requesting vote
    private int lastLogIndex; //index of candidate’s last log entry (§5.4)
    private int lastLogTerm; //term of candidate’s last log entry
}
