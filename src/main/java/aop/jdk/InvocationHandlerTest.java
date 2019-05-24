package aop.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class InvocationHandlerTest implements InvocationHandler {


    private  Object student;

    public InvocationHandlerTest(Object student) {
        this.student = student;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().equalsIgnoreCase("name")) {
            return "teacher";
        }
        return method.invoke(student);
    }

    public Object getProxy() {
        return Proxy.newProxyInstance(this.student.getClass().getClassLoader(),student.getClass().getInterfaces(),this);
    }

    public static void main(String[] args) {
        InvocationHandlerTest invocationHandlerTest = new InvocationHandlerTest(new Student());
        Person person = (Person)invocationHandlerTest.getProxy();
        System.out.println(person.name());
    }
}

interface Person {
   String name();
}
class  Student implements Person {
    @Override
    public String name() {
        return "Student";
    }
}
