/*
package hbase;

import javafx.scene.control.Cell;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;

import java.util.List;

*/
/**
 * @Author：zeqi
 * @Date: Created in 15:21 6/10/18.
 * @Description:
 *//*

public class Hbasedemo {

    static Configuration conf = null;
    private static final String ZKconnect = "localhost:2181";

    static {
        conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", ZKconnect);
    }


    public static void main(String[] args) {
        Hbasedemo a = new Hbasedemo();
        String tableName = "student11";
        String[] family = {"lie01", "lie02"};

        HTableDescriptor htds = new HTableDescriptor(tableName);
        for (int z = 0; z < family.length; z++) {
            HColumnDescriptor h = new HColumnDescriptor(family[z]);
            htds.addFamily(h);
        }
        try {
            a.createTable(tableName,htds);
        } catch (Exception e) {
            e.printStackTrace();
        }

        */
/*Result result = a.getResult("table03", "usr001");
        System.out.println(result.toString());
        List<Cell> cells = result.listCells();
        for (int i = 0; i < cells.size(); i++) {
            Cell cell = cells.get(i);
        }
        System.out.println(cell.toString());
        //          printCell(cell); 
        // }*//*

    }

    public void createTable(String tableName, HTableDescriptor htds) throws Exception
    {  HBaseAdmin admin=new HBaseAdmin(conf);
      boolean tableExists1 = admin.tableExists(Bytes.toBytes(tableName));
      System.out.println(tableExists1 ? "表已存在" :"表不存在");
      admin.createTable(htds);
      boolean tableExists = admin.tableExists(Bytes.toBytes(tableName));
      System.out.println(tableExists ? "创建表成功" : "创建失败");
    }



}
*/
