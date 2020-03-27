package netty.timer;

import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.TimerTask;

import java.util.concurrent.TimeUnit;

public class HashedWheelTimerTest {

    public static void main(String[] args) {
        HashedWheelTimer hashedWheelTimer = new HashedWheelTimer();

        hashedWheelTimer.newTimeout(new TimerTask() {
            @Override
            public void run(Timeout timeout) throws Exception {
                System.out.println("run 5s");
            }
        },5, TimeUnit.SECONDS);

        hashedWheelTimer.newTimeout(new TimerTask() {
            @Override
            public void run(Timeout timeout) throws Exception {
                System.out.println("run 10s");
            }
        },10, TimeUnit.SECONDS);

        hashedWheelTimer.newTimeout(new TimerTask() {
            @Override
            public void run(Timeout timeout) throws Exception {
                System.out.println("run 30s");
            }
        },30, TimeUnit.SECONDS);

        hashedWheelTimer.start();
    }
}
