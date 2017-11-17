package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.text.ParseException;
import java.util.Date;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InsertData {
	private static Logger log = LoggerFactory.getLogger(InsertData.class);

	public static void main(String[] args) throws ParseException {
		args = new String[] { "20170103000000", "20170131000000" };
		Date start = DateUtils.parseDate(args[0], "yyyyMMddHHmmss");
		Date end = DateUtils.parseDate(args[1], "yyyyMMddHHmmss");
		try {
			Connection connection = null;
			Class.forName("org.apache.phoenix.jdbc.PhoenixDriver");
			connection = DriverManager.getConnection("jdbc:phoenix:192.168.2.9:2181", "", "");
			QueryRunner queryRunner = new QueryRunner();

			BuildData buildData = new BuildData(250, start, end);
			String[][] buildData2 = null;
			int num = 0;
			long all = 0;
			while ((buildData2 = buildData.buildData()) != null) {
				int[] batch = queryRunner.batch(connection, "upsert into car_test values(?,?,?,?,?)", buildData2);
				num += batch.length;
				all += batch.length;
				if (num > 1000) {
					long time1 = System.currentTimeMillis();
					connection.commit();
					long time2 = System.currentTimeMillis();
					num = 0;
					System.out.println(new Date() + ":" + "-start:" + args[0] + "-end:" + args[1] + "--" + all + "--"
							+ (time2 - time1));
				}
				// num++;
				// all++;
				// if (num > 1000) {
				// num=0;
				// System.out.println(new Date() + ":" + "-start:" + args[0] +
				// "-end:" + args[1] + "--" + all);
				// }
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
