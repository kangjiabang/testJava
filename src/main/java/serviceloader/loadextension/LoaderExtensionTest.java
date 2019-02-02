package serviceloader.loadextension;

import serviceloader.Hello;

public class LoaderExtensionTest {

    public static void main(String[] args) {
       Hello hello =  (Hello)LoaderExtension.getExtension("english");
       hello.sayHi();
    }
}
