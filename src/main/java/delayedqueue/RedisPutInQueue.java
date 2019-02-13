package delayedqueue;

import org.redisson.Redisson;
import org.redisson.api.RBlockingQueue;
import org.redisson.api.RDelayedQueue;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.concurrent.TimeUnit;

    /**
     * Author:ZhuShangJin
     * Date:2018/8/31
     */
    public class RedisPutInQueue {
        public static void main(String args[]) {
            Config config = new Config();
            config.useSingleServer().setAddress("redis://192.168.19.173:6379").setPassword("ps666").setDatabase(2);
            RedissonClient redissonClient = Redisson.create(config);
            RBlockingQueue<CallCdr> blockingFairQueue = redissonClient.getBlockingQueue("delay_queue");

            RDelayedQueue<CallCdr> delayedQueue = redissonClient.getDelayedQueue(blockingFairQueue);
            for (int i = 0; i <10 ; i++) {
                try {
                    Thread.sleep(1*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 一分钟以后将消息发送到指定队列
                //相当于1分钟后取消订单
//             延迟队列包含callCdr 1分钟，然后将其传输到blockingFairQueue中
                //在1分钟后就可以在blockingFairQueue 中获取callCdr了
                CallCdr callCdr = new CallCdr();
                delayedQueue.offer(callCdr, 1, TimeUnit.MINUTES);

            }
//         在该对象不再需要的情况下，应该主动销毁。
// 仅在相关的Redisson对象也需要关闭的时候可以不用主动销毁。
            delayedQueue.destroy();

//redissonClient.shutdown();
        }
}
*/
