package rpc.rsocket.simple;

import io.rsocket.*;
import io.rsocket.transport.netty.client.TcpClientTransport;
import io.rsocket.transport.netty.server.TcpServerTransport;
import io.rsocket.util.DefaultPayload;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

@Slf4j
public class RSocketBasicTest {

    private static final Logger LOG = LoggerFactory.getLogger(RSocketBasicTest.class);

    @Test
    public void testFireAndForget() throws InterruptedException {
        //SERVER
        RSocketFactory.receive()
                .acceptor(
                        (setupPayload, reactiveSocket) ->
                                Mono.just(
                                        new AbstractRSocket() {
                                            @Override
                                            public Mono<Void> fireAndForget(Payload payload) {
                                                log.info("fire-forget: %s%n", payload.getDataUtf8());
                                                return Mono.empty();
                                            }
                                        }))
                .transport(TcpServerTransport.create("localhost", 8080))
                .start()
                .subscribe();

        //CLIENT
        RSocket socket =
                RSocketFactory.connect()
                        .transport(TcpClientTransport.create("localhost", 8080))
                        .start()
                        .block();

        socket
                .fireAndForget(DefaultPayload.create("Hello"))
                .block();

        socket.dispose();

        TimeUnit.SECONDS.sleep(5);
    }

    @Test
    public void testRequestResponse(){
        //SERVER
        RSocketFactory.receive()
                .acceptor(
                        (setupPayload, reactiveSocket) ->
                                Mono.just(
                                        new AbstractRSocket() {
                                            @Override
                                            public Mono<Payload> requestResponse(Payload p) {

                                                log.info("requestResponse: %s%n", p.getDataUtf8());
                                                return Mono.just(p);
                                            }
                                        }))
                .transport(TcpServerTransport.create("localhost", 8080))
                .start()
                .subscribe();

        //CLIENT
        RSocket socket =
                RSocketFactory.connect()
                        .transport(TcpClientTransport.create("localhost", 8080))
                        .start()
                        .block();

        socket
                .requestResponse(DefaultPayload.create("Hello"))
                .map(Payload::getDataUtf8)
                .onErrorReturn("error")
                .doOnNext(System.out::println)
                .block();

        socket.dispose();
    }

    @Test
    public void testRequestStream(){
        //SERVER
        RSocketFactory.receive()
                .acceptor(new SocketAcceptor() {
                    @Override
                    public Mono<RSocket> accept(ConnectionSetupPayload connectionSetupPayload, RSocket rSocket) {
                        return Mono.just(
                                new AbstractRSocket() {
                                    @Override
                                    public Flux<Payload> requestStream(Payload payload) {
                                        log.info("requestStream: %s%n", payload.getDataUtf8());

                                        return Flux.interval(Duration.ofMillis(1000))
                                                .map(aLong -> DefaultPayload.create("Interval: " + aLong));
                                    }
                                });
                    }
                })
                .transport(TcpServerTransport.create("localhost", 8080))
                .start()
                .subscribe();

        //CLIENT
        RSocket socket =
                RSocketFactory.connect()
                        .transport(TcpClientTransport.create("localhost", 8080))
                        .start()
                        .block();

        socket
                .requestStream(DefaultPayload.create("Hello"))
                .map(Payload::getDataUtf8)
                .doOnNext(System.out::println)
                .take(10)
                .then()
                .doFinally(signalType -> socket.dispose())
                .then()
                .block();
    }


    @Test
    public void testRequestChannel(){
        //SERVER
        RSocketFactory.receive()
                .acceptor(new SocketAcceptor(){
                    @Override
                    public Mono<RSocket> accept(ConnectionSetupPayload setup, RSocket sendingSocket) {
                        return Mono.just(
                                new AbstractRSocket() {
                                    @Override
                                    public Flux<Payload> requestChannel(Publisher<Payload> payloads) {
                                        return Flux.from(payloads)
                                                .map(Payload::getDataUtf8)
                                                .doOnNext(System.out::println)
                                                .map(s -> "Echo: " + s)
                                                .map(DefaultPayload::create);
                                    }
                                });
                    }
                })
                .transport(TcpServerTransport.create("localhost", 8080))
                .start()
                .subscribe();

        //CLIENT
        RSocket socket =
                RSocketFactory.connect()
                        .transport(TcpClientTransport.create("localhost", 8080))
                        .start()
                        .block();

        socket
                .requestChannel(
                        Flux.interval(Duration.ofMillis(1000)).map(i -> DefaultPayload.create("Hello")))
                .map(Payload::getDataUtf8)
                .doOnNext(System.out::println)
                .take(10)
                .doFinally(signalType -> socket.dispose())
                .then()
                .block();
    }


    @Test
    public void testRequestChannelSelf(){
        //SERVER
        RSocketFactory.receive()
                .acceptor(new SocketAcceptor(){
                    @Override
                    public Mono<RSocket> accept(ConnectionSetupPayload setup, RSocket sendingSocket) {
                        return Mono.just(
                                new AbstractRSocket() {
                                    @Override
                                    public Flux<Payload> requestChannel(Publisher<Payload> payloads) {


                                        return Flux.from(payloads)
                                                .map(Payload::getDataUtf8)
                                                .doOnNext(System.out::println)
                                                .map(s -> "Echo: " + s)
                                                .map(DefaultPayload::create);
                                    }
                                });
                    }
                })
                .transport(TcpServerTransport.create("localhost", 8080))
                .start()
                .subscribe();

        //CLIENT
        RSocket socket =
                RSocketFactory.connect()
                        .transport(TcpClientTransport.create("localhost", 8080))
                        .start()
                        .block();

        socket
                .requestChannel(Flux.from(
                        subscriber -> {
                            for (int i = 0; i < 10; i++) {
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                subscriber.onNext(DefaultPayload.create("bang!"));
                            }
                            subscriber.onComplete();

                            try {
                                System.in.read();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        })
/*
                        Flux.interval(Duration.ofMillis(1000)).map(i -> DefaultPayload.create("Hello"))*/)
                .map(Payload::getDataUtf8)
                .doOnNext(System.out::println)
                .take(10)
                .doFinally(signalType -> socket.dispose())
                .then()
                .block();
    }

}
