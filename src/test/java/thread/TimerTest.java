package thread;

import org.springframework.util.StopWatch;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author：zeqi
 * @Date: Created in 18:50 3/1/18.
 * @Description:
 */
public class TimerTest {

    public static void main(String[] args) {
        Timer timer = new Timer();
        StopWatch watch = new StopWatch();
        watch.start();
        for (int i = 0;i< 10;i++) {

            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("threadName: " + Thread.currentThread().getName());
                }
            },1);

        }
        watch.stop();

        System.out.println("time spent: " + watch.getTotalTimeMillis());
    }
}
