/**
 * 
 */
package manageuser.logics;

import java.util.List;

import manageuser.entities.MstGroup;

/**
 * interface thao tác với bảng MstGroup
 * @author dovandung
 *
 */
public interface MstGroupLogic {
	/**
	 * getAllGroups
	 *
	 * @return list groups
	 */
	public List<MstGroup> getAllMstGroup();

	/**
	 * Kiểm tra group Id có tồn tại trong csdl hay không
	 * 
	 * @param groupId
	 *            group_id
	 * @return true: tồn tại - false: không tồn tại
	 */
	public boolean checkExistGroup(int groupId);

	/**
	 * Lấy MstGroup theo group_id
	 * 
	 * @param groupId
	 *            group_id
	 * @return đối tượng MstGroup
	 */
	public MstGroup getMstGroupById(int groupId);

	public String getNameGroup(int groupId);
}
