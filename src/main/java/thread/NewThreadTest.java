package thread;

/**
 * @Author：zeqi
 * @Date: Created in 14:39 18/5/18.
 * @Description:
 */
public class NewThreadTest {

    public static void main(String[] args) {

        for (int i=0;i< 1000;i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            new Thread(() -> {
                System.out.println("start");
            }).start();
        }
    }


}


