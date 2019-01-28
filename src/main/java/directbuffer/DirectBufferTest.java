package directbuffer;

import java.nio.ByteBuffer;

public class DirectBufferTest {


    /**
     * -Xmx64m -Xms64m -Xmn32m -XX:+UseConcMarkSweepGC -XX:+PrintGCDetails -XX:+DisableExplicitGC  -XX:MaxDirectMemorySize=10M
     *
     * native memory满了，但是young区没满，没有发生young gc回收DirectByteBuffer，所以堆外OOM（如果去掉DisableExplicitGC参数程序会一直有Full GC的信息输出，因为分配native memory的时候会主动调用System.GC()）
     * @param args
     */
    public static void main(String[] args) {
        int i =1;
        while(true){
            System.out.println("第"+(i++)+"次");
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect( 1024*1024);

        }
    }
}
