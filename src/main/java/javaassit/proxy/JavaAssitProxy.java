package javaassit.proxy;

import annotation.test.declare.Test;
import javaassit.Point;
import javassist.*;

import java.lang.reflect.Modifier;

public class JavaAssitProxy {


    public static void main(String[] args) {

        try {
            Point point = new Point();

            ClassPool pool = ClassPool.getDefault();

            CtClass ctClass = pool.makeClass("javaassit.JavaProxy");

            CtClass target = pool.get(Point.class.getName());

            CtField f = new CtField(pool.get("java.lang.Object"), "target", ctClass);
            f.setModifiers(Modifier.PRIVATE);

            ctClass.setSuperclass(target);

            ctClass.addField(f);

            CtMethod[] ctMethods = target.getDeclaredMethods();

            for (int i =0;i <ctMethods.length;i++) {

                String methodName = ctMethods[i].getName();


                if (methodName.contains("toString") || methodName.contains("equals") ||
                        methodName.contains("hashCode")) {
                    continue;
                }

                String returnSimpleType = ctMethods[i].getReturnType().getSimpleName();


                ctMethods[i].getParameterTypes();

                CtMethod m = CtNewMethod.make(
                        "public " + returnSimpleType + " "  + methodName +  " (int dx) {  return dx;}",
                        ctClass);

                ctClass.addMethod(m);

            }
            ctClass.toClass();
            ctClass.writeFile();



        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
