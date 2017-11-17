package test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.time.DateUtils;

//id，日期,号牌号码，车型，颜色
public class BuildData {
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	private static String[] ys = new String[] { "红", "橙", "黄", "绿", "青", "蓝", "紫" };
	private static String[] cx = new String[] { "大众", "别克", "奥迪", "宝马" };
	private static String[] zm = new String[] { "A", "B", "C", "D", "E", "F", "G", "H", "I", "G", "K", "L", "M", "N",
			"O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };
	private static String[] zmSz = new String[] { "A", "B", "C", "D", "E", "F", "G", "H", "I", "G", "K", "L", "M", "N",
			"O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "1", "2", "3", "4", "5", "6", "7", "8", "9",
			"0" };
	private static String[] prv = new String[] { "京", "津", "沪", "渝", "冀", "晋", "辽", "吉", "黑", "苏", "浙", "皖", "闽", "赣",
			"鲁", "豫", "鄂", "湘", "粤", "琼", "川", "贵", "云", "陕", "甘", "青", "台", "蒙", "桂", "宁", "新", "藏", "港", "澳" };

	private Calendar start = Calendar.getInstance();
	private Calendar end = Calendar.getInstance();

	private int dayOfSecond = 250;

	public BuildData(int dayOfSecond, Date start, Date end) {
		this.start.setTime(start);
		this.end.setTime(end);
		this.dayOfSecond = dayOfSecond;
	}

	// 生成数据
	public String[][] buildData() {
		if (dayOfSecond > 0) {
			String date = getDate();
			if (date == null) {
				return null;
			}
			String[][] result = new String[dayOfSecond][5];
			for (int i = 0; i < dayOfSecond; i++) {
				result[i][0] = getId();
				result[i][1] = date;
				result[i][2] = getHphm();
				result[i][3] = getCx();
				result[i][4] = getYs();
			}
			return result;
		}
		return null;
	}

	// id
	private String getId() {
		String string = UUID.randomUUID().toString().replaceAll("-", "");
		return string;
	}

	// 日期
	private String getDate() {
		if (end.getTime().getTime() >= start.getTime().getTime()) {
			start.setTime(DateUtils.addSeconds(start.getTime(), 1));
			return sdf.format(start.getTime());
		}
		return null;
	}

	// 车牌 苏E3G02D
	private String getHphm() {
		String p = prv[RandomUtils.nextInt(0, prv.length)];
		StringBuilder sb = new StringBuilder(p);
		sb.append(zm[RandomUtils.nextInt(0, zm.length)]);
		for (int i = 0; i < 5; i++) {
			sb.append(zmSz[RandomUtils.nextInt(0, zmSz.length)]);
		}
		return sb.toString();
	}

	// 车型
	private String getCx() {
		int nextInt = RandomUtils.nextInt(0, cx.length);
		return cx[nextInt];
	}

	// 颜色
	private String getYs() {
		int nextInt = RandomUtils.nextInt(0, ys.length);
		return ys[nextInt];
	}

	public static void main(String[] args) {
	}
}
