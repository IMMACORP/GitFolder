<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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

	<!-- Begin vung input-->
	<form action="AddUserInput.do" method="post" name="inputform">
	<input type="hidden" name="userId" value="${userInfor.userId}"></input>
	<input type="hidden" name="accessType" value="${param.accessType}"></input>
	<input type="hidden" name="userInfor" value="${userInfor}"></input>
		<table class="tbl_input" border="0" width="75%" cellpadding="0"
			cellspacing="0">
			<tr>
				<th align="left">
					<div style="padding-left: 100px;">会員情報編集</div>
				</th>
			</tr>
			<!-- Begin: Hiển thị error message -->
			<tr>
				<td class="errMsg">
					<div style="padding-left:120px">
				<c:set var="lstError" value="${lstError}"></c:set>
				<c:if test="${lstError != null}">
					<c:forEach items="${lstError}" var="err">
						${err} </br>
					</c:forEach>
				</c:if>
				&nbsp;
				</div>
				</td>
			</tr>
			<!-- End: Hiển thị error message -->
			<tr>
				<td align="left">
					<div style="padding-left: 100px;">
						<table border="0" width="100%" class="tbl_input" cellpadding="4"
							cellspacing="0">
							<tr>
								<td class="lbl_left"><font color="red">*</font> アカウント名:</td>
								<td align="left"><input class="txBox" type="text" name="userName"
									 value="${fn:escapeXml(userInfor.loginName)}" size="15" onfocus="this.style.borderColor='#0066ff';"
									onblur="this.style.borderColor='#aaaaaa';" /></td>
							</tr>
							<tr>
								<td class="lbl_left"><font color="red">*</font> グループ:</td>
								<td align="left">
								<select name="group_id">
								<option value="0">選択してください</option>
								<c:set var="groupId" value="${userInfor.groupId}"></c:set>
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
							</select>
								<span>&nbsp;&nbsp;&nbsp;</span>
							    </td>
							</tr>
							<tr>
								<td class="lbl_left"><font color="red">*</font> 氏名:</td>
								<td align="left"><input class="txBox" type="text"
									name="fullName" value="${fn:escapeXml(userInfor.fullName)}" size="30"
									onfocus="this.style.borderColor='#0066ff';"
									onblur="this.style.borderColor='#aaaaaa';" /></td>
							</tr>
							<tr>
								<td class="lbl_left">カタカナ氏名:</td>
								<td align="left"><input class="txBox" type="text"
									name="nameKana" value="${fn:escapeXml(userInfor.fullNameKana)}" size="30"
									onfocus="this.style.borderColor='#0066ff';"
									onblur="this.style.borderColor='#aaaaaa';" /></td>
							</tr>
							<tr>
								<td class="lbl_left"><font color="red">*</font> 生年月日:</td>
								<td align="left">
								<select name="birthdayYear">
										<c:set var="listYear" value="${listYearBirthday}"></c:set>
										<c:if test="${listYear != null}">
											<c:forEach items="${listYear}" var="year">
											<c:choose>
												<c:when test="${year == userInfor.birthday.year}">
													<option value="${year}" selected>${year}</option>
												</c:when>
												<c:when test="${year == yearNow && userInfor.birthday == null}">
													<option value="${year}" selected>${year}</option>
												</c:when>
												<c:otherwise>
													<option value="${year}">${year}</option>
												</c:otherwise>
											</c:choose>
								</c:forEach>
							</c:if>
								</select>年 
								<select name="birthdayMonth">
								<c:set var="listMonth" value="${listMonth}"></c:set>
								<c:if test="${listMonth != null}">
									<c:forEach items="${listMonth}" var="month">
										<c:choose>
											<c:when test="${month == userInfor.birthday.month}">
												<option value="${month}" selected>${month}</option>
											</c:when>
											<c:when test="${month == monthNow && userInfor.birthday == null}">
												<option value="${month}" selected>${month}</option>
											</c:when>
											<c:otherwise>
												<option value="${month}">${month}</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</c:if>
						</select>月
						<select name="birthdayDay">
								<c:set var="listDay" value="${listDay}"></c:set>
								<c:if test="${listDay != null}">
									<c:forEach items="${listDay}" var="day">
										<c:choose>
											<c:when test="${userInfor.birthday.day == day}">
												<option value="${day}" selected>${day}</option>
											</c:when>
											<c:when test="${day == dayNow && userInfor.birthday == null}">
												<option value="${day}" selected>${day}</option>
											</c:when>
											<c:otherwise>
												<option value="${day}">${day}</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</c:if>
						</select>日
						</td>
							</tr>
							<tr>
								<td class="lbl_left"><font color="red">*</font> メールアドレス:</td>
								<td align="left"><input class="txBox" type="text"
									name="email" value="${fn:escapeXml(userInfor.email)}" size="30"
									onfocus="this.style.borderColor='#0066ff';"
									onblur="this.style.borderColor='#aaaaaa';" /></td>
							</tr>
							<tr>
								<td class="lbl_left"><font color="red">*</font>電話番号:</td>
								<td align="left"><input class="txBox" type="text"
									name="tel" value="${fn:escapeXml(userInfor.tel)}" size="30"
									onfocus="this.style.borderColor='#0066ff';"
									onblur="this.style.borderColor='#aaaaaa';" /></td>
							</tr>
							<tr>
								<td class="lbl_left"><font color="red">*</font> パスワード:</td>
								<td align="left"><input class="txBox" type="password"
									name="pass" value="${userInfor.pass}" size="30"
									onfocus="this.style.borderColor='#0066ff';"
									onblur="this.style.borderColor='#aaaaaa';" /></td>
							</tr>
							<tr>
								<td class="lbl_left">パスワード（確認）:</td>
								<td align="left"><input class="txBox" type="password"
									name="passConfirm" value="${fn:escapeXml(userInfor.passConfirm)}" size="30"
									onfocus="this.style.borderColor='#0066ff';"
									onblur="this.style.borderColor='#aaaaaa';" /></td>
							</tr>
							<tr>
								<th align="left" colspan="2"><a href="javascript:toggleDetailUserJapan()">日本語能力</a></th>
							</tr>
							<tr class="detailUserJapan" style="display: none;">
								<td class="lbl_left">資格:</td>
								<td align="left">
							<select name="codeLevel">
								<option value="0">選択してください</option>
								<c:set var="codeLevel" value="${userInfor.codeLevel}"></c:set>
								<c:set var="listMstJapan" value="${listMstJapan}"></c:set>
								<c:if test="${listMstJapan != null}">
									<c:forEach items="${listMstJapan}" var="mstJapan">
										<c:if test="${mstJapan.codeLevel == codeLevel}">
											<option value="${mstJapan.codeLevel}" selected>${mstJapan.nameLevel}</option>
										</c:if>
										<c:if test="${mstJapan.codeLevel != codeLevel}">
											<option value="${mstJapan.codeLevel}">${mstJapan.nameLevel}</option>
										</c:if>
									</c:forEach>
								</c:if>
							</select>
						</td>
							</tr>
							<tr class="detailUserJapan" style="display: none;">
						<td class="lbl_left">資格交付日: </td>
						<td align="left">
							<select name="startYear">
								<c:set var="listYear" value="${listStartYear}"></c:set>
								<c:if test="${listYear != null}">
									<c:forEach items="${listYear}" var="year">
										<c:choose>
											<c:when test="${year == userInfor.startDate.year}">
												<option value="${year}" selected>${year}</option>
											</c:when>
											<c:when test="${year == yearNow && userInfor.startDate == null}">
												<option value="${year}" selected>${year}</option>
											</c:when>
											<c:otherwise>
												<option value="${year}">${year}</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</c:if>
						</select>年
						<select name="startMonth">
								<c:set var="listMonth" value="${listMonth}"></c:set>
								<c:if test="${listMonth != null}">
									<c:forEach items="${listMonth}" var="month">
										<c:choose>
											<c:when test="${month == userInfor.startDate.month}">
												<option value="${month}" selected>${month}</option>
											</c:when>
											<c:when test="${month == monthNow && userInfor.startDate == null}">
												<option value="${month}" selected>${month}</option>
											</c:when>
											<c:otherwise>
												<option value="${month}">${month}</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</c:if>
						</select>月
						<select name="startDay">
								<c:set var="listDay" value="${listDay}"></c:set>
								<c:if test="${listDay != null}">
									<c:forEach items="${listDay}" var="day">
										<c:choose>
											<c:when test="${day == userInfor.startDate.day}">
												<option value="${day}" selected>${day}</option>
											</c:when>
											<c:when test="${day == dayNow && userInfor.startDate == null}">
												<option value="${day}" selected>${day}</option>
											</c:when>
											<c:otherwise>
												<option value="${day}">${day}</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</c:if>
						</select>日
						</td>
					</tr>
					<tr class="detailUserJapan" style="display: none;">
						<td class="lbl_left">失効日: </td>
						<td align="left">
							<select name="endYear">
								<c:set var="yearNowEndDate" value="${yearNow + 1}"></c:set>
								<c:set var="listYear" value="${listEndYear}"></c:set>
								<c:if test="${listYear != null}">
									<c:forEach items="${listYear}" var="year">
										<c:choose>
											<c:when test="${year == userInfor.endDate.year}">
												<option value="${year}" selected>${year}</option>
											</c:when>
											<c:when test="${year == (yearNowEndDate) && userInfor.endDate == null}">
												<option value="${year}" selected>${year}</option>
											</c:when>
											<c:otherwise>
												<option value="${year}">${year}</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</c:if>
						</select>年
						<select name="endMonth">
								<c:set var="listMonth" value="${listMonth}"></c:set>
								<c:if test="${listMonth != null}">
									<c:forEach items="${listMonth}" var="month">
										<c:choose>
											<c:when test="${userInfor.endDate.month == month}">
												<option value="${month}" selected>${month}</option>
											</c:when>
											<c:when test="${month == monthNow && userInfor.endDate ==null}">
												<option value="${month}" selected>${month}</option>
											</c:when>
											<c:otherwise>
												<option value="${month}">${month}</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</c:if>
						</select>月
						<select name="endDay">
								<c:set var="listDay" value="${listDay}"></c:set>
								<c:if test="${listDay != null}">
									<c:forEach items="${listDay}" var="day">
										<c:choose>
											<c:when test="${day == userInfor.endDate.day}">
												<option value="${day}" selected>${day}</option>
											</c:when>
											<c:when test="${day == dayNow && userInfor.endDate == null}">
												<option value="${day}" selected>${day}</option>
											</c:when>
											<c:otherwise>
												<option value="${day}">${day}</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</c:if>
						</select>日
						</td>
					</tr>
							<tr class="detailUserJapan" style="display: none;">
								<td class="lbl_left">点数:</td>
								<td align="left"><input class="txBox" type="text"
									name="total" value="${userInfor.total}" size="5"
									onfocus="this.style.borderColor='#0066ff';"
									onblur="this.style.borderColor='#aaaaaa';" /></td>
							</tr>
						</table>
					</div>
				</td>
			</tr>
		</table>
		<div style="padding-left: 100px;">&nbsp;</div>
		<!-- Begin vung button -->
		<div style="padding-left: 45px;">
			<table border="0" cellpadding="4" cellspacing="0" width="300px">
				<tr>
					<th width="200px" align="center">&nbsp;</th>
					<td><input class="btn" type="submit" value="確認" /></td>
					<c:set var="accessType" value="${param.accessType}"></c:set>
						<c:if test="${'add' eq accessType}">
					<td><input class="btn" type="button" value="戻る" onclick="location.href='ListUser.do?accessType=${param.accessType}'" /></td>
						</c:if>
						<c:if test="${'edit' eq accessType}">
					<td><input class="btn" type="button" value="戻る" onclick="location.href='ViewDetailUser.do?accessType=${param.accessType}&userId=${userInfor.userId}'" /></td>
						</c:if>
						<c:if test="${'back' eq accessType}">
					<td><input class="btn" type="button" value="戻る" onclick="location.href='ViewDetailUser.do?accessType=${param.accessType}&userId=${userInfor.userId}'" /></td>
						</c:if>
				</tr>
			</table>
			<!-- End vung button -->
	</form>
	<!-- End vung input -->

	<!-- Begin vung footer -->
	<jsp:include page="footer.jsp" />
	<!-- End vung footer -->
</body>

</html>