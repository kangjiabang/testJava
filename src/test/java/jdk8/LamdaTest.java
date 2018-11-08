package jdk8;

import com.wacai.model.Student;
import org.junit.Test;

/*import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;*/
import java.util.ArrayList;
import java.util.List;

/**
 * @Author：zeqi
 * @Date: Created in 15:35 2/3/18.
 * @Description:
 */
public class LamdaTest {

    @Test
    public void testLamda() {
       /* Student s1 = new Student(LocalDateTime.now().plus(1, ChronoUnit.DAYS));
        Student s2 = new Student(LocalDateTime.now().plus(4, ChronoUnit.DAYS));
        Student s3 = new Student(LocalDateTime.now().plus(3, ChronoUnit.DAYS));
        Student s4 = new Student(LocalDateTime.now().plus(5, ChronoUnit.DAYS));*/

        List<Student> studentList = new ArrayList();
        /*studentList.add(s1);
        studentList.add(s2);
        studentList.add(s3);
        studentList.add(s4);*/
        /*studentList.sort((e1,e2) -> e1.getBirthDate().isAfter(e2.getBirthDate()) ? 1: -1);
*/
        System.out.println(studentList);
    }
}
