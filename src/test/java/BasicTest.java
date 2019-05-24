import com.google.common.collect.Maps;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.junit.Test;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentMap;

/**
 * @Author：zeqi
 * @Date: Created in 10:45 27/3/18.
 * @Description:
 */
public class BasicTest {


    @Test
    public void testInvoke() {
        this.invoke("name");
    }

    /*public void invoke(String name,String... value) {
        if (value == null) {
            return;
        }
        System.out.println(name);
    }*/

    public void invoke(String name,Object... value) {
        if (value == null) {
            return;
        }
        System.out.println(name);
    }

    @Test
    public void testStringFormat() {
        System.out.println(String.format("计算金额异常，aid=%d",231));
        System.out.println(String.format("计算金额异常，aid=%s",231));
    }

    @Test
    public void testStringThreeTuple() {
        String result = "Hello" + ("Hello" == null ? "world" :" 你好");
        System.out.println(result);
    }

    @Test
    public void testVersionCompare() {
        System.out.println("7.0".compareTo("5.9.6"));
    }

    @Test
    public void random4Digit() {
        System.out.println((int)((Math.random()*9+1)*1000));

        System.out.println(new Random().nextInt(10));
        System.out.println(new Random().nextInt(10));
        System.out.println(new Random().nextInt(10));
        System.out.println(new Random(10).nextInt());
        System.out.println(new Random(10).nextInt());
    }

    @Test
    public void testConcurrentMap() {
        ConcurrentMap<String, List<Integer>> queueIdMap = Maps.newConcurrentMap();
        queueIdMap.put("String",null);
    }

    @Test
    public void testExceptionCatch() {
        try {
            testThrowException();
        } catch (Exception e) {
            String result = ExceptionUtils.getStackTrace(e);
            Throwable t = new Throwable(result);
            RuntimeException runtimeException = new RuntimeException(t);
            t.getCause();
        }
    }

    public void testThrowException() {

        int a = 0;

        int result = a/0;

    }

}
