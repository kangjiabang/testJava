package basic.thread.jmx.jvm;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.openmbean.CompositeDataSupport;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.lang.management.MemoryUsage;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class JvmCollectorPlugins {


    public static void main(String[] args) {
        try {
            getConnection("localhost", 1234);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * set CATALINA_OPTS=-Dcom.sun.management.jmxremote
     * -Dcom.sun.management.jmxremote.port=%my.jmx.port%
     * -Dcom.sun.management.jmxremote.ssl=false
     * -Dcom.sun.management.jmxremote.authenticate=false
     *
     * @throws Exception
     * @return MBeanServerConnection
     */
    private static MBeanServerConnection getConnection(String host, int port) throws Exception {

        //用户名、密码
        Map<String, String[]> map = new HashMap<String, String[]>();
        map
                .put("jmx.remote.credentials", new String[]{"monitorRole",
                        "QED"});
        String jmxURL = String.format("service:jmx:rmi:///jndi/rmi://%s:%d/jmxrmi", host, port);

        JMXServiceURL serviceURL = new JMXServiceURL(jmxURL);
        JMXConnector connector = JMXConnectorFactory.connect(serviceURL, map);
        MBeanServerConnection mbsc = connector.getMBeanServerConnection();


        ObjectName heapObjName = new ObjectName("java.lang:basic.type=Memory");

        //堆内存
        MemoryUsage heapMemoryUsage = MemoryUsage
                .from((CompositeDataSupport) mbsc.getAttribute(heapObjName,
                        "HeapMemoryUsage"));
        long commitMemory = heapMemoryUsage.getCommitted();// 堆当前分配
        long usedMemory = heapMemoryUsage.getUsed();
        System.out.print("堆内存总量:" + heapMemoryUsage.getMax() / (1024 * 1024) + "M,当前分配量:" + commitMemory / (1024 * 1024) + "M,当前使用量:" + usedMemory / (1024 * 1024) + "M,");
        System.out.println("堆内存使用率:" + (int) (usedMemory * 100
                / commitMemory) + "%");// 堆使用率

        //栈内存
        MemoryUsage nonheapMemoryUsage = MemoryUsage
                .from((CompositeDataSupport) mbsc.getAttribute(heapObjName,
                        "NonHeapMemoryUsage"));
        long noncommitMemory = nonheapMemoryUsage.getCommitted();
        long nonusedMemory = nonheapMemoryUsage.getUsed();

        System.out.println("栈内存使用率:" + (int) (nonusedMemory * 100
                / noncommitMemory) + "%");

        ObjectName managerObjName = new ObjectName(
                "Tomcat:basic.type=Manager,*");
        Set<ObjectName> s = mbsc.queryNames(managerObjName, null);
        for (ObjectName obj : s) {

            ObjectName objname = new ObjectName(obj.getCanonicalName());
            System.out.print("objectName:" + objname);
            System.out.print(",最大会话数:"
                    + mbsc.getAttribute(objname, "maxActiveSessions") + ",");
            System.out.print("会话数:"
                    + mbsc.getAttribute(objname, "activeSessions") + ",");
            System.out.println("活动会话数:"
                    + mbsc.getAttribute(objname, "sessionCounter"));

            ThreadCollector threadCollector = new ThreadCollector(mbsc);
            ThreadCollector.ThreadCollectorInfo threadCollectorInfo = threadCollector.getThreadCollectInfo();
            System.out.println(threadCollectorInfo);


            GcCollector gcCollector = new GcCollector(mbsc);
            GcCollector.GcCollectorInfo gcCollectorInfo = gcCollector.getGcCollectInfo();

            System.out.println(gcCollectorInfo);

            JvmMemoryCollector jvmMemoryCollector = new JvmMemoryCollector(mbsc);

            JvmMemoryCollector.GcMemoryCollectorInfo gcMemoryCollectorInfo = jvmMemoryCollector.getGcMemoryCollectInfo();

            System.out.println(gcMemoryCollectorInfo);
        }

        return mbsc;

    }

}
