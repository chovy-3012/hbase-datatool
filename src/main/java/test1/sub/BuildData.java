package test1.sub;

import java.util.UUID;

//制造uudid 10个字段的测试数据,批量每次制造1000个
public class BuildData {
	private static int count = 0;

	public static String[][] buildData() {
		String[][] test = new String[1000][10];
		for (int i = 0; i < 1000; i++) {
			String id = UUID.randomUUID().toString().replaceAll("-", "");
			for (int j = 0; j < 10; j++) {
				test[i][j] = id + "-" + j;
			}
		}
		addCount(1000);
		return test;
	}

	public static synchronized void addCount(int add) {
		count = count + add;
	}

	public static int getCount() {
		return count;
	}
}
