/**
 * Copyright(C) 2017 Luvina SoftWare
MstJapanImpl.java, Jan 19, 2017 DoVanDung
 */
package manageuser.dao.impl;

import java.util.ArrayList;
import java.util.List;

import manageuser.dao.MstJapanDao;
import manageuser.entities.MstJapan;
/**
 * Class triển khai interface thao tác với bảng MstJapan
 * @author dovandung
 *
 */
public class MstJapanDaoImpl extends BaseDaoImpl implements MstJapanDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.dao.MstJapanDao#getAllMstJapan()
	 */
	@Override
	public List<MstJapan> getAllMstJapan() {
		List<MstJapan> result = new ArrayList<MstJapan>();
		if (connectToDB()) {
			try {
				StringBuilder sqlCommand = new StringBuilder();
				// Tạo câu truy vấn SQL
				sqlCommand.append("SELECT * ");
				sqlCommand.append("FROM mst_japan");
				preparedStatement = connection.prepareStatement(sqlCommand.toString());
				resultSet = preparedStatement.executeQuery();

				if (resultSet != null) {
					while (resultSet.next()) {
						// Tạo đối tượng MstJapan
						MstJapan mstJapan = new MstJapan(resultSet.getString("code_level"),
								resultSet.getString("name_level"));
						// Thêm vào result
						result.add(mstJapan);
					}
				}
			} catch (Exception e) {
				System.out.println("Loi tang Dao cua MstJapan: " + e.getMessage());
			} finally {
				closeConnect();
			}
		}
		return result;
	}

}
