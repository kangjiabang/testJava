/*
package basic.thread.ttl;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.TtlRunnable;

import java.util.concurrent.*;

*/
/**
 * Created by Administrator on 2018/11/24.
 *//*

public class TtlThreadPoolExecutorTest {

    static TransmittableThreadLocal<String> ttlThreadLocal = new TransmittableThreadLocal();
    static InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal();

    public static void main(String[] args) {
        ttlThreadLocal.set("ttlThreadLocal");
        inheritableThreadLocal.set("inheritableThreadLocal");
       // final ExecutorService executorService = TtlExecutors.getTtlExecutorService(Executors.newFixedThreadPool(2));

         ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1,2,1000, TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(10));

        for (int i = 0; i < 1; i++) {
            threadPoolExecutor.submit(TtlRunnable.get(new Runnable() {
                @Override
                public void run() {
                    System.out.println("ttlThreadLocal:" + ttlThreadLocal.get());
                    System.out.println("inheritableThreadLocal:" + inheritableThreadLocal.get());
                    System.out.println("This is basic.thread:" + Thread.currentThread().getName());
                }
            }, true));
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //模拟新的请求不携带threadLocal，线程变量是否会被线程池复用
        ttlThreadLocal.set("");
        inheritableThreadLocal.set("");
        //新的线程
       // new Thread(() -> {
            threadPoolExecutor.submit(TtlRunnable.get(new Runnable() {
                @Override
                public void run() {
                    System.out.println("new ttlThreadLocal:" + ttlThreadLocal.get());
                    System.out.println("new inheritableThreadLocal:" + inheritableThreadLocal.get());
                    System.out.println("new This is basic.thread:" + Thread.currentThread().getName());
                }
            }, true));
      //  }).start();

        threadPoolExecutor.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("Result");
            }
        });

    }
}
*/
