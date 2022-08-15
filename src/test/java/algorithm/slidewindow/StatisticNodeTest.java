package algorithm.slidewindow;

import org.junit.Assert;
import org.junit.Test;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class StatisticNodeTest {


    @Test
    public void testSlideWindow() {
        StatisticNode slideWindow = new StatisticNode(4,1000);

        slideWindow.addSuccess();
        slideWindow.addSuccess();
        slideWindow.addSuccess();
        slideWindow.addSuccess();
        slideWindow.addSuccess();

        Assert.assertTrue(slideWindow.totalSuccess() == 5);
    }

    @Test
    public void testSlideWindowAvgRt() {
        StatisticNode slideWindow = new StatisticNode(4,1000);

        slideWindow.addRtAndSuccess(1000,1);
        slideWindow.addRtAndSuccess(500,1);

        System.out.println("avgRt:" + slideWindow.avgRt());
        Assert.assertTrue(slideWindow.avgRt()== 1500.0/2);
    }

    @Test
    public void testSlideWindowQps() {
        StatisticNode slideWindow = new StatisticNode(4,1000);

        slideWindow.addSuccess();
        slideWindow.addSuccess();
        slideWindow.addSuccess();
        slideWindow.addSuccess();

        System.out.println("qps:" + slideWindow.qps());
        Assert.assertTrue(slideWindow.qps()== 4.0);
    }

    @Test
    public void testSlideWindowQps2() {
        StatisticNode slideWindow = new StatisticNode(4,1000);

        slideWindow.addSuccess();
        slideWindow.addSuccess();
        slideWindow.addSuccess();
        slideWindow.addSuccess();

        System.out.println("qps:" + slideWindow.qps());
        Assert.assertTrue(slideWindow.qps()== 4.0);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i=0;i< 10;i++) {
            slideWindow.addSuccess();
        }
        System.out.println("qps:" + slideWindow.qps());
        Assert.assertTrue(slideWindow.qps()== 10.0);
    }


    @Test
    public void testSlideWindowMultipleThread() {
        StatisticNode slideWindow = new StatisticNode(4,1000);

        CountDownLatch countDownLatch = new CountDownLatch(100);
        for (int i=0;i < 100;i++) {

            Thread t = new Thread(() -> {
                System.out.println("start addSuccess." + Thread.currentThread().getName());
                try {
                    Thread.sleep(300 + new Random().nextInt(300));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                slideWindow.addSuccess();
                slideWindow.addSuccess();
                countDownLatch.countDown();
            });
            t.start();
        }

        try {
            //等待线程执行完毕
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Result:" + slideWindow.totalSuccess());
        Assert.assertTrue(slideWindow.totalSuccess() == 200);
    }

    @Test
    public void testSlideWindowTwo() {
        StatisticNode slideWindow = new StatisticNode(4,1000);

        for (int i=0;i<1000;i++) {

            slideWindow.addSuccess();
        }
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i=0;i<200;i++) {

            slideWindow.addSuccess();
        }

        try {
            Thread.sleep(400);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i=0;i<100;i++) {

            slideWindow.addSuccess();
        }
        System.out.println("result:" + slideWindow.totalSuccess());
        Assert.assertTrue(slideWindow.totalSuccess() == 1300);
    }

    @Test
    public void testSlideWindow2() {
        StatisticNode slideWindow = new StatisticNode(4,1000);

        for (int i=0;i<100;i++) {

            slideWindow.addSuccess();
        }

        Assert.assertTrue(slideWindow.totalSuccess() == 100);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        slideWindow.addSuccess();
        slideWindow.addSuccess();

        Assert.assertTrue(slideWindow.totalSuccess() == 2);
    }

    @Test
    public void testSlideWindow3() {
        StatisticNode slideWindow = new StatisticNode(4,1000);

        for (int i=0;i<10;i++) {

            slideWindow.addSuccess();
        }
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        slideWindow.addSuccess();

        Assert.assertTrue(slideWindow.totalSuccess() == 11);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        slideWindow.addSuccess();
        slideWindow.addSuccess();

        Assert.assertTrue(slideWindow.totalSuccess() == 2);
    }

    @Test
    public void testSlideWindow4() {
        StatisticNode slideWindow = new StatisticNode(2,1000);

        for (int i=0;i<2;i++) {

            slideWindow.addSuccess();
        }
        try {
            Thread.sleep(501);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        slideWindow.addSuccess();

        Assert.assertTrue(slideWindow.totalSuccess() == 3);
        try {
            Thread.sleep(501);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //the first will expire
        slideWindow.addSuccess();

        Assert.assertTrue(slideWindow.totalSuccess() == 2);
    }
}
