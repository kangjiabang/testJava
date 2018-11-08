package com.wacai.model;

import java.util.Arrays;
import java.util.Date;

/**
 * @Author：zeqi
 * @Date: Created in 12:17 7/1/18.
 * @Description:
 */
public class Student {

    private String name;

    private int  age;

    //private LocalDateTime birthDate;

    private String[] hobbies;



    public Student(String name, int age) {
        this.name = name;
        this.age = age;
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


    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", hobbies=" + Arrays.toString(hobbies) +
                '}';
    }
}
