package jdk8;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author  kangjiabang
 * @CreateTime 2022-06-23  16:40
 * @Description TODO
 */
public class StreamTest {


    @Test
    public void testStreamFlatMap() {
        List<String> list1 = Lists.newArrayList("a","b","c");
        List<String> list2 = Lists.newArrayList("d","e","f");

        System.out.println(Lists.newArrayList(list1,list2).stream().flatMap(Collection::stream).collect(Collectors.toList()));
    }

    @Test
    public void testStreamMap() {
        List<String> list1 = Lists.newArrayList("123","b","c");

        System.out.println(list1.stream().map(Long::parseLong).collect(Collectors.toList()));

    }

    @Test
    public void testStreamMap2() {
        List<String> list1 = Lists.newArrayList("123","b","c");

        System.out.println(Arrays.asList(list1,null).stream().flatMap(Collection::stream).collect(Collectors.toList()));

    }
}
