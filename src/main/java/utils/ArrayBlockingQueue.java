package utils;

import org.springframework.util.Assert;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author：zeqi
 * @Date: Created in 13:59 9/1/18.
 * @Description:
 */
public class ArrayBlockingQueue<E> {

    private Object[] items;

    private final ReentrantLock lock;

    private int count;

    private  int putIndex;

    private  int takeIndex;

    private Condition  notEmpty;


    private Condition notFull;


    public ArrayBlockingQueue(int size) {
        items = new Object[size];
        lock = new ReentrantLock();
        notEmpty = lock.newCondition();
        notFull = lock.newCondition();
    }

    /**
     * 获取一个元素，阻塞的方法
     * @param e
     * @throws InterruptedException
     */
    public void put(E e) throws InterruptedException {
        Assert.notNull(e,"element can not be Null");
        final ReentrantLock lock = this.lock;
        try {
            lock.lockInterruptibly();

            while (count == items.length) {
                System.out.println("put blocked. wait not full");
                notFull.await();
            }
            items[putIndex] = e;
            if (++putIndex == items.length) {
                putIndex = 0;
            }
            count++;
            notEmpty.signal();
        }
        finally {
            lock.unlock();
        }

    }


    /**
     * 插入一个元素，等待一段时间
     * @param e
     * @param time
     * @param unit
     * @return
     * @throws InterruptedException
     */
    public boolean offer(E e,long time,TimeUnit unit) throws InterruptedException {
        Assert.notNull(e,"element can not be Null");
        final ReentrantLock lock = this.lock;
        long nanoTime = unit.toNanos(time);
        try {
            lock.lockInterruptibly();

            while (count == items.length) {
                System.out.println("put blocked. wait not full");
                if (nanoTime <= 0) {
                    return false;
                }
                nanoTime = notFull.awaitNanos(nanoTime);
            }
            items[putIndex] = e;
            if (++putIndex == items.length) {
                putIndex = 0;
            }
            count++;
            notEmpty.signal();
            return true;
        }
        finally {
            lock.unlock();
        }

    }

    /**
     * 获取阻塞队列中元素
     * @return
     * @throws InterruptedException
     */
    public E take() throws InterruptedException {

        final ReentrantLock lock = this.lock;
        try {
            lock.lockInterruptibly();
            while (count == 0) {
                System.out.println("take blocked. wait not empty");
                notEmpty.await();
            }
            E e = (E)items[takeIndex];
            if (++takeIndex == items.length) {
                takeIndex = 0;
            }
            count--;
            notFull.signal();
            return e;
        } finally {
            lock.unlock();
        }
    }


    /**
     * 获取阻塞队列中元素
     * @return
     * @throws InterruptedException
     */
    public E poll(long time,TimeUnit unit) throws InterruptedException {

        final ReentrantLock lock = this.lock;
        try {
            lock.lockInterruptibly();
            long nanoTime = unit.toNanos(time);
            while (count == 0) {
                System.out.println("take blocked. wait not empty");
                if (nanoTime <=0) {
                    return null;
                }
                nanoTime = notEmpty.awaitNanos(nanoTime);
            }
            E e = (E)items[takeIndex];
            if (++takeIndex == items.length) {
                takeIndex = 0;
            }
            count--;
            notFull.signal();
            return e;
        } finally {
            lock.unlock();
        }
    }

}
