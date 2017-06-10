<%@page import="manageuser.entities.MstGroup"%>
<%@page import="java.util.ArrayList"%>
<%@page import="manageuser.entities.TblUser"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%-- <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/user.js"></script>
<title>ユーザ管理</title>
</head>
<body>

	<!-- Begin vung header -->
	<jsp:include page="header.jsp" />
	<!-- End vung header -->

	<!-- Begin vung dieu kien tim kiem -->
	<form action="ListUser.do" method="post" name="mainform">
		<input type="hidden" name="accessType" value="fromADM002" />
		<input type="hidden" name="currentPage" value="1" /> 
		<input type="hidden" name="sortFlag" value="" /> 
		<input type="hidden" name="sortFullName" value="ASC" /> 
		<input type="hidden" name="sortCodeLevel" value="DESC" /> 
		<input type="hidden" name="sortEndDate" value="DESC" />
		<table class="tbl_input" border="0" width="100%" cellpadding="0"
			cellspacing="0">
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>会員名称で会員を検索します。検索条件無しの場合は全て表示されます。</td>
			</tr>
			<tr>
				<td width="100%">
					<table class="tbl_input" cellpadding="4" cellspacing="0">
						<tr>
							<td class="lbl_left">氏名:</td>
							<td align="left"><input class="txBox" type="text"
								name="name" value="${fn:escapeXml(name)}" size="20"
								onfocus="this.style.borderColor='#0066ff';"
								onblur="this.style.borderColor='#aaaaaa';" /></td>
							<td></td>
						</tr>
						<tr>
							<td class="lbl_left">グループ:</td>
							<td align="left" width="80px"><select name="group_id">
									<option value="0">全て</option>

									<c:set var="groupId" value="${group_id}"></c:set>
									<c:set var="listGroup" value="${listGroup}"></c:set>
									<c:if test="${listGroup != null}">
										<c:forEach items="${listGroup}" var="group">
											<c:if test="${group.groupId == groupId}">
												<option value="${group.groupId}" selected>${group.groupName}</option>
											</c:if>
											<c:if test="${group.groupId != groupId}">
												<option value="${group.groupId}">${group.groupName}</option>
											</c:if> 
										</c:forEach>
									</c:if>

							</select></td>
						</tr>
						<tr>
						<td class="lbl_left">生年月日:</td>
						<td align="left">
								<select name="birthdayYear">
									<option value=""></option>
										<c:set var="listYear" value="${listYearBirthday}"></c:set>
										<c:if test="${listYear != null}">
											<c:forEach items="${listYear}" var="year">
											<c:choose>
												<c:when test="${year == birthday.year}">
													<option value="${year}" selected>${year}</option>
												</c:when>
												<c:when test="${year == yearNow && birthday == null}">
													<option value="${year}" selected>${year}</option>
												</c:when>
												<c:otherwise>
													<option value="${year}">${year}</option>
												</c:otherwise>
											</c:choose>
								</c:forEach>
							</c:if>
								</select>
								<select name="birthdayMonth">
								<option value=""></option>
								<c:set var="listMonth" value="${listMonth}"></c:set>
								<c:if test="${listMonth != null}">
									<c:forEach items="${listMonth}" var="month">
										<c:choose>
											<c:when test="${month == birthday.month}">
												<option value="${month}" selected>${month}</option>
											</c:when>
											<c:when test="${month == monthNow && birthday == null}">
												<option value="${month}" selected>${month}</option>
											</c:when>
											<c:otherwise>
												<option value="${month}">${month}</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</c:if>
						</select>
						<select name="birthdayDay">
						<option value=""></option>
								<c:set var="listDay" value="${listDay}"></c:set>
								<c:if test="${listDay != null}">
									<c:forEach items="${listDay}" var="day">
										<c:choose>
											<c:when test="${birthday.day == day}">
												<option value="${day}" selected>${day}</option>
											</c:when>
											<c:when test="${day == dayNow && birthday == null}">
												<option value="${day}" selected>${day}</option>
											</c:when>
											<c:otherwise>
												<option value="${day}">${day}</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</c:if>
						</select>
						</td>
						<td align="left"><input class="btn" type="submit" value="検索" />
								<input type="hidden" name="type" value="1"> 
								<input class="btn btn_wider" type="button" value="新規追加" onclick="location.href='AddUserInput.do?accessType=add'"/>
								<input class="btn btn_wider" type="button" value="CSVの生成" onclick="location.href='ExportCSV.do?exportKeySession=${exportKeySession}'"/>
								
								</td>
							</tr>
					</table>
				</td>
			</tr>
		</table>
		<!-- End vung dieu kien tim kiem -->
	</form>
	<!-- Begin vung hien thi danh sach user -->
	
	<table class="tbl_list" border="1" cellpadding="4" cellspacing="0"
		width="80%">

		<c:set var="message" value="${message}"></c:set>

		<c:if test="${message != null}">
			<tr>
				<td class="errMsg">${message}</td>
			</tr>
		</c:if>

		<c:if test="${message == null}">
			<tr class="tr2">
				<th align="center" width="20px">ID</th>
				<th align="left"><c:set var="sortFullname"
						value="${sortFullName}"></c:set>
						 <c:choose>
							<c:when test="${'ASC' eq sortFullName}">
							氏名 <a
									href="javascript:putSortFullNameToInput('DESC',${currentPage},3)">▲▽</a>
							</c:when>
							<c:otherwise>
							氏名 <a
									href="javascript:putSortFullNameToInput('ASC',${currentPage},3)">△▼</a>
							</c:otherwise>
					    </c:choose></th>
				<th align="left">生年月日</th>
				<th align="left">グループ</th>
				<th align="left">メールアドレス</th>
				<th align="left" width="70px">電話番号</th>

				<th align="left"><c:set var="sortCodeLevel"
						value="${sortCodeLevel}"></c:set> 
						<c:choose>
							<c:when test="${'ASC' eq sortCodeLevel}">
							日本語能力  <a
									href="javascript:putSortCodeLevelToInput('DESC',${currentPage},3)">△▼</a>
							</c:when>
							<c:otherwise>
							日本語能力  <a
									href="javascript:putSortCodeLevelToInput('ASC',${currentPage},3)">▲▽</a>
							</c:otherwise>
					   </c:choose></th>

				<th align="left"><c:set var="sortEndDate"
						value="${sortEndDate}"></c:set> <c:choose>
						<c:when test="${'ASC' eq sortEndDate}">
						失効日  <a
								href="javascript:putSortEndDateToInput('DESC',${currentPage},3)">▲▽</a>
						</c:when>
						<c:otherwise>
						失効日  <a
								href="javascript:putSortEndDateToInput('ASC',${currentPage},3)">△▼</a>
						</c:otherwise>
					</c:choose></th>

				<th align="left">点数</th>
			</tr>

			<c:set var="listUserInfor" value="${listUserInfor}"></c:set>
			<c:if test="${listUserInfor != null}">
				<c:forEach items="${listUserInfor}" var="userInfor">
					<tr>
						<td align="right">
							<a href="ViewDetailUser.do?userId=${userInfor.userId}">${userInfor.userId}</a>
						</td>
						<td align="left">${fn:escapeXml(userInfor.fullName)}</td>
						
						<td align="center">
							<c:if test="${userInfor.birthday!=null}">${userInfor.birthday.year}/${userInfor.birthday.month}/${userInfor.birthday.day}
							</c:if>
						</td>
						<td align="left">${userInfor.groupName}</td>
						<td align="left">${userInfor.email}</td>
						<td align="left">${userInfor.tel}</td>
						<td align="left">${userInfor.nameLevel}</td>
						<td align="center">
						<c:if test="${userInfor.endDate.year!=0}">${userInfor.endDate.year}/${userInfor.endDate.month}/${userInfor.endDate.day}
							</c:if>
						</td>
						<c:set var="total" value="${userInfor.total}"></c:set>
						<c:choose>
							<c:when test="${total > 0 }">
								<td align="right">${userInfor.total}</td>
							</c:when>

							<c:otherwise>
								<td></td>
							</c:otherwise>
						</c:choose>
					</tr>
				</c:forEach>
			</c:if>
		</c:if>
	</table>
	<!-- End vung hien thi danh sach user -->
	<!-- Begin vung paging -->
	<table>
		<c:if test="${fn:length(listPage) > 1}">
			<tr>
				<c:if test="${leftNext!=0}">
					<td><a
						href="javascript:putToInput(${leftNext},'${sortFlag}','${sortFullName}','${sortCodeLevel}','${sortEndDate}',3)"><<</a></td>
				</c:if>
				<c:forEach items="${listPage}" var="page">
					<td class="lbl_paging">
						<a
							<c:choose>
						    	<c:when test="${currentPage == page}">
						    		class="current_paging"
						    	</c:when>
								<c:otherwise>
									href="javascript:putToInput(${page},'${sortFlag}','${sortFullName}','${sortCodeLevel}','${sortEndDate}',3)"
								</c:otherwise>
							</c:choose>
							>${page}
						</a>
					</td>
				</c:forEach>
				<c:if test="${rightNext!=0}">
					<td>
						<a href="javascript:putToInput(${rightNext},'${sortFlag}','${sortFullName}','${sortCodeLevel}','${sortEndDate}',3)">>></a>
					</td>
				</c:if>
			</tr>
		</c:if>
	</table>
	<!-- End vung paging -->


	<!-- Begin vung footer -->
	<jsp:include page="footer.jsp" />
	<!-- End vung footer -->

</body>

</html>