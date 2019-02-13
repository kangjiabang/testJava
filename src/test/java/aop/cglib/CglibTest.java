package aop.cglib;

import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.*;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * @Author：zeqi
 * @Date: Created in 10:29 15/1/18.
 * @Description:
 */
public class CglibTest {

    @Test
    public void testFiexedValue() {

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(SampleClass.class);
        enhancer.setCallback(new FixedValue() {
            @Override
            public Object loadObject() throws Exception {
                return "Hello springaop.cglib!";
            }
        });

        SampleClass proxy = (SampleClass) enhancer.create();
        Assert.assertEquals("Hello springaop.cglib!", proxy.test(null));
    }

    @Test
    public void testInvocationHandler() {

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(SampleClass.class);
        enhancer.setCallback(new InvocationHandler() {
            @Override
            public Object invoke(Object o, Method method, Object[] objects) throws Throwable {

                if (method.getDeclaringClass() != Object.class && method.getReturnType() == String.class) {
                    return "Hello springaop.cglib!";
                } else {
                    throw new RuntimeException("Do not know what to do.");
                }
            }
        });

        SampleClass proxy = (SampleClass) enhancer.create();
        Assert.assertEquals("Hello springaop.cglib!", proxy.test(null));

        Assert.assertNotEquals("Hello springaop.cglib!", proxy.toString());
    }

    @Test
    public void testMethodInterceptor() {

        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "/Users/zeqi/Documents/code/javaTest/target/classes");

        // 回调实例数组

        Callback[] callbacks = new Callback[] { new MethodInterceptor() {

            @Override
            public Object intercept(Object obj, java.lang.reflect.Method method, Object[] args,
                                    MethodProxy proxy) throws Throwable {

                System.out.println("调用前");
                Object result = proxy.invokeSuper(obj,args);
                System.out.println(" 调用后");
                return result;
            }
        }, NoOp.INSTANCE };

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(SampleClass.class);
        enhancer.setCallbacks(callbacks);
        enhancer.setCallbackFilter(new CallbackFilter() {
            @Override
            public int accept(Method method) {
                if (method.getName().equals("test")) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });

        SampleClass proxy = (SampleClass) enhancer.create();
        Assert.assertEquals("Hello world!", proxy.test(null));
        Assert.assertEquals("Hello world1!", proxy.test1(null));

    }
}
