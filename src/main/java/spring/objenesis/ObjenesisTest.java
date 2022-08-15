package objenesis;

import basic.aop.model.Student;
import org.springframework.objenesis.Objenesis;
import org.springframework.objenesis.ObjenesisStd;

/**
 * @Author：zeqi
 * @Date: Created in 14:21 15/1/18.
 * @Description:
 */
public class ObjenesisTest {


    public static void main(String[] args) {
        Objenesis objenesis = new ObjenesisStd();
        //instance without default constructor
        Student student = objenesis.newInstance(Student.class);
        System.out.println(student);
    }
}
