package basic.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest {

    public static void main(String[] args) {

        System.out.println(Integer.MIN_VALUE & Integer.MAX_VALUE);
        System.out.println(Integer.MAX_VALUE);

        System.out.println(new AtomicInteger(1).addAndGet(Integer.MAX_VALUE));
    }
}
