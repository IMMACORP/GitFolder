/**
 * Copyright(C) 2017 Luvina SoftWare
LoginLogicImpl.java, Jan 19, 2017 DoVanDung
 */
package manageuser.logics.impl;

import manageuser.dao.impl.TblUserDaoImpl;
import manageuser.entities.TblUser;
import manageuser.logics.LoginLogic;
import manageuser.utils.Common;

/**
 * Class về xử lý logic login
 * 
 * @author dovandung
 *
 */
public class LoginLogicImpl implements LoginLogic {

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.logics.LoginLogic#existLoginId(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public Boolean existLoginId(String loginId, String password) {
		TblUserDaoImpl daoImpl = new TblUserDaoImpl();

		TblUser tblUser = daoImpl.getExistedUser(null, loginId);
		if (tblUser != null) {
			password = Common.encryptMD5(tblUser.getSalt() + password);
		}
		// check exist user which have input loginId and encrypt password
		if (daoImpl.existLoginId(loginId, password)) {
			return true;
		} else {
			return false;
		}
	}

}
