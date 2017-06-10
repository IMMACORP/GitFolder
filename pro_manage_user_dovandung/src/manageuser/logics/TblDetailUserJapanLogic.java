/**
 * Copyright(C) 2017 Luvina Software Company
 * manageuser.logics, 2017/04/28, DOVANDUNG
 */
package manageuser.logics;

/**
 * interface thao tác với bảng TblDetailUserJapan
 * @author dovandung
 *
 */
public interface TblDetailUserJapanLogic {

	/**
	 * Hàm thực hiện việc kiểm tra user có trình độ tiếng Nhật trong database hay không
	 * @param userId id của User
	 * @return true nếu có trình độ tiếng Nhật, ngược lại trả về false
	 */
	public boolean checkExistCodeLevel(int userId);
}
