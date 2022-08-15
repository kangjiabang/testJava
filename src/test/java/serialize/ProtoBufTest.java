package serialize;

import org.junit.Test;
import serialization.protobuf.Foo;
import serialization.protobuf.ProtoBufUtils;

import java.util.Arrays;

/**
 * @Author：zeqi
 * @Date: Created in 15:39 10/1/18.
 * @Description:
 */
public class ProtoBufTest {

    @Test
    public  void testSerialize() {
        Foo foo = new Foo("foo", 1, 3.5);

        // ser
        byte[] result = ProtoBufUtils.serialize(foo);
        System.out.println("ser resultLength:" + result.length);
        System.out.println("ser result:" + Arrays.toString(result));

        // deser
        Foo f = ProtoBufUtils.deserialize(Foo.class, result);

        System.out.println(f);

    }
}
