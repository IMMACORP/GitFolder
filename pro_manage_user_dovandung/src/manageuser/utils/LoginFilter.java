/**
 * Copyright(C) 2017 Luvina Software Company
 * manageuser.utils, 2017/05/11, DOVANDUNG
 */
package manageuser.utils;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import manageuser.utils.Common;
import manageuser.utils.Constant;

/**
 * Lớp filter 
 * @author dovandung
 *
 */

/**
 * Check login Servlet Filter implementation class LoginFilter
 */
public class LoginFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public LoginFilter() {
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;

		HttpSession session = req.getSession();
		String contextPath = req.getContextPath();
		String temp1 = req.getRequestURI().toString();
		String temp2 = contextPath + "/Login.do";
		// temp1 == temp2 là trường hợp lần đầu tiên login, van cho di qua, doPost se xu ly
		if (temp1.equals(temp2) || "".equals(Common.checkLogin(session))) {
			// Cho request di qua
			chain.doFilter(request, response);
		} else {
			RequestDispatcher requestDispatcher = req.getRequestDispatcher(Constant.ADM001);
			requestDispatcher.forward(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
