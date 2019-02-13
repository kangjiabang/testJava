package thread;

public class InheritableThreadLocalTest {

    private static InheritableThreadLocal<String> nameThreadLocal = new InheritableThreadLocal<>();

    public static void main(String[] args) {
        nameThreadLocal.set("kang");
        System.out.println(nameThreadLocal.get());
        new Thread(new SubRunnable()).start();
    }


    static class SubRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println(nameThreadLocal.get());
        }
    }
}
