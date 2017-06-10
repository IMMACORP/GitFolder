/**
 * Copyright(C) 2017 Luvina SoftWare
MstGroupDao.java, Jan 19, 2017 DoVanDung
 */
package manageuser.dao;

import java.util.List;

import manageuser.entities.MstGroup;
/**
 * interface cho bảng group
 * @author dovandung
 *
 */
public interface MstGroupDao extends BaseDao {
	/**
	 * Lấy tất cả các group từ csdl
	 *
	 * @return danh sách group
	 */
	public List<MstGroup> getAllMstGroup();

	/**
	 * Lấy ra 1 MstGroup theo groupId.
	 *
	 * @param groupId
	 *            : group_id
	 * @return Trả về 1 đối tượng MstGroup
	 */
	public MstGroup getMstGroupById(int groupId);
}
