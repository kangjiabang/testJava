package thread.dubbojmx;


import thread.jmx.Hello;

import javax.management.JMException;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class DubboRegisterServer
{
    public static void main(String[] args) throws JMException, NullPointerException
    {
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();
        ObjectName helloName = new ObjectName("jmxBean:name=dubbo");
        //create mbean and register mbean
        server.registerMBean(new Hello(), helloName);


        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}