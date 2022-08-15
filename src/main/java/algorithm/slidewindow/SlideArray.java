package algorithm.slidewindow;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.locks.ReentrantLock;

public class SlideArray {


    private AtomicReferenceArray<SlideMetric> slideMetrics;


    private final ReentrantLock updateLock = new ReentrantLock();

    private int intervalSlideInMill = 0;

    private int intervalInMill = 0;

    private int currentIndex = 0;

    public SlideArray(int sampleCount, int intervalInMill) {

        intervalSlideInMill = intervalInMill / sampleCount;
        this.intervalInMill = intervalInMill;

        this.slideMetrics = new AtomicReferenceArray<SlideMetric>(sampleCount);


    }

    public long totalSuccess() {

        long total = 0;
        List<SlideMetric> valideSlideMetrics = values();

        for (int i = 0; i < valideSlideMetrics.size(); i++) {
            total += valideSlideMetrics.get(i).success();
        }
        return total;
    }


    public long totalFail() {

        long total = 0;
        List<SlideMetric> valideSlideMetrics = values();

        for (int i = 0; i < valideSlideMetrics.size(); i++) {
            total += valideSlideMetrics.get(i).fail();
        }
        return total;
    }

    public long totalCount() {

        return this.totalSuccess() + this.totalFail();
    }



    public long totalRt() {

        long total = 0;
        List<SlideMetric> valideSlideMetrics = values();

        for (int i = 0; i < valideSlideMetrics.size(); i++) {
            total += valideSlideMetrics.get(i).rt();
        }
        return total;
    }

    /**
     * 统计有效metric
     * @return
     */
    public List<SlideMetric> values() {

        long currentTime = System.currentTimeMillis();

        List<SlideMetric> slideMetricsFiltered = new ArrayList<>();

        for (int i = 0; i < slideMetrics.length(); i++) {

            SlideMetric slideMetric = slideMetrics.get(i);
            if (slideMetric == null || isMetricExpired(slideMetric.getStartTime(), currentTime)) {
                continue;
            }
            slideMetricsFiltered.add(slideMetric);
        }

        return slideMetricsFiltered;

    }

    /**
     * 是否过期
     *
     * @param startTime
     * @param currentTime
     * @return
     */
    private boolean isMetricExpired(long startTime, long currentTime) {
        return currentTime - startTime > intervalInMill;
    }

    public SlideMetric getSlideMetric(long time) {
        //计算时间开始位置
        long timeStart = time - time % intervalSlideInMill;

        while (true) {

            SlideMetric old = slideMetrics.get(currentIndex);

            //如果当前index为空
            if (old == null) {
                SlideMetric slideMetric = newSlideMetric(timeStart);
                if (slideMetrics.compareAndSet(currentIndex,null,slideMetric)) {
                    return slideMetric;
                } else {
                    // Contention failed, the basic.thread will yield its time slice to wait for SlideMetric available.
                    //Thread.yield();
                }
            } else {

                if (old.getStartTime() == timeStart) {
                    return old;
                } //如果当前窗口的起始时间小于timeStart，更新 currentIndex
                else if (old.getStartTime() < timeStart) {
                    //环形滚动
                    currentIndex = ++currentIndex % slideMetrics.length();
                    SlideMetric nextMetric = slideMetrics.get(currentIndex);
                    if (nextMetric == null) {
                        SlideMetric slideMetric = newSlideMetric(timeStart);

                        if (slideMetrics.compareAndSet(currentIndex,null,slideMetric)) {
                            return slideMetric;
                        } else {
                            //Thread.yield();
                        }
                    } else {
                        if (updateLock.tryLock()) {

                            try {
                                //覆盖原有的老的SlideMetric
                                SlideMetric slideMetric = newSlideMetric(timeStart);
                                slideMetrics.compareAndSet(currentIndex,nextMetric,slideMetric);
                                return slideMetric;
                            } finally {
                                updateLock.unlock();
                            }
                        } else {
                            //Thread.yield();
                        }
                    }
                } else {

                    throw new RuntimeException("error to get Slide Metric");
                }

            }

        }

    }

    private SlideMetric newSlideMetric(long timeStart) {
        SlideMetric slideMetric = new SlideMetric();
        slideMetric.setStartTime(timeStart);
        return slideMetric;
    }


    public int getIntervalInMill() {
        return intervalInMill;
    }

    public double getIntervalInSec() {
        return intervalInMill/1000.0;
    }

    public void setIntervalInMill(int intervalInMill) {
        this.intervalInMill = intervalInMill;
    }
}
