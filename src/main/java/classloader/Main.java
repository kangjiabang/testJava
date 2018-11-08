package classloader;

//import classloader.model.Student;

/**
 * @Author：zeqi
 * @Date: Created in 10:37 29/12/17.
 * @Description:
 */
public class Main {
    public static void main(String[] args) {

        try {
            /*ClassLoader loader = Student.class.getClassLoader();
            System.out.println("loader " +Student.class.getClassLoader());

            System.out.println("current context loader " +Thread.currentThread().getContextClassLoader());
            while (loader != null) {
                loader = loader.getParent();
                System.out.println("loader " +loader);
            }*/

            MyClassLoader selfLoader = new MyClassLoader();
            Class loadedClass = selfLoader.loadClass("/classloader/model/Student.class");
            System.out.println("loader " +loadedClass.getClassLoader());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
