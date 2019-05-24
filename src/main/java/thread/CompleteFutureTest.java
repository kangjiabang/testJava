package thread;

import io.netty.util.concurrent.CompleteFuture;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class CompleteFutureTest {

    public static void main(String[] args) {

        CompletableFuture<String> completeFuture = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Hello";
        });

        completeFuture.whenCompleteAsync((value,exception) -> {
            System.out.println(value + " world");
        });
        completeFuture.complete("Haha");

    }
}
