/**
 * Copyright(C) 2017 Luvina Software Company
 * manageuser.controllers, 2017/05/06, DOVANDUNG
 */
package manageuser.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manageuser.entities.UserInfor;
import manageuser.logics.impl.TblUserLogicImpl;
import manageuser.utils.Constant;
import manageuser.utils.MessageErrorProperties;

/**
 * controller hiển thị chi tiết user
 * 
 * @author dovandung
 *
 */
public class DetailUserInforController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DetailUserInforController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Tạo list UserInfor để lưu dữ liệu lấy từ database
		UserInfor userInfor = new UserInfor();
		// Khai báo đối tượng khác
		TblUserLogicImpl tblUserLogicImpl = new TblUserLogicImpl();

		int userId = 0;

		if (!"".equals(request.getParameter("userId"))) {
			userId = Integer.parseInt(request.getParameter("userId"));
		}
		if (tblUserLogicImpl.checkExistedUserId(userId)) {
			// Lấy userInfor theo Id
			userInfor = tblUserLogicImpl.getUsersById(userId);
			
			userInfor.setUserId(userId);
			request.setAttribute("userInfor", userInfor);
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(Constant.ADM005);
			requestDispatcher.forward(request, response);
		} else {
			request.setAttribute("error", MessageErrorProperties.getErrMessage("ER013"));
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(Constant.System_Error);
			requestDispatcher.forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
