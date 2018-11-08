package channel.selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * @Author：zeqi
 * @Date: Created in 20:15 18/1/18.
 * @Description:
 */
public class SelectorServer {



    private ServerSocketChannel serverSocketChannel;

    private Selector selector = null;

    public SelectorServer() {
        try {
            selector = Selector.open();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SelectorServer server = new SelectorServer();
        server.runServer();
    }
    public void runServer() {

        try {
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress("localhost", 8081));
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            while (true) {
                int count = selector.select();
                if (count > 0) {
                    Iterator<SelectionKey> iters = selector.selectedKeys().iterator();
                    while (iters.hasNext()) {
                        SelectionKey key = iters.next();

                        if (key.isConnectable()) {
                            System.out.println("there is connect");
                        }
                        else if (key.isAcceptable()) {
                            System.out.println("there is accept()");
                            processAccept(serverSocketChannel);
                        } else if (key.isReadable()) {
                            System.out.println("there is info to read");
                            processRead(key);
                        } else if (key.isWritable()) {
                            System.out.println("there is info to write");
                            processWrite(key);
                        }
                        iters.remove();
                    }

                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processAccept(ServerSocketChannel serverSocketChannel) {
        try {
            SocketChannel socketChannel = serverSocketChannel.accept();
            socketChannel.configureBlocking(false);
            socketChannel.register(selector,SelectionKey.OP_READ);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processWrite(SelectionKey key) {
        SocketChannel socketChannel = (SocketChannel)key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put("message from server.".getBytes());
        buffer.flip();
        while (buffer.hasRemaining()) {
            try {
                socketChannel.write(buffer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        buffer.clear();
        try {
            socketChannel.register(selector,SelectionKey.OP_READ);
        } catch (ClosedChannelException e) {
            e.printStackTrace();
        }

    }

    private void processRead(SelectionKey key) {
        try {
            SocketChannel socketChannel = (SocketChannel)key.channel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            socketChannel.read(buffer);
            buffer.flip();
            String output = new String(buffer.array()).trim();
            System.out.println("message read from client: " + output);

            socketChannel.register(selector,SelectionKey.OP_WRITE);
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
