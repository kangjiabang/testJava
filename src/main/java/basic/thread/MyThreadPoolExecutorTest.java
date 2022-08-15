package basic.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author：zeqi
 * @Date: Created in 20:07 11/1/18.
 * @Description:
 */
public class MyThreadPoolExecutorTest {

    public static void main(String[] args) {

        MyThreadPoolExecutor myThreadPoolExecutor =
                new MyThreadPoolExecutor(2,2,new ArrayBlockingQueue(2),new MyThreadPoolExecutor.AbortPolicy());

        AtomicInteger atomicInteger = new AtomicInteger();
        for (int i=0;i< 100 ;i++) {

            /*myThreadPoolExecutor.execute(() -> {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("basic.thread:" + atomicInteger.getAndIncrement());
            });*/
        }

    }
}
