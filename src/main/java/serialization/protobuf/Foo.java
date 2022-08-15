package serialization.protobuf;

/**
 * @Author：zeqi
 * @Date: Created in 15:06 10/1/18.
 * @Description:
 */
public class Foo {


    public Foo(String name, int age, double weight) {
        this.name = name;
        this.age = age;
        this.weight = weight;
    }

    private String name;

    private int age;

    private  double weight;

    @Override
    public String toString() {
        return "Foo{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", weight=" + weight +
                '}';
    }
}
