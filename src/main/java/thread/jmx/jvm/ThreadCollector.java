package thread.jmx.jvm;


import javax.management.MBeanServerConnection;
import javax.management.ObjectName;

public  class ThreadCollector {

    private MBeanServerConnection mbsc;

    public ThreadCollector(MBeanServerConnection mbsc) {
        this.mbsc = mbsc;
    }

    public ThreadCollectorInfo getThreadCollectInfo() {
        try {
            ThreadCollectorInfo threadCollectorInfo = new ThreadCollectorInfo();

            ObjectName threadObjName = new ObjectName("java.lang:type=Threading");

            int  peakThreadCount = (int) mbsc.getAttribute(threadObjName,
                    "PeakThreadCount");

            int  threadCount = (int) mbsc.getAttribute(threadObjName,
                    "ThreadCount");

            threadCollectorInfo.setActiveThreadCount(threadCount);
            threadCollectorInfo.setPeakThreadCount(peakThreadCount);
            return threadCollectorInfo;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public class ThreadCollectorInfo {

        private long activeThreadCount;

        private long peakThreadCount;

        public long getActiveThreadCount() {
            return activeThreadCount;
        }

        public void setActiveThreadCount(long activeThreadCount) {
            this.activeThreadCount = activeThreadCount;
        }

        public long getPeakThreadCount() {
            return peakThreadCount;
        }

        public void setPeakThreadCount(long peakThreadCount) {
            this.peakThreadCount = peakThreadCount;
        }


        @Override
        public String toString() {
            return "ThreadCollectorInfo{" +
                    "activeThreadCount=" + activeThreadCount +
                    ", peakThreadCount=" + peakThreadCount +
                    '}';
        }
    }
}