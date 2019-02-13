package filecopy;

/**
 * Created by Administrator on 2018/11/21.
 */
public class Math {

    public int sqrt(int x) {
        if (x <= 1) {
            return x;
        }
        int begin = 1;
        int end = x;
        int middle = 0;
        while (begin <= end) {
            middle = begin + (end - begin) / 2;
            if (middle == x / middle) {
                return middle;
            } else {
                if (middle < x / middle) {
                    begin = middle + 1;
                } else {
                    end = middle - 1;
                }
            }

        }
        //结束条件end一定<begin，所以返回end  
        return end;
    }

    public static void main(String[] args) {
        System.out.println(new Math().sqrt(5));
        System.out.println(new Math().sqrt(4));
    }
}