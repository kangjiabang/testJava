package directbuffer;

import java.nio.ByteBuffer;

public class DirectBufferTest2 {


    /**
     * -Xmx64m -Xms64m -Xmn32m -XX:+UseConcMarkSweepGC -XX:+PrintGCDetails -XX:+DisableExplicitGC  -XX:MaxDirectMemorySize=10M
     * native memory没满，但是young区在native memory满之前提前满了，发生young gc回收DirectByteBuffer，不会发生OOM
     如果代码换成了下面这种(jvm参数一样),一次分配的native memory足够小，会导致在native memory没有分配满的情况下，发生young gc会搜DirectByteBuffer。同时会回收native memory
     * @param args
     */
    public static void main(String[] args) {
        int i =1;
        while(true){
            System.out.println("第"+(i++)+"次");
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024/2/2/2/2/2);
        }
    }
}
