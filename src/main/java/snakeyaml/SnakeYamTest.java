package snakeyaml;

import org.junit.Assert;
import org.junit.Test;
import org.yaml.snakeyaml.Yaml;
import snakeyaml.model.Address;

import java.util.Map;

public class SnakeYamTest {

    @Test
    public void testLoad() {
        String yamlStr = "key: hello yaml";
        Yaml yaml = new Yaml();
        Map map = yaml.load(yamlStr);
        System.out.println(map.get("key"));
    }

    @Test
    public void test2() throws Exception {
        Yaml yaml = new Yaml();
        Map<String, Object> ret = (Map<String, Object>) yaml.load(this
                .getClass().getClassLoader().getResourceAsStream("test2.yaml"));
        System.out.println(ret);
    }

    @Test
    public void test3() throws Exception {
        Yaml yaml = new Yaml();
        Iterable<Object> ret = yaml.loadAll(this.getClass().getClassLoader()
                .getResourceAsStream("test3.yaml"));
        for (Object o : ret) {
            System.out.println(o);
        }
    }


    @Test
    public void testAddress() throws Exception {
        Yaml yaml = new Yaml();
        Address ret = yaml.loadAs(this.getClass().getClassLoader()
                .getResourceAsStream("address.yaml"), Address.class);
        Assert.assertNotNull(ret);
        Assert.assertEquals("MI", ret.getState());
    }

}
