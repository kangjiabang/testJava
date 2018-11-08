package distributelock;


import org.junit.Assert;
import org.junit.Test;
import redis.DistributeLock;

/**
 * @Author：zeqi
 * @Date: Created in 20:48 2/4/18.
 * @Description:
 */
public class DistributeLockTest {

    @Test
    public void testLock() {

        Assert.assertEquals(true,DistributeLock.lock("lock","locked",10*1000));
        Assert.assertEquals(false,DistributeLock.lock("lock","locked",10*1000));
        Assert.assertEquals(true,DistributeLock.unLock("lock","locked"));
        Assert.assertEquals(true,DistributeLock.lock("lock","locked",10*1000));
        Assert.assertEquals(false,DistributeLock.unLock("lock","locked_1"));
        Assert.assertEquals(false,DistributeLock.lock("lock","locked",10*1000));

        /*Runnable r = () ->
        {
            System.out.println("start");
        };


        Thread t = new Thread(
                () ->
                    System.out.println("start"));*/
    }
}
