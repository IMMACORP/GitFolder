package manageuser.dao;

import java.sql.Connection;
/**
 * Interface cho basedao, tạo kết nối DB
 * @author dovandung
 *
 */
public interface BaseDao {
	 /**
     * connectToDB
     *
     * @return true: connectToDB success false: connectToDB unsuccess
     */
    public boolean connectToDB();
 
    /**
     * closeConnect
     */
    public void closeConnect();
 
    /**
     * get connection
     *
     * @return connection
     */
    public Connection getConnect();
}
