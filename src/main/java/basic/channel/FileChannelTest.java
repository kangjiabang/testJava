package basic.channel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Author：zeqi
 * @Date: Created in 18:44 18/1/18.
 * @Description:
 */
public class FileChannelTest {

    public static void main(String[] args) {

        System.out.println(System.getProperty("user.dir"));

        RandomAccessFile readAccessFile = null;
        RandomAccessFile writeAccessFile = null;
        try {
            readAccessFile = new RandomAccessFile(System.getProperty("user.dir") +
                    "/src/main/resources/spring/spring-service.xml", "rw");

            writeAccessFile = new RandomAccessFile(System.getProperty("user.dir") +
                    "/src/main/resources/spring/spring-service_write.xml", "rw");
            FileChannel readAccessFileChannel = readAccessFile.getChannel();
            FileChannel writeAccessFileChannel = writeAccessFile.getChannel();

            ByteBuffer buffer = ByteBuffer.allocate(1024);

            while (readAccessFileChannel.read(buffer) > 0) {

                buffer.flip();
                writeAccessFileChannel.write(buffer);
               /* while (basic.buffer.hasRemaining()) {
                    System.out.print((char)basic.buffer.get());
                }*/
               //防止一次写入未写完
                buffer.compact();
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                readAccessFile.close();
                writeAccessFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


