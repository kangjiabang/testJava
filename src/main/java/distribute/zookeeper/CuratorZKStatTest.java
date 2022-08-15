package distribute.zookeeper;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCache.StartMode;
import org.apache.curator.retry.RetryNTimes;
import org.apache.dubbo.common.utils.CollectionUtils;
import org.apache.zookeeper.data.Stat;

import java.util.List;

/**
 * Curator framework watch test.
 */
public class CuratorZKStatTest {

    /** Zookeeper info */
    private static final String ZK_ADDRESS = "localhost:2181";
    private static final String ZK_PATH = "/zktest";

    public static void main(String[] args) throws Exception {
        // 1.Connect to zk
        CuratorFramework client = CuratorFrameworkFactory.newClient(
                ZK_ADDRESS,
                new RetryNTimes(10, 5000)
        );
        client.start();
        System.out.println("zk client start successfully!");


        List<String> childrenList = client.getChildren().forPath("/");
        Stat rootStat = client.checkExists().forPath("/");
        String rootInfo = formatStat(rootStat);
        System.out.println(rootInfo);
        if (CollectionUtils.isNotEmpty(childrenList)) {
            for (String child : childrenList) {
                client.checkExists().forPath("/");
            }
        }
        System.out.println("Register zk watcher successfully!");

        Thread.sleep(Integer.MAX_VALUE);
    }

    private static String formatStat(Stat stat) {
        System.out.println(stat);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        stringBuilder.append("\n");
        stringBuilder.append(String.format("nodeCount:%s",stat.getNumChildren()));
        stringBuilder.append(",\n");
        stringBuilder.append(String.format("ctime:%s",stat.getCtime()));
        stringBuilder.append(",\n");
        stringBuilder.append(String.format("mtime:%s",stat.getMtime()));
        stringBuilder.append(",\n");
        stringBuilder.append(String.format("dataLength:%s",stat.getDataLength()));
        stringBuilder.append("\n");
        stringBuilder.append("]");
        return stringBuilder.toString();

    }

}