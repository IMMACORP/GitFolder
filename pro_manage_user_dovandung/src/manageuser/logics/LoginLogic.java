/**
 * Copyright(C) 2017 Luvina SoftWare
LoginLogic.java, Jan 19, 2017 DoVanDung
 */
package manageuser.logics;

import manageuser.logics.LoginLogic;
/**
 * Interface thể hiện các phương thức điều khiển Login
 * @author dovandung
 *
 */
public interface LoginLogic {
	/**
     * Check existLoginId
     *
     * @param loginId
     *            String loginId
     * @param password
     *            String password
     * @return true: existed
     *  false: not exist
     */
    public Boolean existLoginId(String loginId, String password);

}
