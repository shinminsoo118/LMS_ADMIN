<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tile" uri="http://tiles.apache.org/tags-tiles" %>
<div class="header">
	<div class="headerContainer">
		<h1><a href="javascript:adminLogo()">로고</a></h1>
		<ul class="adminMenu">
			<li class="setup"><a href="javascript:adminMenu01(1)"><span>환경설정</span></a></li>
			<li class="member"><a href="javascript:adminMenu02(1)"><span>회원.강사관리</span></a></li>
			<li class="lecture"><a href="javascript:adminMenu03(1)"><span>수강.결제</span></a></li>
			<li class="curriculumn"><a href="javascript:adminMenu04(1)"><span>교육과정</span></a></li>
			<li class="document"><a href="javascript:adminMenu05(1)"><span>학습자료</span></a></li>
			<li class="file"><a href="javascript:adminMenu06(1)"><span>학습룸</span></a></li>
			<li class="board"><a href="javascript:adminMenu07(1)"><span>게시판관리</span></a></li>
			<li class="plan"><a href="javascript:adminMenu08(1)"><span>강의계획서</span></a></li>
			<li class="statistic"><a href="javascript:adminMenu09(1)"><span>통계</span></a></li>
		</ul>
	</div>
</div>

<!-- 공통적으로 사용되는 부분을 인크루드함. -->
<tile:insertTemplate template="../inc/common.jsp" />