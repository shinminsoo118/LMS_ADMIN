<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:scriptlet>
     pageContext.setAttribute("cr", "\r");
     pageContext.setAttribute("lf", "\n");
     pageContext.setAttribute("crlf", "\r\n");
</jsp:scriptlet>
 
<sec:authorize access="hasAnyRole('ROLE_ADMIN' ,'ROLE_SUPERADMIN')" var="roles" method="post" />
<sec:authentication property="principal.username" var="username" scope="request"/>
<c:set var="adminRole" value="${roles}" scope="request"/>
 
 <script>
 $(function(){
 		var adminRole = ${roles};
 		var userName = '${username}';
 		var bid = '${param.bid}';
 	 	var seq = '${param.SEQ}';
 	 	var cPath = '${pageContext.request.contextPath}';
 	 	
 	 	//자바스크립트 변수초기화
 	 	fn_setVariable(cPath, bid, seq, adminRole, userName);
 	})
 </script>