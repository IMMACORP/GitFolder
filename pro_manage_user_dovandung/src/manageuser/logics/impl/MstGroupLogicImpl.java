/**
 * 
 */
package manageuser.logics.impl;

import java.util.List;

import manageuser.dao.impl.MstGroupDaoImpl;
import manageuser.entities.MstGroup;
import manageuser.logics.MstGroupLogic;


/**
 * class triển khai interface MstGroupLogic
 * @author dovandung
 *
 */
public class MstGroupLogicImpl implements MstGroupLogic {
	/*
	 * (non-Javadoc)
	 *
	 * @see net.luvina.manageuser.logics.MstGroupLogic#getAllGroups()
	 */
	@Override
	public List<MstGroup> getAllMstGroup() {
		MstGroupDaoImpl mstGroup = new MstGroupDaoImpl();
		List<MstGroup> list = mstGroup.getAllMstGroup();
		return list;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see net.luvina.manageuser.logics.MstGroupLogic#checkExistGroup(int)
	 */
	@Override
	public boolean checkExistGroup(int groupId) {
		MstGroupDaoImpl mstGroupDao = new MstGroupDaoImpl();
		MstGroup mstGroup = mstGroupDao.getMstGroupById(groupId);
		if (mstGroup != null) {
			return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see net.luvina.manageuser.logics.MstGroupLogic#getMstGroupById(int)
	 */
	@Override
	public MstGroup getMstGroupById(int groupId) {
		MstGroupDaoImpl mstGroupDao = new MstGroupDaoImpl();
		MstGroup mstGroup = mstGroupDao.getMstGroupById(groupId);
		return mstGroup;
	}
	@Override
	public String getNameGroup(int groupId) {
		// TODO Auto-generated method stub
		String groupName = "";
		List<MstGroup> listGroup = getAllMstGroup();
		for (MstGroup group : listGroup){
			if (group.getGroupId() == groupId){
				groupName = group.getGroupName();
				break;
			}
		}
		return groupName;
	}

}
