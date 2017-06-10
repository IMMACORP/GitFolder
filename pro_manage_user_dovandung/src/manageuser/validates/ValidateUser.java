/**
 * Copyright(C) 2017 Luvina SoftWare
ValidateUser.java, Jan 19, 2017 DoVanDung
 */
package manageuser.validates;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import manageuser.entities.UserInfor;
import manageuser.entities.YearMonthDay;
import manageuser.logics.impl.TblUserLogicImpl;
import manageuser.utils.Common;
import manageuser.utils.MessageErrorProperties;
import manageuser.utils.StringUtil;
/**
 * class validate user
 * @author dovandung
 *
 */
public class ValidateUser {

	/**
	 * Thực hiện việc add chuỗi error vào lisst error
	 * 
	 * @param err
	 *            chuỗi thông báo lỗi
	 * @param lstError
	 *            list thông báo lỗi
	 */

	private static List<String> addError(String err, List<String> lstError) {
		if (!err.isEmpty()) {
			lstError.add(err);
		}
		return lstError;
	}

	/**
	 * Thực hiện validate thông tin user nhập từ màn hình ADM003
	 * 
	 * @param userInfor
	 *            Đối tượng user cần check
	 * @return Danh sách lỗi
	 */
	public static List<String> validateUserInfor(UserInfor userInfor) {
		// Tạo danh sách chứa các lỗi
		List<String> lstError = new ArrayList<String>();
		
		/* Start Validate tât cả hạng mục */
		// validate loginName
		lstError = addError(validateLoginName(userInfor.getUserId(), userInfor.getLoginName()), lstError);

		// validate group
		lstError = addError(validateGroup(userInfor.getGroupId()), lstError);

		// validate fullName
		lstError = addError(validateFullName(userInfor.getFullName()), lstError);

		// validate FullNameKana
		lstError = addError(validateFullNameKana(userInfor.getFullNameKana()), lstError);

		// validate Birthday
		lstError = addError(validateBirthday(userInfor.getBirthday()), lstError);

		// validate Email
		lstError = addError(validateEmail(userInfor.getUserId(), userInfor.getEmail()), lstError);

		// validate Tel
		lstError = addError(validateTel(userInfor.getTel()), lstError);

		// validate Password
		String errPassword = validatePassword(userInfor.getUserId(), userInfor.getPass());
		lstError = addError(errPassword, lstError);
		if ("".equals(errPassword)) {// nếu pass thỏa mãn thì validate password
										// Confirm
			addError(validatePassConfirm(userInfor.getPass(), userInfor.getPassConfirm()), lstError);
		}

		// validate code level
		if (!"0".equals(userInfor.getCodeLevel())) { // Nếu chọn trình độ tiếng
														// Nhật thì validate
														// trinh độ
			String errCodeLevel = validateCodeLevel(userInfor.getCodeLevel());
			addError(errCodeLevel, lstError);
			if ("".equals(errCodeLevel)) { // nếu chọn code level thì validata
											// ngày cấp, ngày hết hạn, total
				// validata start date
				addError(validateStartDate(userInfor.getStartDate()), lstError);

				// validate end date
				addError(validateEndtDate(userInfor.getStartDate(), userInfor.getEndDate()), lstError);

				// validate total
				addError(validateTotal(userInfor.getTotal()), lstError);
			}
		}
		/* End Validate tât cả hạng mục */
		return lstError;
	}

	/**
	 * @param codeLevel
	 * @return
	 */

	/**
	 * Hàm kiểm tra hạng mục login Name
	 * 
	 * @param loginName
	 * @return String lỗi của hạng mục
	 */
	private static String validateLoginName(int userId, String loginName) {
		String result = "";
		TblUserLogicImpl tblUserLogicImpl = new TblUserLogicImpl();
		int length = loginName.trim().length();
		if (length == 0) {
			result = MessageErrorProperties.getErrMessage("ER001_loginName");
		} else if (length < 4 || length > 15) {
			result = MessageErrorProperties.getErrMessage("ER007_loginName");
		} else {
			char c = loginName.charAt(0);
			if (Character.isDigit(c)) {
				result = MessageErrorProperties.getErrMessage("ER019_loginName");
			}
		}
		if ("".equals(result)) {
			if (tblUserLogicImpl.checkExistedUser(userId, loginName)) {
				result = MessageErrorProperties.getErrMessage("ER003_loginName");
			}
		}

		return result;
	}

	/**
	 * Hàm validate trường group
	 * 
	 * @param groupId
	 *            id của group truyền vào
	 * @return chuỗi thông báo lỗi
	 */
	private static String validateGroup(int groupId) {
		String result = "";
		TblUserLogicImpl tblUserLogicImpl = new TblUserLogicImpl();
		if (groupId == 0) {
			result = MessageErrorProperties.getErrMessage("ER002_group");
		} else if (!tblUserLogicImpl.checkExistedGroup(groupId)) {
			result = MessageErrorProperties.getErrMessage("ER004_group");
		}
		return result;
	}

	/**
	 * Phương thức validate mục full name
	 * 
	 * @param fullName
	 *            họ tên
	 * @return chuỗi thông báo lỗi
	 */
	private static String validateFullName(String fullName) {
		String result = "";
		int length = fullName.length();
		if (length == 0) {
			result = MessageErrorProperties.getErrMessage("ER001_fullName");
		} else if (length > 255) {
			result = MessageErrorProperties.getErrMessage("ER006_fullName");
		}
		return result;
	}

	/**
	 * Phương thức validate mục Full name kana
	 * 
	 * @param fullNameKana
	 *            tên kana
	 * @return chuỗi thông báo lỗi
	 */
	private static String validateFullNameKana(String fullNameKana) {
		String result = "";
		if (fullNameKana.length() > 255) {
			result = MessageErrorProperties.getErrMessage("ER006_fullName");
		} else if (fullNameKana != null && !Common.checkFullNameKana(fullNameKana.trim())) {
			result = MessageErrorProperties.getErrMessage("ER009");
		}
		return result;
	}

	/**
	 * Phương thức validate mục birthday
	 * 
	 * @param date
	 *            ngày truyền vào
	 * @return chuỗi thông báo lỗi
	 */

	private static String validateBirthday(YearMonthDay date) {
		String result = "";
		if (!Common.checkExistsDate(date)) {
			result = MessageErrorProperties.getErrMessage("ER011_birthday");
		}
		return result;
	}

	/**
	 * Hàm validate mục email
	 * 
	 * @param email
	 *            string
	 * @return thông báo lỗi
	 */
	private static String validateEmail(int userId, String email) {
		String result = "";
		TblUserLogicImpl tblUserLogicImpl = new TblUserLogicImpl();

		int length = email.length();

		if (length == 0) {
			result = MessageErrorProperties.getErrMessage("ER001_email");
		} else if (length > 255) {
			result = MessageErrorProperties.getErrMessage("ER006_email");
		} else if (!Common.checkFormatEmail(email)) {
			result = MessageErrorProperties.getErrMessage("ER005_email");
		}

		if ("".equals(result)) {
			if (tblUserLogicImpl.checkExistedEmail(userId, email)) {
				result = MessageErrorProperties.getErrMessage("ER003_email");
			}
		}

		return result;
	}

	/**
	 * Hàm validate mục Tel
	 * 
	 * @param tel
	 *            số điện thoại
	 * @return chuỗi thông báo lỗi
	 */
	private static String validateTel(String tel) {
		String result = "";
		if ("".equals(tel)) {
			result = MessageErrorProperties.getErrMessage("ER001_tel");
		} else if (tel.length() > 14) {
			result = MessageErrorProperties.getErrMessage("ER006_tel");
		} else if (!Common.checkFormatTel(tel)) {
			result = MessageErrorProperties.getErrMessage("ER005_tel");
		}
		return result;
	}

	/**
	 * Hàm validate mục mật khẩu
	 * 
	 * @param password
	 *            mật khẩu
	 * @return chuỗi thông báo lỗi
	 */
	private static String validatePassword(int userId, String password) {
		String result = "";
		int length = password.length();
		if (userId == 0) {// Trường hợp add user
			if (length == 0) {
				result = MessageErrorProperties.getErrMessage("ER001_pass");
			} else if (length < 5 || length > 15) {
				result = MessageErrorProperties.getErrMessage("ER007_pass");
			} else if (!StringUtil.checkCharIs1Byte(password)) {
				result = MessageErrorProperties.getErrMessage("ER008_pass");
			}
		} else if (length > 0) { // Trường hợp edit user
			if (length < 5 || length > 15) {
				result = MessageErrorProperties.getErrMessage("ER007_pass");
			} else if (!StringUtil.checkCharIs1Byte(password)) {
				result = MessageErrorProperties.getErrMessage("ER008_pass");
			}
		}
		return result;
	}

	/**
	 * @param pass
	 * @param passConfirm
	 * @return
	 */
	private static String validatePassConfirm(String password, String passwordConfirm) {
		String result = "";
		if (!password.equals(passwordConfirm)) {
			result = MessageErrorProperties.getErrMessage("ER017");
		}
		return result;
	}

	/**
	 * Hàm validate mục coe level
	 * 
	 * @param codeLevel
	 *            codelevel được chọn
	 * @return chuỗi thông báo lỗi
	 */
	private static String validateCodeLevel(String codeLevel) {
		String result = "";
		TblUserLogicImpl tblUserLogicImpl = new TblUserLogicImpl();
		if (!tblUserLogicImpl.checkExistedCodeLevel(codeLevel)) {
			result = MessageErrorProperties.getErrMessage("ER004_codeLevel");
		}
		return result;
	}

	/**
	 * Hàm validate mục start date
	 * 
	 * @param startDate
	 *            ngày cấp
	 * @return chuỗi thông báo lỗi
	 */
	private static String validateStartDate(YearMonthDay startDate) {
		String result = "";
		if (!Common.checkExistsDate(startDate)) {
			result = MessageErrorProperties.getErrMessage("ER011_startDate");
		}
		return result;
	}

	/**
	 * Hàm validate mục end date
	 * 
	 * @param startDate
	 *            ngày cấp
	 * @param endDate
	 *            ngày hết hạn
	 * @return chuỗi thông báo lỗi
	 * @throws java.text.ParseException
	 */
	private static String validateEndtDate(YearMonthDay startDate, YearMonthDay endDate) {
		String result = "";
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		if (!Common.checkExistsDate(endDate)) {
			result = MessageErrorProperties.getErrMessage("ER011_endDate");
		} else {
			try {
				String startDateStr = Integer.toString(startDate.getYear()) + "/" +Integer.toString(startDate.getMonth())
				 + "/" + Integer.toString(startDate.getDay());
				String endDateStr = Integer.toString(endDate.getYear()) + "/"  + Integer.toString(endDate.getMonth()) + "/" 
						+ Integer.toString(endDate.getDay());
				Date start = dateFormat.parse(startDateStr);
				Date end = dateFormat.parse(endDateStr);
				if (!start.before(end)) {
					result = MessageErrorProperties.getErrMessage("ER012");
				}
			} catch (ParseException e) {
				System.out.println("Can't convert date");
			} catch (java.text.ParseException e) {
				// TODO Auto-generated catch block
				System.out.println("Loi parse date");
			}
		}
		return result;
	}

	/**
	 * Hàm validate mục total
	 * 
	 * @param total
	 *            điểm
	 * @return chuỗi thông báo lỗi
	 */
	private static String validateTotal(String total) {
		String result = "";
		String regex = "^[0-9]*$";
		if ("".equals(total)) {
			result = MessageErrorProperties.getErrMessage("ER001_total");
			// result = "Total chua nhap";
		} else if (!Pattern.matches(regex, total)) {// check halfsize
			result = MessageErrorProperties.getErrMessage("ER018_total");
		}
		return result;
	}

}
