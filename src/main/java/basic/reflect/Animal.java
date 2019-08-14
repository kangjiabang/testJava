package basic.reflect;

import aop.model.Student;

public class Animal {

    private  final String name = new String("kang");

    private  final Student student = new Student("asdb",12);

    public Animal() {
    }

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                ", student=" + student +
                '}';
    }
}
