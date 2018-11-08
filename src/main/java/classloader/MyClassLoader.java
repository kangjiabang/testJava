package classloader;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @Author：zeqi
 * @Date: Created in 10:45 29/12/17.
 * @Description:
 */
public class MyClassLoader extends  ClassLoader {


    /**
     * 重写加载类
     * @param name
     * @return
     * @throws ClassNotFoundException
     */
    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {

        //判断系统类加载器是否加载，如果加载，return;
        try {
            ClassLoader loader = ClassLoader.getSystemClassLoader();
            Class cl = loader.loadClass(name);
            if (cl != null) {
                return cl;
            }
        } catch (ClassNotFoundException e) {
           //ignore;
        }
        return findClass(name);
    }

    /**
     * 自定义加载
     * @param name
     * @return
     * @throws ClassNotFoundException
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        try {
            String classPath = System.getProperty("user.dir");
            System.out.println("classPath:" + classPath);
            FileInputStream fis = new FileInputStream(classPath +"/target/classes" + name);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            byte[] bytes = new byte[1024];
            int totalCount = 0;
            while ((totalCount = fis.read(bytes)) != -1) {
                baos.write(bytes,0,totalCount);
            }
            byte[]  classBytes = baos.toByteArray();
            return this.defineClass(classBytes,0,classBytes.length);
        } catch (IOException e) {
            return super.findClass(name);
        }

    }
}
