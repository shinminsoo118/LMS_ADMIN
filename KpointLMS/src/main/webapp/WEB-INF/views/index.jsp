<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Knowledgepoint LMS System</title>
<!--[if lt IE 9]> 
<script src="http://ie7-js.googlecode.com/svn/version/2.1(beta4)/IE9.js"></script>
<![endif]-->

<link rel="stylesheet" href="./css/lms.css" type="text/css" />
<link rel="stylesheet" href="./css/index.css" type="text/css" />
<script src="./js/lib/jquery-1.11.2.min.js"></script>
<script src="./js/lib/jquery.placeholder.min.js"></script>
<script src="./js/common_valid.js"></script>
<script src="./js/index.controller.js"></script>
</head>
<body>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
${loginInfo}
<c:if test="${param.error != null }">
     <script>
     	viewLogin();
     </script>
</c:if>

<div class="dim" id="dim">
</div>
<security:authorize access="hasAnyRole('ROLE_SUPERADMIN','ROLE_ADMIN','ROLE_USER','ROLE_TEACHER', 'ROLE_STUDENT')">
	<security:authentication property="principal.username" var="check" />
</security:authorize>

<c:choose>
	<c:when test="${empty check}">
		<ul class="indexMenu" id="indexMenu">
			<li class="indexLecture"><a href="" ><span>Lecture</span></a></li>
			<li class="indexLogin"><a href="login"><span>Login</span></a></li>
			<li class="indexRegister"><a href="join"><span>Register</span></a></li>
		</ul>
	</c:when>
	<c:otherwise>
		<ul class="indexMenu" id="loginIndexMenu">
			<security:authorize access="hasAnyRole('ROLE_STUDENT', 'ROLE_TEACHER', 'ROLE_USER')">
				<li class="indexLecture"><a href="${pageContext.request.contextPath}/lecture/index.do" ><span>Lecture</span></a></li>
			</security:authorize>
			<security:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERADMIN')">
				<li class="indexLecture"><a href="${pageContext.request.contextPath}/administrator/index.do" ><span>Admin</span></a></li>
			</security:authorize>
			<li class="indexLogin"><a href="logout" id="logout"><span>LogOut</span></a></li>
			<li class="indexRegister"><a href="edit"><span>Edit Info</span></a></li>
		</ul>
		<!-- 로그아웃을 위한 부분 -->
		<c:url value="/logout"  var="logOutURL"/>
		<form id="logoutForm" action="${logOutURL}" method="post" style="display:none">
		    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		</form>
		
		<script>
		$(document).ready(function() {
	        $('#logout').click(function() {
	            $('#logoutForm').submit();
	            return false;
	        });
		});
		</script>
	</c:otherwise>
</c:choose>
<p style="color:#fff; z-index:2000; position:absolute;"> 이유 : ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message }
	aaaaa<security:authentication property="principal"/>
</p>
                  <c:remove scope="session" var="SPRING_SECURITY_LAST_EXCEPTION" />
<div class="txtBox"></div>
<div class="login" id="login">
	<c:url var="loginUrl" value="/login" />
	<form action="${loginUrl}" method="post" onsubmit="return loginCheck()">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	<div class="indexTitle loginTitle">
		<span>
			<c:if test="${param.error != null }">
        		<h4>- 회원정보가 일치하지 않습니다.</h4>
        	</c:if>
		</span>
	</div>
	<div class="indexContent">
		<ul class="indexList">
			<li><label for="userid">아이디</label><input type="text" name="username" id="userid" placeholder="아이디를 입력해 주세요." /></li>
			<li><label for="userpw" class="password">비밀번호</label><input type="password" name="password" id="userpw" placeholder="비밀번호를 입력해 주세요."/></li>
			<li class="textLink">
				<p class="left"><input type="checkbox" id="idSave" /> <label class="idSave" for="idSave">아이디 저장</label></p>
				<p class="right"><a href="" class="idpwBtn">아이디. 비밀번호 찾기</a></p>
			</li>
		</ul>
		<div class="indexBtnGrp">
			<!-- <a href="" class="confirm">OK</a> -->
			<input type="submit" value="OK" />
			<a href="" class="cancel" id="LoginCancel">CANCEL</a>
		</div>
	</div>
	</form>
	
</div>
<c:url value="/register.do" var="registerURL" />
<form:form action="${registerURL}" name="registerForm" id="registerForm" method="post" commandName="memberVo">
<div class="join" id="join">
	<div class="indexTitle joinTitle">
		<span>
			<h4><form:errors path="*" cssClass="error" /></h4>
		</span>
	</div>
	<div class="indexContent">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		<ul class="indexList">
			<li>
				<label for="regid">아이디</label>
				<form:input path="USERID" class="regi_input" id="regid" pattern="7" placeholder="아이디를 입력해 주세요." value="${memberVo.USERID}" />
				<%-- <form:errors path="USERID" cssClass="error"/> --%>
			</li>
			<li>
				<label for="regname">이름</label>
				<form:input path="USERNAME" id="regname" class="regi_input" pattern="1" placeholder="이름을 입력해 주세요." value="${memberVo.USERNAME}"/>
				<%-- <form:errors path="USERNAME" cssClass="error"/> --%>
			</li>
			<li>
				<label for="regpw">비밀번호</label>
				<form:password path="USERPW" id="regpw" class="regi_input" pattern="9" placeholder="비밀번호를 입력해 주세요." />
				<%-- <form:errors path="USERPW" cssClass="error"/> --%>
			</li>
			<li>
				<label for="regtel">전화번호</label>
				<form:input path="USERTEL" id="regtel" class="regi_input" pattern="8" placeholder="핸드폰번호를 입력해 주세요." value="${memberVo.USERTEL}"/>
				<%-- <form:errors path="USERTEL" cssClass="error"/> --%>
			</li>
			<li>
				<label for="regemail">이메일</label>
				<form:input path="USEREMAIL"  id="regemail" class="regi_input" pattern="4" placeholder="이메일을 입력해 주세요." value="${memberVo.USEREMAIL}"/>
				<%-- <form:errors path="USEREMAIL" cssClass="error"/> --%>
			</li>
		</ul>
		<div class="indexBtnGrp">
			<a href="" class="confirm" id="Register">OK</a>
			<a href="" class="cancel" id="RegisterCancel">CANCEL</a>
		</div>
	
	</div>
</div>
</form:form>
</body>
</html>