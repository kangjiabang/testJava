package basic.annotation.test;

import basic.annotation.Sample2;
import basic.annotation.declare.ExceptionTest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Author：zeqi
 * @Date: Created in 17:08 1/1/18.
 * @Description:
 */
public class AnnotationExceptionTest {
    public static void main(String[] args) {
        Method[] methods = Sample2.class.getDeclaredMethods();

        int tests = 0;
        int passed = 0;
        for (Method method: methods) {
            if (method.isAnnotationPresent(ExceptionTest.class)) {
                tests++;
                try {
                    //ExceptionTest test = method.getAnnotation(ExceptionTest.class);
                    //System.out.println(test.value());
                    method.invoke(null);
                    passed++;
                } catch (InvocationTargetException e) {
                   Throwable thro = e.getCause();
                   Class<? extends  Exception>[] exTypes = method.getAnnotation(ExceptionTest.class).value();
                   for (Class<? extends Exception> exType : exTypes) {
                       if ( exType.isInstance(thro))   {
                           System.out.println(method + " passed: " + thro.getMessage());
                       }
                   }


                } catch (Exception e) {
                    System.out.println("Invalid @Test: " + method);

                }
                System.out.println(method.getName());
            }
        }
        System.out.println("passed: " + passed + ", failed: " + (tests - passed));
    }
}
