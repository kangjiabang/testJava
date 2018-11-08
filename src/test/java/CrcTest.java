
import org.junit.Test;

import java.util.zip.Adler32;
import java.util.zip.Checksum;

/**
 * @Author：zeqi
 * @Date: Created in 15:19 24/1/18.
 * @Description:
 */
public class CrcTest {

    @Test
    public void testCrcTest() {
        byte[] bytes = "Hello world".getBytes();
        //生成crc校验码
        Checksum checksum = new Adler32();
        checksum.update(bytes,0,bytes.length);
        System.out.println(checksum.getValue());
    }
}
