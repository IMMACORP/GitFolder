/**
 * Copyright(C) 2017 Luvina SoftWare
Constant.java, Jan 19, 2017 DoVanDung
 */
package manageuser.utils;
/**
 * Class định nghĩa constant
 * @author dovandung
 *
 */
public class Constant {
	public static final String ADM001 = "jsp/ADM001.jsp";
	public static final String ADM002 = "jsp/ADM002.jsp";
	public static final String ADM003 = "jsp/ADM003.jsp";
	public static final String ADM004 = "jsp/ADM004.jsp";
	public static final String ADM005 = "jsp/ADM005.jsp";
	public static final String ADM006 = "jsp/ADM006.jsp";
	public static String ADD_USER_CONFIRM = "/pro_manage_user_dovandung/AddUserConfirm.do";
	public static String ADD_USER_SUCCESS = "/pro_manage_user_dovandung/AddSuccess.do";
	public static String System_Error = "jsp/System_Error.jsp";
	// Dinh cac cach access vao ADM002
	public static final int accessFromADM001 = 1;
	public static final int accessFromSearch = 2;
	public static final int accessFromClick = 3;
	
	// sort
	public static final String sortByFullName = "ASC";
	public static final String sortByCodeLevel = "DESC";
	public static final String sortByEndDate = "DESC";
	public static final int maxLenght = 255;
	// url
	public static final String INSERT_CONFIRM_URL = "/AddUserOK.do";
	public static final String SYSTEM_ERROR_URL = "/SystemError.do";
	public static final String LIST_USER_URL = "/ListUser.do";
	public static final String SUCCESS_URL = "/Success.do";
	// notification
	public static final String INSERT_SUCCESS = "insertSucces";
	public static final String SYSTEM_ERROR = "systemError";
	public static final String SYSTEM_ERROR_DB = "noConnectDB";
	public static final String SYSTEM_ERROR_NOEXIST = "noExist";
	public static final String UPDATE_SUCCESS = "updateSucces";
	public static final String DELETE_SUCCESS = "deleteSucces";
	public static final String UPDATE = "update";
	public static final String INSERT = "insert";
	// year
	public static final int yearBirthday = 1980;
	public static final int yearLevel = 2000;
	// rule
	public static int RULE = 0;

}
