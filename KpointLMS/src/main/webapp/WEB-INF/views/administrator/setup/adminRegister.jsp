<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tile" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script src="${pageContext.request.contextPath}/js/setup/admin.controller.js"></script>
<c:url value="bid=${param.bid}&KEYWORD=${param.KEYWORD}&KEYSTRING=${param.KEYSTRING}" var="linkURL" />
<c:url value="${pageContext.request.contextPath}" var="context" />
<script type="text/javascript"> 
	var message = '${msg}'; 
	var returnUrl = '${url}'; 
	console.log(message);
	if(message != undefined || message != ""){
		if(message == "SUCCESS"){
			alert("SUCCESS");
		}else if(message == "FAIL"){
			alert("FAIL");
		}
	}
</script>
<c:if test="${param.msg eq 'SUCCESS'}">
	<script>
		alert("관리자 등록을 완료 하였습니다.");
		location.href= "adminList.do?${linkURL}&currentPageNo=${param.currentPageNo}";
	</script>
</c:if>
<c:if test="${param.msg eq 'FAIL'}">
	<script>
		alert("다시 등록해주세요.");
	</script>
</c:if>
<div class="catagoryTitle">
	관리자등록
</div>
<c:url value="/administrator/setup/adminMemberInsert.do?${_csrf.parameterName}=${_csrf.token}" var="actionURL" />
<form:form name="regForm" id="regForm"  action="${actionURL}" method="post" enctype="multipart/form-data" accept-charset="utf-8">
<input type="hidden" name="WRITER" value="${username}" />
<input type="hidden" name="bid" value="${param.bid}" />
<input type="hidden" name="currentPageNo" value="${param.currentPageNo}" />
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
<div class="tableBox">
	<table class="inputTbl2">
		<colgroup>
			<col width="150px" />
			<col width="" />
		</colgroup>
		<tr>
			<th colspan="2">관리자등록</th>
		</tr>
		<tr>
			<td class="left">아이디</td>
			<td><input type="text" name="USERID" id="USERID" pattern="7" size="25" class="tblInput" /></td>
		</tr>
		<tr>
			<td class="left">비밀번호</td>
			<td><input type="password" name="USERPW" id="USERPW" size="21" pattern="9" class="tblInput" /></td>
		</tr>
		<tr>
			<td class="left">이름</td>
			<td><input type="text" size="25" name="USERNAME" id="USERNAME" pattern="1" class="tblInput" /></td>
		</tr>
		<tr>
			<td class="left">핸드폰번호</td>
			<td>
				<input type="text" size="25" name="USERTEL" id="USERTEL" pattern="8" class="tblInput" />
			</td>
		</tr>
		<tr>
			<td class="left">이메일</td>
			<td>
				<input type="text" size="25" name="USEREMAIL" id="USEREMAIL" pattern="4" class="tblInput" />
			</td>
		</tr>
	</table>
</div>

<div class="btnGrp">
	<a href="" class="confirm" id="adminRegister">확인</a>
	<a href="adminList.do?${linkURL}&currentPageNo=${param.currentPageNo}" class="cancel">취소</a>
</div>
</form:form>