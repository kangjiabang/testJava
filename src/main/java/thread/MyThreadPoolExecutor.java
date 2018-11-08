package thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author：zeqi
 * @Date: Created in 19:06 11/1/18.
 * @Description:
 */
public class MyThreadPoolExecutor {


    //核心线程数
    private int corePoolSize = 0;
    //最大线程数
    private int maxPoolSize = 0;

    //worker 数组
    private List<Worker> workers;
    //当前线程数
    private AtomicInteger currPoolSize = new AtomicInteger(0);
    //阻塞队列
    private BlockingQueue<Runnable> queue;

    private ExecutionHandler executionHandler;

    private ReentrantLock lock;

    public MyThreadPoolExecutor(int corePoolSize, int maxPoolSize, BlockingQueue queue) {
        this(corePoolSize, maxPoolSize, queue, new AbortPolicy());
    }

    public MyThreadPoolExecutor(int corePoolSize, int maxPoolSize, BlockingQueue queue, ExecutionHandler executionHandler) {
        this.corePoolSize = corePoolSize;
        this.maxPoolSize = maxPoolSize;
        this.queue = queue;
        this.executionHandler = executionHandler;
        workers = new ArrayList<Worker>(corePoolSize);
        lock = new ReentrantLock();
    }

    public void execute(Runnable r) {
        try {
            lock.lock();
            //线程池中线程未超过核心线程池数目时，新增线程
            if (currPoolSize.get() <= corePoolSize) {
                Worker w = new Worker("work" + currPoolSize.get(),r);
                workers.add(w);
                currPoolSize.incrementAndGet();
                w.t.start();
            } else if (!addQueue(r)) {
                executionHandler.deal(r);
            }
        } finally {
            lock.unlock();
        }


    }

    /**
     * 将线程放到阻塞队列中
     *
     * @param r
     * @return true 如果成功插入队列 false 如果队列元素已经满了
     */
    private boolean addQueue(Runnable r) {
        return queue.offer(r);
    }


    /**
     * 新的工作线程
     */
    private class Worker implements Runnable {

        Thread t = null;

        private String workName;

        private Runnable firstTask;

        public Worker(String name,Runnable r) {
            this.workName = name;
            t = new Thread(this);
            firstTask = r;
        }

        public void runWorker() {
            while (firstTask != null || (firstTask = queue.poll()) != null) {

                try {
                    firstTask.run();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    firstTask = null;
                }

            }
            currPoolSize.getAndDecrement();
            //线程执行完成，从list中移除
            workers.remove(this);
        }

        @Override
        public void run() {
            runWorker();
        }


        public String getWorkerName() {
            return workName;
        }

        public void setWorkerName(String name) {
            this.workName = name;
        }
    }

    interface ExecutionHandler {
        void deal(Runnable r);
    }

    public static class AbortPolicy implements ExecutionHandler {

        public void deal(Runnable r) {
            System.out.println("thred is abort");
        }
    }

    public static class ExecutePolicy implements ExecutionHandler {

        public void deal(Runnable r) {
            new Thread(r).start();
        }
    }
}
