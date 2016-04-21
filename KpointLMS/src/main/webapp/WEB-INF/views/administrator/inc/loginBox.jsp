<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="loginBox">
	<dl>
		<dt>${pageContext.request.userPrincipal.name}</dt>
		<%-- 
			<dt>${pageContext.request.userPrincipal.name}</dt>
			SpringTaglib 
			<s:authentication property="name" /> ==>스프링 태그라이브러리로 이름가져오기
		 --%>
		<dd>
			<span>접속시간</span>
			<span>2015.11.08(11:20:30)</span>
		</dd>
	</dl>
	<div class="loginBoxBtn">
		<a href="" class="rBoard">회원정보수정</a>
		<!-- 
			${pageContext.request.userPrincipal != null}   ==> 로그인 버튼
			${empty pageContext.request.userPrincipal}     ==> 로그인 버튼
			
			${not empty pageContext.request.userPrincipal} ==> 로그아웃버튼
		-->
		
		<!-- <s:authorize ifAllGranted="ROLE_USER"> -->
			<a href="" class="logout" id="logout">로그아웃</a>
		<!-- </s:authorize> -->
		<form id="logoutForm" action="${pageContext.request.contextPath}/logOut" method="post" style="display:none">
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
	</div>
</div>