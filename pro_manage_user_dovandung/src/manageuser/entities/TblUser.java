/**
 * Copyright(C) 2017 Luvina SoftWare
TblUser.java, Jan 19, 2017 DoVanDung
 */
package manageuser.entities;
/**
 * TblUser
 * @author dovandung
 *
 */
public class TblUser {
	private int userId;
	private int groupId;
	private String loginName;
	private String salt;
	private String password;
	private String rule;
	private String fullName;
	private String fullNameKana;
	private String email;
	private String tel;
	private YearMonthDay birthday;
	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}
	/**
	 * @return the groupId
	 */
	public int getGroupId() {
		return groupId;
	}
	/**
	 * @return the loginName
	 */
	public String getLoginName() {
		return loginName;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @return the rule
	 */
	public String getRule() {
		return rule;
	}
	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}
	/**
	 * @return the fullNameKana
	 */
	public String getFullNameKana() {
		return fullNameKana;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @return the tel
	 */
	public String getTel() {
		return tel;
	}
	/**
	 * @return the birthday
	 */
	public YearMonthDay getBirthday() {
		return birthday;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	/**
	 * @param groupId the groupId to set
	 */
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	/**
	 * @param loginName the loginName to set
	 */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @param rule the rule to set
	 */
	public void setRule(String rule) {
		this.rule = rule;
	}
	/**
	 * @param fullName the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	/**
	 * @param fullNameKana the fullNameKana to set
	 */
	public void setFullNameKana(String fullNameKana) {
		this.fullNameKana = fullNameKana;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @param tel the tel to set
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}
	/**
	 * @param birthday the birthday to set
	 */
	public void setBirthday(YearMonthDay birthday) {
		this.birthday = birthday;
	}
	/**
	 * @param userId
	 * @param groupId
	 * @param loginName
	 * @param password
	 * @param rule
	 * @param fullName
	 * @param fullNameKana
	 * @param email
	 * @param tel
	 * @param birthday
	 */
	public TblUser(int userId, int groupId, String loginName, String password,String salt, String rule, String fullName,
			String fullNameKana, String email, String tel, YearMonthDay birthday) {
		super();
		this.userId = userId;
		this.groupId = groupId;
		this.loginName = loginName;
		this.password = password;
		this.salt = salt;
		this.rule = rule;
		this.fullName = fullName;
		this.fullNameKana = fullNameKana;
		this.email = email;
		this.tel = tel;
		this.birthday = birthday;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}



}
