package channel.asynchronous;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @Author：zeqi
 * @Date: Created in 11:45 19/1/18.
 * @Description:
 */
public class AsynSocketChannelServer {


    public static void main(String[] args) {
        AsynSocketChannelServer server = new AsynSocketChannelServer();
        server.runServer();

    }


    public void runServer() {

        AsynchronousServerSocketChannel serverSocketChannel;

        try {
            serverSocketChannel = AsynchronousServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress("localhost", 8081));
            //serverSocketChannel.configureBlocking(false);

            while (true) {

                Future<AsynchronousSocketChannel> socketChannelFuture = serverSocketChannel.accept();

                AsynchronousSocketChannel asynchronousSocketChannel = socketChannelFuture.get();
                processorSocket(asynchronousSocketChannel);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    /**
     * @param socketChannel
     */
    private void processorSocket(AsynchronousSocketChannel socketChannel) {

        if (socketChannel != null && socketChannel.isOpen()) {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            try {
                while (true) {

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    //first read
                    Future<Integer> readResult = socketChannel.read(buffer);
                    readResult.get();
                    buffer.flip();
                    while (buffer.hasRemaining()) {
                        System.out.print((char) buffer.get());
                    }
                    buffer.clear();

                    //then write
                    String mess = "Hello client.";
                    buffer.put(mess.getBytes());
                    buffer.flip();
                    Future<Integer> writeResult =    socketChannel.write(buffer);
                    writeResult.get();
                    System.out.println();
                    buffer.clear();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } finally {
                try {
                    socketChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}


