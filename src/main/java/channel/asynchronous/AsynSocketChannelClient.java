package channel.asynchronous;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @Author：zeqi
 * @Date: Created in 20:02 18/1/18.
 * @Description:
 */
public class AsynSocketChannelClient {
    public static void main(String[] args) {
        new AsynSocketChannelClient().runClient();
}

public void runClient() {

    try {
        AsynchronousSocketChannel socketChannel = AsynchronousSocketChannel.open();
        Future<Void> future = socketChannel.connect(new InetSocketAddress("localhost",8081));

        future.get();

        System.out.println("connected to server");

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while (true) {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //first  write
            String mess = "Hello server.";
            buffer.put(mess.getBytes());
            buffer.flip();
            Future<Integer>  writeResult = socketChannel.write(buffer);
            writeResult.get();
            buffer.clear();

            //then read
            Future<Integer> readResult = socketChannel.read(buffer);
            readResult.get();

            buffer.flip();
            while (buffer.hasRemaining()) {

                System.out.print((char)buffer.get());
            }
            System.out.println();
            buffer.clear();
        }
    } catch (IOException e) {
        e.printStackTrace();
    } catch (InterruptedException e) {
        e.printStackTrace();
    } catch (ExecutionException e) {
        e.printStackTrace();
    }


}
}
