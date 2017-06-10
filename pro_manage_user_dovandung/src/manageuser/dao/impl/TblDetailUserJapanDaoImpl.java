/**
 * Copyright(C) 2017 Luvina SoftWare
TblDetailUserJapanDaoImpl.java, Jan 19, 2017 DoVanDung
 */
package manageuser.dao.impl;

import java.util.ArrayList;
import java.util.List;

import manageuser.dao.TblDetailUserJapanDao;
import manageuser.entities.TblDetailUserJapan;
import manageuser.entities.YearMonthDay;
import manageuser.utils.Common;
import manageuser.utils.StringUtil;
import manageuser.dao.impl.BaseDaoImpl;
/**
 * Class triển khai interface thao tác với TblDetailUserJapan
 * @author dovandung
 *
 */
public class TblDetailUserJapanDaoImpl extends BaseDaoImpl implements TblDetailUserJapanDao {
	@Override
	public TblDetailUserJapan getDetailUserJapanById(int userId) {
		TblDetailUserJapan tblDetailUserJapan = null;
		if (connectToDB()) {
			try {
				// Tạo câu truy vấn
				StringBuilder sqlCommand = new StringBuilder();
				sqlCommand.append("SELECT * FROM tbl_detail_user_japan ");
				sqlCommand.append("WHERE user_id = ? ");

				preparedStatement = connection.prepareStatement(sqlCommand.toString());
				preparedStatement.setInt(1, userId);
				resultSet = preparedStatement.executeQuery();

				if (resultSet != null) {
					while (resultSet.next()) {
						List<Integer> list = new ArrayList<Integer>();
						list = StringUtil
								.convertStringDateToArrInt(Common.displayDate(resultSet.getDate("start_date")));
						YearMonthDay startDate = new YearMonthDay(list);
						list = StringUtil.convertStringDateToArrInt(Common.displayDate(resultSet.getDate("end_date")));
						YearMonthDay endDate = new YearMonthDay(list);

						tblDetailUserJapan = new TblDetailUserJapan(resultSet.getInt("detail_user_japan_id"),
								resultSet.getInt("user_id"), resultSet.getString("code_level"), startDate, endDate,
								resultSet.getInt("total"));
					}
				} else {
					tblDetailUserJapan = null;
				}
			} catch (Exception e) {
				System.out.println("an exception occur: " + e.getMessage());
			} finally {
				closeConnect();
			}
		}
		return tblDetailUserJapan;
	}

}
