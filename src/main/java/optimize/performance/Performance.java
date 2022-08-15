package optimize.performance;

import com.google.common.hash.Hashing;

import java.util.HashSet;
import java.util.Set;

public class Performance {
    Set<Object> sets = new HashSet<Object>();
    public  void cpu() {

        for (int i = 0; i < 1000000; i++) {
            int a = 0;
            a += i;
            a = a * i;
            for (int j=0;j< 4;i++) {

                Hashing.md5().newHasher().putBytes("falwiejwierjlawirjawelirjilwaerjwaierwrjawlerijawer".getBytes()).hash();
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public  void cpuWait() {

        for (int i = 0; i < 1000000; i++) {
            int[] mem = new int[1000];
            //sets.add(mem);
            System.out.println("cpu wait");
            Math.max(i,i+1);
            Math.asin(i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
