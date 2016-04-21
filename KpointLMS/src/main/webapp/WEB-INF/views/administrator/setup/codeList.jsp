<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tile" uri="http://tiles.apache.org/tags-tiles" %>
<script src="${pageContext.request.contextPath}/js/setup/code.controller.js"></script>
<c:url value="bid=${param.bid}&KEYWORD=${param.KEYWORD}&KEYSTRING=${param.KEYSTRING}&" var="linkURL" />		
<div class="catagoryTitle">
	${boardConfig.BOARDNAME}
</div>
<div class="searchBox">
	<form id="codeSearchForm" action=codeList.do name="codeSearchForm" method="GET">
	<input type="hidden" name="bid" value="${param.bid}" />
	<input type="hidden" name="type" value="${param.type}" />
	<table class="searchTbl">
		<colgroup>
			<col width="100px" />
			<col width="" />
			<%-- <col width="80px" />
			<col width="30%" />
			<col width="50px" /> --%>
		</colgroup>
		<tr>
			<th>키워드검색</th>
			<td>
				<select name="KEYWORD" id="KEYWORD" >
					<option value="">==검색조건==</option>
					<option value="CODE" ${param.KEYWORD == "CODE"  ? "selected" : ""}>상품코드</option>
					<option value="PRONAME" ${param.KEYWORD == "PRONAME"  ? "selected" : ""}>교육명</option>
					<option value="WRITER" ${param.KEYWORD == "WRITER"  ? "selected" : ""}>작성자</option>
				</select>
				<input type="text" size="30" class="tblInput" id="KEYSTRING" name="KEYSTRING" value= ${param.KEYSTRING != "" ? param.KEYSTRING : "" } >
			</td>
<!-- 			<th>등록일</th>
			<td>
				<input type="text" size="20" class="tblInput" /> <a href="" class="calendar"></a>
				- 
				<input type="text" size="20" class="tblInput" /> <a href="" class="calendar"></a>
			</td>
			<td>
				<a href="" class="dBtn4">검색</a>
			</td> -->
		</tr>
		<tr>
			<th>회원가입일</th>
			<td colspan="3">
				<input type="text" name="beforeDate" size="20" class="tblInput" value=${param.beforeDate != "" ? param.beforeDate : "" }> <a href="#" class="calendar"></a>
				- 
				<input type="text" name="afterDate" size="20" class="tblInput" value=${param.afterDate != "" ? param.afterDate : "" }> <a href="#" class="calendar"></a>
			</td>
			
		</tr>		
	</table>
	<div class="searchBtnGrp">
		<a href="" class="confirm" id="codeListSearchBtn">검색</a>
	</div>
	</form>
</div>
<tile:insertTemplate template="CodeListTemplate.jsp" />
<div class="pagingGrp">
	<a href="codeRegister.do?bid=${param.bid}&currentPageNo=${pageConfig.currentPageNo}&${linkURL}" class="dBtn3 floatRight">상품코드등록</a>
	<a href="codeList.do?currentPageNo=${pageConfig.currentPageNo - 10}&${linkURL}">이전</a>
	<c:forEach var="i" begin="${pageConfig.startPage}" end="${pageConfig.endPage}">
		<c:if test="${ i == pageConfig.currentPageNo }">
			<strong>${i}</strong>
		</c:if>
		<c:if test="${ i != pageConfig.currentPageNo }">
			<a href="codeList.do?currentPageNo=${i}&${linkURL}">${i}</a>
		</c:if>
	</c:forEach>
	<a href="codeList.do?currentPageNo=${pageConfig.currentPageNo + 10}&${linkURL}">다음</a>
</div> 
