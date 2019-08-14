package thread;

/**
 * @Author：zeqi
 * @Date: Created in 13:58 13/6/18.
 * @Description:
 */
public class TwinsLockTest {

    public static void main(String[] args) {
        TwinsLock twinsLock = new TwinsLock();
        for (int i=0;i< 10;i++) {
            /*Thread t = new Thread(() -> {
                twinsLock.lock();
                System.out.println(Thread.currentThread().getName() + " lock addSuccess.");
                try {
                    Thread.sleep(4*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    twinsLock.unlock();
                    System.out.println(Thread.currentThread().getName() + " unlock addSuccess.");
                }
            });
            t.start();*/

        }

    }
}
