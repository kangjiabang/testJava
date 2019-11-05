import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.Asserts;
import org.apache.http.util.EntityUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import validation.Student;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentMap;

/**
 * @Author：zeqi
 * @Date: Created in 10:45 27/3/18.
 * @Description:
 */
public class BasicTest {


    @Test
    public void testInvoke() {
        this.invoke("name");
    }

    /*public void invoke(String name,String... value) {
        if (value == null) {
            return;
        }
        System.out.println(name);
    }*/

    public void invoke(String name,Object... value) {
        if (value == null) {
            return;
        }
        System.out.println(name);
    }

    @Test
    public void testStringFormat() {
        System.out.println(String.format("计算金额异常，aid=%d",231));
        System.out.println(String.format("计算金额异常，aid=%s",231));
    }

    @Test
    public void testStringThreeTuple() {
        String result = "Hello" + ("Hello" == null ? "world" :" 你好");
        System.out.println(result);
    }

    @Test
    public void testVersionCompare() {
        System.out.println("7.0".compareTo("5.9.6"));
    }

    @Test
    public void random4Digit() {
        System.out.println((int)((Math.random()*9+1)*1000));

        System.out.println(new Random().nextInt(10));
        System.out.println(new Random().nextInt(10));
        System.out.println(new Random().nextInt(10));
        System.out.println(new Random(10).nextInt());
        System.out.println(new Random(10).nextInt());
    }

    @Test
    public void testConcurrentMap() {
        ConcurrentMap<String, List<Integer>> queueIdMap = Maps.newConcurrentMap();


        Assertions.assertThatExceptionOfType(NullPointerException.class).isThrownBy(() -> {
            queueIdMap.put("String",null);
        });
    }


    @Test
    public void testHttpClient() {

        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("https://apollo.souche-inc.com/apps");
        httpPost.setHeader("Cookie","_security_token_inc=91563766436748337;Path=/;Domain=.souche-inc.com;Max-Age=86400");
        try {

        String json = "{\n" +
                "  \"name\":\"test-benben113321\",\n" +
                "  \"appId\":\"test-benben13333213\",\n" +
                "  \"orgId\":\"default\",\n" +
                "  \"orgName\":\"default\",\n" +
                "  \"ownerName\":\"15068759176(陈善奔)\",\n" +
                "  \"admins\":[\"15068759176(陈善奔)\"]\n" +
                "}";
        StringEntity postingString = new StringEntity(json,"utf-8");// json传递
        httpPost.setEntity(postingString);
        httpPost.setHeader("Content-type", "application/json");


            CloseableHttpResponse response1 = httpclient.execute(httpPost);
            System.out.println(response1.getStatusLine());
            HttpEntity entity1 = response1.getEntity();
            // do something useful with the response body
            // and ensure it is fully consumed
            EntityUtils.consume(entity1);


        }catch ( Exception e) {

        } finally{
            //response1.close();
        }

    }
    @Test
    public void testExceptionCatch() {
        try {
            testThrowException();
        } catch (Exception e) {
            String result = ExceptionUtils.getStackTrace(e);
            Throwable t = new Throwable(result);
            RuntimeException runtimeException = new RuntimeException(t);
            t.getCause();
        }
    }

    @Test
    public void testFastJson() {
        try {
            Student stuent = JSONObject.parseObject("{\"sex\":\"BOY\"}",Student.class);

            Student.SEX sex = JSON.parseObject("\"BOY\"",Student.SEX.class);
            System.out.println("student: " + stuent);
            System.out.println("sex: " + sex);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void testThrowException() {

        int a = 0;

        int result = a/0;

    }

}
