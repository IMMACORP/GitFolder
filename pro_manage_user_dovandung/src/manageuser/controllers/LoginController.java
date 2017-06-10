/**
 * Copyright(C) 2017 Luvina SoftWare
LoginController.java, Jan 19, 2017 DoVanDung
 */
package manageuser.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import manageuser.utils.Constant;
import manageuser.validates.ValidateLogin;

/**
 * Điều khiển việc login Servlet implementation class LoginController
 */

public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher req = request.getRequestDispatcher(Constant.ADM001);
		req.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// returns root path of application
		String contextPath = request.getContextPath();
		// get session
		HttpSession session = request.getSession();
		// Khởi tạo list hiển thị error message
		List<String> lsErrMessage = new ArrayList<String>();

		// lấy parameter từ input của user
		String loginId = request.getParameter("loginId").toString();
		String password = request.getParameter("password").toString();

		// validate đối với trường hợp login lần đầu
		if ("login".equals(request.getParameter("accessType"))) {
			lsErrMessage = ValidateLogin.validateLogin(loginId, password);
		}
		// lấy error message
		if (lsErrMessage.size() > 0) {
			// Set lsErrMessage to request
			request.setAttribute("lsErrMessage", lsErrMessage);

			// forward ADM001
			RequestDispatcher req = request.getRequestDispatcher(Constant.ADM001);
			req.forward(request, response);
			// Nếu login thành công
		} else {
			// gọi controller ListUserController kiểu doGet
			response.sendRedirect(contextPath + "/ListUser.do");
			// Chỉ truyền loginId lên session để check login
			session.setAttribute("loginId", loginId);
			return;
		}

	}

}
