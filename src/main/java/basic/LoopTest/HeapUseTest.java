package basic.LoopTest;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class HeapUseTest {

    static Map<String,Student>  map = new HashMap<String,Student>();

    public static void main(String[] args) {

        Long i = 0l;
        while (true) {
            map.put(String.valueOf(i) + new Random().nextDouble(),new Student("student" + i, new Random().nextDouble()));
            i++;
            if (i == 100) {
                i = 0l;
                try {
                    Thread.sleep(800);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static class Student {

        public Student(String name, double weight) {
            this.name = name;
            this.weight = weight;
        }

        private String name;
        private double weight;

        private String[] strs = new String[1000];
    }
}
