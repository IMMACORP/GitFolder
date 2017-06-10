/**
 * Copyright(C) 2017 Luvina SoftWare
TblUserDaoImpl.java, Jan 19, 2017 DoVanDung
 */
package manageuser.dao.impl;

import java.security.SecureRandom;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import manageuser.dao.TblUserDao;
import manageuser.entities.TblDetailUserJapan;
import manageuser.entities.TblUser;
import manageuser.entities.UserInfor;
import manageuser.entities.YearMonthDay;
import manageuser.utils.Common;
import manageuser.utils.Constant;
import manageuser.utils.StringUtil;

/**
 * classe triển khai BaseDaoImpl và áp dụng TblUserDao
 * 
 * @author dovandung
 *
 */
public class TblUserDaoImpl extends BaseDaoImpl implements TblUserDao {
	/**
	 * 
	 * @param loginId:
	 *            input login Id
	 * @param password:
	 *            password
	 * @return true or false
	 */
	public boolean existLoginId(String loginId, String password) {
		boolean result = false;
		if (connectToDB()) {
			try {
				StringBuilder sqlCommand = new StringBuilder();
				sqlCommand.append("SELECT *");
				sqlCommand.append(" FROM tbl_user ");
				sqlCommand.append(" WHERE");
				sqlCommand.append(" rule  = ?");
				sqlCommand.append(" AND");
				sqlCommand.append(" login_name=?");
				sqlCommand.append(" AND");
				sqlCommand.append(" passwords=?");
				sqlCommand.append(";");
				preparedStatement = connection.prepareStatement(sqlCommand.toString());
				int i = 1;
				preparedStatement.setInt(i++, Constant.RULE);
				preparedStatement.setString(i++, loginId);
				preparedStatement.setString(i++, password);
				resultSet = preparedStatement.executeQuery();
				if (resultSet.next()) {
					result = true;
				}
			} catch (SQLException e) {
				System.out.println("an exception occur: " + e.getMessage());
			}

		}
		return result;
	}

	@Override

	public List<UserInfor> getListUsers(int offset, int limit, int groupId, String fullName,
			YearMonthDay birthdaySearch, String sortFlag, String sortByFullName, String sortByCodeLevel,
			String sortByEndDate) {
		List<UserInfor> result = new ArrayList<UserInfor>();
		if (connectToDB()) {
			try {
				// Tạo câu truy vấn
				StringBuilder sqlCommand = new StringBuilder();
				sqlCommand.append("SELECT ");
				sqlCommand.append("tbl_user.user_id, ");
				sqlCommand.append("tbl_user.group_id, ");
				sqlCommand.append("tbl_user.login_name, ");
				sqlCommand.append("tbl_user.full_name, ");
				sqlCommand.append("tbl_user.full_name_kana, ");
				sqlCommand.append("tbl_user.birthday, ");
				sqlCommand.append("mst_group.group_name, ");
				sqlCommand.append("tbl_user.email, ");
				sqlCommand.append("tbl_user.tel, ");
				sqlCommand.append("mst_japan.name_level, ");
				sqlCommand.append("tbl_detail_user_japan.code_level, ");
				sqlCommand.append("tbl_detail_user_japan.start_date, ");
				sqlCommand.append("tbl_detail_user_japan.end_date, ");
				sqlCommand.append("tbl_detail_user_japan.total ");

				sqlCommand.append("FROM  tbl_user ");
				sqlCommand.append("INNER JOIN  mst_group ");
				sqlCommand.append("ON tbl_user.group_id = mst_group.group_id ");
				sqlCommand.append("LEFT JOIN (tbl_detail_user_japan ");
				sqlCommand.append("INNER JOIN mst_japan ");
				sqlCommand.append("ON tbl_detail_user_japan.code_level = mst_japan.code_level) ");
				sqlCommand.append("ON tbl_user.user_id = tbl_detail_user_japan.user_id ");

				// Điều kiện tìm kiếm
				if (groupId > 0 && !fullName.isEmpty() && birthdaySearch != null) {
					sqlCommand.append("WHERE ");
					sqlCommand.append("tbl_user.group_id = ?");
					sqlCommand.append(" AND ");
					sqlCommand.append("tbl_user.full_name LIKE ? ");
					sqlCommand.append(" AND ");
					sqlCommand.append("tbl_user.birthday = ? ");
					// 2 gia tri group va name duoc input
				} else if (groupId > 0 && !fullName.isEmpty()) {
					sqlCommand.append("WHERE ");
					sqlCommand.append("tbl_user.group_id = ?");
					sqlCommand.append(" AND ");
					sqlCommand.append("tbl_user.full_name LIKE ? ");
					// 2 giá trị group vs birthday
				} else if (groupId > 0 && birthdaySearch != null) {
					sqlCommand.append("WHERE ");
					sqlCommand.append("tbl_user.group_id = ?");
					sqlCommand.append(" AND ");
					sqlCommand.append("tbl_user.birthday = ? ");
				} else if (!fullName.isEmpty() && birthdaySearch != null) {
					sqlCommand.append("WHERE ");
					sqlCommand.append("tbl_user.full_name LIKE ? ");
					sqlCommand.append(" AND ");
					sqlCommand.append("tbl_user.birthday = ? ");
				} else if (groupId > 0) {
					sqlCommand.append("WHERE ");
					sqlCommand.append("tbl_user.group_id = ?");
					// 2 giá trị name vs birthday
				} else if (!fullName.isEmpty()) {
					sqlCommand.append("WHERE ");
					sqlCommand.append("tbl_user.full_name LIKE ? ");
				} else if (birthdaySearch != null) {
					sqlCommand.append("WHERE ");
					sqlCommand.append("tbl_user.birthday = ? ");
				}
				//
				sqlCommand.append(" ORDER BY ");
				if (sortFlag.equals("sortFullName")) {
					sqlCommand.append("tbl_user.full_name " + sortByFullName);
				} else if (sortFlag.equals("sortCodeLevel")) {
					sqlCommand.append("mst_japan.name_level " + sortByCodeLevel);
				} else if (sortFlag.equals("sortEndDate")) {
					sqlCommand.append("tbl_detail_user_japan.end_date " + sortByEndDate);
				} else {
					sqlCommand.append("tbl_user.full_name " + sortByFullName);
					sqlCommand.append(" , mst_japan.name_level " + sortByCodeLevel);
					sqlCommand.append(" , tbl_detail_user_japan.end_date " + sortByEndDate);

				}

				sqlCommand.append(" LIMIT ? OFFSET ?");

				preparedStatement = connection.prepareStatement(sqlCommand.toString());

				if (groupId > 0 && !fullName.isEmpty() && birthdaySearch != null) {
					int i = 1;
					preparedStatement.setInt(i++, groupId);
					preparedStatement.setString(i++, "%" + fullName + "%");
					preparedStatement.setString(i++, StringUtil.DateObjectToDateInDb(birthdaySearch.getYear(),
							birthdaySearch.getMonth(), birthdaySearch.getDay()));
					preparedStatement.setInt(i++, limit);
					preparedStatement.setInt(i++, offset);
					// 2 gia tri group va name duoc input
				} else if (groupId > 0 && !fullName.isEmpty()) {
					int i = 1;
					preparedStatement.setInt(i++, groupId);
					preparedStatement.setString(i++, "%" + fullName + "%");
					preparedStatement.setInt(i++, limit);
					preparedStatement.setInt(i++, offset);
					// 2 giá trị group vs birthday
				} else if (groupId > 0 && birthdaySearch != null) {
					int i = 1;
					preparedStatement.setInt(i++, groupId);
					preparedStatement.setString(i++, StringUtil.DateObjectToDateInDb(birthdaySearch.getYear(),
							birthdaySearch.getMonth(), birthdaySearch.getDay()));
					preparedStatement.setInt(i++, limit);
					preparedStatement.setInt(i++, offset);
				} else if (!fullName.isEmpty() && birthdaySearch != null) {
					int i = 1;
					preparedStatement.setString(i++, "%" + fullName + "%");
					preparedStatement.setString(i++, StringUtil.DateObjectToDateInDb(birthdaySearch.getYear(),
							birthdaySearch.getMonth(), birthdaySearch.getDay()));
					preparedStatement.setInt(i++, limit);
					preparedStatement.setInt(i++, offset);
				} else if (groupId > 0) {
					int i = 1;
					preparedStatement.setInt(i++, groupId);
					preparedStatement.setInt(i++, limit);
					preparedStatement.setInt(i++, offset);
					// 2 giá trị name vs birthday
				} else if (!fullName.isEmpty()) {
					int i = 1;
					preparedStatement.setString(i++, "%" + fullName + "%");
					preparedStatement.setInt(i++, limit);
					preparedStatement.setInt(i++, offset);
				} else if (birthdaySearch != null) {
					int i = 1;
					preparedStatement.setString(i++, StringUtil.DateObjectToDateInDb(birthdaySearch.getYear(),
							birthdaySearch.getMonth(), birthdaySearch.getDay()));
					preparedStatement.setInt(i++, limit);
					preparedStatement.setInt(i++, offset);
				} else {
					int i = 1;
					preparedStatement.setInt(i++, limit);
					preparedStatement.setInt(i++, offset);
				}

				ResultSet resultSet = preparedStatement.executeQuery();

				if (resultSet != null) {
					while (resultSet.next()) {
						List<Integer> list = new ArrayList<Integer>();
						list = StringUtil.convertStringDateToArrInt(Common.displayDate(resultSet.getDate("birthday")));
						YearMonthDay birthday = new YearMonthDay(list);
						//
						list = StringUtil
								.convertStringDateToArrInt(Common.displayDate(resultSet.getDate("start_date")));
						YearMonthDay startDate = new YearMonthDay(list);
						//
						list = StringUtil.convertStringDateToArrInt(Common.displayDate(resultSet.getDate("end_date")));
						YearMonthDay endDate = new YearMonthDay(list);
						
						UserInfor userInfor = new UserInfor(resultSet.getInt("user_id"),
								resultSet.getString("login_name"), resultSet.getString("full_name"),
								resultSet.getString("full_name_kana"), resultSet.getString("group_name"),
								resultSet.getInt("group_Id"), birthday, resultSet.getString("email"),
								resultSet.getString("tel"), "", "", resultSet.getString("name_level"), resultSet.getString("code_level"), endDate,
								startDate, resultSet.getString("total"));
						result.add(userInfor);
					}
					resultSet.close();
				}

			} catch (Exception e) {
				System.out.println("an exception occur: " + e.getMessage());
			} finally {
				closeConnect();
			}
		}
		return result;

	}

	@Override
	public int getTotalUsers(int groupId, String fullName, YearMonthDay birthdaySearch) {

		int totalUsers = 0;
		if (connectToDB()) {
			try {
				StringBuilder sqlCommand = new StringBuilder();
				// create SQL
				sqlCommand.append("SELECT count(*) AS total ");
				sqlCommand.append("FROM tbl_user AS u ");
				sqlCommand.append("INNER JOIN mst_group AS g ");
				sqlCommand.append("ON u.group_id = g.group_id ");
				// Conditions search

				// tim kiem khi co birthday
				if (groupId > 0 && !fullName.isEmpty() && birthdaySearch != null) {
					System.out.println("3 điều kiện tìm kiếm đều on");
					sqlCommand.append("WHERE ");
					sqlCommand.append("u.group_id = ?");
					sqlCommand.append(" AND ");
					sqlCommand.append("u.full_name LIKE ? ");
					sqlCommand.append(" AND ");
					sqlCommand.append("u.birthday = ? ");

					// 2 gia tri group va name duoc input
				} else if (groupId > 0 && !fullName.isEmpty()) {
					sqlCommand.append("WHERE ");
					sqlCommand.append("u.group_id = ?");
					sqlCommand.append(" AND ");
					sqlCommand.append("u.full_name LIKE ? ");
					// 2 giá trị group vs birthday
				} else if (groupId > 0 && birthdaySearch != null) {
					sqlCommand.append("WHERE ");
					sqlCommand.append("u.group_id = ?");
					sqlCommand.append(" AND ");
					sqlCommand.append("u.birthday = ? ");
				} else if (!fullName.isEmpty() && birthdaySearch != null) {
					sqlCommand.append("WHERE ");
					sqlCommand.append("u.full_name LIKE ? ");
					sqlCommand.append(" AND ");
					sqlCommand.append("u.birthday = ? ");
				} else if (groupId > 0) {
					sqlCommand.append("WHERE ");
					sqlCommand.append("u.group_id = ?");
					// 2 giá trị name vs birthday
				} else if (!fullName.isEmpty()) {
					sqlCommand.append("WHERE ");
					sqlCommand.append("u.full_name LIKE ? ");
				} else if (birthdaySearch != null) {
					sqlCommand.append("WHERE ");
					sqlCommand.append("u.birthday = ? ");
				}

				// end tim kiem khi co birthday
				preparedStatement = connection.prepareStatement(sqlCommand.toString());
				//
				if (groupId > 0 && !fullName.isEmpty() && birthdaySearch != null) {
					int i = 1;
					preparedStatement.setInt(i++, groupId);
					preparedStatement.setString(i++, "%" + fullName + "%");
					preparedStatement.setString(i++, StringUtil.DateObjectToDateInDb(birthdaySearch.getYear(),
							birthdaySearch.getMonth(), birthdaySearch.getDay()));
					// 2 gia tri group va name duoc input
				} else if (groupId > 0 && !fullName.isEmpty()) {
					int i = 1;
					preparedStatement.setInt(i++, groupId);
					preparedStatement.setString(i++, "%" + fullName + "%");
					// 2 giá trị group vs birthday
				} else if (groupId > 0 && birthdaySearch != null) {
					int i = 1;
					preparedStatement.setInt(i++, groupId);
					preparedStatement.setString(i++, StringUtil.DateObjectToDateInDb(birthdaySearch.getYear(),
							birthdaySearch.getMonth(), birthdaySearch.getDay()));
				} else if (!fullName.isEmpty() && birthdaySearch != null) {
					int i = 1;
					preparedStatement.setString(i++, "%" + fullName + "%");
					preparedStatement.setString(i++, StringUtil.DateObjectToDateInDb(birthdaySearch.getYear(),
							birthdaySearch.getMonth(), birthdaySearch.getDay()));
				} else if (groupId > 0) {
					preparedStatement.setInt(1, groupId);
					// 2 giá trị name vs birthday
				} else if (!fullName.isEmpty()) {
					preparedStatement.setString(1, "%" + fullName + "%");
				} else if (birthdaySearch != null) {
					preparedStatement.setString(1, StringUtil.DateObjectToDateInDb(birthdaySearch.getYear(),
							birthdaySearch.getMonth(), birthdaySearch.getDay()));
				}
				//
				// run SQL
				resultSet = preparedStatement.executeQuery();

				// read result from SQL
				if (resultSet != null) {
					while (resultSet.next()) {
						totalUsers = resultSet.getInt("total");
					}
					resultSet.close();
				}

			} catch (Exception e) {
				System.out.println("an exception occur: " + e.getMessage());
			}
			closeConnect();
		}

		return totalUsers;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.dao.TblUserDao#checkExistedLoginName(java.lang.Integer,
	 * java.lang.String)
	 */
	@Override
	public TblUser getExistedUser(Integer userId, String loginName) {
		System.out.println("test update " + userId + loginName);
		TblUser tblUser = null;
		if (connectToDB()) {
			try {
				// Tạo câu truy vấn
				StringBuilder sqlCommand = new StringBuilder();
				sqlCommand.append("SELECT * from tbl_user ");
				if (userId == null && loginName != null) {
					sqlCommand.append("WHERE login_name = ?");
				} else if (userId != null && loginName == null) {
					sqlCommand.append("WHERE user_id = ?");
				} else if (userId != null && loginName != null) {
					sqlCommand.append("WHERE login_name = ? ");
					sqlCommand.append("AND user_id <> ?");
				}

				preparedStatement = connection.prepareStatement(sqlCommand.toString());
				if (userId == null && loginName != null) {
					preparedStatement.setString(1, loginName);
				} else if (userId != null && loginName == null) {
					preparedStatement.setInt(1, userId);
				} else if (userId != null && loginName != null) {
					preparedStatement.setString(1, loginName);
					preparedStatement.setInt(2, userId);
				}
				resultSet = preparedStatement.executeQuery();
				if (resultSet != null) {
					while (resultSet.next()) {
						List<Integer> list = new ArrayList<Integer>();
						list = StringUtil.convertStringDateToArrInt(Common.displayDate(resultSet.getDate("birthday")));
						YearMonthDay birthday = new YearMonthDay(list);
						tblUser = new TblUser(resultSet.getInt("user_id"), resultSet.getInt("group_id"),
								resultSet.getString("login_name"), resultSet.getString("passwords"),
								resultSet.getString("salt"), resultSet.getString("rule"),
								resultSet.getString("full_name"), resultSet.getString("full_name_kana"),
								resultSet.getString("email"), resultSet.getString("tel"), birthday);
					}
				} else {
					tblUser = null;
				}
				resultSet.close();
			} catch (Exception e) {
				System.out.println("an exception occur(TblUserDaoImpl281): " + e.getMessage());
			} finally {
				closeConnect();
			}
		}
		return tblUser;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.dao.TblUserDao#checkExistedGroup(int)
	 */
	@Override
	public boolean checkExistedGroup(int groupId) {
		// TODO Auto-generated method stub
		if (connectToDB()) {
			try {
				// Tạo câu truy vấn
				StringBuilder sqlCommand = new StringBuilder();
				sqlCommand.append("SELECT * ");
				sqlCommand.append("FROM mst_group ");
				sqlCommand.append("WHERE group_id = ?");

				preparedStatement = connection.prepareStatement(sqlCommand.toString());
				preparedStatement.setInt(1, groupId);
				resultSet = preparedStatement.executeQuery();
				if (resultSet != null) {
					while (resultSet.next()) {
						return true;
					}
				} else {
					return false;
				}
			} catch (Exception e) {
				System.out.println("an exception occur: " + e.getMessage());
			} finally {
				closeConnect();
			}
		}
		return false;
	}

	@Override
	public TblUser getUserByEmail(final Integer userId, String email) {
		// TODO Auto-generated method stub
		TblUser tblUser = null;
		if (connectToDB()) {
			try {
				// Tạo câu truy vấn
				StringBuilder sqlCommand = new StringBuilder();
				sqlCommand.append("SELECT * from tbl_user ");
				if (userId == null) {
					sqlCommand.append("WHERE email = ? ");
				} else {
					sqlCommand.append("WHERE email = ? ");
					sqlCommand.append("AND user_id <> ? ");
				}

				preparedStatement = connection.prepareStatement(sqlCommand.toString());
				if (userId == null) {
					preparedStatement.setString(1, email);
				} else {
					preparedStatement.setString(1, email);
					preparedStatement.setInt(2, userId);
				}
				resultSet = preparedStatement.executeQuery();

				if (resultSet != null) {
					while (resultSet.next()) {
						List<Integer> list = new ArrayList<Integer>();
						list = StringUtil.convertStringDateToArrInt(Common.displayDate(resultSet.getDate("birthday")));
						YearMonthDay birthday = new YearMonthDay(list);
						tblUser = new TblUser(resultSet.getInt("user_id"), resultSet.getInt("group_id"),
								resultSet.getString("login_name"), resultSet.getString("passwords"),
								resultSet.getString("salt"), resultSet.getString("rule"),
								resultSet.getString("full_name"), resultSet.getString("full_name_kana"),
								resultSet.getString("email"), resultSet.getString("tel"), birthday);
					}
				} else {
					tblUser = null;
				}
				resultSet.close();
			} catch (Exception e) {
				System.out.println("an exception occur: " + e.getMessage());
			} finally {
				closeConnect();
			}
		}
		return tblUser;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.dao.TblUserDao#insertUser(manageuser.entities.TblUser,
	 * manageuser.entities.TblDetailUserJapan)
	 */
	@Override
	public boolean insertUser(TblUser tblUser, TblDetailUserJapan tblDetailUserJapan) {
		if (connectToDB()) {
			try {
				connection.setAutoCommit(false);
				// Tạo truy vấn insert vào bảng tbl.user
				StringBuilder sqlCommand = new StringBuilder();
				sqlCommand.append(
						"INSERT INTO tbl_user (group_id, login_name, passwords,salt, rule, full_name, full_name_kana, email, tel, birthday)");
				sqlCommand.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?,?)");
				preparedStatement = connection.prepareStatement(sqlCommand.toString(), Statement.RETURN_GENERATED_KEYS);
				int i = 1;
				preparedStatement.setInt(i++, tblUser.getGroupId());
				preparedStatement.setString(i++, tblUser.getLoginName());
				preparedStatement.setString(i++, tblUser.getPassword());
				preparedStatement.setString(i++, tblUser.getSalt());
				preparedStatement.setString(i++, tblUser.getRule());
				preparedStatement.setString(i++, tblUser.getFullName());
				preparedStatement.setString(i++, tblUser.getFullNameKana());
				preparedStatement.setString(i++, tblUser.getEmail());
				preparedStatement.setString(i++, tblUser.getTel());
				preparedStatement.setString(i++, StringUtil.convertDateObjectToString(tblUser.getBirthday()));
				preparedStatement.executeUpdate();
				resultSet = preparedStatement.getGeneratedKeys();
				if (resultSet != null && resultSet.next()) {
					// Gán giá trị userId cho đối tượng tblUser và
					// tblDetailUserJapan truyền vào
					tblUser.setUserId(resultSet.getInt(1));
					tblDetailUserJapan.setUserId(tblUser.getUserId());
				}
				if (tblUser.getUserId() > 0) {
					// Nếu có trình độ tiếng Nhật
					if (!"0".equals(tblDetailUserJapan.getCodeLevel())) {
						// Tạo câu truy vấn insert vào bảng
						// tbl_detail_user_japan
						sqlCommand = new StringBuilder();
						sqlCommand.append(
								"INSERT INTO tbl_detail_user_japan (user_id, code_level, start_date, end_date, total) ");
						sqlCommand.append("VALUES (?, ?, ?, ?, ?)");

						preparedStatement = connection.prepareStatement(sqlCommand.toString(),
								Statement.RETURN_GENERATED_KEYS);
						i = 1;
						preparedStatement.setInt(i++, tblDetailUserJapan.getUserId());
						preparedStatement.setString(i++, tblDetailUserJapan.getCodeLevel());
						preparedStatement.setString(i++,
								StringUtil.convertDateObjectToString(tblDetailUserJapan.getStartDate()));
						preparedStatement.setString(i++,
								StringUtil.convertDateObjectToString(tblDetailUserJapan.getEndDate()));
						preparedStatement.setInt(i++, tblDetailUserJapan.getTotal());

						preparedStatement.executeUpdate();

						resultSet = preparedStatement.getGeneratedKeys();
						if (resultSet != null && resultSet.next()) {
							tblDetailUserJapan.setDetailUserJapanId(resultSet.getInt(1));
						}
					}
				}
				// Nếu không có lỗi gì thì thực hiện commit
				connection.commit();
			} catch (Exception e) {
				System.out.println("an exception occur: " + e.getMessage());
				try {
					connection.rollback();
					return false;
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					System.out.println("an exception occur: " + e.getMessage());
				}
			} finally {
				closeConnect();
			}
		}
		return true;
	}

	@Override
	public boolean updateUser(UserInfor userInfor, final String typeJapan) {
		// TODO Auto-generated method stub
		if (connectToDB()) {
			try {
				connection.setAutoCommit(false);
				// Tạo câu truy vấn
				StringBuilder sqlCommand = new StringBuilder();
				if (!"".equals(userInfor.getPass())) {
					sqlCommand.append("UPDATE tbl_user ");
					sqlCommand.append("SET group_id = ?, login_name =?, passwords = ?, salt=?,");
					sqlCommand.append("full_name = ?, full_name_kana = ?, email = ?, tel = ?, birthday = ? ");
					sqlCommand.append("WHERE user_id = ? ");
				} else {
					sqlCommand.append("UPDATE tbl_user ");
					sqlCommand.append("SET group_id = ?, ");
					sqlCommand.append("login_name =?, ");
					sqlCommand.append("full_name = ?, full_name_kana = ?, email = ?, tel = ?, birthday = ? ");
					sqlCommand.append("WHERE user_id = ? ");
				}
				preparedStatement = connection.prepareStatement(sqlCommand.toString());
				int i = 1;
				// start fix bug Web security
				SecureRandom random = new SecureRandom();
				String salt = Integer.toString(random.hashCode());
				// end fix bug Web security
				if (!"".equals(userInfor.getPass())) {
					preparedStatement.setInt(i++, userInfor.getGroupId());
					preparedStatement.setString(i++, userInfor.getLoginName());
					preparedStatement.setString(i++, Common.encryptMD5(salt + userInfor.getPass()));
					preparedStatement.setString(i++, salt);
					preparedStatement.setString(i++, userInfor.getFullName());
					preparedStatement.setString(i++, userInfor.getFullNameKana());
					preparedStatement.setString(i++, userInfor.getEmail());
					preparedStatement.setString(i++, userInfor.getTel());
					preparedStatement.setString(i++, StringUtil.convertDateObjectToString(userInfor.getBirthday()));
					preparedStatement.setInt(i++, userInfor.getUserId());
				} else {
					preparedStatement.setInt(i++, userInfor.getGroupId());
					preparedStatement.setString(i++, userInfor.getLoginName());
					preparedStatement.setString(i++, userInfor.getFullName());
					preparedStatement.setString(i++, userInfor.getFullNameKana());
					preparedStatement.setString(i++, userInfor.getEmail());
					preparedStatement.setString(i++, userInfor.getTel());
					preparedStatement.setString(i++, StringUtil.convertDateObjectToString(userInfor.getBirthday()));
					preparedStatement.setInt(i++, userInfor.getUserId());
				}

				int result = preparedStatement.executeUpdate();
				if (result > 0) {// Nếu updata vào bảng tbl_user thành công
					// Trường hợp update tbl_detail_user_japan
					if ("Update".equals(typeJapan)) {
						// Tạo câu truy vấn update vào bảng
						// tbl_detail_user_japan
						sqlCommand = new StringBuilder();
						sqlCommand.append("UPDATE tbl_detail_user_japan	");
						sqlCommand.append("SET code_level = ?, start_date = ?, end_date = ?, total = ? ");
						sqlCommand.append("WHERE user_id = ?");

						preparedStatement = connection.prepareStatement(sqlCommand.toString());
						i = 1;
						preparedStatement.setString(i++, userInfor.getCodeLevel());
						preparedStatement.setString(i++,
								StringUtil.convertDateObjectToString(userInfor.getStartDate()));
						preparedStatement.setString(i++, StringUtil.convertDateObjectToString(userInfor.getEndDate()));
						preparedStatement.setInt(i++, Integer.parseInt(userInfor.getTotal()));
						preparedStatement.setInt(i++, userInfor.getUserId());
						preparedStatement.executeUpdate();

					}

					// Trường hợp Delete tbl_detail_user_japan
					if ("Delete".equals(typeJapan)) {
						// Tạo câu truy vấn delete vào bảng
						// tbl_detail_user_japan
						sqlCommand = new StringBuilder();
						sqlCommand.append("DELETE FROM tbl_detail_user_japan ");
						sqlCommand.append("WHERE user_id = ? ");
						preparedStatement = connection.prepareStatement(sqlCommand.toString());
						i = 1;
						preparedStatement.setInt(i++, userInfor.getUserId());
					}

					// Trường hợp Insert tbl_detail_user_japan
					if ("Insert".equals(typeJapan)) {
						// Tạo câu truy vấn insert vào bảng
						// tbl_detail_user_japan
						sqlCommand = new StringBuilder();
						sqlCommand.append(
								"INSERT INTO tbl_detail_user_japan (user_id, code_level, start_date, end_date, total) ");
						sqlCommand.append("VALUES (?, ?, ?, ?, ?)");

						preparedStatement = connection.prepareStatement(sqlCommand.toString(),
								Statement.RETURN_GENERATED_KEYS);
						i = 1;
						preparedStatement.setInt(i++, userInfor.getUserId());
						preparedStatement.setString(i++, userInfor.getCodeLevel());
						preparedStatement.setString(i++,
								StringUtil.convertDateObjectToString(userInfor.getStartDate()));
						preparedStatement.setString(i++, StringUtil.convertDateObjectToString(userInfor.getEndDate()));
						preparedStatement.setInt(i++, Integer.parseInt(userInfor.getTotal()));

						preparedStatement.executeUpdate();

						resultSet = preparedStatement.getGeneratedKeys();

					}
					connection.commit();
				}
			} catch (Exception e) {
				System.out.println("an exception occur: " + e.getMessage());
				try {
					connection.rollback();
					return false;
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					System.out.println("an exception occur: " + e.getMessage());
				}
			} finally {
				closeConnect();
			}
		}
		return true;
	}

	@Override
	public boolean deleteUserById(int userId, String exitsCodeLevel) {
		// TODO Auto-generated method stub
		if (connectToDB()) {
			try {
				connection.setAutoCommit(false);
				if ("exist".equals(exitsCodeLevel)) {
					// Tạo câu truy vấn delete vào bảng tbl_detail_user_japan
					StringBuilder sqlCommand = new StringBuilder();
					sqlCommand.append("DELETE FROM tbl_detail_user_japan ");
					sqlCommand.append("WHERE user_id = ? ");
					preparedStatement = connection.prepareStatement(sqlCommand.toString());
					int i = 1;
					preparedStatement.setInt(i++, userId);
					preparedStatement.executeUpdate();
				}

				// Tạo câu truy vấn delete vào bảng tbl_tbl_user
				StringBuilder sqlCommand = new StringBuilder();
				sqlCommand.append("DELETE FROM tbl_user ");
				sqlCommand.append("WHERE user_id = ? ");
				preparedStatement = connection.prepareStatement(sqlCommand.toString());
				int i = 1;
				preparedStatement.setInt(i++, userId);
				preparedStatement.executeUpdate();
				connection.commit();

			} catch (Exception e) {
				System.out.println("an exception occur: " + e.getMessage());
				try {
					connection.rollback();
					return false;
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					System.out.println("an exception occur: " + e.getMessage());
				}
			} finally {
				closeConnect();
			}
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.dao.TblUserDao#checkExistedCodeLevel(java.lang.String)
	 */
	@Override
	public boolean checkExistedCodeLevel(String codeLevel) {
		// TODO Auto-generated method stub
		if (connectToDB()) {
			try {
				// Tạo câu truy vấn
				StringBuilder sqlCommand = new StringBuilder();
				sqlCommand.append("SELECT * ");
				sqlCommand.append("FROM mst_japan ");
				sqlCommand.append("WHERE code_level LIKE '" + codeLevel + "'");

				preparedStatement = connection.prepareStatement(sqlCommand.toString());
				resultSet = preparedStatement.executeQuery();
				if (resultSet != null) {
					while (resultSet.next()) {
						return true;
					}
				} else {
					return false;
				}
			} catch (Exception e) {
				System.out.println("an exception occur: " + e.getMessage());
			} finally {
				closeConnect();
			}
		}
		return false;
	}

}
