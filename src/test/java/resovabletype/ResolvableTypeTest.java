package resovabletype;

import basic.classloader.model.Student;
import org.junit.Test;
import org.springframework.core.ResolvableType;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;

/**
 * Created by Administrator on 2018/12/31.
 */
public class ResolvableTypeTest<E> {

    ArrayList<Student> list = new ArrayList<Student>();




    public Class<E> getTClass()
    {
        Class<E> tClass = (Class<E>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return tClass;
    }

    @SuppressWarnings("serial")
    static class ExtendsList<ApplicationEvent> extends ArrayList<CharSequence> {
    }


    @Test
    public void testGetFieldGeneric() {
        ResolvableTypeTest<String> resolvableTypeTest = new ResolvableTypeTest<String>();
        System.out.println(resolvableTypeTest.getTClass());

    }

    @Test
    public void testGetPublicClassGeneric() {

        try {

            ResolvableType resolvableType = ResolvableType.forField(this.getClass().getDeclaredField("resolvableTypeTest"));


           /* if (basic.type instanceof ParameterizedType){

                for(Type param : ((ParameterizedType)basic.type).getActualTypeArguments()){
                    //打印实际参数类型
                    System.out.println("---basic.type actualType---" + param.toString());
                }
                //打印所在的父类的类型
                System.out.println("---basic.type ownerType0---"+ ((ParameterizedType)	 					basic.type).getOwnerType());
                //打印其本身的类型
                System.out.println("---basic.type rawType---"+ ((ParameterizedType) 							basic.type).getRawType());
            }*/
            System.out.println("ResolvableTypeTest generic " + resolvableType.getGeneric(0).resolve());
        } catch (Exception e) {
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
