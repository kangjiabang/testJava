package groovy;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroovyShellTest {


    public static void main(String args[]) {


        Binding binding = new Binding();
        binding.setVariable("x", 10);
        binding.setVariable("language", "Groovy");
        binding.setVariable("userId",123);
        binding.setVariable("userName","zhang3");

        GroovyShell shell = new GroovyShell(binding);
        Object value = shell.evaluate("println \"Welcome to $language\"; y = x * 2; z = x * 3; return x ");

        List userIdAndUserName = (ArrayList)shell.evaluate(" [userId = userId*3,userName == (\"zhang\") ? userName : null]");

        if (userIdAndUserName.get(1) != null) {

            System.err.println(userIdAndUserName.get(0) + ", " + userIdAndUserName.get(0).equals(369));
            System.err.println(userIdAndUserName.get(1) + ", " + userIdAndUserName.get(1).equals("zhang"));
        }
        System.err.println(value + ", " + value.equals(10));
        System.err.println(binding.getVariable("y") + ", " + binding.getVariable("y").equals(20));
        System.err.println(binding.getVariable("z") + ", " + binding.getVariable("z").equals(30));
    }

    @Test
    public void testTransFilter() {

        List<Map<String,Object>> listMap = new ArrayList<>();

        Map<String,Object> map1 = new HashMap<>();
        map1.put("userId",123);
        map1.put("userName","kangjiabang");

        Map<String,Object> map2 = new HashMap<>();
        map2.put("userId",124);
        map2.put("userName","zhang");

        listMap.add(map1);
        listMap.add(map2);

        //List<Map<String,Object>> newListMap = new ArrayList<>();

        listMap.forEach(map -> {

            HashMap<String,Object> newMap = new HashMap<>();

            Binding binding = new Binding();


            binding.setVariable("userId",map.get("userId"));
            binding.setVariable("userName",map.get("userName"));

            GroovyShell shell = new GroovyShell(binding);

            Map<String,Object> resultMap = (Map<String,Object>)shell.evaluate(" [\"userId\": userId = userId*3,\"userName\": userName == (\"zhang\") ? userName + 12 : userName,\"newValue\":5]");

            resultMap.forEach( (key,value) -> {
                map.put(key,value);
            });

            /*if (userIdAndUserName.get(1) != null) {

                System.err.println(userIdAndUserName.get(0) + ", " + userIdAndUserName.get(0).equals(369));
                System.err.println(userIdAndUserName.get(1) + ", " + userIdAndUserName.get(1).equals("zhang"));
            }*/

        });
        System.out.println(listMap);
    }

    @Test
    public void testFilterByCondition() {

        List<Map<String,Object>> listMap = new ArrayList<>();

        Map<String,Object> map1 = new HashMap<>();
        map1.put("userId",123);
        map1.put("userName","kangjiabang");

        Map<String,Object> map2 = new HashMap<>();
        map2.put("userId",124);
        map2.put("userName","zhang");

        listMap.add(map1);
        listMap.add(map2);

        List<Map<String,Object>> newListMap = new ArrayList<>();

        listMap.forEach(map -> {

            HashMap<String,Object> newMap = new HashMap<>();

            Binding binding = new Binding();


            binding.setVariable("userId",map.get("userId"));
            binding.setVariable("userName",map.get("userName"));

            GroovyShell shell = new GroovyShell(binding);

            boolean isTrue = (Boolean)shell.evaluate(" userId == 123");


            if (isTrue) {
                newListMap.add(map);
            }

            /*if (userIdAndUserName.get(1) != null) {

                System.err.println(userIdAndUserName.get(0) + ", " + userIdAndUserName.get(0).equals(369));
                System.err.println(userIdAndUserName.get(1) + ", " + userIdAndUserName.get(1).equals("zhang"));
            }*/

        });
        System.out.println(newListMap);
    }
}
