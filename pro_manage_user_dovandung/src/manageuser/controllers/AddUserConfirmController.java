/**
 * Copyright(C) 2017 Luvina Software Company
 * manageuser.controllers, 2017/05/01, DOVANDUNG
 */
package manageuser.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import manageuser.entities.UserInfor;
import manageuser.logics.impl.TblUserLogicImpl;
import manageuser.utils.Constant;
import manageuser.utils.MessageErrorProperties;
// test git diff
/**
 * class xử lý confirm việc add user
 * 
 * @author dovandung
 *
 */
public class AddUserConfirmController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AddUserConfirmController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String userInforSession = request.getParameter("userInforSession");
		String accessType = request.getParameter("accessType");
		if (session.getAttribute(userInforSession) != null) {
			// Nếu session khác rỗng
			request.setAttribute("userInfor", session.getAttribute(userInforSession));
			request.setAttribute("accessType", accessType);
			request.setAttribute("userInforSession", userInforSession);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(Constant.ADM004);
			requestDispatcher.forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String accessType = request.getParameter("accessType").toString();
		String userInforSession = request.getParameter("userInforSession");
		TblUserLogicImpl tblUserLogicImpl = new TblUserLogicImpl();
		// <Check>
		UserInfor userInfor = (UserInfor) session.getAttribute(userInforSession);
		if ("add".equals(accessType)) {
			if (tblUserLogicImpl.createUser(userInfor)) {
				request.setAttribute("userInfor006", userInfor);
				response.sendRedirect(Constant.ADD_USER_SUCCESS + "?type=MSG001");
			} else {
				request.setAttribute("error", MessageErrorProperties.getErrMessage("ER015"));
				RequestDispatcher requestDispatcher = request.getRequestDispatcher(Constant.System_Error);
				requestDispatcher.forward(request, response);

			}
		}
		if ("edit".equals(accessType)) {
			if (tblUserLogicImpl.updateUser(userInfor)) {
				String message = "MSG002";
				response.sendRedirect(Constant.ADD_USER_SUCCESS + "?type=" + message);
			} else {
				// Chuyển đến màn hình System_Error
				request.setAttribute("error", MessageErrorProperties.getErrMessage("ER015"));
				RequestDispatcher requestDispatcher = request.getRequestDispatcher(Constant.System_Error);
				requestDispatcher.forward(request, response);
			}
		}
		request.setAttribute("accessType", accessType);

	}
}
