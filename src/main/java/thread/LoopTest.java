package thread;

/**
 * @Author：zeqi
 * @Date: Created in 14:39 18/5/18.
 * @Description:
 */
public class LoopTest {

    public static void main(String[] args) {
        try {
            Thread.sleep(1000*5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while(true) {
            int count = 0;
            for (int i=0;i< Integer.MAX_VALUE;i++) {
                count++;
                if (count%500 ==0) {
                   
                }
            }

        }
    }
}


