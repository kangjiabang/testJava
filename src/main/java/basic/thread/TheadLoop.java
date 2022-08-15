package basic.thread;

import java.util.HashMap;

/**
 * @Author：zeqi
 * @Date: Created in 16:06 2/2/18.
 * @Description:
 */
public class TheadLoop {

    private static HashMap<String,Object> map = new HashMap<>();

    public static void main(String[] args) {
        try {
            Thread.sleep(1000*5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while(true) {
            int count = 0;
            for (int i=0;i< Integer.MAX_VALUE;i++) {
                map.put("value" + i,new Student(String.valueOf(i),i));
                count++;
                if (count%500 ==0) {
                    count =0;
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }

    public static class Student {

        public Student(String name, long height) {
            this.name = name;
            this.height = height;
        }

        String name;
        long height;
    }
}
