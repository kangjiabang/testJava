package reflection;

import org.junit.Test;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author：zeqi
 * @Date: Created in 15:30 28/5/18.
 * @Description:
 */
public class ReflectionTest {


    @Test
    public void testReflectionTestField() {

        Field[] fields = TestClass.class.getDeclaredFields();
        for (Field field: fields) {
            Type genericType = field.getGenericType();
            if (genericType instanceof ParameterizedType) {
                ParameterizedType pt = (ParameterizedType) genericType;
                Type typeArgu = pt.getActualTypeArguments()[0];
                System.out.println("参数类型:" + typeArgu);
            }
        }

    }

    @Test
    public void testReflectionTestMethod() {
        try {
            Method method =  TestClass.class.getMethod("test",Map.class);
            Type[] types = method.getGenericParameterTypes();

            for (Type genericType : types) {
                System.out.println("#" + genericType);
                if (genericType instanceof ParameterizedType) {
                    ParameterizedType pt = (ParameterizedType) genericType;
                    Type[] typeArguments = pt.getActualTypeArguments();

                    for (Type typeArgu : typeArguments) {
                        System.out.println("泛型参数类型:" + typeArgu);
                    }
                }
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }


}
