/**
 * Copyright(C) 2017 Luvina SoftWare
TblUserDao.java, Jan 19, 2017 DoVanDung
 */
package manageuser.dao;

import java.util.List;

import manageuser.entities.TblDetailUserJapan;
import manageuser.entities.TblUser;
import manageuser.entities.UserInfor;
import manageuser.entities.YearMonthDay;
/**
 * Interface thực thi thao tác với bảng tbluser
 * @author dovandung
 *
 */
public interface TblUserDao extends BaseDao {

	/**
	 * Hàm trả về listuser cho màn hình ADM 002
	 * 
	 * @param offset
	 * @param limit
	 * @param groupId
	 * @param fullName
	 * @param sortType
	 * @param sortByFullName
	 * @param sortByCodeLevel
	 * @param sortByEndDate
	 * @return
	 */

	public List<UserInfor> getListUsers(int offset, int limit, int groupId, String fullName, YearMonthDay birthday, String sortType,
			String sortByFullName, String sortByCodeLevel, String sortByEndDate);
	/**
	 * Hàm trả về tổng số user
	 * 
	 * @param groupId
	 * @param fullName
	 * @return
	 */
	public int getTotalUsers(int groupId, String fullName, YearMonthDay birthday);

	/**
	 * Lấy user dựa vào login name
	 * 
	 * @param userId
	 *            id user
	 * @param loginName
	 * @return
	 */
	public TblUser getExistedUser(final Integer userId, final String loginName);

	/**
	 * Kiểm tra code level có tồn tại trong database hay không
	 * 
	 * @param codeLevel
	 *            mã code level
	 * @return true nếu tồn tại, ngược lại trả về false
	 */
	public boolean checkExistedCodeLevel(String codeLevel);
	/**
	 * Check group có tồn tại hay ko
	 * @param groupId
	 * @return true: tồn tại
	 * 			false: ko tồn tại
	 */
	public boolean checkExistedGroup(int groupId);
	/**
	 * lấy thông tin user bằng email
	 * @param userId
	 * @param email
	 * @return đối tượng tbluser
	 */
	public TblUser getUserByEmail(final Integer userId, String email);

	/**
	 * Insert user to DB
	 * 
	 * @param tblUser
	 * @param tblDetailUserJapan
	 */
	public boolean insertUser(TblUser tblUser, TblDetailUserJapan tblDetailUserJapan);
	/**
	 * update user
	 * @param userInfor
	 * @param typeJapan
	 * @return true: update thành công
	 * 			false: update thất bại
	 */
	public boolean updateUser(UserInfor userInfor, final String typeJapan);
	/**
	 * Xóa user
	 * @param userId
	 * @param exitsCodeLevel
	 * @return true: thành công
	 * 			false: thất bại
	 */
	public boolean deleteUserById(int userId, String exitsCodeLevel);
}
