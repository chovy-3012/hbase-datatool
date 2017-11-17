package test1.sub;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Date;

import org.apache.commons.dbutils.QueryRunner;

//local index表插入数据
public class LocalData {
	public void execute(String arg) {
		int thread = 1;
		int parseInt = Integer.parseInt(arg);
		thread = parseInt;

		for (int i = 0; i < thread; i++) {
			new Thread() {
				public void run() {
					insertData();
				}
			}.start();
		}
	}

	public void insertData() {
		try {
			Connection connection = null;
			Class.forName("org.apache.phoenix.jdbc.PhoenixDriver");
			connection = DriverManager.getConnection("jdbc:phoenix:192.168.2.5:2181", "", "");
			QueryRunner queryRunner = new QueryRunner();

			String[][] data = null;
			while ((data = BuildData.buildData()) != null) {
				queryRunner.batch(connection, "upsert into test_table_local values (?,?,?,?,?,?,?,?,?,?)", data);
				connection.commit();
				System.out.println(new Date() + ":" + Thread.currentThread().getName() + "--" + BuildData.getCount());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
