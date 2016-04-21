<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tile" uri="http://tiles.apache.org/tags-tiles" %>
<c:url value="bid=${param.bid}&KEYWORD=${param.KEYWORD}&KEYSTRING=${param.KEYSTRING}&" var="linkURL" />
<div class="catagoryTitle">
	학습자료 목록
</div>
<%-- <div class="searchBox">
	<table class="searchTbl">
		<colgroup>
			<col width="150px" />
			<col width="" />
		</colgroup>
		<tr>
			<th>키워드검색</th>
			<td>
			<select>
				<option>== 통합검색 ==</option>
			</select>
			<select>
				<option>== 그룹검색 ==</option>
			</select>
			<input type="text" size="30" class="tblInput" /> <a href="" class="dBtn4">검색</a>
			</td>
		</tr>
	</table>
	
</div> --%>
<div class="searchBox">
	<form id="boardSearchForm" action="documentList.do" name="boardSearchForm" method="GET">
	<input type="hidden" name="bid" value="${param.bid}" />
	<table class="searchTbl">
		<colgroup>
			<col width="150px" />
			<col width="" />
		</colgroup>
		<tr>
			<th>키워드검색</th>
			<td>
			<select class="board" name="KEYWORD" id="KEYWORD">
				<option value="">== 검색조건 ==</option>
				<option value="WRITER" 	${param.KEYWORD == "WRITER"  ? "selected" : ""}>작성자</option>
				<option value="TITLE"   ${param.KEYWORD == "TITLE"   ? "selected" : ""}>제목</option>
				<option value="CONTENT" ${param.KEYWORD == "CONTENT" ? "selected" : ""}>내용</option>
				<option value="TITLE_CONTENT" ${param.KEYWORD == "TITLE_CONTENT" ? "selected" : ""}>제목+내용</option>
			</select>
			<select>
				<option>== 그룹검색 ==</option>
			</select>
			<input type="text" size="30" name="KEYSTRING" id="KEYSTRING" class="tblInput" />
			<a href="" class="dBtn4" id="DocSearch">검색</a>
			</td>
		</tr>
	</table>
	</form>
</div>
<div class="tableBox">
<tile:insertTemplate template="ListTemplate.jsp" />
</div>
<div class="pagingGrp">
	<a href="documentReg.do?bid=${param.bid}" class="dBtn3 floatRight">학습자료 올리기</a>
	<a href="documentList.do?currentPageNo=${pageConfig.currentPageNo - 10}&${linkURL}">이전</a>
	<c:forEach var="i" begin="${pageConfig.startPage}" end="${pageConfig.endPage}">
		<c:if test="${ i == pageConfig.currentPageNo }">
			<strong>${i}</strong>
		</c:if>
		<c:if test="${ i != pageConfig.currentPageNo }">
			<a href="documentList.do?currentPageNo=${i}&${linkURL}">${i}</a>
		</c:if>
	</c:forEach>
	<a href="documentList.do?currentPageNo=${pageConfig.currentPageNo + 10}&${linkURL}">다음</a>
</div>
