package Btrace;

/**
 * @Author：zeqi
 * @Date: Created in 17:47 17/5/18.
 * @Description:
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Demo {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("please input the first param:");
        String p1 = bufferedReader.readLine();
        System.out.println("please input the first param:");
        String p2 = bufferedReader.readLine();
        new Demo().getResult(Integer.parseInt(p1), Integer.parseInt(p2));
        System.out.println("press ENTER TO EXIT ...");
        bufferedReader.readLine();
    }

    public  int getResult(int param1, int param2) {
        return param1 * param2;
    }
}