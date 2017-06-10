/**
 * Copyright(C) 2017 Luvina Software Company
 * manageuser.utils, 2017/05/01, DOVANDUNG
 */
package manageuser.utils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import manageuser.entities.YearMonthDay;

/**
 * class xử lý date
 * @author dovandung
 *
 */
public class StringUtil {
	/**
	 * Chuyển mảng string thành mảng int
	 * 
	 * @param arrString
	 *            mảng string
	 * @return mảng int
	 */
	public static List<Integer> arrStringToArrInt(String[] arrString) {
		List<Integer> result = new ArrayList<Integer>();

		for (int i = 0; i < arrString.length; i++) {
			result.add(Integer.parseInt(arrString[i]));
		}
		return result;
	}

	/**
	 * Chuyển chuỗi date định dạng yyyy/MM/dd thành mảng int
	 * 
	 * @param date
	 *            chuỗi date
	 * @return mảng int
	 */
	public static List<Integer> convertStringDateToArrInt(String date) {
		if (!"".equals(date)) {
			String[] arrStrDate = date.split("/");
			return arrStringToArrInt(arrStrDate);
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @param z
	 * @return
	 */
	public static String DateObjectToDateInDb(int x, int y, int z) {
		String date = null;
		if (y < 10 && z < 10) {
			date = Integer.toString(x) + "0" + Integer.toString(y) + "0" + Integer.toString(z);
		} else if (y < 10) {
			date = Integer.toString(x) + "0" + Integer.toString(y) + Integer.toString(z);
		} else if (z < 10) {
			date = Integer.toString(x) + Integer.toString(y) + "0" + Integer.toString(z);
		}
		return date;
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public static String convertDateObjectToString(YearMonthDay date) {
		String convertedString = "";
		if (date != null) {
			convertedString = Integer.toString(date.getYear()) + "/" + Integer.toString(date.getMonth()) + "/"
					+ Integer.toString(date.getDay());
		}
		return convertedString;
	}

	/**
	 * Hàm kiểm tra chuỗi có phải chứa toàn bộ ký tự 1 byte hay không
	 * 
	 * @param str
	 *            chuỗi cần kiểm tra
	 * @return true nếu là chứa toàn ký tự 1 byte, ngược lại trả về false
	 */
	public static boolean checkCharIs1Byte(String str) {
		try {
			if (str.length() != str.getBytes("UTF-8").length) {
				return false;
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

}
