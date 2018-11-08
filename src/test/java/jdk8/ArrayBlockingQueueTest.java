package jdk8;

import org.junit.Test;
import utils.ArrayBlockingQueue;

import java.util.concurrent.TimeUnit;

/**
 * @Author：zeqi
 * @Date: Created in 14:34 9/1/18.
 * @Description:
 */
public class ArrayBlockingQueueTest {


    @Test
    public void testArrayBlockingQueue() {
        try {
            ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(3);
            queue.put(1);
            queue.put(2);
            queue.put(3);
            //queue.put(4);
            System.out.println(queue.take());
            System.out.println(queue.take());
            System.out.println(queue.take());
            System.out.println(queue.take());
            System.out.println(queue.take());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testArrayBlockingQueueTimeOuut() {
        try {
            ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(3);
            queue.offer(1,2,TimeUnit.SECONDS);
            queue.offer(2,2,TimeUnit.SECONDS);
            queue.offer(3,2,TimeUnit.SECONDS);
            System.out.println(queue.offer(4,2,TimeUnit.SECONDS));
            System.out.println(queue.poll(2,TimeUnit.SECONDS));
            System.out.println(queue.poll(2,TimeUnit.SECONDS));
            System.out.println(queue.poll(2,TimeUnit.SECONDS));
            System.out.println(queue.poll(2,TimeUnit.SECONDS));
            System.out.println(queue.poll(2,TimeUnit.SECONDS));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testTimeUnitNanoTimes() {
        System.out.println(TimeUnit.SECONDS.toNanos(1));
        System.out.println(TimeUnit.MILLISECONDS.toNanos(1));
    }
}
