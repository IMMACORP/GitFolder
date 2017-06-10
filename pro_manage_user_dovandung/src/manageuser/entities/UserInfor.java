/**
 * Copyright(C) 2017 Luvina Software Company
 * manageuser.entities, 2017/04/16, DOVANDUNG
 */
package manageuser.entities;

/**
 * UserInfor
 * @author dovandung
 *
 */
public class UserInfor {
	private int userId;
	private String loginName;
	private String fullName;
	private String fullNameKana;
	private String groupName;
	private int groupId;
	private YearMonthDay birthday;
	private String email;
	private String tel;
	private String pass;
	private String passConfirm;
	private String nameLevel;
	private String codeLevel;
	private YearMonthDay endDate;
	private YearMonthDay startDate;
	private String total;

	public UserInfor() {

	}

	/**
	 * @param userId
	 * @param fullName
	 * @param birthday
	 * @param groupName
	 * @param email
	 * @param tel
	 * @param nameLevel
	 * @param endDate
	 * @param total
	 */
	public UserInfor(int userId, String fullName, YearMonthDay birthday, String groupName, String email, String tel,
			String nameLevel, YearMonthDay endDate, String total) {
		super();
		this.userId = userId;
		this.fullName = fullName;
		this.birthday = birthday;
		this.groupName = groupName;
		this.email = email;
		this.tel = tel;
		this.total = total;
		this.nameLevel = nameLevel;
		this.endDate = endDate;
	}

	/**
	 *
	 * @param loginName
	 * @param fullName
	 * @param fullNameKana
	 * @param groupName
	 * @param groupId
	 * @param birthday
	 * @param email
	 * @param tel
	 * @param pass
	 * @param passConfirm
	 * @param nameLevel
	 * @param codeLevel
	 * @param endDate
	 * @param startDate
	 * @param total
	 */
	public UserInfor(int userId, String loginName, String fullName, String fullNameKana, String groupName, int groupId,
			YearMonthDay birthday, String email, String tel, String pass, String passConfirm, String nameLevel,
			String codeLevel, YearMonthDay endDate, YearMonthDay startDate, String total) {
		super();
		this.userId = userId;
		this.loginName = loginName;
		this.fullName = fullName;
		this.fullNameKana = fullNameKana;
		this.groupName = groupName;
		this.groupId = groupId;
		this.birthday = birthday;
		this.email = email;
		this.tel = tel;
		this.pass = pass;
		this.passConfirm = passConfirm;
		this.nameLevel = nameLevel;
		this.codeLevel = codeLevel;
		this.endDate = endDate;
		this.startDate = startDate;
		this.total = total;
	}

	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * @return the loginName
	 */
	public String getLoginName() {
		return loginName;
	}

	/**
	 * @param loginName
	 *            the loginName to set
	 */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * @param fullName
	 *            the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * @return the fullNameKana
	 */
	public String getFullNameKana() {
		return fullNameKana;
	}

	/**
	 * @param fullNameKana
	 *            the fullNameKana to set
	 */
	public void setFullNameKana(String fullNameKana) {
		this.fullNameKana = fullNameKana;
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
	 * @return the birthday
	 */
	public YearMonthDay getBirthday() {
		return birthday;
	}

	/**
	 * @param birthday
	 *            the birthday to set
	 */
	public void setBirthday(YearMonthDay birthday) {
		this.birthday = birthday;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the tel
	 */
	public String getTel() {
		return tel;
	}

	/**
	 * @param tel
	 *            the tel to set
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}

	/**
	 * @return the pass
	 */
	public String getPass() {
		return pass;
	}

	/**
	 * @param pass
	 *            the pass to set
	 */
	public void setPass(String pass) {
		this.pass = pass;
	}

	/**
	 * @return the passConfirm
	 */
	public String getPassConfirm() {
		return passConfirm;
	}

	/**
	 * @param passConfirm
	 *            the passConfirm to set
	 */
	public void setPassConfirm(String passConfirm) {
		this.passConfirm = passConfirm;
	}

	/**
	 * @return the nameLevel
	 */
	public String getNameLevel() {
		return nameLevel;
	}

	/**
	 * @param nameLevel
	 *            the nameLevel to set
	 */
	public void setNameLevel(String nameLevel) {
		this.nameLevel = nameLevel;
	}

	/**
	 * @return the codeLevel
	 */
	public String getCodeLevel() {
		return codeLevel;
	}

	/**
	 * @param codeLevel
	 *            the codeLevel to set
	 */
	public void setCodeLevel(String codeLevel) {
		this.codeLevel = codeLevel;
	}

	/**
	 * @return the endDate
	 */
	public YearMonthDay getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate
	 *            the endDate to set
	 */
	public void setEndDate(YearMonthDay endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the startDate
	 */
	public YearMonthDay getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate
	 *            the startDate to set
	 */
	public void setStartDate(YearMonthDay startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the total
	 */
	public String getTotal() {
		return total;
	}

	/**
	 * @param total
	 *            the total to set
	 */
	public void setTotal(String total) {
		this.total = total;
	}

}
