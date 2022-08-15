package basic.thread.jmx.jvm;


import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.openmbean.CompositeDataSupport;
import java.lang.management.MemoryUsage;

public  class JvmMemoryCollector {

    private MBeanServerConnection mbsc;

    public JvmMemoryCollector(MBeanServerConnection mbsc) {
        this.mbsc = mbsc;
    }

    public GcMemoryCollectorInfo getGcMemoryCollectInfo() {
        try {
            GcMemoryCollectorInfo gcMemoryCollectorInfo = new GcMemoryCollectorInfo();
            ObjectName edenHeapObjName = new ObjectName("java.lang:basic.type=MemoryPool,name=PS Eden Space");

            MemoryUsage youngHeapUsage = MemoryUsage
                    .from((CompositeDataSupport) mbsc.getAttribute(edenHeapObjName, "Usage"));

            gcMemoryCollectorInfo.setEdenUsed(youngHeapUsage.getUsed()/(1024*1024));
            gcMemoryCollectorInfo.setEdenCommited(youngHeapUsage.getCommitted()/(1024*1024));
            gcMemoryCollectorInfo.setEdenTotal(youngHeapUsage.getMax()/(1024*1024));
            gcMemoryCollectorInfo.setEdenPercent((int)(youngHeapUsage.getUsed()*100/youngHeapUsage.getCommitted()));


            ObjectName oldHeapObjName = new ObjectName("java.lang:basic.type=MemoryPool,name=PS Old Gen");

            MemoryUsage oldHeapUsage = MemoryUsage
                    .from((CompositeDataSupport) mbsc.getAttribute(oldHeapObjName, "Usage"));

            gcMemoryCollectorInfo.setOldUsed(oldHeapUsage.getUsed()/(1024*1024));
            gcMemoryCollectorInfo.setOldCommited(oldHeapUsage.getCommitted()/(1024*1024));
            gcMemoryCollectorInfo.setOldTotal(oldHeapUsage.getMax()/(1024*1024));
            gcMemoryCollectorInfo.setOldPercent((int)(oldHeapUsage.getUsed()*100/oldHeapUsage.getCommitted()));


            ObjectName metaHeapObjName = new ObjectName("java.lang:basic.type=MemoryPool,name=Metaspace");

            MemoryUsage metaHeapUsage = MemoryUsage
                    .from((CompositeDataSupport) mbsc.getAttribute(metaHeapObjName, "Usage"));

            gcMemoryCollectorInfo.setMetaUsed(metaHeapUsage.getUsed()/(1024*1024));
            gcMemoryCollectorInfo.setMetaCommited(metaHeapUsage.getCommitted()/(1024*1024));
            gcMemoryCollectorInfo.setMetaTotal(metaHeapUsage.getMax()/(1024*1024));
            gcMemoryCollectorInfo.setMetaPercent((int)(metaHeapUsage.getUsed()*100/metaHeapUsage.getCommitted()));

           /* System.out.print("目前新生区分 配最大内存:"+youngHeapUsage.getMax()/(1024*1024)+"M,");
            System.out.print("新生区已分配:"+youngHeapUsage.getCommitted()/(1024*1024)+"M,");
            System.out.print("新生区初始化:"+youngHeapUsage.getInit()/(1024*1024)+"M,");
            System.out.println("新生区已使用"+youngHeapUsage.getUsed()/(1024*1024)+"M");*/

            return gcMemoryCollectorInfo;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public class GcMemoryCollectorInfo {

        /**
         * 年老代使用百分比
         */
        private int oldPercent;

        /**
         * eden使用百分比
         */
        private int edenPercent;

        /**
         * meta使用百分比
         */
        private int metaPercent;

        /**
         *  年老代已使用 mb
         */
        private long oldUsed;

        /**
         * old已分配 mb
         */
        private long oldCommited;


        /**
         * 年老代已总的 mb
         */
        private long oldTotal;

        /**
         *  eden已使用 mb
         */
        private long edenUsed;

        /**
         * eden已分配 mb
         */
        private long edenCommited;

        /**
         * eden 总的 mb
         */
        private long edenTotal;

        /**
         *  meta已使用 mb
         */
        private long metaUsed;

        /**
         * meta已分配 mb
         */
        private long metaCommited;

        /**
         * meta 总的 mb
         */
        private long metaTotal;

        public int getOldPercent() {
            return oldPercent;
        }

        public void setOldPercent(int oldPercent) {
            this.oldPercent = oldPercent;
        }

        public int getEdenPercent() {
            return edenPercent;
        }

        public void setEdenPercent(int edenPercent) {
            this.edenPercent = edenPercent;
        }

        public long getOldUsed() {
            return oldUsed;
        }

        public void setOldUsed(long oldUsed) {
            this.oldUsed = oldUsed;
        }

        public long getOldTotal() {
            return oldTotal;
        }

        public void setOldTotal(long oldTotal) {
            this.oldTotal = oldTotal;
        }

        public long getEdenUsed() {
            return edenUsed;
        }

        public void setEdenUsed(long edenUsed) {
            this.edenUsed = edenUsed;
        }

        public long getEdenTotal() {
            return edenTotal;
        }

        public void setEdenTotal(long edenTotal) {
            this.edenTotal = edenTotal;
        }


        public long getOldCommited() {
            return oldCommited;
        }

        public void setOldCommited(long oldCommited) {
            this.oldCommited = oldCommited;
        }

        public long getEdenCommited() {
            return edenCommited;
        }

        public void setEdenCommited(long edenCommited) {
            this.edenCommited = edenCommited;
        }

        public int getMetaPercent() {
            return metaPercent;
        }

        public void setMetaPercent(int metaPercent) {
            this.metaPercent = metaPercent;
        }

        public long getMetaUsed() {
            return metaUsed;
        }

        public void setMetaUsed(long metaUsed) {
            this.metaUsed = metaUsed;
        }

        public long getMetaCommited() {
            return metaCommited;
        }

        public void setMetaCommited(long metaCommited) {
            this.metaCommited = metaCommited;
        }

        public long getMetaTotal() {
            return metaTotal;
        }

        public void setMetaTotal(long metaTotal) {
            this.metaTotal = metaTotal;
        }

        @Override
        public String toString() {
            return "GcMemoryCollectorInfo{" +
                    "oldPercent=" + oldPercent +
                    "%, edenPercent=" + edenPercent +
                    "%, metaPercent=" + metaPercent +
                    "%, oldUsed=" + oldUsed +
                    "m, oldCommited=" + oldCommited +
                    "m, oldTotal=" + oldTotal +
                    "m, edenUsed=" + edenUsed +
                    "m, edenCommited=" + edenCommited +
                    "m, edenTotal=" + edenTotal +
                    "m, metaUsed=" + metaUsed +
                    "m, metaCommited=" + metaCommited +
                    "m, metaTotal=" + metaTotal +
                    "m }";
        }
    }
}