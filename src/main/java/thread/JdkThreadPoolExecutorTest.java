package thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2018/11/24.
 */
public class JdkThreadPoolExecutorTest {

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1,2,1000, TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(1));

        for (int i=0;i< 10;i++) {
            threadPoolExecutor.submit(new Runnable() {

                @Override
                public void run() {
                    System.out.println("This is thread:" + Thread.currentThread().getName());
                }
            });
        }

    }
}
