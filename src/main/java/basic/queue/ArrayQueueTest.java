package basic.queue;

import java.util.ArrayDeque;

/**
 * Created by Administrator on 2018/11/23.
 */
public class ArrayQueueTest {

    public static void main(String[] args) {
        ArrayDeque arrayDeque = new ArrayDeque(8);
        arrayDeque.addFirst(1);
        arrayDeque.addFirst(2);
        arrayDeque.addLast(10);
        arrayDeque.addFirst(3);
        arrayDeque.addFirst(4);
        arrayDeque.addFirst(5);
    }
}
