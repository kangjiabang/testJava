package serialize;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.io.Serializable;

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

    @Test
    public  void testJsonObject() {


        Assertions.assertThatExceptionOfType(RuntimeException.class).isThrownBy(() -> {
             JSONObject.parse("hello");
        });

        // System.out.println(f);

    }


    @Test
    public  void testJsonSer() {

        System.out.println(JSON.toJSONString(new ObjectTest("kang",12)));

        // System.out.println(f);

    }

    class ObjectTest implements Serializable{

        public ObjectTest(String name, int age) {
            this.name = name;
            this.age = age;
        }

        private String name = "xiaokang";

        private int age = 13;
    }
}
