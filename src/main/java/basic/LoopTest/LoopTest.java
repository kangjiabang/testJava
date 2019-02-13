package basic.LoopTest;

import java.util.concurrent.atomic.AtomicInteger;

public class LoopTest {

    public static void main(String[] args) {

        AtomicInteger atomicInteger = new AtomicInteger();
        while (true) {

            atomicInteger.getAndIncrement();
            if (atomicInteger.get() == 1000000) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                atomicInteger.set(0);
            }
        }
    }
}
