package thread;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class ProcessBuilderTest {


    public static void main(String[] args) throws Exception {
        // Async part
        Runnable r = () -> {
            ProcessBuilder pb = new ProcessBuilder().command("ls","-al");
            // Merge System.err and System.out
            pb.redirectErrorStream(true);
            // Inherit System.out as redirect output stream
            pb.redirectOutput(ProcessBuilder.Redirect.INHERIT);
            try {
                pb.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
        new Thread(r, "asyncOut").start();

    }

}
