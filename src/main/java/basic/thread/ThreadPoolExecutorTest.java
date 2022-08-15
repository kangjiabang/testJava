package basic.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author：zeqi
 * @Date: Created in 18:50 3/1/18.
 * @Description:
 */
public class ThreadPoolExecutorTest {

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                0,30,1000, TimeUnit.MILLISECONDS,new ArrayBlockingQueue<Runnable>(0));

        for (int i = 0;i< 5;i++) {
            executor.execute(() -> {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("threadName: " + Thread.currentThread().getName());
            });
        }
        System.out.println("executor detail: activeSize:" + executor.getActiveCount());

        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("executor detail: largestPoolSize:" + executor.getLargestPoolSize());
        System.out.println("executor detail: corePoolSize:" + executor.getCorePoolSize());
        System.out.println("executor detail: activeSize:" + executor.getActiveCount());

        for (int i = 0;i< 5;i++) {
            executor.execute(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("threadName: " + Thread.currentThread().getName());
            });
        }
        System.out.println("executor detail: activeSize:" + executor.getActiveCount());

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("executor detail: largestPoolSize:" + executor.getLargestPoolSize());
        System.out.println("executor detail: corePoolSize:" + executor.getCorePoolSize());
        System.out.println("executor detail: activeSize:" + executor.getActiveCount());


    }
}
