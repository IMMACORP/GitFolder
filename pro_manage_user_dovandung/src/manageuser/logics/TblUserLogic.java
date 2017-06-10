/**
 * Copyright(C) 2017 Luvina SoftWare
TblUserLogic.java, Jan 19, 2017 DoVanDung
 */
package manageuser.logics;

import java.util.List;

import manageuser.entities.UserInfor;
import manageuser.entities.YearMonthDay;

/**
 * interface thể hiện các phương thức thao tác với bảng tbl_user thông qua Dao
 * 
 * @author dovandung
 *
 */
public interface TblUserLogic {

	/**
	 * Lấy danh sách user
	 * 
	 * @param offset
	 *            vị trí data cần lấy nào
	 * @param limit
	 *            số lượng lấy
	 * @param groupId
	 *            mã nhóm tìm kiếm
	 * @param fullName
	 *            Tên tìm kiếm
	 * @param sortType
	 *            Nhận biết xem cột nào được ưu tiên sắp xếp(full_name or
	 *            end_date or code_level)
	 * @param sortByFullName
	 *            Giá trị sắp xếp của cột Tên(ASC or DESC)
	 * @param sortByCodeLevel
	 *            Giá trị sắp xếp của cột Trình độ tiếng nhật(ASC or DESC)
	 * @param sortByEndDate
	 *            Giá trị sắp xếp của cột Ngày kết hạn(ASC or DESC)
	 * @return danh sach user
	 */
	public List<UserInfor> getListUsers(int offset, int limit, int groupId, String fullName, YearMonthDay birthday,
			String sortType, String sortByFullName, String sortByCodeLevel, String sortByEndDate);

	/**
	 * Lay user bang id
	 * @param userId
	 * @return
	 */
	public UserInfor getUsersById(int userId);

	/**
	 * Lấy tổng users
	 * 
	 * @param groupId
	 * @param fullName
	 * @return
	 */
	public int getTotalUsers(int groupId, String fullName, YearMonthDay birthday);

	/**
	 * Insert data user vào bảng tbl_user và tbl_detail_user_japan
	 * 
	 * @param tblUser
	 * @return True: insert thành công False: insert không thành công
	 */
	public boolean createUser(UserInfor userInfor);

	/**
	 * update user
	 * 
	 * @param userInfor
	 * @return true: update thành công false : update thất bại
	 */
	public boolean updateUser(UserInfor userInfor);

	/**
	 * Xóa user trong db
	 * 
	 * @param userId
	 * @return true: xóa thành công false: xóa thất bại
	 */
	public boolean deleteUserById(int userId);

	/**
	 * Kiểm tra loginName đã tồn tại trong bảng tbl_user chưa?
	 * 
	 * @param userId
	 *            id của user
	 * @param loginName
	 *            tên đăng nhập
	 * @return - True: loginName đã tồn tại, False: loginName không tồn tại
	 */
	public boolean checkExistedUser(final Integer userId, final String loginName);

	/**
	 * Kiểm tra code level có tồn tại trong database hay không
	 * 
	 * @param codeLevel
	 *            mã code level
	 * @return true nếu tồn tại, ngược lại trả về false
	 */
	public boolean checkExistedCodeLevel(String codeLevel);

	/**
	 * Check 1 group có tồn tại hay ko
	 * 
	 * @param groupId
	 * @return
	 */
	public boolean checkExistedGroup(int groupId);

	/**
	 * Kiểm tra email đã tồn tại trong bảng tbl_user chưa?
	 * 
	 * @param userId
	 * @param email
	 * @return True: email đã tồn tại False: email không tồn tại
	 * 
	 */
	public boolean checkExistedEmail(final Integer userId, final String email);

	/**
	 * Check tồn tại userId
	 * 
	 * @param userId
	 * @return true: tồn tại false: ko tồn tại
	 */
	public boolean checkExistedUserId(int userId);

}
