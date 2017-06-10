/**
 * Copyright(C) 2017 Luvina SoftWare
TblDetailUserJapanDao.java, Jan 19, 2017 DoVanDung
 */
package manageuser.dao;

import manageuser.entities.TblDetailUserJapan;
/**
 * Interface cho bảng tblDetailUserJapan
 * @author dovandung
 *
 */
public interface TblDetailUserJapanDao extends BaseDao {
	/**
	 * Lấy thông tin chi tiết user theo id
	 * @param userId
	 * @return
	 */
	public TblDetailUserJapan getDetailUserJapanById(int userId);

}
