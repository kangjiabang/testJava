package channel;


import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Author：zeqi
 * @Date: Created in 19:41 18/1/18.
 * @Description:
 */
public class ChannelTest {


    @Test
    public void testFileChannelUsePosition() {
        System.out.println(System.getProperty("user.dir"));

        RandomAccessFile readAccessFile = null;
        try {
            readAccessFile = new RandomAccessFile(System.getProperty("user.dir") +
                    "/src/main/resources/spring/spring-service.xml", "rw");


            FileChannel readAccessFileChannel = readAccessFile.getChannel();


            System.out.println(new String(read(readAccessFileChannel,500)));
            System.out.println(new String(read(readAccessFileChannel,1000)));
            System.out.println(new String(read(readAccessFileChannel,1000)));


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                readAccessFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *
     * @param readAccessFileChannel
     * @return
     * @throws IOException
     */
    private byte[] read(FileChannel readAccessFileChannel,int count) throws IOException {

        if (readAccessFileChannel.position() < readAccessFileChannel.size()) {

            long remainingCount = readAccessFileChannel.size() - readAccessFileChannel.position();

            //判断需要读取的字节数
            int countToRead  = (int)(remainingCount > count ? count : remainingCount);

            ByteBuffer buffer = ByteBuffer.allocate(countToRead);

            byte[] bytes = new byte[countToRead];

            //开始读取操作
            readAccessFileChannel.read(buffer);

            buffer.flip();

            //字节数组中的内容放入到bytes中
            buffer.get(bytes,0,bytes.length);

            System.out.println(readAccessFileChannel.position());

            buffer.clear();
            return bytes;
        }

        return new byte[0];
    }


    @Test
    public void testFileChannel() {
        System.out.println(System.getProperty("user.dir"));

        RandomAccessFile readAccessFile = null;
        try {
            readAccessFile = new RandomAccessFile(System.getProperty("user.dir") +
                    "/src/main/resources/spring/spring-service.xml", "rw");


            FileChannel readAccessFileChannel = readAccessFile.getChannel();


            ByteBuffer buffer = ByteBuffer.allocate(1024);

            while (readAccessFileChannel.read(buffer) > 0) {

                buffer.flip();

               while (buffer.hasRemaining()) {
                    System.out.print((char)buffer.get());
                }
                buffer.clear();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                readAccessFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testCopyFileUsingFileChannel() {
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
               /* while (buffer.hasRemaining()) {
                    System.out.print((char)buffer.get());
                }*/
                //防止一次写入未写完
                buffer.compact();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
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


    @Test
    public void testCopyFileUsingFileChannelTransferTo() {
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

            writeAccessFileChannel.transferFrom(readAccessFileChannel,0,readAccessFileChannel.size());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
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
