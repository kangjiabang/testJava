package channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketOption;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Set;

/**
 * @Author：zeqi
 * @Date: Created in 20:02 18/1/18.
 * @Description:
 */
public class SocketChannelClient {
    public static void main(String[] args) {
        new SocketChannelClient().runClient();
}

public void runClient() {
    try {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost",8081));

        while(!socketChannel.finishConnect() ){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

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
            socketChannel.write(buffer);
            buffer.clear();

            //then read
            socketChannel.read(buffer);

            buffer.flip();
            while (buffer.hasRemaining()) {

                System.out.print((char)buffer.get());
            }
            System.out.println();
            buffer.clear();
        }




    } catch (IOException e) {
        e.printStackTrace();
    }

}
}
