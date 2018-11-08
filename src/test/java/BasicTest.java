import org.junit.Test;

import java.util.Random;

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
}
