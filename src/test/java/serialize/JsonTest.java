package serialize;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import protobuf.Foo;
import protobuf.ProtoBufUtils;

import java.util.Arrays;

/**
 * @Author：zeqi
 * @Date: Created in 15:39 10/1/18.
 * @Description:
 */
public class JsonTest {

    @Test
    public  void testJsonString() {

        System.out.println(JSON.toJSONString(null));

       // System.out.println(f);

    }
}
