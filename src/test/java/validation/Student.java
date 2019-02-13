package validation;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Arrays;

/**
 * @Author：zeqi
 * @Date: Created in 12:17 7/1/18.
 * @Description:
 */
public class Student {

    @NotNull(message = "名字不能为空")
    private String name;

    @NotNull(message = "家庭住址不能为空")
    private String address;

    @Min(value=1,message = "年龄字段必须有值")
    private int  age;


    private String[] hobbies;

    public Student() {

    }


    public Student(String name, int age, String[] hobbies) {
        this.name = name;
        this.age = age;
        this.hobbies = hobbies;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String[] getHobbies() {
        return hobbies;
    }

    public void setHobbies(String[] hobbies) {
        this.hobbies = hobbies;
    }


}
