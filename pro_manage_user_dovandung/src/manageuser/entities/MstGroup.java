/**
 * Copyright(C) 2017 Luvina SoftWare
MstGroup.java, Jan 19, 2017 DoVanDung
 */
package manageuser.entities;

import java.io.Serializable;
/**
 * MstGroup
 * @author dovandung
 *
 */
public class MstGroup implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int groupId;
	private String groupName;

	/**
	 * Hàm khởi tạo không có tham số
	 */
	public MstGroup() {
		super();
	}

	/**
	 * Constructor
	 *
	 * @param groupId
	 *            group_id
	 * @param groupName
	 *            group_name
	 */
	public MstGroup(int groupId, String groupName) {
		super();
		this.groupId = groupId;
		this.groupName = groupName;
	}

	/**
	 * @return the groupId
	 */
	public int getGroupId() {
		return groupId;
	}

	/**
	 * @param groupId
	 *            the groupId to set
	 */
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	/**
	 * @return the groupName
	 */
	public String getGroupName() {
		return groupName;
	}

	/**
	 * @param groupName
	 *            the groupName to set
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

}
