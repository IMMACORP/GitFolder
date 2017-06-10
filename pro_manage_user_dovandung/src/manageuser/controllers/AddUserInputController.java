/**
 * Copyright(C) 2017 Luvina Software Company
 * manageuser.controllers, 2017/04/26, DOVANDUNG
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

import manageuser.entities.MstGroup;
import manageuser.entities.MstJapan;
import manageuser.entities.UserInfor;
import manageuser.entities.YearMonthDay;
import manageuser.logics.impl.MstGroupLogicImpl;
import manageuser.logics.impl.MstJapanLogicImpl;
import manageuser.logics.impl.TblUserLogicImpl;
import manageuser.utils.Common;
import manageuser.utils.Constant;
import manageuser.validates.ValidateUser;

/**
 * Controller dành cho add user
 * 
 * @author dovandung
 *
 */
public class AddUserInputController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AddUserInputController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Lấy session
		HttpSession session = request.getSession();
		// Khai báo đối tượng khác
		TblUserLogicImpl tblUserLogicImpl = new TblUserLogicImpl();
		// Nhận biết đường vào ADM 003
		String accessType = request.getParameter("accessType").toString();

		// lấy giá trị hiển thị đề fault cho các hạng mục
		UserInfor userInfor = setDefaultValue(request, response);
		// đổ các data vào pulldown
		setDataLogic(request, response);
		/* Cách vào MH 003 từ 002 */
		if ("add".equals(accessType)) {

			/* Đưa dữ liệu lên request chuyển ra view dùng */
			request.setAttribute("accessType", accessType);
			request.setAttribute("userInfor", userInfor);
			// Forward request
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(Constant.ADM003);
			requestDispatcher.forward(request, response);
		}

		/* Cách vào MH 003 từ 004 */
		if ("back".equals(accessType)) {
			userInfor = (UserInfor) session.getAttribute(request.getParameter("userInforSession"));
			// gửi lên view để hiển thị
			request.setAttribute("userInfor", userInfor);
			// di chuyển về 003
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(Constant.ADM003);
			requestDispatcher.forward(request, response);
		}

		/* Cách vào MH 003 từ 005 */
		if ("edit".equals(accessType)) {

			// Cách lấy băng userId gửi qua url từ ADM002
			int userId = 0;
			// userId gửi qua url từ ADM002
			if (request.getParameter("userId") != null) {
				userId = Integer.parseInt(request.getParameter("userId"));
				userInfor.setUserId(userId);
			}
			// Trước khi lấy thông tin user, check tồn tại(phòng đã bị xóa)
			if (tblUserLogicImpl.checkExistedUserId(userId)) {
				// Lấy userInfor theo Id
				userInfor = tblUserLogicImpl.getUsersById(userId);
			}
			/* Đưa dữ liệu lên request để view hiển thị, dùng */
			request.setAttribute("accessType", accessType);
			request.setAttribute("userInfor", userInfor);

			// Forward request
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(Constant.ADM003);
			requestDispatcher.forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Lấy session
		HttpSession session = request.getSession();
		// xem cách vào MH ADM003
		String accessType = request.getParameter("accessType").toString();

		// set hiển thị pulldown
		setDataLogic(request, response);

		/* Khởi tạo các đối tượng */
		// list error cho check validate
		List<String> lstError = new ArrayList<String>();
		// khởi tạo thông tin user
		UserInfor userInfor = new UserInfor();
		// group
		MstGroupLogicImpl groupLogicImpl = new MstGroupLogicImpl();

		/* Khai báo các hạng mục của thông tin user */
		int userId = 0;
		String groupName = "";
		// ngày sinh
		int birthdayYear = 0;
		int birthdayMonth = 0;
		int birthdayDay = 0;
		YearMonthDay birthday = null;
		// start date
		YearMonthDay startDate = null;
		int startYear = 0;
		int startMonth = 0;
		int startDay = 0;
		// end date
		YearMonthDay endDate = null;
		int endYear = 0;
		int endMonth = 0;
		int endDay = 0;

		/*
		 * Start lấy data các hạng mục, set thông tin user vào đối tượng
		 * userInfor
		 */
		String userIdReq = request.getParameter("userId");
		if (!"".equals(userIdReq)) {
			userId = Integer.parseInt(userIdReq);
		}
		userInfor.setUserId(userId);
		// setLoginName
		String loginNameReq = request.getParameter("userName");
		if (loginNameReq != null) {
			userInfor.setLoginName(loginNameReq.toString());
		}
		// setGroupId, setGroupName
		String groupIdReq = request.getParameter("group_id");
		if (groupIdReq != null) {
			int index = Integer.parseInt(groupIdReq.toString());
			userInfor.setGroupId(index);
			groupName = groupLogicImpl.getNameGroup(index);
			userInfor.setGroupName(groupName);
		}
		// setFullName
		String fullNameReq = request.getParameter("fullName");
		if (fullNameReq != null) {
			userInfor.setFullName(fullNameReq.toString());
		}
		// setFullNameKana
		String fullNameKanaReq = request.getParameter("nameKana");
		if (fullNameKanaReq != null) {
			userInfor.setFullNameKana(fullNameKanaReq);
		}
		/* Start birthday */
		String birthdayYearReq = request.getParameter("birthdayYear");
		if (birthdayYearReq != null) {
			birthdayYear = Integer.parseInt(birthdayYearReq);
		}
		//
		String birthdayMonthReq = request.getParameter("birthdayMonth");
		if (birthdayMonthReq != null) {
			birthdayMonth = Integer.parseInt(birthdayMonthReq);
		}
		//
		String birthdayDayReq = request.getParameter("birthdayDay");
		if (birthdayDayReq != null) {
			birthdayDay = Integer.parseInt(birthdayDayReq);
		}
		if (!"".equals(birthdayYear) && !"".equals(birthdayMonth) && !"".equals(birthdayDay)) {
			birthday = new YearMonthDay(birthdayYear, birthdayMonth, birthdayDay);
		}
		// setBirthday
		userInfor.setBirthday(birthday);
		/* End birthday */
		// email
		String emailReq = request.getParameter("email");
		if (emailReq != null) {
			userInfor.setEmail(emailReq.toString());
		}
		// String tel
		String telReq = request.getParameter("tel");
		if (telReq != null) {
			userInfor.setTel(telReq.toString());
		}
		// String pass
		String passReq = request.getParameter("pass");
		if (passReq != null) {
			userInfor.setPass(passReq.toString());
		}
		// passConfirm
		String passConfirmReq = request.getParameter("passConfirm");
		if (passConfirmReq != null) {
			userInfor.setPassConfirm(passConfirmReq.toString());
		}
		// String codeLevel = "";
		String codeLevelReq = request.getParameter("codeLevel");
		if (codeLevelReq != null) {
			userInfor.setCodeLevel(codeLevelReq.toString());
		}
		/* Start startDate */
		String startYearReq = request.getParameter("startYear");
		if (startYearReq != null) {
			startYear = Integer.parseInt(startYearReq);
		}
		//
		String startMonthReq = request.getParameter("startMonth");
		if (startMonthReq != null) {
			startMonth = Integer.parseInt(startMonthReq);
		}
		//
		String startDayReq = request.getParameter("startDay");
		if (startDayReq != null) {
			startDay = Integer.parseInt(startDayReq);
		}
		if (!"".equals(startYear) && !"".equals(startMonth) && !"".equals(startDay)) {
			startDate = new YearMonthDay(startYear, startMonth, startDay);
		}
		userInfor.setStartDate(startDate);
		/* End startDate */

		/* Start endDate */
		String endYearReq = request.getParameter("endYear");
		if (endYearReq != null) {
			endYear = Integer.parseInt(endYearReq);
		}
		//
		String endMonthReq = request.getParameter("endMonth");
		if (endMonthReq != null) {
			endMonth = Integer.parseInt(endMonthReq);
		}
		//
		String endDayReq = request.getParameter("endDay");
		if (endDayReq != null) {
			endDay = Integer.parseInt(endDayReq);
		}
		if (!"".equals(endYear) && !"".equals(endMonth) && !"".equals(endDay)) {
			endDate = new YearMonthDay(endYear, endMonth, endDay);
		}
		userInfor.setEndDate(endDate);
		/* End endDate */

		// String total
		String totalReq = request.getParameter("total");
		if (totalReq != null) {
			userInfor.setTotal(totalReq.toString());
		}
		/*
		 * End lấy data các hạng mục, set thông tin user vào đối tượng userInfor
		 */

		// Validate sau khi lấy được đối tượng userInfor
		lstError = ValidateUser.validateUserInfor(userInfor);
		/* Xử lý trả lỗi */
		if (lstError.size() > 0) {
			request.setAttribute("lstError", lstError);
			request.setAttribute("userInfor", userInfor);
			RequestDispatcher req = request.getRequestDispatcher(Constant.ADM003);
			req.forward(request, response);

		} else {
			// Không có lỗi validate thì sang doget sang ADM004
			// biến truyền key đánh dấu session
			String userInforToken = Common.sessionToken();
			// set value tương ứng
			session.setAttribute(userInforToken, userInfor);

			response.sendRedirect(Constant.ADD_USER_CONFIRM + "?userInforSession=" + userInforToken + "&accessType="
					+ accessType + "&userId=" + userId);
		}

	}

	/**
	 * Set giá trị default cho màn hình ADM003(Chưa cần xử lý vì chưa thấy miêu
	 * tả trong Req⇒ để sau khi testcase rồi làm)
	 * 
	 * @param request
	 * @param response
	 * @return đối tượng UerInfor
	 */
	private UserInfor setDefaultValue(HttpServletRequest request, HttpServletResponse response) {
		String accountName = "";
		int userId = 0;
		int groupId = 0;
		String groupName = "";
		String fullName = "";
		String fullNameKana = "";
		// Chỗ này có 3 pulldown thì xử lý kiểu nào cho tiện
		int defaultYear = Common.getYearNow();
		int defaultMonth = Common.getMonthNow();
		int defaultDay = Common.getDayNow();

		YearMonthDay birthday = null;
		String email = "";
		String tel = "";
		String pass = "";
		String passConfirm = "";
		YearMonthDay endDate = null;
		YearMonthDay startDate = null;
		String codeLevel = "";
		String nameLevel = "";
		String total = "";
		//
		birthday = new YearMonthDay(defaultYear, defaultMonth, defaultDay);
		endDate = new YearMonthDay(defaultYear + 1, defaultMonth, defaultDay);
		startDate = new YearMonthDay(defaultYear, defaultMonth, defaultDay);
		// tạo đối tượng userInfor cho tầng view
		UserInfor userInfor = new UserInfor(userId, accountName, fullName, fullNameKana, groupName, groupId, birthday,
				email, tel, pass, passConfirm, nameLevel, codeLevel, endDate, startDate, total);
		return userInfor;

	}

	/**
	 * Thực hiện set giá trị cho các hạng mục selectbox ở màn hình ADM003
	 * 
	 * @param request
	 * @param response
	 */
	private void setDataLogic(HttpServletRequest request, HttpServletResponse response) {
		// Lấy session
		HttpSession session = request.getSession();
		//
		MstGroupLogicImpl mstGroupLogicImpl = new MstGroupLogicImpl();
		MstJapanLogicImpl mstJapanLogicImpl = new MstJapanLogicImpl();
		List<MstGroup> listGroup = mstGroupLogicImpl.getAllMstGroup();
		List<MstJapan> listMstJapan = mstJapanLogicImpl.getAllMstJapan();

		// Tạo list year của mục birthday
		List<Integer> listYearBirthday = Common.getListYear(1900, Common.getYearNow());
		// Tạo list year của mục startDate
		List<Integer> listStartYear = Common.getListYear(1900, Common.getYearNow());
		// Tạo list year của mục endDate
		List<Integer> listEndYear = Common.getListYear(1900, Common.getYearNow() + 1);
		// Tạo list tháng, ngày
		List<Integer> listMonth = Common.getListMonth();
		List<Integer> listDay = Common.getListDay();
		// gửi lên session cho view hiển thị pulldown
		session.setAttribute("listGroup", listGroup);
		session.setAttribute("listMstJapan", listMstJapan);
		session.setAttribute("listYearBirthday", listYearBirthday);
		session.setAttribute("listStartYear", listStartYear);
		session.setAttribute("listEndYear", listEndYear);
		session.setAttribute("listMonth", listMonth);
		session.setAttribute("listDay", listDay);
	}

}
