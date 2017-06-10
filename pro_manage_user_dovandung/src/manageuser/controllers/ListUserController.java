/**
 * Copyright(C) 2017 Luvina SoftWare
ListUserController.java, Jan 19, 2017 DoVanDung
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
import manageuser.entities.UserInfor;
import manageuser.entities.YearMonthDay;
import manageuser.logics.impl.MstGroupLogicImpl;
import manageuser.logics.impl.TblUserLogicImpl;
import manageuser.utils.Common;
import manageuser.utils.Constant;
import manageuser.utils.MessageProperties;

/**
 * Điều khiển lấy listUser cho ADM002 Servlet implementation class
 * ListUserController
 */
public class ListUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListUserController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// tối ưu src code, khi cùng 1 xử lý với doPost
		doPost(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Lấy session
		HttpSession session = request.getSession();

		/* Khai báo các đối tượng */
		// Tạo list UserInfor để lưu dữ liệu lấy từ database
		List<UserInfor> listUserInfor = new ArrayList<UserInfor>();
		// Khai báo đối tượng khác
		TblUserLogicImpl tblUserLogicImpl = new TblUserLogicImpl();
		List<MstGroup> listGroup = new ArrayList<MstGroup>();
		MstGroupLogicImpl mstGroupLogicImpl = new MstGroupLogicImpl();

		// tạo biến lưu list các page
		List<Integer> listPage = new ArrayList<Integer>();

		// biến nhận biết kiểu vào MH ADM002
		String accessType = "";

		/* Khai báo các parameter sẽ dùng trong hàm getListUser */
		// Tạo biến lưu giá trị fullName
		String fullName = "";
		// Tạo biến lưu giá trị GroupId
		int groupId = 0;
		// đổ vào listGroup
		listGroup = mstGroupLogicImpl.getAllMstGroup();
		// Tạo biến lưu thông tin tìm kiếm ngày sinh
		YearMonthDay birthdaySearch = null;
		int birthdayYear = 0;
		int birthdayMonth = 0;
		int birthdayDay = 0;
		// Đổ vào pulldown list birthday
		List<Integer> listYearBirthday = Common.getListYear(1900, Common.getYearNow());
		List<Integer> listMonth = Common.getListMonth();
		List<Integer> listDay = Common.getListDay();

		/* Tạo biến lưu các đk sort trên bảng user */
		String sortFlag = "";
		String sortFullName = "ASC";
		String sortCodeLevel = "DESC";
		String sortEndDate = "DESC";

		// Biến lưu message cho kết quả tìm kiếm
		String message = null;

		/* Tạo biến cho việc phân trang format như sau: <<xyz>> */
		// Tạo biến lưu page hiện tại
		int currentPage = 0;
		// Lấy số user giới hạn hiển thị trên 1 page
		int limit = Common.getLimit();
		// giá trị offset
		int offset = 0;
		// giá trị của << chỗ phân trang
		int leftNext = 0;
		// giá trị của >> chỗ phân trang
		int rightNext = 0;

		/*
		 * Lấy dữ liệu từ form của ADM002 để hiển thị kết quả tìm kiếm, sort,
		 * chuyển trang
		 */
		// biến nhận biết kiểu vào MH ADM002
		String accessTypeRequest = request.getParameter("accessType");
		if (accessTypeRequest != null) {
			accessType = accessTypeRequest.toString();
		}
		// Nếu là từ mh adm002
		if ("fromADM002".equals(accessType)) {
			// lấy group id
			String groupIdRequest = request.getParameter("group_id");
			if (groupIdRequest != null) {
				groupId = Integer.parseInt(groupIdRequest.toString());
			}
			// lấy fullname
			String nameRequest = request.getParameter("name");
			if (nameRequest != null) {
				fullName = nameRequest.toString();
			}
			// lấy thông tin ngày sinh
			String birthdayYearRequest = request.getParameter("birthdayYear");
			if (!"".equals(birthdayYearRequest)) {
				birthdayYear = Integer.parseInt(birthdayYearRequest);

			}
			String birthdayMonthRequest = request.getParameter("birthdayMonth");
			if (!"".equals(birthdayMonthRequest)) {
				birthdayMonth = Integer.parseInt(birthdayMonthRequest);
			}
			String birthdayDayRequest = request.getParameter("birthdayDay");
			if (!"".equals(birthdayDayRequest)) {
				birthdayDay = Integer.parseInt(birthdayDayRequest);

			}
			// cho thông tin ngày sinh vào đối tượng birthday
			if (birthdayYear != 0 && birthdayMonth != 0 && birthdayDay != 0) {
				birthdaySearch = new YearMonthDay(birthdayYear, birthdayMonth, birthdayDay);
			}
			// Nếu là khi chọn add user rồi back về ADM002
		} else if ("add".equals(accessType) || "3".equals(accessType) || "edit".equals(accessType)) {
			// Lấy thông tin từ session
			groupId = (int) session.getAttribute("group_id");
			fullName = session.getAttribute("name").toString();
			birthdaySearch = (YearMonthDay) session.getAttribute("birthday");
		}
		/* lấy parameter cho phân trang */
		// lay bien flag
		String sortFlagRequest = request.getParameter("sortFlag");
		if (sortFlagRequest != null) {
			sortFlag = sortFlagRequest.toString();
		}
		// lay sortFullName
		String sortFullNameRequest = request.getParameter("sortFullName");
		if (sortFullNameRequest != null) {
			sortFullName = sortFullNameRequest.toString();
		}
		// lay sortCodeLevel
		String sortCodeLevelRequest = request.getParameter("sortCodeLevel");
		if (sortCodeLevelRequest != null) {
			sortCodeLevel = sortCodeLevelRequest.toString();
		}
		// lay sortEndDate
		String sortEndDateRequest = request.getParameter("sortEndDate");
		if (sortEndDateRequest != null) {
			sortEndDate = sortEndDateRequest.toString();
		}
		/* Lấy biến page */
		String currentPageStr = request.getParameter("currentPage");
		if (currentPageStr == null) {
			currentPage = 1;
		} else {
			currentPage = Integer.parseInt(currentPageStr.toString());
		}

		/* Get input cho table user */
		// lay tong so record
		int totalRecords = tblUserLogicImpl.getTotalUsers(groupId, fullName, birthdaySearch);
		// Phan trang
		leftNext = Common.getLeftNext(currentPage);
		rightNext = Common.getRightNext(totalRecords, limit, currentPage);
		listPage = Common.getListPaging(totalRecords, limit, currentPage);
		// lay offset
		offset = Common.getOffset(currentPage, limit);
		// lấy list user
		listUserInfor = tblUserLogicImpl.getListUsers(offset, limit, groupId, fullName, birthdaySearch, sortFlag,
				sortFullName, sortCodeLevel, sortEndDate);
		// Trường hợp ko có user nào hiện message thông báo
		if (listUserInfor.size() == 0) {
			message = MessageProperties.getData("MSG005");
		}

		/* Đưa data lên view */
		// xu ly escape trước khi đưa lên jsp; hoặc có thể dùng hàm escapeXML
		// của jstl trực tiếp trên jsp
		// fullName = Common.escapeHtml(fullName);
		// dua du lieu len view
		request.setAttribute("listUserInfor", listUserInfor);
		/* cho len session de export csv */
		// set only key on request
		String exportKeySession = Common.getIndexSessionByTime();
		// gửi key theo request, gửi value theo session
		request.setAttribute("exportKeySession", exportKeySession);
		session.setAttribute(exportKeySession, listUserInfor);
		// set attribute để jsp lấy
		request.setAttribute("message", message);

		request.setAttribute("name", fullName);
		session.setAttribute("name", fullName);
		request.setAttribute("listGroup", listGroup);
		request.setAttribute("group_id", groupId);
		session.setAttribute("group_id", groupId);
		request.setAttribute("listYearBirthday", listYearBirthday);
		session.setAttribute("listYearBirthday", listYearBirthday);
		request.setAttribute("listMonth", listMonth);
		request.setAttribute("listDay", listDay);
		request.setAttribute("birthday", birthdaySearch);
		session.setAttribute("birthday", birthdaySearch);

		request.setAttribute("sortFlag", sortFlag);
		request.setAttribute("sortFullName", sortFullName);
		request.setAttribute("sortCodeLevel", sortCodeLevel);
		request.setAttribute("sortEndDate", sortEndDate);

		request.setAttribute("currentPage", currentPage);
		session.setAttribute("currentPage", currentPage);
		request.setAttribute("listPage", listPage);
		request.setAttribute("leftNext", leftNext);
		request.setAttribute("rightNext", rightNext);

		// Forward request
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(Constant.ADM002);
		requestDispatcher.forward(request, response);
	}

}
