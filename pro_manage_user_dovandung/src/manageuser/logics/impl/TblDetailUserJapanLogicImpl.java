/**
 * Copyright(C) 2017 Luvina Software Company
 * manageuser.logics.impl, 2017/04/28, DOVANDUNG
 */
package manageuser.logics.impl;

import manageuser.logics.TblDetailUserJapanLogic;
import manageuser.dao.impl.TblDetailUserJapanDaoImpl;

/**
 * class triển khai interface TblDetailUserJapanLogic
 * @author dovandung
 *
 */
public class TblDetailUserJapanLogicImpl implements TblDetailUserJapanLogic {

	/* (non-Javadoc)
	 * @see manageuser.logics.TblDetailUserJapanLogic#checkExistCodeLevel(int)
	 */
	@Override
	public boolean checkExistCodeLevel(int userId) {
		// TODO Auto-generated method stub
		TblDetailUserJapanDaoImpl tblDetailUserJapanDaoImpl = new TblDetailUserJapanDaoImpl();
		if (tblDetailUserJapanDaoImpl.getDetailUserJapanById(userId) != null){
			return true;
		}
		return false;
	}

}
