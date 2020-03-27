package basic;

import java.util.Collection;

public class GenericClass<T> {

    private Class classForT(T... t) {
        return t.getClass().getComponentType();
    }

    public static void main(String[] args) {
        GenericClass<Collection> g = new GenericClass<Collection>();

        System.out.println(g.classForT());

    }
}