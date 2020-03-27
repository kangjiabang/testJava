package netty.client.objectEncoder;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Response implements Serializable {
    private String response;
    private Date responseTime;
}
