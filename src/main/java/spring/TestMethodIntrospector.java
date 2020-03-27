package spring;


import org.junit.Test;
import org.springframework.core.MethodIntrospector;

import java.lang.reflect.Method;
import java.util.Map;

public class TestMethodIntrospector {



    @Test
    public void testMethodIntrospector() {
       /* Map<Method,Boolean> methodExistMap = MethodIntrospector.selectMethods(Student.class,
               new MethodIntrospector.MetadataLookup() {
                   @Override
                   public Object inspect(Method method) {
                       return method.getName().equals("name");
                   }
               });*/

        Map<Method,Boolean> methodExistMap = MethodIntrospector.selectMethods(Student.class,
                (MethodIntrospector.MetadataLookup<Boolean>) method ->
                         method.getName().equals("name")
                );
        System.out.println(methodExistMap);
    }
    class  Student{

        public String name(String nickName) {
            return nickName;
        }
    }
}

