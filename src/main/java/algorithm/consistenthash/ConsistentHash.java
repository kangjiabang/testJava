package consistenthash;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by Administrator on 2018/11/10.
 */
public class ConsistentHash {

    public ConsistentHash() {
        this.initNodes();
    }

    /**
     * 副本个数
     */
    private static final int REPLIC = 2;
    /**
     * hash 值和虚拟节点之间映射
     */
    private SortedMap<Long,VirtualNode> hashToVirtualNodeMap  = Maps.newTreeMap();
    /**
     * 虚拟节点和真实节点的映射
     */
    private Map<VirtualNode,Machine> virtualNodeMachineTreeMap  = Maps.newHashMap();
    /**
     * 机器列表
     */
    private List<Machine> machines = ImmutableList.of(new Machine("127.0.0.1"),new Machine("127.0.0.2"),new Machine("127.0.0.3"));

    public void initNodes() {

        machines.stream().forEach( machine -> {
            for (int i=0;i< REPLIC;i++) {
                  virtualNodeMachineTreeMap.put(new VirtualNode(machine.getIp() +"#" + (i+1)),machine);
            }
        });

        virtualNodeMachineTreeMap.entrySet().stream().forEach( virtualNodeMachineEntry -> {
            //127.0.0.1#1 <--> 127.0.0.1
            hashToVirtualNodeMap.put(hash(virtualNodeMachineEntry.getKey().getName()),virtualNodeMachineEntry.getKey());
        });

    }

    /**
     * 求Hash值
     * @param key
     * @return
     */
    private Long hash(String key) {
        if (StringUtils.isEmpty(key)) {
            return 0L;
        }
        return Hashing.md5().hashBytes(key.getBytes()).asLong();
    }

    /**
     * 获取存储的机器
     * @param value
     * @return
     */
    public Machine getMachine(String value) {
       VirtualNode virtualNode = this.getVirtualNode(value);
        return virtualNodeMachineTreeMap.get(virtualNode);
    }

    /**
     * 获取存储的机器
     * @param value
     * @return
     */
    public VirtualNode getVirtualNode(String value) {
        SortedMap<Long,VirtualNode> tailMap =  this.hashToVirtualNodeMap.tailMap(hash(value));
        //如果截取后的map为空，取第一个机器
        if (tailMap.size() == 0) {
            return hashToVirtualNodeMap.get(hashToVirtualNodeMap.firstKey());
        }
        return tailMap.get(tailMap.firstKey());
    }


    /**
     * Machine
     */
    @Data
    private static class Machine {

        public Machine(String ip) {
            this.ip = ip;
        }

        private String name;
        private String ip;

    }

    /**
     * 虚拟节点
     */
    @Data
    private static class VirtualNode {
        public VirtualNode(String name) {
            this.name = name;
        }

        private String name;
    }

    public static void main(String[] args) {
        ConsistentHash consistentHash = new ConsistentHash();
        System.out.println(consistentHash.virtualNodeMachineTreeMap);
        System.out.println(consistentHash.hashToVirtualNodeMap);
        System.out.println("nihao hash value="  + consistentHash.hash("nihao"));
        System.out.println(consistentHash.getMachine("nihao"));
        System.out.println("nihao1 hash ="  + consistentHash.hash("nihao1"));
        System.out.println(consistentHash.getMachine("nihao1"));
    }
}
