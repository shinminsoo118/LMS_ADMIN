<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="${pageContext.request.contextPath}/js/setup/admin.controller.js"></script>
<%-- <script src="${pageContext.request.contextPath}/js/member/controller.js"></script> --%>
<script>
	console.log('${result}');
	console.log('${memberUpdateVo}')
</script>
	<div class="catagoryTitle">
		사용자정보상세보기
	</div>
<div class="tableBox">
	<c:url value="memberUpdate.do?${_csrf.parameterName}=${_csrf.token}" var="actionURL" />
	<form:form id="memberUpdateForm" action="${actionURL}" commandName="memberUpdateVo" enctype="multipart/form-data">
		<c:set var="hasError"><form:errors path="*" cssClass="errorblock" element="div" /></c:set>
		<input type="hidden" name="USERID" value=${result.USERID } />
		<input type="hidden" name="type" value=${param.type } />
		<table class="inputTbl2">
			<colgroup>
				<col width="150px" />
				<col width="" />
			</colgroup>
			<tr>
				<th colspan="2">사용자정보</th>
			</tr>
			<tr>
				<td class="left">아이디</td>
				<td>${result.USERID }</td>
			</tr>
			<tr>
				<td class="left">이름</td>
				<td>
				<c:choose>
					<c:when test="${empty hasError }">
						<form:input type="text" class="tblInput" path="username" value="${result.USERNAME }" />
					</c:when>
					<c:otherwise>
						<form:input type="text" class="tblInput" path="username" /><form:errors path="username" cssClass="error" />
					</c:otherwise>
				</c:choose>
				</td>
				
			</tr>
			<tr>
				<td class="left">성별</td>
				<td>
					<form:radiobutton path="usersex" value="남" checked='${result.USERSEX == "남" ? "checked" : "" }' /> 남자 
					<form:radiobutton path="usersex" value="여" checked='${result.USERSEX == "여" ? "checked" : "" }' /> 여자 
				</td>
				<form:errors path="usersex" cssClass="error" />
			<tr>
				<td class="left">E-MAIL</td>
				<td>
				<c:choose>
					<c:when test="${empty hasError }">
						<form:input type="text" class="tblInput" path="useremail" value="${result.USEREMAIL }" />
					</c:when>
					<c:otherwise>
						<form:input type="text" class="tblInput" path="useremail" /><form:errors path="useremail" cssClass="error" />
					</c:otherwise>
				</c:choose>
				</td>
				
			</tr>
			<tr>
				<td class="left">전화번호</td>
				<td>
				<c:choose>
					<c:when test="${empty hasError }">
						<form:input type="text" class="tblInput" path="usertel" value="${result.USERTEL }" />
					</c:when>
					<c:otherwise>
						<form:input type="text" class="tblInput" path="usertel" /><form:errors path="usertel" cssClass="error" />
					</c:otherwise>
				</c:choose>
				</td>
				
			</tr>
			
			<tr>
				<td class="left">회원등급</td>
				<td>
					<form:select path="userrole" style="width:20%">
						<option value="ROLE_STUDENT" ${result.USERROLE == "ROLE_STUDENT" ? "selected" : ""}>ROLE_STUDENT</option>
						<option value="ROLE_ADMIN" ${result.USERROLE == "ROLE_ADMIN" ? "selected" : ""}>ROLE_ADMIN</option>
					</form:select>
					<form:errors path="userrole" cssClass="error" />
				</td>
			</tr>
			
			<tr>
				<td class="left">학습그룹</td>
				<td>학습 그룹이 없습니다.</td>
			</tr>
			<tr>
				<td class="left">회원가입일</td>
				<td>${result.USERREGDATE}</td>
			</tr>
			<tr>
				<td class="left">주소</td>
				<td>
				<c:choose>
					<c:when test="${empty hasError }">
						<form:input type="text" class="tblInput" path="useraddr" value="${result.USERADDR }" />
					</c:when>
					<c:otherwise>
						<form:input type="text" class="tblInput" path="useraddr" /><form:errors path="useraddr" cssClass="error" />
					</c:otherwise>
				</c:choose>
				</td>
				
			</tr>
			<tr>
				<td class="left">유저이미지</td>
				<td>
					<img id="profileImg" src='${pageContext.request.contextPath}/upload/memberIcon/${result.USERIMG }' alt="profileImg" /> 
					<input id="profileImgFile" type="file" name="file" value="${result.USERIMG }" />
					<input id="profileImgFileName" type="hidden" name="USERIMG" value=${result.USERIMG } />
				</td>
			</tr>
		</table>
	</form:form>
</div>
<div class="btnGrp">
	<a href="javascript:fn_memberUpdate()" id="memberConfirm">확인</a>
	<a href="javascript:history.back()" class="update">취소</a>
</div>


		