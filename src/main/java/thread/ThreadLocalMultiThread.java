package thread;

import com.google.common.collect.Maps;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadLocalMultiThread {


    static ThreadLocal<Map<String,String>> threadLocal = new ThreadLocal<>();
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        //threadLocal.set("thread ");
        //如果存储的值是全局变量，可能会有线程安全问题
        Map<String,String> map = Maps.newHashMap();
        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {

                map.put("a","20");
                threadLocal.set(map);
                System.out.println(threadLocal.get());


            }
        };
        Thread t1 = new Thread(runnable1);
        t1.start();

        Runnable runnable2 = new Runnable() {
            @Override
            public void run() {

               // Map<String,String> map2 = Maps.newHashMap();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                map.put("a","30");
                threadLocal.set(map);
                System.out.println(threadLocal.get());
            }
        };
        Thread t2 = new Thread(runnable2);
        t2.start();

    }
}
