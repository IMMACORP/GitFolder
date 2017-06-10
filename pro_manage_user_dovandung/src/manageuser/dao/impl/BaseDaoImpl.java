
package manageuser.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import manageuser.dao.BaseDao;
import manageuser.utils.DatabaseProperties;
/**
 * class triển khai interface BaseDao
 * @author dovandung
 *
 */
public class BaseDaoImpl implements BaseDao {
	protected static Connection connection = null;
	protected PreparedStatement preparedStatement = null;
	protected ResultSet resultSet = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.luvina.manageuser.dao.BaseDao#closeConnect()
	 */
	public void closeConnect() {
		if (connection != null) {
			try {
				connection.close();
			} catch (Exception e) {
				System.out.println("an exception occur: " + e.getMessage());
			}
			connection = null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.luvina.manageuser.dao.BaseDao#connectToDB()
	 */
	public boolean connectToDB() {
		boolean result = true;
		try {
			// load mysql driver
			Class.forName(DatabaseProperties.getData("driver"));
			connection = DriverManager.getConnection(DatabaseProperties.getData("url"),
					DatabaseProperties.getData("user"), DatabaseProperties.getData("password"));
		} catch (Exception e) {
			System.out.println("an exception occur: " + e.getMessage());
			result = false;
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.luvina.manageuser.dao.BaseDao#getConnect()
	 */
	public Connection getConnect() {
		if (connection != null) {
			return connection;
		}
		return null;
	}

}
