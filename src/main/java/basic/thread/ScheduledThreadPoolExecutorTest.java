package basic.thread;

import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Author：zeqi
 * @Date: Created in 15:23 4/1/18.
 * @Description:
 */
public class ScheduledThreadPoolExecutorTest {

    public static void main(String[] args) {
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = null;
        try {
            scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(4);
            final CountDownLatch end = new CountDownLatch(10);
            StopWatch watch = new StopWatch();
            watch.start();
            List<Future> result = new ArrayList<Future>();
            for (int i=0;i< 10;i++) {

                result.add(scheduledThreadPoolExecutor.schedule(new Callable<String>() {
                    @Override
                    public String call() {

                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(Thread.currentThread().getName());
                        end.countDown();
                        return "addSuccess";
                    }
                },2, TimeUnit.SECONDS));
            }
            end.await();
            watch.stop();
            System.out.println("time spend: " + watch.getTotalTimeMillis() + " ms");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {

            scheduledThreadPoolExecutor.shutdown();
        }
    }
}
