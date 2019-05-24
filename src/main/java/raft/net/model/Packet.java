package raft.net.model;

import lombok.Builder;
import lombok.Data;
import raft.enums.CommandType;
import raft.model.RequestVote;
import raft.model.RequestVoteReponse;

import java.io.Serializable;

/**
 * @Author：zeqi
 * @Date: Created in 15:20 1/2/18.
 * @Description:
 */
@Data
public class Packet implements Serializable {

    public Packet() {
    }

    private CommandType header;

    private RequestVote requestVote;

    private RequestVoteReponse requestVoteReponse;



}
