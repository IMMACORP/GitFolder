/**
 * Copyright(C) 2017 Luvina Software Company
 * manageuser.logics.impl, 2017/04/27, DOVANDUNG
 */
package manageuser.logics.impl;

import java.util.ArrayList;
import java.util.List;

import manageuser.entities.MstJapan;
import manageuser.logics.MstJapanLogic;
import manageuser.dao.impl.MstJapanDaoImpl;

/**
 * class triển khai interface MstJapanLogic
 * @author dovandung
 *
 */
public class MstJapanLogicImpl implements MstJapanLogic {

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.logics.MstJapanLogic#getAllMstJapan()
	 */
	@Override
	public List<MstJapan> getAllMstJapan() {
		List<MstJapan> result = new ArrayList<MstJapan>();
		MstJapanDaoImpl mstJapanDaoImpl = new MstJapanDaoImpl();
		result = mstJapanDaoImpl.getAllMstJapan();
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.logics.MstJapanLogic#getNameLevel(java.lang.String)
	 */
	@Override
	public String getNameLevel(String codeLevel) {
		// TODO Auto-generated method stub
		String nameLevel = "";
		List<MstJapan> listMstJapan = getAllMstJapan();
		for (MstJapan mstJapan : listMstJapan) {
			if (codeLevel.equals(mstJapan.getCodeLevel())) {
				nameLevel = mstJapan.getNameLevel();
				break;
			}
		}
		return nameLevel;
	}

}
