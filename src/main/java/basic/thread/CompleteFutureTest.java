package basic.thread;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class CompleteFutureTest {

    @Test
    public void testSupplyAsync() {

        CompletableFuture<String> completeFuture = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Hello";
        });

        completeFuture.whenCompleteAsync((value, exception) -> {
            System.out.println(value + " world");
        });

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // completeFuture.complete("Haha");

    }


    @Test
    public void testThenApplyAync() {

        try {
            CompletableFuture<String> completeFuture = CompletableFuture.runAsync(() -> {
                System.out.println("first");
            }).thenApply(
                    (first) -> {
                        System.out.println("second");
                        return "first " + "second";
                    }
            );
        /*completeFuture.whenCompleteAsync((value, exception) -> {
            System.out.println(value + " world");
        });*/

            System.out.println("result: " + completeFuture.get(5, TimeUnit.SECONDS));


            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // completeFuture.complete("Haha");

    }


    @Test
    public void tesSupplyAsyncThenApplyAync() {

        try {
            CompletableFuture<String> completeFuture = CompletableFuture.supplyAsync(() -> {
                System.out.println("first");
                return "first";
            }).thenApply(
                    (first) -> {
                        System.out.println("second");
                        return first + " second";
                    }
            ).thenApply(

                    (second) -> {
                        System.out.println("third");
                        return second + " third";
                    }
            );
        /*completeFuture.whenCompleteAsync((value, exception) -> {
            System.out.println(value + " world");
        });*/

            System.out.println("result: " + completeFuture.get(5, TimeUnit.SECONDS));


            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // completeFuture.complete("Haha");

    }

    @Test
    public void testThenApplyAyncMultiTimes() {

        try {
            List<Integer> integerList = Lists.newArrayList(1, 2, 3);
            CompletableFuture<Integer> completeFuture = null;

            completeFuture = CompletableFuture.supplyAsync(() -> integerList.get(0));
            for (int i = 1; i < integerList.size(); i++) {

                Integer temp = integerList.get(i);
                completeFuture = completeFuture.thenApplyAsync(
                        result -> (temp + result)
                );
            }

          /*  CompletableFuture<Integer> result = completeFuture.whenComplete((value, Exception) -> {
                System.out.println("Value:" + value);
            });*/
            Integer result = completeFuture.get(5, TimeUnit.SECONDS);
            System.out.println(result);
            Assert.assertTrue(result == 6);

            Thread.sleep(2000);
        } catch (
                Exception e)

        {
            e.printStackTrace();
        }
        // completeFuture.complete("Haha");

    }

    @Test
    public void testThenApplyBoth() {

        CompletableFuture.runAsync(() -> {
            for (int i = 0; i < 5; i++) {

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("first");
            }
        }).runAfterBothAsync(CompletableFuture.runAsync(() -> {

            for (int i = 0; i < 5; i++) {

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Second");
            }


        }), () -> {
            System.out.println("Over");
        });


        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // completeFuture.complete("Haha");

    }


    @Test
    public void testThenApplyBothStage() {

        try {
            CompletableFuture<String> completeFuture = CompletableFuture.supplyAsync(() -> {
                return " world";
            });

            String result = completeFuture.get();

            CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(new Random().nextInt(1000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(result + "helo1");

                }
                return result + "hello1";
            });

            CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(new Random().nextInt(1000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(result + "hell2");

                }
                return result + "hell2";
            });

            future1.whenCompleteAsync((value, exception) -> {
                System.out.println(value);
            });

            future2.whenCompleteAsync((value, exception) -> {
                System.out.println(value);
            });


            Thread.sleep(10000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // completeFuture.complete("Haha");

    }


}
