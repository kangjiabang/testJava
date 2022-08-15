package basic.serviceloader;

import java.util.Iterator;
import java.util.ServiceLoader;

public class ServiceLoaderTest {

    public static void main(String[] args) {

        ServiceLoader<Hello> serviceLoader = ServiceLoader.load(Hello.class);
        Iterator<Hello> iterator = serviceLoader.iterator();

        while (iterator.hasNext()) {
            Hello hello = iterator.next();
            hello.sayHi();
        }

    }
}
