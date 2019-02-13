import org.junit.Test;

import java.nio.ByteBuffer;

/**
 * @Author：zeqi
 * @Date: Created in 16:08 24/1/18.
 * @Description:
 */
public class DirectByteBufferTest {

    public static void main(String[] args) {

        //使用full gc 回收直接内存的配置方法  -verbose:gc -XX:+PrintGCDetails
        //内存溢出的配置方法。 -verbose:gc -XX:+PrintGCDetails  -XX:+DisableExplicitGC   -XX:MaxDirectMemorySize=100M
            while(true) {
                ByteBuffer bb = ByteBuffer.allocateDirect(1024 * 1024 * 4);
                try {
                    Thread.sleep(4*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
    }
}
