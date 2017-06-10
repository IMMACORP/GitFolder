/**
 * Copyright(C) 2017 Luvina Software Company
 * manageuser.logics, 2017/04/27, DOVANDUNG
 */
package manageuser.logics;

import java.util.List;

import manageuser.entities.MstJapan;

/**
 * interface thao tác với bảng MstJapan
 * @author dovandung
 *
 */
public interface MstJapanLogic {
	/**
	 * Lấy về tất cả trình độ tiếng Nhật
	 * 
	 * @return list MstJapan
	 */
	public List<MstJapan> getAllMstJapan();

	/**
	 * Hàm lấy về name level theo code level
	 * 
	 * @param codeLevel
	 *            codelevel
	 * @return name level
	 */
	public String getNameLevel(String codeLevel);
}
