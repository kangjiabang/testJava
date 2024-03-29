package rpc.rsocket.simple;

import io.rsocket.*;
import io.rsocket.util.DefaultPayload;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.time.Instant;

@Slf4j
public class HelloWorldSocketAcceptor implements SocketAcceptor {

    @Override
    public Mono<RSocket> accept(ConnectionSetupPayload setup, RSocket sendingSocket) {
        log.info("Received connection with setup payload: [{}] and meta-data: [{}]", setup.getDataUtf8(), setup.getMetadataUtf8());
        return Mono.just(new AbstractRSocket() {
            @Override
            public Mono<Void> fireAndForget(Payload payload) {
                log.info("Received 'fire-and-forget' request with payload: [{}]", payload.getDataUtf8());
                return Mono.empty();
            }

            @Override
            public Mono<Payload> requestResponse(Payload payload) {
                log.info("Received 'request response' request with payload: [{}] ", payload.getDataUtf8());
                return Mono.just(DefaultPayload.create("Hello " + payload.getDataUtf8()));
            }

            @Override
            public Flux<Payload> requestStream(Payload payload) {
                log.info("Received 'request stream' request with payload: [{}] ", payload.getDataUtf8());
                return Flux.interval(Duration.ofMillis(1000))
                        .map(time -> DefaultPayload.create("Hello " + payload.getDataUtf8() + " @ " + Instant.now()));
            }

            @Override
            public Flux<Payload> requestChannel(Publisher<Payload> payloads) {
                return Flux.from(payloads)
                        .doOnNext(payload -> {
                            log.info("Received payload: [{}]", payload.getDataUtf8());
                        })
                        .map(payload -> DefaultPayload.create("Hello " + payload.getDataUtf8() + " @ " + Instant.now()))
                        .subscribeOn(Schedulers.parallel());
            }

            @Override
            public Mono<Void> metadataPush(Payload payload) {
                log.info("Received 'metadata push' request with metadata: [{}]", payload.getMetadataUtf8());
                return Mono.empty();
            }
        });
    }
}
