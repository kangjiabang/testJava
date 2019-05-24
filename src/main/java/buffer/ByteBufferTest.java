package buffer;


import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Scanner;

public class ByteBufferTest {

    /*public static void main(String[] args) {

        File file = new File("/Users/dasouche/Documents/code/testJava/pom.xml");
        long len = file.length();
        byte[] ds = new byte[(int) len];

        try {
            MappedByteBuffer mappedByteBuffer = new RandomAccessFile(file, "r")
                    .getChannel()
                    .map(FileChannel.MapMode.READ_ONLY, 0, len);
            for (int offset = 0; offset < len; offset++) {
                byte b = mappedByteBuffer.get();
                ds[offset] = b;
            }

            Scanner scan = new Scanner(new ByteArrayInputStream(ds)).useDelimiter(" ");
            while (scan.hasNext()) {
                System.out.print(scan.next() + " ");
            }

        } catch (IOException e) {
        }
    }*/


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
