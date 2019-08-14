package slidewindow;


public class StatisticNode {

    private SlideArray slideArray;


    public StatisticNode(int sampleCount, int intervalInMill) {
        this.slideArray = new SlideArray(sampleCount, intervalInMill);
    }



    public void addSuccess() {
        SlideMetric slideMetric = slideArray.getSlideMetric(System.currentTimeMillis());
        slideMetric.addSuccess(1);
    }

    public void addSuccess(int count) {
        SlideMetric slideMetric = slideArray.getSlideMetric(System.currentTimeMillis());
        slideMetric.addSuccess(count);
    }

    public void addFail(int count) {
        SlideMetric slideMetric = slideArray.getSlideMetric(System.currentTimeMillis());
        slideMetric.addFail(count);
    }

    public void addRt(long rt) {
        SlideMetric slideMetric = slideArray.getSlideMetric(System.currentTimeMillis());
        slideMetric.addRt(rt);
    }

    public void addRtAndSuccess(long rt,int successCount) {
        this.addRt(rt);
        this.addSuccess(successCount);
    }

    public void addRtAndFail(long rt,int failCount) {
        this.addRt(rt);
        this.addFail(failCount);
    }

    public long totalSuccess() {
        return slideArray.totalSuccess();
    }
    public double avgRt() {
        long totalCount = slideArray.totalCount();
        if (totalCount == 0) {
            return 0;
        }
        return slideArray.totalRt()*1.0/totalCount;
    }


    public double qps() {
        return slideArray.totalCount()/slideArray.getIntervalInSec();
    }

}
