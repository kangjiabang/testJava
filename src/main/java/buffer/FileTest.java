package buffer;

import io.netty.buffer.UnpooledHeapByteBuf;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Scanner;

public class FileTest {

    public static void main(String[] args) {

                File file = new File("/Users/dasouche/Documents/work/test1.txt");
                long len = file.length();
                byte[] ds = new byte[(int) len];

                try {

                    if (!file.exists()) {
                        file.createNewFile();
                        FileOutputStream fileOutputStream = new FileOutputStream(file);

                        ByteBuffer byteBuffer = ByteBuffer.allocate(25);
                        byteBuffer.putInt(4);
                        byteBuffer.putLong(8l);
                        byteBuffer.putLong(9l);
                        byteBuffer.flip();


                        fileOutputStream.write(convert(byteBuffer));

                        fileOutputStream.flush();
                    } else {
                        System.out.println("File length:" + file.length());
                    }



                } catch (IOException e) {}
                finally {

                }
            }

    //必须调用完后flip()才可以调用此方法
    public static byte[] convert(ByteBuffer byteBuffer){
        int len = byteBuffer.limit() - byteBuffer.position();
        byte[] bytes = new byte[len];

        if(byteBuffer.isReadOnly()){
            return null;
        }else {
            byteBuffer.get(bytes);
        }
        return bytes;
    }


}
