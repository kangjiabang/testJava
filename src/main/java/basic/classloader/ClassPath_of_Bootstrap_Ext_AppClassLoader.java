package basic.classloader;

import java.net.URL;
import java.net.URLClassLoader;
/**
 * @Author：zeqi
 * @Date: Created in 13:31 29/12/17.
 * @Description:
 */


/**
分析BootstrapClassLoader/ExtClassLoader/AppClassLoader的加载路径
*
*/
public class ClassPath_of_Bootstrap_Ext_AppClassLoader
{
    public static void main(String[] args)
    {
        System.out.println("BootstrapClassLoader 的加载路径: ");

        URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();
        for(URL url : urls)
            System.out.println(url);
        System.out.println("----------------------------");

        //取得扩展类加载器
        URLClassLoader extClassLoader = (URLClassLoader)ClassLoader.getSystemClassLoader().getParent();

        System.out.println(extClassLoader);
        System.out.println("扩展类加载器 的加载路径: ");

        urls = extClassLoader.getURLs();
        for(URL url : urls)
            System.out.println(url);

        System.out.println("----------------------------");


        //取得应用(系统)类加载器
        URLClassLoader appClassLoader = (URLClassLoader)ClassLoader.getSystemClassLoader();

        System.out.println(appClassLoader);
        System.out.println("应用(系统)类加载器 的加载路径: ");

        urls = appClassLoader.getURLs();
        for(URL url : urls)
            System.out.println(url);

        System.out.println("----------------------------");
    }
}