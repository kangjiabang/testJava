package basic.thread;

public class DaemonThreadTest {

    public static void main(String[] args) {

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    System.out.println("run task");
                }
            }
        });

        t.setDaemon(true);
        t.start();
    }
}
