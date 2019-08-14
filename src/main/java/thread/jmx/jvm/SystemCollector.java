package thread.jmx.jvm;


import com.sun.management.GcInfo;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.openmbean.CompositeDataSupport;

public  class SystemCollector {

    private MBeanServerConnection mbsc;

    public SystemCollector(MBeanServerConnection mbsc) {
        this.mbsc = mbsc;
    }

    public GcCollectorInfo getGcCollectInfo() {
        try {
            GcCollectorInfo gcCollectorInfo = new GcCollectorInfo();
            ObjectName oldThreadObjName = new ObjectName("java.lang:type=GarbageCollector,name=PS MarkSweep");
            long fullGcCount = (long)mbsc.getAttribute(oldThreadObjName,"CollectionCount");

            GcInfo fullGcInfo = GcInfo.from((CompositeDataSupport)mbsc.getAttribute(oldThreadObjName,"LastGcInfo"));

            gcCollectorInfo.setFullGcCount(fullGcCount);
            gcCollectorInfo.setLastFullGcCostTime(fullGcInfo.getDuration());
            System.out.println("fullGcCount: " + fullGcCount);
            System.out.println("last full Gc cost time: " + fullGcInfo.getDuration() + "ms");
            //System.out.println("last full Gc start time: " + gcInfo.getStartTime());

            ObjectName youngThreadObjName = new ObjectName("java.lang:type=GarbageCollector,name=PS Scavenge");
            long youngGcCount = (long)mbsc.getAttribute(youngThreadObjName,"CollectionCount");

            GcInfo yountGcInfo = GcInfo.from((CompositeDataSupport)mbsc.getAttribute(youngThreadObjName,"LastGcInfo"));

            gcCollectorInfo.setYoungGcCount(youngGcCount);
            gcCollectorInfo.setLastYoungGcCostTime(yountGcInfo.getDuration());
            System.out.println("last young Gc cost time: " + yountGcInfo.getDuration() + "ms");
            System.out.println("youngGcCount: " + youngGcCount);

            return gcCollectorInfo;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public class GcCollectorInfo {

        private long fullGcCount;

        private long youngGcCount;

        /**
         * ms
         */
        private long lastFullGcCostTime;

        /**
         * ms
         */
        private long lastYoungGcCostTime;

        public long getFullGcCount() {
            return fullGcCount;
        }

        public void setFullGcCount(long fullGcCount) {
            this.fullGcCount = fullGcCount;
        }

        public long getYoungGcCount() {
            return youngGcCount;
        }

        public void setYoungGcCount(long youngGcCount) {
            this.youngGcCount = youngGcCount;
        }

        public long getLastFullGcCostTime() {
            return lastFullGcCostTime;
        }

        public void setLastFullGcCostTime(long lastFullGcCostTime) {
            this.lastFullGcCostTime = lastFullGcCostTime;
        }

        public long getLastYoungGcCostTime() {
            return lastYoungGcCostTime;
        }

        public void setLastYoungGcCostTime(long lastYoungGcCostTime) {
            this.lastYoungGcCostTime = lastYoungGcCostTime;
        }

        @Override
        public String toString() {
            return "GcCollectorInfo{" +
                    "fullGcCount=" + fullGcCount +
                    ", youngGcCount=" + youngGcCount +
                    ", lastFullGcCostTime=" + lastFullGcCostTime +
                    " ms, lastYoungGcCostTime=" + lastYoungGcCostTime +
                    " ms}";
        }
    }
}