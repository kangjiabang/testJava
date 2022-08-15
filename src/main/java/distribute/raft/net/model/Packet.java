package distribute.raft.net.model;

import lombok.Data;
import distribute.raft.enums.CommandType;
import distribute.raft.model.RequestVote;
import distribute.raft.model.RequestVoteReponse;

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
