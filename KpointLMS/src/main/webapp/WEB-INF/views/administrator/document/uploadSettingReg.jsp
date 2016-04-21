<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tile" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:url value="${pageContext.request.contextPath}" var="context" />

	<div class="catagoryTitle">
		업로드설정
	</div>	
	<div class="tableBox">
	<form>
		<table class="inputTbl2">
			<colgroup>
				<col width="150px" />
				<col width="" />
			</colgroup>
			<tr>
				<th colspan="2">관리자등록</th>
			</tr>
			<tr>
				<td class="left">보드ID</td>
				<td><input type="text" size="70" class="tblInput" /></td>
			</tr>
			<tr>
				<td class="left">서브패스</td>
				<td><input type="text" size="70" class="tblInput" /></td>
			</tr>
			<tr>
				<td class="left">폴더생성</td>
				<td><input type="text" size="70" class="tblInput" /></td>
			</tr>
			<tr>
				<td class="left">작성자</td>
				<td><input type="text" size="70" class="tblInput" /></td>
			</tr>
		</table>
	</form>
	</div>
	<div class="btnGrp">
		<a href="" class="confirm">확인</a>
		<a href="" class="cancel">취소</a>
	</div>