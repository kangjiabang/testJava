package buffer;

import org.junit.Test;
import sun.nio.ch.DirectBuffer;

import java.nio.ByteBuffer;

/**
 * @Author：zeqi
 * @Date: Created in 16:01 24/1/18.
 * @Description:
 */
public class DirectByteBufferTest {

    @Test
    public void testDirectByteBufferTest() {

        try {
            System.out.println("Hello World!");
            ByteBuffer bb = ByteBuffer.allocateDirect(1024 * 1024 * 1280);
            Thread.sleep(10000);
            ((DirectBuffer)bb).cleaner().clean();
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}
