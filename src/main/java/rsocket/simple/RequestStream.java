package rsocket.simple;

import io.rsocket.Payload;
import io.rsocket.RSocket;
import io.rsocket.RSocketFactory;
import io.rsocket.transport.netty.client.TcpClientTransport;
import io.rsocket.util.DefaultPayload;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import static rsocket.simple.Constants.TCP_PORT;

public class RequestStream {

    public static void main(String[] args) {

        RSocket socket = RSocketFactory.connect()
                .transport(TcpClientTransport.create("localhost", TCP_PORT))
                .start()
                .block();
        socket.requestStream(DefaultPayload.create("Jenny", "example-metadata"))
                .subscribe(new BackPressureSubscriber());

        socket.dispose();
    }

    @Slf4j
    private static class BackPressureSubscriber implements Subscriber<Payload> {

        private static final Integer NUMBER_OF_REQUESTED_ITEMS = 5;
        private Subscription subscription;
        int receivedItems;

        @Override
        public void onSubscribe(Subscription s) {
            this.subscription = s;
            subscription.request(NUMBER_OF_REQUESTED_ITEMS);
        }

        @Override
        public void onNext(Payload payload) {
            receivedItems++;
            if (receivedItems % NUMBER_OF_REQUESTED_ITEMS == 0) {
                log.info("Requesting next [{}] elements", NUMBER_OF_REQUESTED_ITEMS);
                subscription.request(NUMBER_OF_REQUESTED_ITEMS);
            }
        }

        @Override
        public void onError(Throwable t) {
            log.error("Stream subscription error [{}]", t);
        }

        @Override
        public void onComplete() {
            log.info("Completing subscription");
        }
    }

}
