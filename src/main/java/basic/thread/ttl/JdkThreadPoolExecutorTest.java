package basic.thread.ttl;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2018/11/24.
 */
public class JdkThreadPoolExecutorTest {

    static InheritableThreadLocal<String>  inheritableThreadLocal = new InheritableThreadLocal();
    static ThreadLocal<String>  threadLocal = new ThreadLocal();

    public static void main(String[] args) {
        inheritableThreadLocal.set("perf");
        threadLocal.set("inheritableThreadLocal");
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1,2,1000, TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(10));

        for (int i=0;i< 10;i++) {
            threadPoolExecutor.submit(new Runnable() {
                //static ThreadLocal<String>  inheritableThreadLocal = new ThreadLocal();
                @Override
                public void run() {
                    System.out.println("inheritableThreadLocal:" + inheritableThreadLocal.get());
                    System.out.println("inheritableThreadLocal:" + threadLocal.get());
                    System.out.println("This is basic.thread:" + Thread.currentThread().getName());
                }
            });
        }
        //模拟新的请求不携带threadLocal，线程变量是否会被线程池复用，实际上线程变量是会被复用
        inheritableThreadLocal.set("");
        new Thread(() -> {
            threadPoolExecutor.submit(new Runnable() {

                @Override
                public void run() {
                    System.out.println("new inheritableThreadLocal:" + inheritableThreadLocal.get());
                    System.out.println("new inheritableThreadLocal:" + threadLocal.get());
                    System.out.println("new This is basic.thread:" + Thread.currentThread().getName());
                }
            });
        }).start();

    }
}
