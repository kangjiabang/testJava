package basic.type;


import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author：zeqi
 * @Date: Created in 19:06 28/5/18.
 * @Description:
 */
public class GenericTest {

    @Test
    public void testListAnyType() {
        List list = Lists.newArrayList("a","b","c");
        print(list);
        printSpecify(list);
        printList(list);
    }

    @Test
    public void testSum() {
        List list = Lists.newArrayList(1,2,3);
        List listDouble = Lists.newArrayList(1.1,2.5,3.3);
        addNumbers(list);
        addNumbers(listDouble);
    }

    @Test
    public void testUpperBound() {
        List list = Lists.newArrayList(1,2,3);
        List listDouble = Lists.newArrayList(1.1,2.5,3.3);
        List<? extends Number> intList = new ArrayList<>();
        intList = list;
        intList = listDouble;
    }


    public void print(List<Object> list) {
        for (Object object: list) {
            System.out.println(object);
        }
    }

    public void printSpecify(List<?> list) {
        for (Object elem: list) {
            System.out.println(elem);
        }
    }

    public void  addNumbers(List<? extends Number> list) {
        double sum = 0;
        for (Number ele : list) {
            sum += ele.doubleValue();
        }
        System.out.println("sum=" + sum);
    }

    public void  printList(List<? super Number> list) {
        for (Object ele : list) {
            System.out.print(" " + ele);
        }
    }

}
