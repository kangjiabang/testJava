package basic.thread;

/**
 * @Author：zeqi
 * @Date: Created in 14:39 18/5/18.
 * @Description:
 */
public class NewThreadTest {

    private static int count = 0;

    public static void main(String[] args) {


        for (int i=0;i< 3;i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            new Thread(() -> {
                count++;

                System.out.println(Thread.currentThread().getName() +  " " + count);


            }).start();
        }
    }


}


