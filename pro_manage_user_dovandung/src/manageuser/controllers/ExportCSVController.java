/**
 * Copyright(C) 2017 Luvina Software Company
 * manageuser.controllers, 2017/05/17, DOVANDUNG
 */
package manageuser.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import manageuser.entities.UserInfor;
import manageuser.utils.Common;

/**
 * Controller xử lý export csv file
 * @author dovandung
 *
 */
public class ExportCSVController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ExportCSVController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Lấy listUser cần export từ session
		HttpSession session = request.getSession();
		String exportKeySession = (String) request.getParameter("exportKeySession");
		System.out.println("exportKeySession is " + exportKeySession);
		List<UserInfor> listUserExport = (List<UserInfor>) session.getAttribute(exportKeySession);
		// Set giá trị header cho respone
		response.setHeader("Content-type", "application/csv");
		response.setHeader("Content-disposition", "inline; filename=listuserExport.csv");
		// OutputStream os = new
		// FileOutputStream("d:/Training.Lu/MsHuong/output/BaiTap/JavaCore/pro_manage_user_dovandung/csv/listuserExport.csv");

		// os.write(239);
		// os.write(187);
		// os.write(191);
		// ghi dữ liệu lên respose
		PrintWriter writer = response.getWriter();
		// PrintWriter writer = new PrintWriter(new OutputStreamWriter(os,
		// "UTF-8"));

		writer.print(Common.exportCSV(listUserExport));
		writer.flush();
		writer.close();

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
