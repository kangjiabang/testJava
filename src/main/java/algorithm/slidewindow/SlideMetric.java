package algorithm.slidewindow;

import java.util.concurrent.atomic.LongAdder;

public class SlideMetric {


    public SlideMetric() {
        MetricEvent[] metricEvents = MetricEvent.values();
        this.counters = new LongAdder[metricEvents.length];
        for (MetricEvent event : metricEvents) {
            counters[event.ordinal()] = new LongAdder();
        }
    }

    private LongAdder[] counters;

    private long startTime;


    public long success() {
        return get(MetricEvent.SUCCESS);
    }

    public long fail() {
        return get(MetricEvent.FAIL);
    }

    public long rt() {
        return get(MetricEvent.RT);
    }

    public void addSuccess(int value) {
        add(MetricEvent.SUCCESS,value);
    }

    public void addFail(int value) {
        add(MetricEvent.FAIL,value);
    }

    public void addRt(long rt) {
        add(MetricEvent.RT,rt);
    }



    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    private void add(MetricEvent event, long value) {
        counters[event.ordinal()].add(value);
    }
    private long get(MetricEvent event) {
        return counters[event.ordinal()].sum();
    }
}

