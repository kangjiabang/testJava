package com.wacai.aop;

/**
 * @Author：zeqi
 * @Date: Created in 22:53 26/12/17.
 * @Description:
 */
public class DaoImpl implements Dao {

    private String name;
    private int   age;


    public void select() {
        System.out.println("Enter DaoImpl.select()");
    }

    public void insert() {
        System.out.println("Enter DaoImpl.insert()");
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

    public DaoImpl() {
    }

    public DaoImpl(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
