package buffer;


import org.junit.Test;
import org.springframework.util.Assert;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Scanner;

public class MappedByteBufferWriteTest {

    @Test
    public void testMappedByteBufferWrite() {

        try {

            URL url = MappedByteBufferWriteTest.class.getClassLoader().getResource("test.yaml");
            Assert.notNull(url,"can not find test.yaml in class path");

            File file = new File(url.getFile());
            long len = file.length();
            byte[] ds = new byte[(int) len];

            MappedByteBuffer mappedByteBuffer = new RandomAccessFile(file, "rw")
                    .getChannel()
                    .map(FileChannel.MapMode.READ_WRITE, 0, len + 1000);

            mappedByteBuffer.position((int)len);
            mappedByteBuffer.put("Hello world".getBytes());
            mappedByteBuffer.force();

            Scanner scan = new Scanner(new ByteArrayInputStream(ds)).useDelimiter(" ");
            while (scan.hasNext()) {
                System.out.print(scan.next() + " ");
            }

        } catch (IOException e) {
        }
    }


    @Test
    public void slice() {

        ByteBuffer byteBuffer = ByteBuffer.allocate(100);
        //从第6个开始截取，截取5个字符串
        byteBuffer.put("Hello world".getBytes());
        byteBuffer.position(6);
        ByteBuffer sliceResult = byteBuffer.slice();
        sliceResult.limit(5);
        System.out.println(new String(convert(sliceResult)));

    }


    //必须调用完后flip()才可以调用此方法
    public static byte[] convert(ByteBuffer byteBuffer) {
        int len = byteBuffer.limit() - byteBuffer.position();
        byte[] bytes = new byte[len];

        if (byteBuffer.isReadOnly()) {
            return null;
        } else {
            byteBuffer.get(bytes);
        }
        return bytes;
    }

}
