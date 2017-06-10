/**
 * Copyright(C) 2017 Luvina SoftWare
MstJapanDao.java, Jan 19, 2017 DoVanDung
 */
package manageuser.dao;

import java.util.List;

import manageuser.entities.MstJapan;
/**
 * interface cho bảng MstJapan
 * @author dovandung
 *
 */
public interface MstJapanDao {
	/**
	 * Hàm lấy về tất cả các trình độ tiếng Nhật
	 * @return list các đối tượng MstJapan
	 */
	public List<MstJapan> getAllMstJapan();
}
