package basic.thread.reactor;

import org.junit.Test;
import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicLong;

public class ReactorTest {

    @Test
    public void testFlux() {
        Flux<Integer> ints = Flux.range(1,3);
        ints.subscribe();
    }

    @Test
    public void testFluxPrint() {
        Flux<Integer> ints = Flux.range(1,3);
        ints.subscribe(i -> System.out.println(i));
    }

    @Test
    public void testFluxWithError() {
        Flux<Integer> ints = Flux.range(1,4).map(i -> {
            if (i <=3) return i;
            throw new RuntimeException("Got to 4");
        });
        ints.subscribe(i -> System.out.println(i), error -> {
            System.err.println("Error:" + error);
        });
    }

    @Test
    public void testFluxWithDone() {
        Flux<Integer> ints = Flux.range(1,4);
        ints.subscribe(i -> System.out.println(i), error -> {
            System.err.println("Error:" + error);
        }, () -> System.out.println("Done"));
    }

    @Test
    public void testFluxWithSubscripton() {
        Flux<Integer> ints = Flux.range(1,12);
        ints.subscribe(i -> System.out.println(i), error -> {
            System.err.println("Error:" + error);
        }, () -> System.out.println("Done"), sub -> sub.request(8));
    }

    @Test
    public void testFluxWithGenerate() {
        Flux<String> flux = Flux.generate(
                AtomicLong::new,
                (state, sink) -> {
                    long i = state.getAndIncrement();
                    sink.next("3 x " + i + " = " + 3*i);
                    if (i == 10) sink.complete();
                    return state;
                },(state) -> System.out.println("state: " + state));


    }


}
