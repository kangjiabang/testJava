package annotation.test;

import annotation.test.declare.ExceptionTest;

/**
 * @Author：zeqi
 * @Date: Created in 17:06 1/1/18.
 * @Description:
 */
public class Sample2 {
    @ExceptionTest(value = ArithmeticException.class)
    public static void m1() {
        int  i =0;
        int j = i/i;
    };

    @ExceptionTest(value = ArithmeticException.class)
    public static void m2() {
        throw  new RuntimeException("crash");
    };

    @ExceptionTest(value = ArithmeticException.class)
    public void  m3() {

    }
}
