package optimize.prometheus;

import io.prometheus.client.exporter.HTTPServer;
import io.prometheus.client.hotspot.DefaultExports;

import java.io.IOException;

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
