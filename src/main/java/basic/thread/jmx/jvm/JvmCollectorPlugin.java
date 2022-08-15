package basic.thread.jmx.jvm;

import javax.management.MBeanServerConnection;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.util.HashMap;
import java.util.Map;

public class JvmCollectorPlugin {


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


        return mbsc;

    }

    public static ThreadCollector.ThreadCollectorInfo getThreadCollectorInfo(MBeanServerConnection mBeanServerConnection) {
        ThreadCollector threadCollector = new ThreadCollector(mBeanServerConnection);
        return threadCollector.getThreadCollectInfo();

    }

    public static GcCollector.GcCollectorInfo getGcCollectorInfo(MBeanServerConnection mBeanServerConnection) {
        GcCollector gcCollector = new GcCollector(mBeanServerConnection);
        return gcCollector.getGcCollectInfo();

    }

    public static JvmMemoryCollector.GcMemoryCollectorInfo getGcMemoryCollectorInfo(MBeanServerConnection mBeanServerConnection) {
        JvmMemoryCollector jvmMemoryCollector = new JvmMemoryCollector(mBeanServerConnection);

        return jvmMemoryCollector.getGcMemoryCollectInfo();

    }


}
