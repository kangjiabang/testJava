package com.annotation;

import com.annotation.declare.Test;

/**
 * @Author：zeqi
 * @Date: Created in 17:06 1/1/18.
 * @Description:
 */
public class Sample {
    @Test
    public static void m1() {};

    @Test
    public static void m2() {
        throw  new RuntimeException("crash");
    };

    @Test
    public void  m3() {

    }
}
