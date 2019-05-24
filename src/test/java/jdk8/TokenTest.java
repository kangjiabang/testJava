package jdk8;

import com.google.common.collect.Maps;
import io.swagger.models.auth.In;
import org.junit.Test;
import utils.StringUtils;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;
import java.util.concurrent.ConcurrentHashMap;

import static com.google.common.base.Preconditions.checkArgument;

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


    @Test
    public void testCheckArgument() {
        checkArgument(
                true, "Invalid character '%s' in key name '%s'", 'a', "error");
    }
    public static void checkArgument(boolean expression, @Nullable String errorMessageTemplate, @Nullable Object... errorMessageArgs) {
        if (!expression) {
           /* throw new IllegalArgumentException(format(errorMessageTemplate, errorMessageArgs));*/
        }
    }

    @Test
    public void testConcurrentHashMap() {

        ConcurrentHashMap map = new ConcurrentHashMap<String,String>();
        map.put("TopicTest","a");

        map.put("TopicTestSql","b");

        System.out.println(map.toString());
    }
}
