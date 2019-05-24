package serviceloader.loadextension;


import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ClassUtils;
import utils.CollectionUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class LoaderExtension {

    public static final String fileName = "META-INF/extensions/com.providers.hello";
    //扩展的类
   public static Map<String,Class<?>> extensionClasses = new ConcurrentHashMap<String,Class<?>>();

    //扩展的实例
    public static Map<String,Object> extensionInstance = new ConcurrentHashMap<String,Object>();

   static {
       getExtensionClasses();
   }

   public static void getExtensionClasses() {

       ClassLoader classLoader = LoaderExtension.class.getClassLoader();
       Enumeration<URL> urls = null;
       try {
           if (classLoader != null)  {
               urls = classLoader.getResources(fileName);

           } else {
               urls = ClassLoader.getSystemResources(fileName);
           }
       } catch (IOException e) {
           new RuntimeException("fail to load file " + fileName);
       }


       if (urls != null) {
           while (urls.hasMoreElements()) {
               URL url = urls.nextElement();
               parse(url);
           }
       }


   }

    /**
     * 解析URL
     * @param url
     */
    private static void parse(URL url) {
     try {
         InputStream inputStream  = url.openStream();
        List<String> lines = IOUtils.readLines(inputStream);

        if (CollectionUtils.isNotEmpty(lines)) {
            for(String line :lines)  {
                String[] nameAndClass = line.split("=");
                Class<?> clazz = null;
                try {
                    clazz = Class.forName(nameAndClass[1],false, ClassUtils.getDefaultClassLoader());
                } catch (ClassNotFoundException e) {
                    new RuntimeException("fail to load class " + nameAndClass[1]);
                }
                extensionClasses.put(nameAndClass[0],clazz);
            }
        }
    } catch (IOException e) {
        new RuntimeException("fail to open " + fileName);
    }
    }

    /**
     *  根据名称获取实例化对象
     * @param name
     * @return
     */
    public static Object getExtension(String name) {
        if (StringUtils.isEmpty(name)) {
            throw new IllegalArgumentException("name can not be null");
        }
        //如果找不到扩展的类
        if (extensionClasses.get(name) == null) {
            throw new IllegalArgumentException(name + " can not be  found in extension files");
        }
        //如果实例化缓存中未发现，先实例化
        if (extensionInstance.get(name) == null) {

            synchronized (extensionInstance) {
                try {
                    if (extensionInstance.get(name) == null) {
                        extensionInstance.put(name,extensionClasses.get(name).newInstance());
                    }
                } catch (InstantiationException | IllegalAccessException e) {
                    throw new RuntimeException(extensionClasses.get(name) + " can not be instantiated",e);

                }
            }
        }
        return extensionInstance.get(name);

    }
}
