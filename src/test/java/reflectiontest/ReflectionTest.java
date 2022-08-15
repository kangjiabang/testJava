package reflectiontest;

import basic.aop.model.Student;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @Author：zeqi
 * @Date: Created in 12:19 7/1/18.
 * @Description:
 */
public class ReflectionTest {


    @Test
    public void instanceStudentUsingReflection() {

        Class<?>[] parameterTypes = new Class<?>[] {String.class,int.class,String[].class};
        try {
            Constructor<?> studentConstructor =  Student.class.getConstructor(parameterTypes);

            studentConstructor.setAccessible(true);
            studentConstructor.newInstance("xiaokang",0,null);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
