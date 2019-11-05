package rsocket.simple;

import io.rsocket.RSocket;
import io.rsocket.RSocketFactory;
import io.rsocket.transport.netty.client.TcpClientTransport;
import io.rsocket.util.DefaultPayload;

public class SimpleClient {

    private final RSocket socket;

    public SimpleClient() {

        socket = RSocketFactory.connect()
                .transport(TcpClientTransport.create("localhost", Constants.TCP_PORT))
                .start()
                .block();
    }


    public void firAndForget() {
        socket.fireAndForget(DefaultPayload.create("Hello world!")).block();
    }

    public void dispose() {
        this.socket.dispose();
    }
}
