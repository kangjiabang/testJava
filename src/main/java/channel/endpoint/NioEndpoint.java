package channel.endpoint;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author：zeqi
 * @Date: Created in 20:15 18/1/18.
 * @Description:
 */
public class NioEndpoint {


    private ServerSocketChannel serverSocketChannel;

    private Acceptor[] acceptors = null;

    private int acceptorCount = 10;

    private  Poller[] pollers = null;

    private AtomicInteger loopInteger = new AtomicInteger();

    public static void main(String[] args) {
        NioEndpoint server = new NioEndpoint();
        server.init();

        server.startInternal();


    }

    /**
     *
     */
    private void startInternal() {
        startAcceptorsThread();
    }

    private void startAcceptorsThread() {
        acceptors = new Acceptor[acceptorCount];
        for (int i=0 ;i < acceptors.length;i++) {
            Acceptor acceptor = new Acceptor("acceptor[" + i + "]");
            Thread t = new Thread(acceptor);
            t.setName("acceptor thread[" + i +"]");
            t.setDaemon(false);
            t.start();
        }
    }

    public void init() {

        try {
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress("localhost", 8081));
            //serverSocketChannel.configureBlocking(false);
            //Poller 个数设置
            startPollers();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void startPollers() {
        pollers = new Poller[Runtime.getRuntime().availableProcessors()];
        for (int i=0;i<pollers.length;i++) {
            pollers[i] = new Poller();
            Thread t = new Thread(pollers[i]);
            t.start();
        }
    }

    /**
     * 轮询选择一个Poller
     * @return
     */
    public Poller getPoller() {
        return pollers[Math.abs(loopInteger.getAndIncrement())%pollers.length];
    }
    /**
     *
     */
    private class Acceptor implements  Runnable {

        private  SocketChannel socketChannel;

        private String name;

        public Acceptor(String name) {
            this.name = name;
        }
        @Override
        public void run() {
            while (true) {

                try {
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    getPoller().register(socketChannel,SelectionKey.OP_READ);
                    //processorSocket(socketChannel);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        /**
         *
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
                        System.out.print((char)buffer.get());
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

    private class Poller implements Runnable {

        Selector selector;

        Queue<PollerEvent> queue = new LinkedList<PollerEvent>();
        public Poller() {
            try {
                selector = Selector.open();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            while(true) {
                try {
                    //int count = selector.select(1000);
                    int count = selector.selectNow();
                    if (count == 0)
                        events();
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();

                        if (key.isReadable()) {
                            SocketChannel channel = (SocketChannel)key.channel();
                            ByteBuffer buffer = ByteBuffer.allocate(1024);
                            //first read
                            channel.read(buffer);

                            buffer.flip();
                            while (buffer.hasRemaining()) {
                                System.out.print((char)buffer.get());
                            }
                            buffer.clear();

                            channel.register(selector,SelectionKey.OP_WRITE);
                        } else if (key.isWritable()) {
                            SocketChannel channel = (SocketChannel)key.channel();
                            ByteBuffer buffer = ByteBuffer.allocate(1024);
                            //then write
                            String mess = "Hello client.";
                            buffer.put(mess.getBytes());
                            buffer.flip();
                            while (buffer.hasRemaining()) {

                                channel.write(buffer);
                            }
                            System.out.println();
                            buffer.clear();
                            channel.register(selector,SelectionKey.OP_READ);
                        }
                        //remove SelectionKey
                        iterator.remove();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        public void register(SocketChannel channel,int interestOps) {

            /*try {
                channel.configureBlocking(false);
                channel.register(selector,interestOps);
            } catch (IOException e) {
                e.printStackTrace();
            }*/
            synchronized (queue) {

                queue.offer(new PollerEvent(channel,interestOps));
            }

        }
        public void events() {
            PollerEvent event = null;
            synchronized (queue) {

                while ((event = queue.poll()) != null) {
                    try {
                        event.channel.configureBlocking(false);
                        event.channel.register(selector,event.interestOps);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        private class PollerEvent {

            public PollerEvent(SocketChannel channel, int interestOps) {
                this.channel = channel;
                this.interestOps = interestOps;
            }

            private SocketChannel channel;
            private int interestOps;
        }
    }

}
