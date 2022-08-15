package optimize.Btrace;

/**
 * @Author：zeqi
 * @Date: Created in 17:47 17/5/18.
 * @Description:
 */
import com.sun.btrace.BTraceUtils;
import com.sun.btrace.annotations.*;

@BTrace(unsafe = true)
public class BtraceTest {
    @OnMethod(clazz = "optimize.Btrace.Demo", method = "getResult", location = @Location(Kind.RETURN))
    public static void getParamAndResultByBtrace(int param1, int param2, @Return int result) {

        BTraceUtils.println("===========BTrace begin==================");
        BTraceUtils.println("the first param:" + param1);
        BTraceUtils.println("the second param:" + param2);
        BTraceUtils.println("result: " + result);
        BTraceUtils.println("===========BTrace end====================");

    }
}