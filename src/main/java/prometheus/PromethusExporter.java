package prometheus;

import io.prometheus.client.exporter.HTTPServer;
import io.prometheus.client.hotspot.DefaultExports;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class PromethusExporter {
    public static void main(String[] args) throws IOException {

        //HTTPServer server = new HTTPServer(1234);
        HTTPServer server = new HTTPServer(1235);

        CounterStatistics counterStatistics = new CounterStatistics();
        for (int i=0;i< 10;i++) {

            counterStatistics.processRequest();
        }

        // JVM exports
        DefaultExports.initialize();

    }
}
