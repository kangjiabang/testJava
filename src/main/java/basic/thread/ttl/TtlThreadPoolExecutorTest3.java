/*
package basic.thread.ttl;

import com.alibaba.ttl.threadpool.TtlExecutors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

*/
/**
 * Created by Administrator on 2018/11/24.
 *//*

public class TtlThreadPoolExecutorTest3 {

    static InheritableThreadLocal<String> ttlThreadLocal = new InheritableThreadLocal<>();
    static ThreadLocal<String>  threadLocal = new ThreadLocal();

    public static void main(String[] args) {
        ttlThreadLocal.set("perf");
        threadLocal.set("inheritableThreadLocal");
        final ExecutorService executorService = TtlExecutors.getTtlExecutorService(Executors.newFixedThreadPool(2));

         //ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1,2,1000, TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(10));

        for (int i = 0; i < 10; i++) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println("ttlThreadLocal:" + ttlThreadLocal.get());
                    System.out.println("inheritableThreadLocal:" + threadLocal.get());
                    System.out.println("This is basic.thread:" + Thread.currentThread().getName());
                }
            });
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //模拟新的请求不携带threadLocal，线程变量是否会被线程池复用
        ttlThreadLocal.set("");
        //新的线程
        new Thread(() -> {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println("new ttlThreadLocal:" + ttlThreadLocal.get());
                    System.out.println("new inheritableThreadLocal:" + threadLocal.get());
                    System.out.println("new This is basic.thread:" + Thread.currentThread().getName());
                }
            });
        }).start();

    }
}
*/
