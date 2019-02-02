package weakreference;

import com.wacai.model.Student;

import java.lang.ref.WeakReference;

public class WeakReferenceTest {

    public static void main(String[] args) {
        Student student = new Student("xiaoming",50);
        WeakReference weakReference = new WeakReference(student);
        //如果不把引用去除，垃圾收集器不会回收
        student = null;

        int i=0;
        while(true) {
            i++;
            weakReference = new WeakReference(student);
            System.out.println(i + " times loop,get xiaoming result:" + weakReference.get());
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (i == 5) {
                System.gc();
            }
        }
    }
}
