package optimize.prometheus;
import io.prometheus.client.Collector;
import io.prometheus.client.Counter;
import io.prometheus.client.GaugeMetricFamily;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class CounterStatistics {
    static final Counter requests = Counter.build()
            .name("requests_total").help("Total requests.").register();

    public CounterStatistics() {
            // Registration
           new YourCustomCollector().register();
    }

    void processRequest() {
        requests.inc();
        // Your code here.
    }

    class YourCustomCollector extends Collector {
        @Override
        public List<MetricFamilySamples> collect() {
            List<MetricFamilySamples> mfs = new ArrayList<MetricFamilySamples>();
            //With no labels
            mfs.add(new GaugeMetricFamily("my_gauge","help",42));
            //With labels
            GaugeMetricFamily labeledGauge = new GaugeMetricFamily("my_other_gauge","help", Arrays.asList("labelname"));
            labeledGauge.addMetric(Arrays.asList("foo"),4);
            labeledGauge.addMetric(Arrays.asList("bar"),5);
            mfs.add(labeledGauge);

            return mfs;
        }

    }
}