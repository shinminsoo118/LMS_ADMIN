<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tile" uri="http://tiles.apache.org/tags-tiles" %>
<c:url value="bid=${param.bid}&KEYWORD=${param.KEYWORD}&KEYSTRING=${param.KEYSTRING}&" var="linkURL" />
<div class="catagoryTitle">
	${boardConfig.BOARDNAME}
</div>

<div class="searchBox">
	<form id="boardSearchForm" action="List.do" name="boardSearchForm" method="GET">
	<input type="hidden" name="bid" value="${param.bid}" />
	<div class="searchBtnGrp alignRight">
		<select class="board" name="KEYWORD" id="KEYWORD">
			<option value="">== 검색조건 ==</option>
			<option value="USERNAME" 	${param.KEYWORD == "USERNAME"  ? "selected" : ""}>작성자</option>
			<option value="TITLE"   ${param.KEYWORD == "TITLE"   ? "selected" : ""}>제목</option>
			<option value="CONTENT" ${param.KEYWORD == "CONTENT" ? "selected" : ""}>내용</option>
			<option value="TITLE_CONTENT" ${param.KEYWORD == "TITLE_CONTENT" ? "selected" : ""}>제목+내용</option>
		</select>
		<input type="text" size="30" name="KEYSTRING" id="KEYSTRING" class="tblInput" />
		<a href="" class="dBtn4" id="BoardSearch">검색</a>
	</div>
	</form>
</div>
<tile:insertTemplate template="ListTemplate.jsp" />
<div class="pagingGrp">
	<a href="Reg.do?bid=${param.bid}" class="dBtn3 floatRight">글쓰기</a>
	<a href="List.do?currentPageNo=${pageConfig.currentPageNo - 10}&${linkURL}">이전</a>
	<c:forEach var="i" begin="${pageConfig.startPage}" end="${pageConfig.endPage}">
		<c:if test="${ i == pageConfig.currentPageNo }">
			<strong>${i}</strong>
		</c:if>
		<c:if test="${ i != pageConfig.currentPageNo }">
			<a href="List.do?currentPageNo=${i}&${linkURL}">${i}</a>
		</c:if>
	</c:forEach>
	<a href="List.do?currentPageNo=${pageConfig.currentPageNo + 10}&${linkURL}">다음</a>
</div> 