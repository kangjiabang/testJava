package thread;

import com.wacai.model.Student;

import java.util.HashMap;

/**
 * @Author：zeqi
 * @Date: Created in 14:39 18/5/18.
 * @Description:
 */
public class OOMTest {

    private static HashMap<Integer,Student> map = new HashMap<Integer,Student>();


    public static void main(String[] args) {
        for (int i=0 ;i < Integer.MAX_VALUE;i++) {
            map.put(i,new Student("zhangsan" + i,i));
        }
    }
}


