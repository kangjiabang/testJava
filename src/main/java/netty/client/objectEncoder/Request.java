package netty.client.objectEncoder;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Request implements Serializable {
    private String request;
    private Date requestTime;

}
