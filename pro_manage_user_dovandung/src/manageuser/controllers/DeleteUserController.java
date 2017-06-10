/**
 * Copyright(C) 2017 Luvina Software Company
 * manageuser.controllers, 2017/05/07, DOVANDUNG
 */
package manageuser.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manageuser.logics.impl.TblUserLogicImpl;
import manageuser.utils.Constant;
import manageuser.utils.MessageErrorProperties;

/**
 * controller xử lý delete user
 * 
 * @author dovandung
 *
 */
public class DeleteUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteUserController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int userId = 0;
		if (request.getParameter("userId") != null) {
			userId = Integer.parseInt(request.getParameter("userId"));
		}
		TblUserLogicImpl tblUserLogicImpl = new TblUserLogicImpl();
		// Kiểm tra User có tồn tại trong database không, nếu tồn tại mới delete
		if (userId != 0 && tblUserLogicImpl.checkExistedUserId(userId)) {
			// Xóa user
			if (tblUserLogicImpl.deleteUserById(userId)) {
				response.sendRedirect(Constant.ADD_USER_SUCCESS + "?type=MSG003");
			} else {
				response.sendRedirect(Constant.ADD_USER_SUCCESS + "?type=MSG005");
			}
		} else {
			request.setAttribute("error", MessageErrorProperties.getErrMessage("ER014"));
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
