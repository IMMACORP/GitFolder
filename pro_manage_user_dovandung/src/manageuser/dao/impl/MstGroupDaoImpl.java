/**
 * Copyright(C) 2017 Luvina SoftWare
MstGroupImpl.java, Jan 19, 2017 DoVanDung
 */
package manageuser.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import manageuser.dao.MstGroupDao;
import manageuser.entities.MstGroup;
/**
 * class triển khai interface MstGroupDao thao tác với bảng group
 * @author dovandung
 *
 */
public class MstGroupDaoImpl extends BaseDaoImpl implements MstGroupDao {

	@Override
	public List<MstGroup> getAllMstGroup() {
		List<MstGroup> result = new ArrayList<MstGroup>();
		if (connectToDB()) {
			try {
				StringBuilder sqlCommand = new StringBuilder();
				// Tạo câu truy vấn SQL
				sqlCommand.append("SELECT * ");
				sqlCommand.append("FROM mst_group ");

				preparedStatement = connection.prepareStatement(sqlCommand.toString());
				ResultSet resultSet = preparedStatement.executeQuery();

				if (resultSet != null) {
					while (resultSet.next()) {
						// Tạo đối tượng MstGroup
						MstGroup group = new MstGroup(resultSet.getInt("group_id"), resultSet.getString("group_name"));
						// Thêm vào list result
						result.add(group);
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
	public MstGroup getMstGroupById(int groupId) {
		MstGroup group = null;
		if (connectToDB()) {
			try {
				StringBuilder sqlCommand = new StringBuilder();
				sqlCommand.append(" SELECT group_name FROM mst_group");
				sqlCommand.append(" WHERE group_id = ?");
				sqlCommand.append(";");
				preparedStatement = connection.prepareStatement(sqlCommand.toString());
				int index = 1;
				preparedStatement.setInt(index++, groupId);
				resultSet = preparedStatement.executeQuery();
				if (resultSet != null) {
					if (resultSet.next()) {
						group = new MstGroup();
						group.setGroupId(groupId);
						group.setGroupName(resultSet.getString("group_name"));
					}
				}
			} catch (SQLException e) {
				System.out.println("an exception occur: " + e.getMessage());
			}
		}
		return group;

	}

	/*
	 * public static void main(String[] args) { MstGroupDaoImpl daoImpl = new
	 * MstGroupDaoImpl(); List<MstGroup> result = new ArrayList<MstGroup>();
	 * result = daoImpl.getAllMstGroup(); for (MstGroup mstGroup : result) {
	 * 
	 * System.out.println(mstGroup.getGroupName()); }
	 * 
	 * }
	 */
}
