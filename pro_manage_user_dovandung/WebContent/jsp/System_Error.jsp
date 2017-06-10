<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="manageuser.entities.*"%>
<%@page import="manageuser.utils.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp" />
<!-- Begin vung input-->
	<form action="ListUser.do" method="post" name="inputform">
	<table  class="tbl_input"   border="0" width="80%"  cellpadding="0" cellspacing="0" >
		<tr>
			<td align="center" colspan="2">
				<div style="height:50px"></div>
			</td>
		</tr>
		<tr>
			<td align="center" colspan="2">
				<font color = "red">${error}</font>
			</td>
		</tr>
		<tr>
			<td align="center" colspan="2">
				<div style="height:70px"></div>
			</td>
		</tr>
		<tr>
			<td align="center" colspan="2">
				<input class="btn" type="submit" value="OK" onclick=""/>
			</td>
		</tr>
	</table>
	</form>
<!-- End vung input -->

<!-- Begin vung footer -->
<jsp:include page="footer.jsp" />