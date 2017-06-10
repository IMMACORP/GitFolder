/**
 * Copyright(C) 2017 Luvina SoftWare
Common.java, Jan 19, 2017 DoVanDung
 */
package manageuser.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

import manageuser.entities.UserInfor;
import manageuser.entities.YearMonthDay;
/**
 * Class chứa các hàm common
 * @author dovandung
 *
 */
public class Common {
	/**
	 * Kiểm tra đăng nhập
	 * 
	 * @param httpSession
	 *            session
	 * @return trống nếu đã đăng nhập, ngược lại return chuỗi /jsp/ADM001.jsp
	 */
	public static String checkLogin(HttpSession httpSession) {
		String template = "";
		if (httpSession.getAttribute("loginId") == null) {
			template = Constant.ADM001;
		}
		return template;
	}

	/**
	 * 
	 * @param totalRecords
	 * @param limit
	 * @param currentPage
	 * @return
	 */
	public static List<Integer> getListPaging(int totalRecords, int limit, int currentPage) {
		List<Integer> listPage = new ArrayList<Integer>();
		int total = getTotalPage(totalRecords, limit);
		int pageRange = getPageRange();
		int currentRange = getCurrentRange(currentPage, pageRange);
		int leftPage = getLeftPage(currentRange, pageRange);
		int rightPage = getRightPage(leftPage, pageRange, total);

		for (int i = leftPage; i <= rightPage; i++) {
			listPage.add(i);
		}

		return listPage;
	}

	/**
	 * Lấy trang bên khi click <<
	 * 
	 * @param currentPage
	 * @return
	 */
	public static int getLeftNext(int currentPage) {
		int pageRange = getPageRange();
		int currentRange = getCurrentRange(currentPage, pageRange);
		int leftPage = getLeftPage(currentRange, pageRange);

		if (leftPage == 1) {
			return 0;
		} else {
			return (leftPage - 3);
		}

	}

	/**
	 * Lấy trang bên khi click>>
	 * 
	 * @param totalRecords
	 * @param limit
	 * @param currentPage
	 * @return
	 */
	public static int getRightNext(int totalRecords, int limit, int currentPage) {
		int total = getTotalPage(totalRecords, limit);
		int pageRange = getPageRange();
		int currentRange = getCurrentRange(currentPage, pageRange);
		int leftPage = getLeftPage(currentRange, pageRange);
		int rightPage = getRightPage(leftPage, pageRange, total);

		if (rightPage == total) {
			return 0;
		} else {
			return (rightPage + 1);
		}

	}

	/**
	 * Hàm số tính trang đứng ngoài cùng bên phải
	 * 
	 * @param leftPage
	 * @param pageRange
	 * @param total
	 * @return
	 */
	private static int getRightPage(int leftPage, int pageRange, int totalPage) {
		int rightPage = leftPage;
		for (int i = 1; i < pageRange; i++) {
			if (rightPage >= totalPage) {
				return totalPage;
			} else {
				rightPage++;
			}
		}
		return rightPage;
	}

	/**
	 * Hàm số tính trang đứng ngoài cùng bên trái
	 * 
	 * @param currentRange
	 * @param pageRange
	 * @return
	 */
	private static int getLeftPage(int currentRange, int pageRange) {
		int leftPage = 0;
		if (currentRange <= 0) {
			leftPage = 1;
		} else {
			leftPage = pageRange * (currentRange - 1) + 1;
		}
		return leftPage;
	}

	/**
	 * hàm tìm Range thứ n của trang hiện tại(123 là range 1, 456 là range2)
	 * 
	 * @param currentPage
	 * @param pageRange
	 * @return
	 */
	public static int getCurrentRange(int currentPage, int pageRange) {
		int currentRange = 0;
		if (currentPage % pageRange == 0) {
			currentRange = currentPage / pageRange;
		} else {
			currentRange = currentPage / pageRange + 1;
		}
		return currentRange;
	}

	/**
	 * Hàm lấy tông số trang
	 * 
	 * @param totalRecords
	 *            tổng số record
	 * @param limit
	 *            số record trên 1 page
	 * @return tổng số trang
	 */
	public static int getTotalPage(int totalRecords, int limit) {
		int totalPage = 0;
		if (totalRecords % limit == 0) {
			totalPage = totalRecords / limit;
		} else {
			totalPage = totalRecords / limit + 1;
		}
		return totalPage;
	}

	/**
	 * Lấy độ dài của chuỗi
	 *
	 * @return độ dài của chuỗi
	 */
	public static int getPageRange() {
		int pageRange = Integer.parseInt(ConfigProperties.getData("page_range"));
		return pageRange;
	}

	/**
	 * Hàm lấy số lượng hiển thị bản ghi trên 1 trang
	 * 
	 * @return số lượng record cần lấy
	 */
	public static int getLimit() {
		int result = 0;
		result = Integer.parseInt(ConfigProperties.getData("limit"));
		return result;
	}

	/**
	 * Hàm lấy vị trí data cần lấy
	 * 
	 * @param currentPage
	 *            Trang hiện tại
	 * @param limit
	 *            lượng cần hiển thị trên 1 trang
	 * @return vị trí cần lấy
	 */
	public static int getOffset(int currentPage, int limit) {
		return (currentPage > 0) ? limit * ((int) currentPage - 1) : 0;
	}

	/**
	 * Hàm hiển thị dữ liệu Date theo định dạng yyyy/MM/đ
	 * 
	 * @param date
	 *            ngày hiển thị
	 * @return chuỗi date theo định dạng
	 */
	public static String displayDate(Date date) {
		String result = "";
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		if (date != null) {
			result = dateFormat.format(date);
		}
		return result;
	}

	/**
	 * Phương thức tạo ra chuỗi thời gian hiện tại
	 * 
	 * @return chuỗi thời gian
	 */
	public static String getIndexSessionByTime() {
		return String.valueOf(System.currentTimeMillis());
	}

	/**
	 * Xl escape html input
	 * 
	 * @param inputText
	 * @return
	 */
	public static String escapeHtml(String inputText) {
		if (inputText.isEmpty())
			return inputText;
		inputText = inputText.replaceAll("<", "&lt;");
		inputText = inputText.replaceAll(">", "&gt;");
		inputText = inputText.replaceAll("'", "&#x27;");
		inputText = inputText.replaceAll("\"", "&quot;");
		inputText = inputText.replaceAll("&", "&amp;");
		inputText = inputText.replaceAll("/", "&#x2F;");
		return inputText;
	}

	/**
	 * Lấy năm hiện tại
	 * 
	 * @return năm hiện tại
	 */
	public static int getYearNow() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.YEAR);
	}

	/**
	 * get current month
	 * 
	 * @return current month
	 */
	public static int getMonthNow() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.MONTH) + 1;
	}

	/**
	 * get current month
	 * 
	 * @return current month
	 */
	public static int getDayNow() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * Lấy danh sách các năm từ năm 1980 -> năm hiện tại + 1
	 * 
	 * @param fromYear
	 *            Lấy từ năm nào
	 * @param toYear
	 *            Lấy đến năm nào
	 * @return list các năm
	 */
	public static List<Integer> getListYear(int fromYear, int toYear) {
		List<Integer> result = new ArrayList<Integer>();
		for (int i = fromYear; i <= toYear; i++) {
			result.add(new Integer(i));
		}
		return result;
	}

	/**
	 * Lấy danh sách các tháng từ 1->12
	 * 
	 * @return list các tháng
	 */
	public static List<Integer> getListMonth() {
		List<Integer> result = new ArrayList<Integer>();
		for (int i = 1; i <= 12; i++) {
			result.add(new Integer(i));
		}
		return result;
	}

	/**
	 * Lấy danh sách các ngày từ 1->31
	 * 
	 * @return list ngày
	 */
	public static List<Integer> getListDay() {
		List<Integer> result = new ArrayList<Integer>();
		for (int i = 1; i <= 31; i++) {
			result.add(new Integer(i));
		}
		return result;
	}

	/**
	 * Hàm kiểm tra chuỗi có phải là chuỗi ký tự kana hay không
	 * 
	 * @param name
	 *            tên kana
	 * @return true nếu là chuỗi kana, ngược lại trả về false
	 */
	public static boolean checkFullNameKana(String name) {
		String regex = "^[\u3040-\u309F\u30A0-\u30FF　]*$";
		return Pattern.matches(regex, name);
	}

	/**
	 * Hàm kiểm tra ngày có tồn tại hay không
	 * 
	 * @param date
	 *            ngày cần kiểm tra
	 * @return true nếu tồn tại, ngược lại trả về false
	 */
	public static boolean checkExistsDate(YearMonthDay date) {
		try {
			// Chuyển các phần tử của mảng trên về dạng số nguyên
			int y = date.getYear();
			int m = date.getMonth();
			int d = date.getDay();

			// Kiểm tra giới hạn của ngày, tháng, năm
			if (m < 1 || m > 12 || d < 1 || y < 0) {
				return false;
			}

			int[] array_days = { 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
			if (d > array_days[m - 1]) {
				return false;
			}

			// Kiểm tra trường hợp tháng 2 của năm không nhuận, nếu ngày = 29
			// thì false
			if (m == 2) {
				if (!((y % 4 == 0) && (y % 100 != 0)) || (y % 400 == 0)) {
					if (d == 29)
						return false;
				}
			}
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	/**
	 * Hàm kiểm tra định dạng email
	 * 
	 * @param email
	 *            chuỗi string
	 * @return true nếu đúng định dạng, ngược lại trả về false
	 */
	public static boolean checkFormatEmail(String email) {
		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
		java.util.regex.Matcher m = p.matcher(email);
		return m.matches();
	}

	/**
	 * Hàm kiểm tra định dạng số điện thoại XXX-XXXX-XXXX
	 * 
	 * @param tel
	 *            số điện thoại
	 * @return true nếu đúng định dạng, ngược lại trả về false
	 */
	public static boolean checkFormatTel(String tel) {
		String regex = "^[0-9]{1,4}-[0-9]{1,4}-[0-9]{1,4}$";
		return Pattern.matches(regex, tel);
	}

	/**
	 * Phương thức tạo ra chuỗi thời gian hiện tại
	 * 
	 * @return chuỗi thời gian
	 */
	public static String sessionToken() {
		return String.valueOf(System.currentTimeMillis());

	}

	/**
	 * Phương thức mã hóa chuỗi thành chuỗi MD5
	 * 
	 * @param input
	 *            chuỗi cần mã hóa
	 * @return chuỗi sau khi mã hóa
	 */
	public static String encryptMD5(String input) {
		String hashText = new String();
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(input.getBytes());
			BigInteger number = new BigInteger(1, messageDigest);
			hashText = number.toString(16);
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
		return hashText;
	}

	/**
	 * Phương thức thực hiện chuyển dữ liệu từ listUserExport sang chuỗi String
	 * 
	 * @param listUserExport
	 *            là list UserInfor
	 * @return String
	 * @throws ParseException
	 */
	public static StringBuilder exportCSV(List<UserInfor> listUserExport) {
//		String output = ConfigProperties.getData("header_export");
		StringBuilder output = new StringBuilder();
		output.append(ConfigProperties.getData("header_export"));
		for (UserInfor userInfor : listUserExport) {
			if (!"".equals(userInfor.getEndDate())) {
				output.append(userInfor.getUserId() + ", ");
				output.append(userInfor.getFullName() + ", ");
				output.append(Integer.toString(userInfor.getBirthday().getYear())
						+"/"+ Integer.toString(userInfor.getBirthday().getMonth())
						+ "/"+ Integer.toString(userInfor.getBirthday().getDay()) + ", ");
				output.append(userInfor.getGroupName() + ", ");
				output.append(userInfor.getEmail() + ", ");
				output.append(userInfor.getTel() + ", ");
				output.append(userInfor.getNameLevel() + ", ");
				output.append(Integer.toString(userInfor.getEndDate().getYear())
						+ "/"+ Integer.toString(userInfor.getEndDate().getMonth())
						+ "/"+ Integer.toString(userInfor.getEndDate().getDay()) + ", ");
				output.append(userInfor.getTotal() + ", " + "\n");
			} else {
				output.append(userInfor.getUserId() + ", ");
				output.append(userInfor.getFullName() + ", ");
				output.append(Integer.toString(userInfor.getBirthday().getYear())
						+ "/"+ Integer.toString(userInfor.getBirthday().getMonth())
						+ "/"+ Integer.toString(userInfor.getBirthday().getDay()) + ", ");
				output.append(userInfor.getGroupName() + ", ");
				output.append(userInfor.getEmail() + ", ");
				output.append(userInfor.getTel() + ", ");
				output.append(" " + ", ");
				output.append(" " + ", ");
				output.append(" " + ", " + "\n");
			}
		}
		return output;
	}
}
