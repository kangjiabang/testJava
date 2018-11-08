package thread;

/**
 * @Author：zeqi
 * @Date: Created in 20:03 25/4/18.
 * @Description:
 */
public class StopThread {

    public static volatile boolean isStop = false;
    public static void main(String[] args) {


       /* Runnable r = () -> {
            int i = 0;
            while (!isStop) {
                i++;
            }
        };
        Thread t = new Thread(r);
        t.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        isStop = true;*/

    }
}
