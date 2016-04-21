<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<!DOCTYPE html>
<html>
<head>
<meta name="_csrf" content="${_csrf.token}"/>
<!-- default header name is X-CSRF-TOKEN -->
<meta name="_csrf_header" content="${_csrf.headerName}"/>
<meta charset="utf-8">
<title>Knowledgepoint LMS System</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/lms.css" type="text/css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css" type="text/css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-ui.min.css" type="text/css" />
<script src="${pageContext.request.contextPath}/js/lib/jquery-1.11.2.min.js"></script>
<%-- <script src="${pageContext.request.contextPath}/js/lib/jquery.validate.min.js"></script>
<script src="${pageContext.request.contextPath}/js/lib/tmpl.min.js"></script>
<script src="${pageContext.request.contextPath}/js/lib/additional-methods.min.js"></script>
<script src="${pageContext.request.contextPath}/js/admin.message.js"></script> --%>
<script src="${pageContext.request.contextPath}/js/admin.menu.js"></script>
<script src="${pageContext.request.contextPath}/js/common.js"></script>
<script src="${pageContext.request.contextPath}/js/lib/jquery-ui.min.js"></script>
</head>
<body>
	<%-- <form id="commonForm" name="commonForm">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	</form> --%>
	<tiles:insertAttribute name="header" />
	<div class="content">
		<div class="contentContainer">
			<div class="contentLeft">
				<tiles:insertAttribute name="loginBox" />
				<tiles:insertAttribute name="leftMenu" />
			</div>
			<!-- Content부분 -->
			<div class="contentRight">
				<tiles:insertAttribute name="content" />
				<%-- tiles:insertAttribute name="content" /> --%>
			</div>	
		</div>
	</div>
	<tiles:insertAttribute name="footer" />
</body>
</html>