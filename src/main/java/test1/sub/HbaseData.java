package test1.sub;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;

//批量插入hbase数据
public class HbaseData extends AbstractData {

	public void insertData() {
		try {
			Configuration create = HBaseConfiguration.create();
			create.set("hbase.zookeeper.quorum", "192.168.2.5:2181");
			Connection createConnection = ConnectionFactory.createConnection(create);
			Table table = createConnection.getTable(TableName.valueOf("test_table_hbase"));
			for (;;) {
				List<Put> puts = new ArrayList<Put>();
				String[][] buildData = BuildData.buildData();
				for (int i = 0; i < buildData.length; i++) {
					String[] array1 = buildData[i];
					String cf1 = "cf1";
					String rowkey = array1[0];
					Put put = new Put(array1[0].getBytes());
					put.addColumn(cf1.getBytes(), "f1".getBytes(), (rowkey + "-" + 0).getBytes());
					put.addColumn(cf1.getBytes(), "f2".getBytes(), (rowkey + "-" + 1).getBytes());
					put.addColumn(cf1.getBytes(), "f3".getBytes(), (rowkey + "-" + 2).getBytes());
					put.addColumn(cf1.getBytes(), "f4".getBytes(), (rowkey + "-" + 3).getBytes());
					put.addColumn(cf1.getBytes(), "f5".getBytes(), (rowkey + "-" + 4).getBytes());
					put.addColumn(cf1.getBytes(), "f6".getBytes(), (rowkey + "-" + 5).getBytes());
					put.addColumn(cf1.getBytes(), "f7".getBytes(), (rowkey + "-" + 6).getBytes());
					put.addColumn(cf1.getBytes(), "f8".getBytes(), (rowkey + "-" + 7).getBytes());
					put.addColumn(cf1.getBytes(), "f9".getBytes(), (rowkey + "-" + 8).getBytes());
					puts.add(put);
				}
				table.put(puts);
				System.out.println(new Date() + ":" + Thread.currentThread().getName() + "--" + BuildData.getCount());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
