package rpc.rsocket;

import io.rsocket.Payload;
import io.rsocket.RSocket;
import io.rsocket.RSocketFactory;
import io.rsocket.transport.netty.client.TcpClientTransport;
import io.rsocket.util.DefaultPayload;
import reactor.core.publisher.Flux;
import rpc.rsocket.support.Constants;

public class ReqStreamClient {

    private final RSocket socket;

    public ReqStreamClient() {
        this.socket = RSocketFactory.connect()
          .transport(TcpClientTransport.create("localhost", Constants.TCP_PORT))
          .start()
          .block();
    }

    public Flux<Float> getDataStream() {
        return socket
          .requestStream(DefaultPayload.create(Constants.DATA_STREAM_NAME))
          .map(Payload::getData)
          .map(buf -> buf.getFloat())
          .onErrorReturn(null);
    }

    public void dispose() {
        this.socket.dispose();
    }
}
