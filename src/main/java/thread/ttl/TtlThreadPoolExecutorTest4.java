package thread.ttl;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.TtlRunnable;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2018/11/24.
 */
public class TtlThreadPoolExecutorTest4 {

    static TransmittableThreadLocal<String> ttlThreadLocal = new TransmittableThreadLocal();

    public static void main(String[] args) {
        ttlThreadLocal.set("perf");


        Thread subThread = new Thread(TtlRunnable.get(new Runnable() {


            ThreadLocal<String> threadLocal = new ThreadLocal();

            @Override
            public void run() {
                threadLocal.set("alibaba");
                System.out.println("ttlThreadLocal:" + ttlThreadLocal.get());
                System.out.println("subTheadValue:" + threadLocal.get());
                System.out.println("This is thread:" + Thread.currentThread().getName());
            }
        }, true));
        subThread.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //模拟新的请求不携带threadLocal，线程变量是否会被线程池复用
        ttlThreadLocal.set("");

    }
}
