package test1;

import test1.sub.GlobalData;
import test1.sub.HbaseData;
import test1.sub.LocalData;
import test1.sub.PhoenixData;

//普通表插入数据
public class TestData {
	public static void main(String[] args) {
		if (args == null || args.length == 0) {
			throw new RuntimeException("至少要有一个参数");
		}
		String program = args[0];
		if (program.equals("phoenixData")) {
			PhoenixData phoenixData = new PhoenixData();
			phoenixData.execute(args[1]);
		} else if (program.equals("hbaseData")) {
			HbaseData hbaseData = new HbaseData();
			hbaseData.execute(args[1]);
		} else if (program.equals("localData")) {
			LocalData localData = new LocalData();
			localData.execute(args[1]);
		} else if (program.equals("globalData")) {
			GlobalData globalData = new GlobalData();
			globalData.execute(args[1]);
		}
	}
}
