package jsonpath;

import com.jayway.jsonpath.JsonPath;
import org.junit.Test;

/**
 * @Author：zeqi
 * @Date: Created in 10:45 27/3/18.
 * @Description:
 */
public class JSONPathTest {

    /*String json = "{\"code\":200,\"msg\":\"ok\",\"list\":[{\"id\":20,\"no\":\"1000020\",\"items\":[{\"name\":\"n1\",\"price\":21,\"infos\":{\"feature\":\"\"}}]}],\"metainfo\":{\"total\":20,\"info\":{\"owner\":\"qinshu\",\"parts\":[{\"count\":13,\"time\":{\"start\":1230002456,\"end\":234001234}}]}}}";

    String storeJson = "{\"store\":{\"book\":[{\"category\":\"reference\",\"author\":\"NigelRees\",\"title\":\"SayingsoftheCentury\",\"price\":8.95},{\"category\":\"fiction\",\"author\":\"EvelynWaugh\",\"title\":\"SwordofHonour\",\"price\":12.99},{\"category\":\"fiction\",\"author\":\"HermanMelville\",\"title\":\"MobyDick\",\"isbn\":\"0-553-21311-3\",\"price\":8.99},{\"category\":\"fiction\",\"author\":\"J.R.R.Tolkien\",\"title\":\"TheLordoftheRings\",\"isbn\":\"0-395-19395-8\",\"price\":22.99}],\"bicycle\":{\"color\":\"red\",\"price\":19.95}}}";
    @Test
    public void testGetJson() {
        System.out.println(JsonPath.read(json,"$.code"));
        System.out.println(JsonPath.read(json,"$..id"));
        System.out.println(JsonPath.read(json,"$.list[0].no"));
    }

    @Test
    public void testGetStoreJson() {
        System.out.println(JsonPath.read(storeJson,"$.store"));
        System.out.println(JsonPath.read(storeJson,"$..author"));
        System.out.println(JsonPath.read(storeJson,"$..price"));
        System.out.println(JsonPath.read(storeJson,"$..book[3]"));

        //输出全部author的值，使用Iterator迭代
        print(JsonPath.read(storeJson, "$.store.book[*].author"));
        //输出book[*]中category == 'reference'的book
        print(JsonPath.read(storeJson, "$.store.book[?(@.category == 'reference')]"));

        //输出book[*]中price>10的book
        print(JsonPath.read(storeJson, "$.store.book[?(@.price>10)]"));
    }*/

    public void print(Object str) {
        System.out.println(str);
    }
}
