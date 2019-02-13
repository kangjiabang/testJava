package reference;

import java.lang.ref.WeakReference;

/**
 * Created by Administrator on 2018/11/10.
 */
public class WeakReferenceTest {

    public static void main(String[] args) {
        WeakReference<String> weakReference = new WeakReference<String>(new String("hello world"));
        System.out.println(weakReference.get());
        System.gc();
        System.out.println(weakReference.get());
    }
}
