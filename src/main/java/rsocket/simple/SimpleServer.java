package rsocket.simple;

import io.rsocket.RSocketFactory;
import io.rsocket.transport.netty.server.TcpServerTransport;
import reactor.core.Disposable;

public class SimpleServer {

    private final Disposable server;

    public SimpleServer() {
        server = RSocketFactory.receive()
                .acceptor(new HelloWorldSocketAcceptor())
                .transport(TcpServerTransport.create("localhost", Constants.TCP_PORT))
                .start()
                .subscribe();
    }

    public void dispose() {
        this.server.dispose();
    }
}
