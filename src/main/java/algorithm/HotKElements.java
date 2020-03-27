package algorithm;

import com.google.common.collect.Maps;
import org.apache.commons.collections.comparators.ReverseComparator;

import java.util.*;

public class HotKElements {

    private static Map<String,Integer> stringIntegerMap = Maps.newHashMap();

    private static SortedMap<Integer, List<String>> map = Maps.newTreeMap((t1,t2) -> t2 - t1);

    public static void main(String[] args) {

        PriorityQueue<Resource> queue = new PriorityQueue<Resource>((Resource t1,Resource t2) -> t2.getCount() - t1.getCount());

        queue.add(putResource("Hello"));


        while(queue.size() > 0) {
            System.out.println(queue.poll());
        }
    }

    private static Resource putResource(String key) {
        if (stringIntegerMap.get(key) == null) {
            stringIntegerMap.putIfAbsent(key,1);
        } else {
            Integer value = stringIntegerMap.get(key);
            stringIntegerMap.putIfAbsent(key,value.intValue() + 1);
        }
        return null;

    }

    static class Resource {
        String name;
        int count;

        public Resource(String name, int count) {
            this.name = name;
            this.count = count;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }

}
