package test1.sub;

import java.util.UUID;

public class BuildData {
	private int count = 0;

	public String[][] buildData() {
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

	public synchronized void addCount(int add) {
		count = count + add;
	}

	public int getCount() {
		return count;
	}
}
