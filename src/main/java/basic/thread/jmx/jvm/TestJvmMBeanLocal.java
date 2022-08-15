package basic.thread.jmx.jvm;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.Iterator;

public class TestJvmMBeanLocal {

    public static void main(String[] args) {

        try {
            MBeanServerConnection beanConn;
            JMXConnector jmxc = null;
            beanConn = ManagementFactory.getPlatformMBeanServer();
            Iterator<ObjectName> iterator = beanConn.queryNames(null,null).iterator();

            ObjectName threadObjName = new ObjectName("java.lang:basic.type=Threading");

            //MBeanInfo mBeanInfo = beanConn.getMBeanInfo(threadObjName);

            ThreadMXBean mx = ManagementFactory.newPlatformMXBeanProxy(beanConn,ManagementFactory.THREAD_MXBEAN_NAME, ThreadMXBean.class);

            ThreadInfo[] txx = mx.dumpAllThreads(true, false);

            System.out.println(txx);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
