package thread;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @Author：zeqi
 * @Date: Created in 11:34 17/12/17.
 * @Description:
 */
public class Reactor implements Runnable {
    final Selector selector;
    final ServerSocketChannel serverSocketChannel;

    public Reactor(int port) throws IOException {
        selector = Selector.open();
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(port));
        serverSocketChannel.configureBlocking(false);
        SelectionKey key = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        key.attach(new Acceptor());


    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                int readyCount = selector.select();

                if (readyCount == 0) {
                    continue;
                }
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey>  iters = keys.iterator();
                while (iters.hasNext()) {
                    SelectionKey key = iters.next();
                    dispatch(key);
                }
                keys.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 处理链接请求
     * @param key
     */
    private void dispatch(SelectionKey key) {
        Runnable runnable = (Runnable)key.attachment();
        if (runnable != null) {
            runnable.run();
        }
    }

    public class  Acceptor implements Runnable {

        public void run() {
            try {
                SocketChannel sc = serverSocketChannel.accept();
                if (sc != null) {
                    new Handler(selector,sc);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    final class Handler implements Runnable{

        final SocketChannel socket;
        final SelectionKey key;
        ByteBuffer input = ByteBuffer.allocate(1024);
        ByteBuffer output = ByteBuffer.allocate(1024);
        static final int READING = 0,SENDING =1;
        int state = READING;

        public Handler(Selector selector, SocketChannel c) throws IOException
        {
            socket = c;
            socket.configureBlocking(false);
            key = socket.register(selector, 0);
            key.attach(this);
            key.interestOps(SelectionKey.OP_READ);//注册可读事件
            selector.wakeup();
        }
        public void run()
        {
            try {
                if(state == READING)
                    read();
                else if(state == SENDING)
                    send();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        boolean inputIsComplete(){
            return input.hasRemaining();
        }
        boolean outputIsComplete(){
            return output.hasRemaining();
        }
        void process(){
            //读数据
            StringBuilder reader = new StringBuilder();
            input.flip();
            while(input.hasRemaining()){
                reader.append((char)input.get());
            }
            System.out.println("[JMXClient-INFO]");
            System.out.println(reader.toString());
            String str = "HTTP/1.1 200 OK\r\nDate: Fir, 10 March 2017 21:20:01 GMT\r\n"+
                    "Content-Type: text/html;charset=UTF-8\r\nContent-Length: 23\r\nConnection:close"+
                    "\r\n\r\nHelloWorld"+System.currentTimeMillis();
            output.put(str.getBytes());
            System.out.println("process over.... ");
        }
        void read() throws IOException{
            socket.read(input);
            if(inputIsComplete()){
                process();
                state = SENDING;
                key.interestOps(SelectionKey.OP_WRITE);
            }
        }
        void send() throws IOException {
            output.flip();
            socket.write(output);
            if(outputIsComplete()){
                key.cancel();
            }
            state = READING;
            key.interestOps(SelectionKey.OP_READ);
        }
    }
    public static void main(String[] args) throws IOException
    {
        new Thread(new Reactor(9001)).start();
        System.out.println("Server start...");
    }


}
