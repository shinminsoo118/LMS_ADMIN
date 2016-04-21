<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/style.css" type="text/css" />
</head>
<body>
	<h2>게시판</h2>
	<form>
		<select name="keyoword">
			<option value="TITLE">제목</option>
			<option value="CONTENT">글내용</option>
			<option value="WRITER">작성자</option>
		</select>
		<input type="text" name="keyword" />
		<input type="submit" value="검색" />
	</form>
	<br/>
	<table>
		<colgroup>
        <col width="10%"/>
        <col width="*"/>
        <col width="15%"/>
        <col width="20%"/>
        <col width="10%"/>
    </colgroup>
		
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회수</th>
		</tr>
		<c:if test="${fn:length(list) > 0 }">
		<c:forEach var="board" items="${list}">
			<tr>
				<td>${board.SEQ}</td>
				<td><a href="view.do" class="listItem">${board.TITLE}</a></td>
				<td>${board.WRITER}</td>
				<td>${board.REGDATE}</td>
				<td>${board.HIT}</td>
			</tr>
		</c:forEach>
		</c:if>
		<c:if test="${fn:length(list) == 0 }">
			<tr colspan="5">
				<td>조회된결과값이 없습니다.</td>
			</tr>
		</c:if>
	</table>
	<br/>
	<div>
		1 2 3 4 5 6 
	</div>
	<br/>
	<div>
		<a href="write.do">글작성</a>
	</div>
</body>
</html>