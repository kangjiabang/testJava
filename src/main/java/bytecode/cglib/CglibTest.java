
package bytecode.cglib;


import net.sf.cglib.proxy.Enhancer;
import org.junit.Test;
import org.springframework.core.DefaultParameterNameDiscoverer;

import java.lang.reflect.Method;

public class CglibTest {

    private static DefaultParameterNameDiscoverer parameterNameDiscoverer = new DefaultParameterNameDiscoverer();


    public static void main(String[] args) {
        new CglibTest().testCglib();
    }
    @Test
    public void testCglib() {
        DaoProxy daoProxy = new DaoProxy();

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Dao.class);
        enhancer.setCallback(daoProxy);

        Dao dao = (Dao)enhancer.create();
        dao.update("name");
        dao.select();

        Method method = null;
        try {
             method = dao.getClass().getDeclaredMethod("update",String.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        String[]  paramNames = parameterNameDiscoverer.getParameterNames(method);

    }

}

