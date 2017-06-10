/**
 * Copyright(C) 2017 Luvina Software Company
 * manageuser.controllers, 2017/05/02, DOVANDUNG
 */
package manageuser.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manageuser.utils.Constant;
import manageuser.utils.MessageProperties;

/**
 * Controller hiển thị thành công
 * @author dovandung
 *
 */
public class SuccessController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SuccessController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String message = "";
		String codeMessage = "";
		if (request.getParameter("type") != null) {
			 codeMessage = request.getParameter("type").toString();
		}
		System.out.println(codeMessage);
		message = MessageProperties.getData(codeMessage);

		request.setAttribute("message", message);
		if ("".equals(message)) {
			RequestDispatcher req = request.getRequestDispatcher(Constant.ADM001);
			req.forward(request, response);
		} else {
			RequestDispatcher req = request.getRequestDispatcher(Constant.ADM006);
			req.forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
