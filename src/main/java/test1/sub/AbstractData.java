package test1.sub;

public abstract class AbstractData {

	public void execute(String[] args) {
		// 线程数量
		int thread = 1;
		if (args.length >= 2) {
			int parseInt = Integer.parseInt(args[1]);
			thread = parseInt;
		}
		// 计数器
		if (args.length >= 3) {
			long parseLong = Long.parseLong(args[2]);
			BuildData.setCount(parseLong);
		}
		for (int i = 0; i < thread; i++) {
			new Thread() {
				public void run() {
					insertData();
				}
			}.start();
		}
	}

	public abstract void insertData();
}
