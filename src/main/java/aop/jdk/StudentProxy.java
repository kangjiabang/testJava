package aop.jdk;

import org.springframework.core.LocalVariableTableParameterNameDiscoverer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class StudentProxy implements InvocationHandler {

    private static LocalVariableTableParameterNameDiscoverer parameterNameDiscoverer = new LocalVariableTableParameterNameDiscoverer();

    private  Object student;

    public StudentProxy(Object student) {
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

        Student student = new Student();

        StudentProxy studentProxy = new StudentProxy(new Student());
        Person person = (Person)studentProxy.getProxy();

        Method nameMethod = null;
        try {
            nameMethod = person.getClass().getDeclaredMethod("name",String.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        Class<?>  interfaceClass =  person.getClass().getInterfaces()[0];
        Method nameMethodFromInterface = null;
        try {
            nameMethodFromInterface = interfaceClass.getDeclaredMethod("name",String.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        String[]  paramNames = parameterNameDiscoverer.getParameterNames(nameMethodFromInterface);


        Method nameMethodWithOutAop = null;
        try {
            nameMethodWithOutAop = Student.class.getDeclaredMethod("name",String.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        //String[]  paramNamesNoAop = parameterNameDiscoverer.getParameterNames(nameMethodWithOutAop);

        //System.out.println(person.name("xiaoming"));
    }
}

interface Person {
   String name(String nickName);
}
class  Student implements Person {
    @Override
    public String name(String nickName) {
        return nickName;
    }
}
