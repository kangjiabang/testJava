package virtualmachine;

import com.sun.tools.attach.*;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class VirtualMachineTest {

    @Test
    public void testVirtualMachineList() {

        List<VirtualMachineDescriptor> list = VirtualMachine.list();
        for (VirtualMachineDescriptor vmd : list) {
            System.out.println("pid:" + vmd.id() + ":" + vmd.displayName());
        }
    }

    @Test
    public void testVirtualMachineAttach() {

        try {
            String pid = "5109";
            VirtualMachine virtualmachine = VirtualMachine.attach(pid);
        } catch (AttachNotSupportedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testVirtualMachineLoadAgent() {

        try {
            String pid = "5109";
            VirtualMachine virtualmachine = VirtualMachine.attach(pid);

            String javaHome = virtualmachine.getSystemProperties().getProperty("java.home");
            String agentPath = javaHome + File.separator + "jre" + File.separator + "lib" + File.separator + "management-agent.jar";
            ;
            File file = new File(agentPath);
            if (!file.exists()) {
                agentPath = javaHome + File.separator + "lib" + File.separator + "management-agent.jar";
                file = new File(agentPath);
                if (!file.exists())
                    throw new IOException("Management agent not found");
            }


            agentPath = file.getCanonicalPath();

            virtualmachine.loadAgent(agentPath, "com.sun.management.jmxremote");

            Properties properties = virtualmachine.getAgentProperties();
            String address = (String) properties.get("com.sun.management.jmxremote.localConnectorAddress");
            virtualmachine.detach();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testVirtualMachineLoadAgent2() {

        try {
            String pid = "45751";
            VirtualMachine virtualmachine = VirtualMachine.attach(pid);

            virtualmachine.loadAgent("/Users/kangjiabangnew/document/code/javaAgent/target/javaAgent-1.0-SNAPSHOT.jar");

            //virtualmachine.detach();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
