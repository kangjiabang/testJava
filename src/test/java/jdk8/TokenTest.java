package jdk8;

import io.swagger.models.auth.In;
import org.junit.Test;
import utils.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @Author：zeqi
 * @Date: Created in 00:08 3/1/18.
 * @Description:
 */
public class TokenTest {

    @Test
    public void testStringTokenizer() {
        /*StringTokenizer stringToken = new StringTokenizer("1,2,3;4",",;");
        while (stringToken.hasMoreTokens()) {
            String ele  = stringToken.nextToken();
            System.out.println(ele);
        }*/
        //String[] result = StringUtils.tokenizeToStringArray("1,2,3 ;4,",",;",true,false);
        String[] result = StringUtils.tokenizeToStringArray("1,2,3, ;4,",",;",true,false);

        System.out.println(Arrays.toString(result));

    }

    @Test
    public void testGetStackTrace() {

        StackTraceElement[] elements = new Throwable().getStackTrace();
        for (StackTraceElement ele : elements) {
            System.out.println(ele.toString());
            System.out.println(ele.getLineNumber());
        }
    }

    @Test
    public void testBitOperation() {
        System.out.println(-1 << 2 | 0);
    }

    @Test
    public void testArrayCopyOf() {
        int[] intArr = {1,2,3,4,6};
        int[] newArr = Arrays.copyOf(intArr,10);
        System.out.println(Arrays.toString(newArr));
    }

    @Test
    public void testStringJoin() {
        String[] strArr = {"Hello","world"};
        List<String> strList = Arrays.asList(strArr);

        /*String result = String.join(",",strList);
        System.out.println(result);*/
    }
}
