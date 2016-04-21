<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="${pageContext.request.contextPath}/js/member/controller.js"></script>	
<!--<c:url value="bid=${param.bid}&KEYWORD=${param.KEYWORD}&KEYSTRING=${param.KEYSTRING}&" var="linkURL" />-->		

<script>
	//console.log("${param}");
	//console.log("${resResult}");
	var linkURL = "${linkURL}";
	var _csrf = "${_csrf.token}";
	var _csrfParameter = "${_csrf.parameterName}"
</script>
<c:if test="${param.retVal eq 'success'}">
	<script>alert("메일 전송 성공하였습니다.")</script>
</c:if>
<c:if test="${param.retVal eq 'fail'}">
	<script>alert("메일 전송 실패하였습니다.")</script>
</c:if>
<div class="catagoryTitle">
	메일리스트
</div>
<div class="searchBox">
	<form id="mailSearchForm" action="mailList.do" name="mailSearchForm" method="GET">
	<input type="hidden" name="bid" value="${param.bid}" />
	<input type="hidden" name="type" value="${param.type}" />
	<table class="searchTbl">
		<colgroup>
			<col width="150px" />
			<col width="" />
		</colgroup>
		<tr>
			<th>키워드검색</th>
			<td>
			<select name="KEYWORD" id="KEYWORD">
				<option value="">==검색조건==</option>
				<option value="TITLE" ${param.KEYWORD == "TITLE"  ? "selected" : ""}>제목</option>
				<option value="WRITER" ${param.KEYWORD == "WRITER"  ? "selected" : ""}>작성자</option>
			</select>
			<input type="text" size="30" class="tblInput" id="KEYSTRING"  name="KEYSTRING" value= ${param.KEYSTRING != "" ? param.KEYSTRING : "" } >
			</td>
<!--			<th>그룹</th>
 			<td>
				<select name="GROUP" id="GROUP">
					<option value="">==등급검색==</option>
					<option value="COMMON">일반등급 </option>
				</select>
			</td>
 -->		</tr>
		<tr>
			<th>회원가입일</th>
		<!-- 	<td colspan="3"> -->
			<td>
				<input type="text" name="beforeDate" size="20" class="tblInput" value=${param.beforeDate != "" ? param.beforeDate : "" }> <a href="#" class="calendar"></a>
				- 
				<input type="text" name="afterDate" size="20" class="tblInput" value=${param.afterDate != "" ? param.afterDate : "" }> <a href="#" class="calendar"></a>
			</td>
			
		</tr>
	</table>
	<div class="searchBtnGrp">
		<a href="#this" class="confirm" id="mailListSearchBtn">검색</a>
	</div>
	</form>
</div>
<div class="tableBox">
<table class="listTbl">
	<colgroup>
		<col width="5%" />
		<col width="5%" />
		<col width="10%" />
		<col width="10%" />
		<col width="20%" />
		<col width="20%" />
		<col width="" />
		<col width="10%" />
	</colgroup>
	<tr>
		<!-- <th><input type="checkbox" /></th> -->
		<th>번호</th>
		<th>보드ID</th>
		<th>그룹</th>
		<th>제목</th>
		<th>작성자</th>
		<th>등록일</th>
	</tr>
	<c:forEach items="${userList}" var="list" varStatus="status">
		<tr>
			<!-- <td><input type="checkbox" /></td> -->
			<td>${pageConfig.totalListCount - (pageConfig.currentPageNo - 1)* boardConfig.LISTPERPAGE - status.index}</td>
			<td>${list.BOARDID}</td>
			<td>${list.GROUPS}</td>
			<td id="userId"><a href="mailDetail.do?SEQ=${list.SEQ}&currentPageNo=${pageConfig.currentPageNo}&${linkURL}" class="listItem">${list.TITLE}</a></td>
			<td>${list.WRITER}</td>
			<td>${list.REGDATE}</td>
		</tr>
	</c:forEach>
	
</table>
</div>
<div class="pagingGrp">
	<a href="mailRegi.do?bid=${param.bid}&currentPageNo=${pageConfig.currentPageNo}&${linkURL}" class="dBtn3 floatRight">메일작성</a>
	<a href="mailList.do?currentPageNo=${pageConfig.currentPageNo - 10}&${linkURL}">이전</a>
	<c:forEach var="i" begin="${pageConfig.startPage}" end="${pageConfig.endPage}">
		<c:if test="${ i == pageConfig.currentPageNo }">
			<strong>${i}</strong>
		</c:if>
		<c:if test="${ i != pageConfig.currentPageNo }">
			<a href="mailList.do?currentPageNo=${i}&${linkURL}">${i}</a>
		</c:if>
	</c:forEach>
	<a href="mailList.do?currentPageNo=${pageConfig.currentPageNo + 10}&${linkURL}">다음</a>
</div>
