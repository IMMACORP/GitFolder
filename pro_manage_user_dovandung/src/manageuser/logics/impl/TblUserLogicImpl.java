/**
 * Copyright(C) 2017 Luvina SoftWare
TblUserLogicImpl.java, Jan 19, 2017 DoVanDung
 */
package manageuser.logics.impl;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import manageuser.dao.impl.TblUserDaoImpl;
import manageuser.entities.TblDetailUserJapan;
import manageuser.entities.TblUser;
import manageuser.entities.UserInfor;
import manageuser.entities.YearMonthDay;
import manageuser.logics.TblUserLogic;
import manageuser.utils.Common;

/**
 * Thực thi interface TblUserLogic
 * 
 * @author dovandung
 *
 */
public class TblUserLogicImpl implements TblUserLogic {

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.logics.TblUserLogic#getTotalUsers(int, java.lang.String)
	 */
	@Override
	public int getTotalUsers(int groupId, String fullName, YearMonthDay birthdaySearch) {
		int totalUser = 0;
		TblUserDaoImpl tblUserDaoImpl = new TblUserDaoImpl();
		totalUser = tblUserDaoImpl.getTotalUsers(groupId, fullName, birthdaySearch);
		return totalUser;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.logics.TblUserLogic#getListUsers(int, int, int,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public List<UserInfor> getListUsers(int offset, int limit, int groupId, String fullName,
			YearMonthDay birthdaySearch, String sortType, String sortByFullName, String sortByCodeLevel,
			String sortByEndDate) {
		// TODO Auto-generated method stub
		List<UserInfor> result = new ArrayList<UserInfor>();
		TblUserDaoImpl tblUserDaoImpl = new TblUserDaoImpl();
		result = tblUserDaoImpl.getListUsers(offset, limit, groupId, fullName, birthdaySearch, sortType, sortByFullName,
				sortByCodeLevel, sortByEndDate);
		return result;
	}

	@Override
	public UserInfor getUsersById(int userId) {
		// Tạo list UserInfor để lưu dữ liệu lấy từ database
		List<UserInfor> listUserInfor = new ArrayList<UserInfor>();
		UserInfor userInfor = new UserInfor();
		// Tạo biến lưu giá trị fullName
		String fullName = "";
		// Tạo biến lưu giá trị GroupId
		int groupId = 0;
		YearMonthDay birthdaySearch = null;
		/* Tạo biến lưu các đk sort trên bảng user */
		String sortFlag = "";
		String sortFullName = "ASC";
		String sortCodeLevel = "DESC";
		String sortEndDate = "DESC";
		// Lấy số user giới hạn hiển thị trên 1 page
		int limit = getTotalUsers(groupId, fullName, birthdaySearch);
		// giá trị offset
		int offset = 0;
		listUserInfor = getListUsers(offset, limit, groupId, fullName, birthdaySearch, sortFlag,
				sortFullName, sortCodeLevel, sortEndDate);
		for (UserInfor user : listUserInfor) {
			if (user.getUserId() == userId) {
				userInfor = user;
			}
		}
		return userInfor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * manageuser.logics.TblUserLogic#checkExistedLoginName(java.lang.Integer,
	 * java.lang.String)
	 */
	@Override
	public boolean checkExistedUser(final Integer userId, String loginName) {
		// TODO Auto-generated method stub
		TblUserDaoImpl tblUserDaoImpl = new TblUserDaoImpl();
		if (tblUserDaoImpl.getExistedUser(userId, loginName) != null) {
			return true;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * manageuser.logics.TblUserLogic#createUser(manageuser.entities.TblUser)
	 */
	@Override
	public boolean createUser(UserInfor userInfor) {
		int userId = userInfor.getUserId();
		int groupId = userInfor.getGroupId();
		String loginName = userInfor.getLoginName();
		// start fix bug Web security
		SecureRandom random = new SecureRandom();
		String salt = Integer.toString(random.hashCode());
		// end fix bug Web security
		String password = Common.encryptMD5(salt + userInfor.getPass());
		String rule = "1";
		String fullName = userInfor.getFullName();
		String fullNameKana = userInfor.getFullNameKana();
		String email = userInfor.getEmail();
		String tel = userInfor.getTel();
		YearMonthDay birthday = userInfor.getBirthday();
		String codeLevel = userInfor.getCodeLevel();
		YearMonthDay startDate = null;
		YearMonthDay endDate = null;
		int total = 0;
		int detailUserJapanId = 0;

		if (!"0".equals(codeLevel)) {
			startDate = userInfor.getStartDate();
			endDate = userInfor.getEndDate();
			total = Integer.parseInt(userInfor.getTotal());
		}

		TblUser tblUser = new TblUser(userId, groupId, loginName, password, salt, rule, fullName, fullNameKana, email,
				tel, birthday);
		TblDetailUserJapan tblDetailUserJapan = new TblDetailUserJapan(detailUserJapanId, userId, codeLevel, startDate,
				endDate, total);
		TblUserDaoImpl tblUserDaoImpl = new TblUserDaoImpl();
		return tblUserDaoImpl.insertUser(tblUser, tblDetailUserJapan);
	}

	@Override
	public boolean updateUser(UserInfor userInfor) {
		// TODO Auto-generated method stub
		TblDetailUserJapanLogicImpl tblDetailUserJapanLogicImpl = new TblDetailUserJapanLogicImpl();
		TblUserDaoImpl tblUserDaoImpl = new TblUserDaoImpl();
		if (tblDetailUserJapanLogicImpl.checkExistCodeLevel(userInfor.getUserId())) {
			if (!"0".equals(userInfor.getCodeLevel())) {
				return tblUserDaoImpl.updateUser(userInfor, "Update");
			} else {
				return tblUserDaoImpl.updateUser(userInfor, "Delete");
			}
		} else {
			if (!"0".equals(userInfor.getCodeLevel())) {
				return tblUserDaoImpl.updateUser(userInfor, "Insert");
			} else {
				return tblUserDaoImpl.updateUser(userInfor, null);
			}
		}
	}

	@Override
	public boolean deleteUserById(int userId) {
		// TODO Auto-generated method stub
		TblDetailUserJapanLogicImpl tblDetailUserJapanLogicImpl = new TblDetailUserJapanLogicImpl();
		TblUserDaoImpl userDaoImpl = new TblUserDaoImpl();
		if (tblDetailUserJapanLogicImpl.checkExistCodeLevel(userId)) {
			return userDaoImpl.deleteUserById(userId, "exist");
		} else {
			return userDaoImpl.deleteUserById(userId, "no");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.logics.TblUserLogic#checkExistedEmail(java.lang.Integer,
	 * java.lang.String)
	 */
	@Override
	public boolean checkExistedEmail(Integer userId, String email) {
		TblUserDaoImpl tblUserDaoImpl = new TblUserDaoImpl();
		if (tblUserDaoImpl.getUserByEmail(userId, email) != null) {
			return true;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.logics.TblUserLogic#checkExistedGroup(int)
	 */
	@Override
	public boolean checkExistedGroup(int groupId) {
		TblUserDaoImpl tblUserDaoImpl = new TblUserDaoImpl();

		return tblUserDaoImpl.checkExistedGroup(groupId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * manageuser.logics.TblUserLogic#checkExistedCodeLevel(java.lang.String)
	 */
	@Override
	public boolean checkExistedCodeLevel(String codeLevel) {
		// TODO Auto-generated method stub
		TblUserDaoImpl tblUserDaoImpl = new TblUserDaoImpl();
		return tblUserDaoImpl.checkExistedCodeLevel(codeLevel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.logics.TblUserLogic#checkExistedUserId(int)
	 */
	@Override
	public boolean checkExistedUserId(int userId) {
		// TODO Auto-generated method stub
		TblUserDaoImpl tblUserDaoImpl = new TblUserDaoImpl();
		if (tblUserDaoImpl.getExistedUser(userId, null) != null) {
			return true;
		}
		return false;
	}

}
