package thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @Author：zeqi
 * @Date: Created in 10:52 13/6/18.
 * @Description:
 */
public class TwinsLock  implements Lock{


    private Sync sync;
    public TwinsLock() {
        sync = new Sync(2);
    }
    private static class Sync extends AbstractQueuedSynchronizer {

        public Sync(int count) {
           this.setState(count);
        }

        @Override
        protected int tryAcquireShared(int acquires) {
            for(;;) {
                int stateCount = this.getState();
                int newStateCount = stateCount - acquires;
                if (newStateCount < 0 || compareAndSetState(stateCount,newStateCount )) {
                    return newStateCount;
                }
            }
        }

        @Override
        protected boolean tryReleaseShared(int acquires) {
            for(;;) {
                int stateCount = this.getState();
                int newStateCount = stateCount + acquires;
                return  compareAndSetState(stateCount,newStateCount);
            }
        }
    }

    @Override
    public void lock() {
        sync.acquireShared(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquireShared(1) > 0;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1,unit.toNanos(time));
    }

    @Override
    public void unlock() {
         sync.releaseShared(1);
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
