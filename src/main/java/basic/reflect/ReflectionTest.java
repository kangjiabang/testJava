package basic.reflect;


import aop.model.Student;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class ReflectionTest {


    @Test
    public void setRef() {
        try {
            Animal animal = new Animal();

            Field field = Animal.class.getDeclaredField("student");

            Field modifiers = Field.class.getDeclaredField("modifiers");

            modifiers.setAccessible(true);

            modifiers.setInt(field,field.getModifiers()& ~Modifier.FINAL);

            field.setAccessible(true);
            field.set(animal,new Student("nasew",14));

            System.out.println(animal);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void setPrimitive() {
        try {
            Animal animal = new Animal();

            Field field = Animal.class.getDeclaredField("name");

            Field modifiers = Field.class.getDeclaredField("modifiers");

            modifiers.setAccessible(true);

            modifiers.setInt(field,field.getModifiers()& ~Modifier.FINAL);

            field.setAccessible(true);
            field.set(animal,"xiaokang");

            System.out.println(animal);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
