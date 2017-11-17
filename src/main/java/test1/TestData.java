package test1;

import test1.sub.AbstractData;
import test1.sub.GlobalData;
import test1.sub.HbaseData;
import test1.sub.LocalData;
import test1.sub.PhoenixData;

//普通表插入数据
public class TestData {
	// args 0.程序名称1.线程数2.起始记数
	public static void main(String[] args) {
		if (args == null || args.length == 0) {
			throw new RuntimeException("至少要有一个参数");
		}
		String program = args[0];
		AbstractData adata = null;
		if (program.equals("hbaseData")) {
			adata = new HbaseData();
		} else if (program.equals("phoenixData")) {
			adata = new PhoenixData();
		} else if (program.equals("localData")) {
			adata = new LocalData();
		} else if (program.equals("globalData")) {
			adata = new GlobalData();
		}
		adata.execute(args);
	}
}
