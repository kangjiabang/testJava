package basic.thread;

/**
 * @Author：zeqi
 * @Date: Created in 14:39 18/5/18.
 * @Description:
 */
public class DeadLockTest {

    private Object lock1 = new Object();

    private Object lock2 = new Object();


    public static void main(String[] args) {
        DeadLockTest deadLockTest = new DeadLockTest();
        Thread produerThread = new Thread(deadLockTest.new Producer());
        Thread consumerThread = new Thread(deadLockTest.new Consumer());

        produerThread.start();
        consumerThread.start();
    }

    private void silentlySleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {

        }
    }

    class Producer implements Runnable {

        @Override
        public void run() {
            synchronized(lock1) {

                silentlySleep(3*1000);

                synchronized (lock2) {
                    silentlySleep(5*1000);
                    System.out.println("producing");
                }

            }
        }
    }

    class Consumer implements Runnable {

        @Override
        public void run() {
            synchronized(lock2) {

                silentlySleep(3*1000);

                synchronized (lock1) {
                    silentlySleep(5*1000);
                    System.out.println("consuming");
                }

            }
        }


    }

}


