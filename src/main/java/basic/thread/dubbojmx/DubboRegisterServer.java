package basic.thread.dubbojmx;


import basic.thread.jmx.Hello;

import javax.management.JMException;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.io.IOException;
import java.lang.management.ManagementFactory;

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