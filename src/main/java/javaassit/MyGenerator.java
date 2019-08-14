package javaassit;


import javassist.*;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;
import javassist.util.proxy.MethodFilter;
import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.Proxy;
import javassist.util.proxy.ProxyFactory;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.impl.DefaultHttpResponseFactory;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicStatusLine;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class MyGenerator {

    @Test
    public void testGenerateMethod() {
        try {
            ClassPool pool = ClassPool.getDefault();
            //创建Programmer类
            CtClass cc= pool.makeClass("com.samples.Programmer");
            //定义code方法
            CtMethod method = CtNewMethod.make("public void code(){}", cc);
            //插入方法代码
            method.insertBefore("System.out.println(\"I'm a Programmer,Just Coding.....\");");
            cc.addMethod(method);
            //保存生成的字节码
            cc.writeFile("/Users/dasouche/Documents/work/javaassit/");
        } catch (CannotCompileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testSetSuperClass() {
        try {
            ClassPool pool = ClassPool.getDefault();
            CtClass cc = pool.get("javaassit.Rectangle");
            cc.setSuperclass(pool.get("javaassit.Point"));
            cc.writeFile();
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (CannotCompileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAddConstructorMethod() {
        try {
            ClassPool pool = ClassPool.getDefault();
            CtClass cc = pool.get("javaassit.Point");
            /*CtConstructor ctConstructor = cc.getConstructor("");
            ctConstructor.insertBefore("{ System.out.println($1); System.out.println($2); $1 = 0;}");*/

            // 为类设置构造器
            // 无参构造器
            CtConstructor constructor = new CtConstructor(null, cc);
            /*constructor.setModifiers(Modifier.PUBLIC);
            constructor.setBody("{}");
            cc.addConstructor(constructor);*/


            // 参数构造器
            constructor = new CtConstructor(new CtClass[] {pool.get(String.class.getName())}, cc);
            constructor.setModifiers(Modifier.PUBLIC);
            constructor.setBody("{this.x=$1;}");
            cc.addConstructor(constructor);


            cc.writeFile();
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (CannotCompileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testInsertConstructorMethod() {
        try {
            ClassPool pool = ClassPool.getDefault();
            CtClass cc = pool.get("javaassit.Point");
            CtConstructor ctConstructor = cc.getDeclaredConstructor(new CtClass[0]);
            ctConstructor.insertBefore("{ System.out.println(\"Hello\" ); }");

            cc.writeFile();
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (CannotCompileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testInsertAfterConstructorMethod() {
        try {
            ClassPool pool = ClassPool.getDefault();
            CtClass cc = pool.get("javaassit.Point");
            CtConstructor ctConstructor = cc.getDeclaredConstructor(new CtClass[0]);
            ctConstructor.insertAfter("{ System.out.println(\" after Hello\" ); }");

            cc.writeFile();
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (CannotCompileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCatchConstructorMethod() {
        try {
            ClassPool pool = ClassPool.getDefault();
            CtClass cc = pool.get("javaassit.Point");
            CtConstructor ctConstructor = cc.getDeclaredConstructor(new CtClass[0]);

            CtClass etype = ClassPool.getDefault().get("java.io.IOException");
            ctConstructor.addCatch("{ System.out.println($e); if ($e instanceof java.io.IOException) { return 0;} throw $e; }", etype);

            cc.writeFile();
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (CannotCompileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSetConstructorBody() {
        try {
            ClassPool pool = ClassPool.getDefault();
            CtClass cc = pool.get("javaassit.Point");
            CtConstructor ctConstructor = cc.getDeclaredConstructor(new CtClass[0]);
            ctConstructor.setBody("{ System.out.println(\"Body\" ); }");

            cc.writeFile();
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (CannotCompileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testBeforeMethod() {
        try {
            ClassPool pool = ClassPool.getDefault();
            CtClass cc = pool.get("javaassit.Point");
            CtMethod m = cc.getDeclaredMethod("move");
            m.insertBefore("{ System.out.println($1); System.out.println($2); $1 = 0;}");
            cc.writeFile();
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (CannotCompileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testBeforeMethodModifyParams() {
        try {
            ClassPool pool = ClassPool.getDefault();
            CtClass cc = pool.get("javaassit.Person");
            CtMethod m = cc.getDeclaredMethod("setAddress");
            m.insertBefore("{ $1 = new javaassit.Address(\"xiaokang\");}");
            cc.writeFile();
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (CannotCompileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testBeforeMethodModifyParams1() {
        try {
            ClassPool pool = ClassPool.getDefault();
            CtClass cc = pool.get("javaassit.Person");
            pool.insertClassPath(new ClassClassPath(this.getClass()));
            pool.appendSystemPath();
            pool.importPackage("java.util");
            pool.importPackage("javaassit");
            CtMethod m = cc.getDeclaredMethod("setAddress",new CtClass[]{pool.get("java.util.List")});
            m.insertBefore("{ $1 = new ArrayList(); $1.add(new Address(\"xiaokang\"));}");
            cc.writeFile();
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (CannotCompileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testBeforeMethodModifyParams2() {
        try {
            ClassPool pool = ClassPool.getDefault();

            CtClass cc = pool.get("javaassit.Person");
            CtMethod m = cc.getDeclaredMethod("setName");
            m.insertBefore("{ $1 = \"xiaokang\";}");
            cc.writeFile();
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (CannotCompileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @Test
    public void testAfterMethod() {
        try {
            ClassPool pool = ClassPool.getDefault();
            CtClass cc = pool.get("javaassit.Point");
            CtMethod m = cc.getDeclaredMethod("move");
            m.insertAfter("{ $_ = 10;" +
                    "  }");
            cc.writeFile();
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (CannotCompileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAfterMethod2() {
        try {
            ClassPool pool = ClassPool.getDefault();
            CtClass cc = pool.get("javaassit.Point");
            CtMethod m = cc.getDeclaredMethod("move");
            m.insertAfter("{ System.out.println($_); return new javaassit.MyGenerator().execute();" +
                    "  }");
            cc.writeFile();
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (CannotCompileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public int execute() {
        return 4;
    }

    @Test
    public void testCatchMethod() {
        try {
            ClassPool pool = ClassPool.getDefault();
            CtClass cc = pool.get("javaassit.Point");
            CtMethod m = cc.getDeclaredMethod("move");
            CtClass etype = ClassPool.getDefault().get("java.io.IOException");
            m.addCatch("{ System.out.println($e); if ($e instanceof java.io.IOException) { return 0;} throw $e; }", etype);
            cc.writeFile();
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (CannotCompileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCatchMethod2() {
        try {
            ClassPool pool = ClassPool.getDefault();
            CtClass cc = pool.get("javaassit.Point");
            CtMethod m = cc.getDeclaredMethod("move");
            CtClass etype = ClassPool.getDefault().get("java.io.IOException");
            m.addCatch("{ System.out.println($e); if ($e instanceof java.io.IOException) { return 0;} throw $e; }", etype);
            cc.writeFile();
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (CannotCompileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testLinkErrorMethod() {
        try {

            Point pointOri = new Point();
            ClassPool pool = ClassPool.getDefault();

            CtClass cc = pool.get("javaassit.Point");
            CtMethod cm = cc.getDeclaredMethod("move");
            cc.defrost();
            cm.setBody("{ System.out.println($1); return $1;}");

            cc.writeFile();

            Point point = (Point)cc.toClass().newInstance();
            //System.out.println(point.move(1,3));
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (CannotCompileException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Test
    public void testLinkErrorMethod2() {
        try {

            ClassPool pool = ClassPool.getDefault();

            CtClass cc = pool.get("javaassit.Point");
            CtMethod cm = cc.getDeclaredMethod("move");
            cc.defrost();
            cm.setBody("{ System.out.println($1); return $1;}");

            cc.writeFile();

            Point point = (Point)cc.toClass().newInstance();
            Point point1 = (Point)cc.toClass().newInstance();
            //System.out.println(point.move(1,3));
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (CannotCompileException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }





    @Test
    public void testBeforeMethodGeneric() {
        try {
            ClassPool pool = ClassPool.getDefault();
            CtClass cc = pool.get("javaassit.Office");
            CtClass[] params = new CtClass[] {pool.get("java.lang.String"),pool.get("javaassit.Address")};
            CtMethod m = cc.getDeclaredMethod("getAdress",params);

            m.insertBefore("{ System.out.println($1);}");
            cc.writeFile();
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (CannotCompileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testBeforeMethodGeneric2() {
        try {
            ClassPool pool = ClassPool.getDefault();
            CtClass cc = pool.get("javaassit.Office");
            CtClass[] params = new CtClass[] {pool.get("java.lang.String"),pool.get("javaassit.Address"),pool.get("int"),pool.get("boolean")};
            CtMethod m = cc.getDeclaredMethod("getAdress",params);

            m.insertBefore("{ System.out.println($1); System.out.println($2); }");
            cc.writeFile();
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (CannotCompileException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testProxyFactory() {
        try {
            ProxyFactory f = new ProxyFactory();
            f.setSuperclass(Point.class);
            f.setFilter(new MethodFilter() {
                public boolean isHandled(Method m) {
                    // ignore finalize()
                    return !m.getName().equals("move");
                }
            });
            Class c = f.createClass();
            MethodHandler mi = new MethodHandler() {
                public Object invoke(Object self, Method m, Method proceed,
                                     Object[] args) throws Throwable {
                    System.out.println("Name: " + m.getName());
                    return proceed.invoke(self, args);  // execute the original method.
                }
            };
            Point foo = (Point) c.newInstance();
            ((Proxy)foo).setHandler(mi);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testModifyClassModifier() {
        try {
            ClassPool pool = ClassPool.getDefault();

            pool.appendClassPath(new LoaderClassPath(this.getClass().getClassLoader()));

            CtClass cc = pool.get("org.apache.http.impl.execchain.HttpResponseProxy");

            cc.setModifiers(Modifier.PUBLIC);

            // 无参构造器
            CtConstructor constructor = new CtConstructor(null, cc);
            constructor.setModifiers(Modifier.PUBLIC);
            constructor.setBody("{}");
            cc.addConstructor(constructor);

            //定义code方法
            CtMethod method = CtNewMethod.make("public void setHttpResponse(org.apache.http.HttpResponse httpResponse){ this.original = httpResponse; }", cc);

            cc.addMethod(method);

            Class httpResponseClazz = cc.toClass();

            CloseableHttpResponse closeableHttpResponse = (CloseableHttpResponse)httpResponseClazz.newInstance();

            BasicHttpResponse basicHttpResponse = (BasicHttpResponse)new DefaultHttpResponseFactory().newHttpResponse(HttpVersion.HTTP_1_1,
                    HttpStatus.SC_OK,null);

            BasicHttpEntity httpEntity = new BasicHttpEntity();
            String result = "hello world";
            httpEntity.setContent(new ByteArrayInputStream(result.getBytes()));
            httpEntity.setContentLength(result.length());
            basicHttpResponse.setEntity(httpEntity);


            Field originalField = httpResponseClazz.getDeclaredField("original");
            originalField.setAccessible(true);
            originalField.set(closeableHttpResponse,basicHttpResponse);

            if (closeableHttpResponse.getStatusLine().getStatusCode() == 200) {

                System.out.println(EntityUtils.toString(closeableHttpResponse.getEntity()));
            }
            //httpResponseClazz.getConstructor(org.apache.http.HttpResponse.class, org.apache.http.impl.execchain.ConnectionHolder.class);


            cc.writeFile();
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (CannotCompileException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testBeforeMethodGenericReflect() {
        try {

            Office.class.getDeclaredMethod("getAdress",new Class[]{java.lang.String.class,javaassit.Address.class,int.class,boolean.class});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}