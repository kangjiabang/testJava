package resovabletype;

import classloader.model.Student;
import org.junit.Test;
import org.springframework.core.ResolvableType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/12/31.
 */
public class ResolvableTypeTest {

    ArrayList<Student> list = new ArrayList<Student>();

    @SuppressWarnings("serial")
    static class ExtendsList<ApplicationEvent> extends ArrayList<CharSequence> {
    }
    /*public static void main(String[] args) {
       // ArrayList<Student> list = new ArrayList<Student>();
        ResolvableType resolvableType = ResolvableType.forType(ExtendsList.class);
        ResolvableType generic = resolvableType.getSuperType().getGeneric(0);
        System.out.println("ArrayList<Student> " + generic.getRawClass());
    }*/

    @Test
    public void testGetFieldGeneric() {

        try {
            ResolvableType resolvableType = ResolvableType.forField(ResolvableTypeTest.class.getDeclaredField("list"));
            ResolvableType generic = resolvableType.getGeneric(0);
            System.out.println("ArrayList<Student> generic " + generic.getType());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetClassGeneric() {

        try {
            ResolvableType resolvableType = ResolvableType.forClass(ExtendsList.class).getGeneric();

            System.out.println(" ExtendsList<ApplicationListener>  generic " + resolvableType.getType());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
