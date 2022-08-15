package basic.channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @Author：zeqi
 * @Date: Created in 20:15 18/1/18.
 * @Description:
 */
public class SocketChannelServer {


    private ServerSocketChannel serverSocketChannel;

    public static void main(String[] args) {
        SocketChannelServer server = new SocketChannelServer();
        server.runServer();

    }


    public void runServer() {

        try {
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress("localhost", 8081));
            //serverSocketChannel.configureBlocking(false);
            while (true) {

                SocketChannel socketChannel = serverSocketChannel.accept();

                processorSocket(socketChannel);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param socketChannel
     */
    private void processorSocket(SocketChannel socketChannel) {

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while (true) {

            try {

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //first read
                socketChannel.read(buffer);

                buffer.flip();
                while (buffer.hasRemaining()) {
                    System.out.print((char) buffer.get());
                }
                buffer.clear();

                //then write
                String mess = "Hello client.";
                buffer.put(mess.getBytes());
                buffer.flip();
                while (buffer.hasRemaining()) {

                    socketChannel.write(buffer);
                }
                System.out.println();
                buffer.clear();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
