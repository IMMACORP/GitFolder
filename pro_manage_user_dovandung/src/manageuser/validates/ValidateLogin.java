/**
 * Copyright(C) 2017 Luvina SoftWare
LoginController.java, Jan 19, 2017 DoVanDung
 */
package manageuser.validates;

import java.util.ArrayList;
import java.util.List;

import manageuser.logics.impl.LoginLogicImpl;
import manageuser.utils.MessageErrorProperties;

/**
 * Class validate login
 * @author DoVanDung
 *
 */
public class ValidateLogin {
	/**
	 * Hàm validate Login
	 *
	 * @param loginId
	 *            String loginId
	 * @param password
	 *            String password
	 * @return list errorMess
	 */
	public static List<String> validateLogin(String loginId, String password) {
		loginId = loginId.trim();
		List<String> lsErrorMess = new ArrayList<String>();
		// nếu chưa nhập id
		if (loginId.length() == 0) {
			lsErrorMess.add(MessageErrorProperties.getMessage("ER001_loginName"));
		}
		// nếu chưa nhập pass
		if (password.length() == 0) {
			lsErrorMess.add(MessageErrorProperties.getMessage("ER001_pass"));
		}
		LoginLogicImpl loginLogic = new LoginLogicImpl();
		// nếu đã nhập id và pass thì check tồn tại user
		if (loginId.length() != 0 && password.length() != 0) {
			if (!loginLogic.existLoginId(loginId, password)) {
				lsErrorMess.add(MessageErrorProperties.getMessage("ER016"));
			}
		}

		return lsErrorMess;
	}
}
