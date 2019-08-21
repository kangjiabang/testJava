/*
package mockito;

import com.google.common.collect.Maps;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class SoucheACLFilterTest {


    @InjectMocks
    private SoucheACLFilterHelper soucheACLFilterHelper;

    @Mock
    private Jedis jedis;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testBuildTree() {
        Map<String, String> map = Maps.newLinkedHashMap();

        */
/**
         * ["java.util.ArrayList",[{"@class":"com.alibaba.fastjson.JSONObject",    "code":"app1", "domain":"guanghui"
         },
         { "@class":"com.alibaba.fastjson.JSONObject",    "code":"app2", "domain":"dandian"
         }
         ]]
         *//*

        map.put("com.souche.tangeche.v1.test1", "[\"java.util.ArrayList\"," + "[" + "{\"@class\":" + "\"com.alibaba.fastjson.JSONObject\"," +
                "    \"code\":\"app1\", \"domain\":\"guanghui\"\n" +
                "  },\n" +
                "  { \"@class\":" + "\"com.alibaba.fastjson.JSONObject\"," +
                "    \"code\":\"app2\", \"domain\":\"dandian\"\n" +
                "  }\n" +
                "]]");


        map.put("com.souche.tangeche.v1.test3", "[\"java.util.ArrayList\"," + "[" + "{\"@class\":" + "\"com.alibaba.fastjson.JSONObject\"," +
                "    \"code\":\"app5\", \"domain\":\"yuantong\"\n" +
                "  }" +
                "]]");

        map.put("com.souche.tangeche.v2.test2", "[\"java.util.ArrayList\"," + "[" + "{\"@class\":" + "\"com.alibaba.fastjson.JSONObject\"," +
                "    \"code\":\"app3\", \"domain\":\"infiniti\"\n" +
                "  }" +
                "]]");

        map.put("com.souche.tangeche.v3.test4", "[\"java.util.ArrayList\"," + "[" + "{\"@class\":" + "\"com.alibaba.fastjson.JSONObject\"," +
                "    \"code\":\"app6\", \"domain\":\"d6\"\n" +
                "  }" +
                "]]");

        map.put("com.souche.tangeche.v3.*", "[\"java.util.ArrayList\"," + "[" + "{\"@class\":" + "\"com.alibaba.fastjson.JSONObject\"," +
                "    \"code\":\"app4\", \"domain\":\"oushang\"\n" +
                "  }" +
                "]]");


        System.out.println(map.get("com.souche.tangeche.v1.test1"));


        when(jedis.hgetAll(anyString())).thenReturn(map);

        soucheACLFilterHelper.makeJedisAclMap();
        SoucheACLFilterHelper.ACLEntity aclEntity = soucheACLFilterHelper.buildTree();

        //SoucheACLFilter.ACLEntity aclEntity = filter.buildTree(map);

        Assert.assertNotNull(aclEntity);
        Assert.assertEquals("zroot", aclEntity.getName());
        Assert.assertEquals(-1, aclEntity.getDepth());
        Assert.assertNull(aclEntity.getAppDomainSet());

        Assert.assertNotNull(aclEntity.getNext());
        Assert.assertEquals(1, aclEntity.getNext().size());
        SoucheACLFilterHelper.ACLEntity l0 = aclEntity.getNext().get(0);
        Assert.assertEquals("com", l0.getName());
        Assert.assertEquals(0, l0.getDepth());
        Assert.assertNull(l0.getAppDomainSet());

        List<SoucheACLFilterHelper.ACLEntity> l1s = l0.getNext();
        Assert.assertNotNull(l1s);
        Assert.assertEquals(1, l1s.size());
        SoucheACLFilterHelper.ACLEntity l1 = l1s.get(0);
        Assert.assertEquals("souche", l1.getName());
        Assert.assertEquals(1, l1.getDepth());
        Assert.assertNull(l1.getAppDomainSet());

        List<SoucheACLFilterHelper.ACLEntity> l2s = l1.getNext();
        Assert.assertNotNull(l2s);
        Assert.assertEquals(1, l2s.size());
        SoucheACLFilterHelper.ACLEntity l2 = l2s.get(0);
        Assert.assertEquals("tangeche", l2.getName());
        Assert.assertEquals(2, l2.getDepth());
        Assert.assertNull(l2.getAppDomainSet());


        List<SoucheACLFilterHelper.ACLEntity> l3s = l2.getNext();
        Assert.assertNotNull(l3s);
        Assert.assertEquals(3, l3s.size());

        SoucheACLFilterHelper.ACLEntity l30 = l3s.get(0);
        Assert.assertEquals("v1", l30.getName());
        Assert.assertEquals(3, l30.getDepth());
        Assert.assertNull(l30.getAppDomainSet());

        SoucheACLFilterHelper.ACLEntity l31 = l3s.get(1);
        Assert.assertEquals("v2", l31.getName());
        Assert.assertEquals(3, l31.getDepth());
        Assert.assertNull(l31.getAppDomainSet());
        Assert.assertNotNull(l31.getNext());
        Assert.assertEquals(1, l31.getNext().size());

        SoucheACLFilterHelper.ACLEntity l32 = l3s.get(2);
        Assert.assertEquals("v3", l32.getName());
        Assert.assertEquals(3, l32.getDepth());
        Assert.assertNull(l32.getAppDomainSet());
        Assert.assertNotNull(l32.getNext());
        Assert.assertEquals(2, l32.getNext().size());

        List<SoucheACLFilterHelper.ACLEntity> l40s = l30.getNext();
        Assert.assertNotNull(l40s);
        Assert.assertEquals(2, l40s.size());

        SoucheACLFilterHelper.ACLEntity l40 = l40s.get(0);
        Assert.assertNotNull(l40);
        Assert.assertEquals("test1", l40.getName());
        Assert.assertEquals(4, l40.getDepth());
        Assert.assertNull(l40.getNext());
        Assert.assertNotNull(l40.getAppDomainSet());
        Assert.assertEquals(2, l40.getAppDomainSet().size());
        Assert.assertTrue(l40.getAppDomainSet().contains("guanghui#app1"));
        Assert.assertTrue(l40.getAppDomainSet().contains("dandian#app2"));

        SoucheACLFilterHelper.ACLEntity l41 = l40s.get(1);
        Assert.assertNotNull(l41);
        Assert.assertEquals("test3", l41.getName());
        Assert.assertEquals(4, l41.getDepth());
        Assert.assertNull(l41.getNext());
        Assert.assertNotNull(l41.getAppDomainSet());
        Assert.assertEquals(1, l41.getAppDomainSet().size());
        Assert.assertTrue(l41.getAppDomainSet().contains("yuantong#app5"));

        List<SoucheACLFilterHelper.ACLEntity> l42s = l31.getNext();
        Assert.assertNotNull(l42s);
        Assert.assertEquals(1, l42s.size());

        SoucheACLFilterHelper.ACLEntity l42 = l42s.get(0);
        Assert.assertNotNull(l42);
        Assert.assertEquals("test2", l42.getName());
        Assert.assertNull(l42.getNext());
        Assert.assertEquals(4, l42.getDepth());
        Assert.assertNotNull(l42.getAppDomainSet());
        Assert.assertEquals(1, l42.getAppDomainSet().size());
        Assert.assertTrue(l42.getAppDomainSet().contains("infiniti#app3"));

        List<SoucheACLFilterHelper.ACLEntity> l43s = l32.getNext();
        Assert.assertNotNull(l43s);
        Assert.assertEquals(2, l43s.size());

        SoucheACLFilterHelper.ACLEntity l43 = l43s.get(0);
        Assert.assertNotNull(l43);
        Assert.assertEquals("*", l43.getName());
        Assert.assertEquals(4, l43.getDepth());
        Assert.assertNull(l43.getNext());
        Assert.assertNotNull(l43.getAppDomainSet());
        Assert.assertEquals(1, l43.getAppDomainSet().size());
        Assert.assertTrue(l43.getAppDomainSet().contains("oushang#app4"));

        SoucheACLFilterHelper.ACLEntity l44 = l43s.get(1);
        Assert.assertNotNull(l44);
        Assert.assertEquals("test4", l44.getName());
        Assert.assertEquals(4, l44.getDepth());
        Assert.assertNull(l44.getNext());
        Assert.assertNotNull(l44.getAppDomainSet());
        Assert.assertEquals(1, l44.getAppDomainSet().size());
        Assert.assertTrue(l44.getAppDomainSet().contains("d6#app6"));
    }

    */
/*@Test
    public void testCheckFromTree() {
        Map<String, String> map = Maps.newLinkedHashMap();

        map.put("com.souche.v3.v4", "[\n" +
                "  {\n" +
                "    \"code\":\"app4\", \"domain\":\"d4\"\n" +
                "  }\n" +
                "]");

        map.put("com.souche.v3.*", "[\n" +
                "  {\n" +
                "    \"code\":\"app5\", \"domain\":\"d4\"\n" +
                "  }\n" +
                "]");

        map.put("com.souche.v1", "[\n" +
                "  {\n" +
                "    \"code\":\"app1\", \"domain\":\"d1\"\n" +
                "  }\n" +
                "]");

        map.put("com.souche.*", "[\n" +
                "  {\n" +
                "    \"code\":\"app2\", \"domain\":\"d2\"\n" +
                "  }\n" +
                "]");

        map.put("com.souche.tangeche.v2", "[\n" +
                "  {\n" +
                "    \"code\":\"app3\", \"domain\":\"d3\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"code\":\"app4\", \"domain\":\"d3\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"code\":\"app6\", \"domain\":\"d4\"\n" +
                "  }\n" +
                "]");

        map.put("com.souche.v5", "[\n" +
                "  {\n" +
                "    \"code\":\"*\", \"domain\":\"d5\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"code\":\"*\", \"domain\":\"d6\"\n" +
                "  }\n" +
                "]");

        map.put("com.souche.v6", "[\n" +
                "  {\n" +
                "    \"code\":\"*\", \"domain\":\"*\"\n" +
                "  }\n" +
                "]");

        map.put("*", "[\n" +
                "  {\n" +
                "    \"code\":\"app12\", \"domain\":\"d12\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"code\":\"*\", \"domain\":\"d13\"\n" +
                "  }\n" +
                "]");

        SoucheACLFilter filter = new SoucheACLFilter();
        SoucheACLFilter.ACLEntity aclEntity = filter.buildTree(map);
        filter.setRoot(aclEntity);

        Assert.assertTrue(filter.checkFromTree("com.souche.v3", "d4", "app5"));
        Assert.assertFalse(filter.checkFromTree("com.souche.v3", "d4444", "app5"));
        Assert.assertFalse(filter.checkFromTree("com.souche.v3", "d4", "app5555"));

        Assert.assertTrue(filter.checkFromTree("com.souche.v3.v4", "d4", "app4"));
        Assert.assertFalse(filter.checkFromTree("com.souche.v3.v4", "d444", "app4"));
        Assert.assertFalse(filter.checkFromTree("com.souche.v3.v4", "d4", "app4444"));
        Assert.assertFalse(filter.checkFromTree("com.souche.v3.v4.v5", "d4", "app4"));

        Assert.assertTrue(filter.checkFromTree("com.souche.v3.v4.v5", "d4", "app5"));

        Assert.assertTrue(filter.checkFromTree("com.souche.v1", "d1", "app1"));
        Assert.assertFalse(filter.checkFromTree("com.souche.v1", "d2", "app1"));
        Assert.assertFalse(filter.checkFromTree("com.souche.v1", "d1", "app2"));
        Assert.assertTrue(filter.checkFromTree("com.souche.v1", "d2", "app2"));

        Assert.assertTrue(filter.checkFromTree("com.souche.v5", "d2", "app2"));
        Assert.assertTrue(filter.checkFromTree("com.souche.v3.v4", "d2", "app2"));
        Assert.assertTrue(filter.checkFromTree("com.souche", "d2", "app2"));

        Assert.assertTrue(filter.checkFromTree("com.souche.tangeche.v2", "d3", "app3"));
        Assert.assertTrue(filter.checkFromTree("com.souche.tangeche.v2", "d3", "app4"));
        Assert.assertTrue(filter.checkFromTree("com.souche.tangeche.v2", "d4", "app6"));
        Assert.assertFalse(filter.checkFromTree("com.souche.tangeche.v2", "d4", "app4"));

        Assert.assertTrue(filter.checkFromTree("com.souche.v5", "d5", "app7"));
        Assert.assertTrue(filter.checkFromTree("com.souche.v5", "d6", "app8"));
        Assert.assertFalse(filter.checkFromTree("com.souche.v5", "d7", "app9999"));

        Assert.assertTrue(filter.checkFromTree("com.souche.v6", "d8", "app9"));
        Assert.assertTrue(filter.checkFromTree("com.souche.v6", "d9", "app10"));

        Assert.assertTrue(filter.checkFromTree("com.v7", "d12", "app12"));
        Assert.assertFalse(filter.checkFromTree("com.v7", "d12", "app12222"));
        Assert.assertTrue(filter.checkFromTree("com.v7", "d13", "app1333"));
        Assert.assertTrue(filter.checkFromTree("com.v7", "d13", "app1334"));


        Assert.assertTrue(filter.checkFromTree("com.v8", "d12", "app12"));
        Assert.assertFalse(filter.checkFromTree("com.v8", "d12", "app12222"));
        Assert.assertTrue(filter.checkFromTree("com.v8", "d13", "app1333"));
        Assert.assertTrue(filter.checkFromTree("com.v8", "d13", "app1334"));
    }

    @Test
    public void testCheckFromTreeWithSimpleMap() {
        Map<String, String> map = Maps.newHashMap();

        map.put("*", "[\n" +
                "  {\n" +
                "    \"code\":\"*\", \"domain\":\"*\"\n" +
                "  }\n" +
                "]");
        SoucheACLFilter filter = new SoucheACLFilter();
        SoucheACLFilter.ACLEntity aclEntity = filter.buildTree(map);
        filter.setRoot(aclEntity);

        Assert.assertTrue(filter.checkFromTree("com.souche.v1", "d1", "app1"));
        Assert.assertTrue(filter.checkFromTree("com.souche.v1.v2", "d2", "app2"));

    }*//*

}
*/
