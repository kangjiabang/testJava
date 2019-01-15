package thread;

import org.springframework.util.StopWatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

public class AtomicIntegerPerformanceTest {

    private static AtomicInteger atomicInteger = new AtomicInteger();
    private static LongAdder longAdder = new LongAdder();


    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        StopWatch watch = new StopWatch();
        watch.start("atmoicInteger");
        for (int i =0 ;i < 2000;i++) {
            new Thread(() -> {
                atomicInteger.getAndIncrement();
            }).start();
        }
        watch.stop();
        System.out.println("atomicInteger time spent:" + watch.getLastTaskTimeMillis());

        watch.start("long adder");
        for (int i =0 ;i < 2000;i++) {
            new Thread(() -> {
                longAdder.increment();
            }).start();
        }
        watch.stop();
        System.out.println("atomicInteger time spent:" + watch.prettyPrint());
    }


}
