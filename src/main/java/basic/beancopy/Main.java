package basic.beancopy;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

public class Main {

    @Test
    public void testShadowCopy() {
        try {

            Address address = new Address("hangzhou");
            Person person = new Person("kang",18,address);

            Person person1 = null;

            //shadow COpy
            BeanUtils.copyProperties(person,person1);


            System.out.println("person:" + person);
            System.out.println("person1:" + person1);

            address.setName("shenzhen");

            System.out.println("after person:" + person);
            System.out.println("after person1:" + person1);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Test
    public void testDeepCopyFastJson() {
        try {

            Address address = new Address("hangzhou");
            Person person = new Person("kang",18,address);

            Person person1 = null;

            //deep Copy ,Person must have default Contructor
            person1 = JSONObject.parseObject(JSON.toJSONString(person),Person.class);

            //BeanUtils.copyProperties(person,person1);


            System.out.println("person:" + person);
            System.out.println("person1:" + person1);

            address.setName("shenzhen");

            System.out.println("after person:" + person);
            System.out.println("after person1:" + person1);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    @Test
    public void testDeepCopyGson() {
        try {

            Address address = new Address("hangzhou");
            Person person = new Person("kang",18,address);

            //deep Copy
            Gson gson = new Gson();
            Person person1 = gson.fromJson(gson.toJson(person), Person.class);

            //BeanUtils.copyProperties(person,person1);


            System.out.println("person:" + person);
            System.out.println("person1:" + person1);

            address.setName("shenzhen");

            System.out.println("after person:" + person);
            System.out.println("after person1:" + person1);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
