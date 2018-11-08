package thread;

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
                2,3,1000, TimeUnit.MILLISECONDS,new ArrayBlockingQueue<Runnable>(20));

        for (int i = 0;i< 10;i++) {

            /*executor.execute(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("threadName: " + Thread.currentThread().getName());
            });*/
        }
    }
}
