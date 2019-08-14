package thread.ttl;

import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TtlThreadTest {


    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);

        try {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println("Run");
                }
            });
        } catch (Throwable e) {
            e.printStackTrace();
        }

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
