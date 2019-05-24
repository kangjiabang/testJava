package basic;


import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ConcurrentModifyListTest {

    @Test
    public void testConcurrentModifyList() {
        List<String> list = new ArrayList<String>();
        list.add("a");
        list.add("b");
        list.add("c");


        Iterator<String> iterator = list.iterator();

        while (iterator.hasNext()) {
            //遍历的同时做了修改操作，就会报java.util.ConcurrentModificationException，可以使用iter.remove安全删除
            iterator.next();

            list.remove("a");

        }
    }

    @Test
    public void testListSort() {
        List<String> list = new ArrayList<String>();
        list.add("d");
        list.add("b");
        list.add("a");
        list.add("c");

        Collections.sort(list);

        Iterator<String> iterator = list.iterator();

        while (iterator.hasNext()) {
            //遍历的同时做了修改操作，就会报java.util.ConcurrentModificationException，可以使用iter.remove安全删除
            iterator.next();

            list.remove("a");

        }
    }



}
