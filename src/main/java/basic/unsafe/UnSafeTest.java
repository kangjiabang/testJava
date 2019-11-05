package basic.unsafe;

import org.junit.Assert;
import org.junit.Test;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class UnSafeTest {


    public static Unsafe getUnSafe() {

        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            return  (Unsafe)field.get(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        Unsafe unsafe = getUnSafe();
        unsafe.allocateMemory(1000);

    }

    @Test
    public void testGuard() {
        try {
            Guard guard = new Guard();

            Assert.assertTrue(!guard.giveAccess());

            Unsafe unsafe = getUnSafe();

            Field field = Guard.class.getDeclaredField("ACCESS_ALLOWED");

            unsafe.putInt(guard,unsafe.objectFieldOffset(field),42);

            Assert.assertTrue(guard.giveAccess());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

    }
}
