package resovabletype;

import classloader.model.Student;
import org.junit.Test;
import org.springframework.core.MethodParameter;
import org.springframework.core.ResolvableType;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

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


           /* if (type instanceof ParameterizedType){

                for(Type param : ((ParameterizedType)type).getActualTypeArguments()){
                    //打印实际参数类型
                    System.out.println("---type actualType---" + param.toString());
                }
                //打印所在的父类的类型
                System.out.println("---type ownerType0---"+ ((ParameterizedType)	 					type).getOwnerType());
                //打印其本身的类型
                System.out.println("---type rawType---"+ ((ParameterizedType) 							type).getRawType());
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
