package rpc.rsocket;


import io.rsocket.RSocket;
import io.rsocket.RSocketFactory;
import io.rsocket.transport.netty.client.TcpClientTransport;
import reactor.core.publisher.Flux;
import rpc.rsocket.support.Constants;
import rpc.rsocket.support.GameController;


public class ChannelClient {

    private final RSocket socket;
    private final GameController gameController;

    public ChannelClient() {
        this.socket = RSocketFactory.connect()
          .transport(TcpClientTransport.create("localhost", Constants.TCP_PORT))
          .start()
          .block();

        this.gameController = new GameController("Client Player");
    }

    public void playGame() {
        socket.requestChannel(Flux.from(gameController))
          .doOnNext(gameController::processPayload)
          .blockLast();
    }

    public void dispose() {
        this.socket.dispose();
    }
}
