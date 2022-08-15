package basic.annotation.test;

import basic.annotation.Sample;
import basic.annotation.declare.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Author：zeqi
 * @Date: Created in 17:08 1/1/18.
 * @Description:
 */
public class AnnotationTest {
    public static void main(String[] args) {
        Method[] methods = Sample.class.getDeclaredMethods();

        int tests = 0;
        int passed = 0;
        for (Method method: methods) {
            if (method.isAnnotationPresent(Test.class)) {
                tests++;
                try {
                    method.invoke(null);
                    passed++;
                } catch (InvocationTargetException e) {
                   Throwable thro = e.getCause();
                    System.out.println(method + " failed: " + thro.getMessage());
                } catch (Exception e) {
                    System.out.println("Invalid @Test: " + method);

                }
                System.out.println(method.getName());
            }
        }
        System.out.println("passed: " + passed + ", failed: " + (tests - passed));
    }
}
