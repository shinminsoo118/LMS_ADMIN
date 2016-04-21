<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<script src="${pageContext.request.contextPath}/js/member/controller.js"></script>
<!--<c:url value="bid=${param.bid}&type=${param.type}&KEYWORD=${param.KEYWORD}&KEYSTRING=${param.KEYSTRING}&" var="linkURL" />-->		

<script>
	console.log("${param}");
	var linkURL = "${linkURL}";
	var _csrf = "${_csrf.token}";
	var _csrfParameter = "${_csrf.parameterName}"
</script>
<div class="catagoryTitle">
		SMS 리스트
	</div>
	<div class="searchBox">
		<form id="smsListSearchForm" action="smsList.do" name="smsListSearchForm" method="GET">
		<input type="hidden" name="bid" value="${param.bid}" />
		<input type="hidden" name="type" value="${param.type}" />
		<table class="searchTbl">
			<colgroup>
				<col width="100px" />
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
			</tr>
			<tr>
			<th>회원가입일</th>
			<td>
				<input type="text" name="beforeDate" size="20" class="tblInput" value=${param.beforeDate != "" ? param.beforeDate : "" }> <a href="#" class="calendar"></a>
				- 
				<input type="text" name="afterDate" size="20" class="tblInput" value=${param.afterDate != "" ? param.afterDate : "" }> <a href="#" class="calendar"></a>
			</td>
			
		</tr>
		</table>
		<div class="searchBtnGrp">
			<a href="#this" class="confirm" id="smsListSearchBtn">검색</a>
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
				<th>번호</th>
				<th>보드ID</th>
				<th>그룹</th>
				<th>제목</th>
				<th>작성자</th>
				<th>등록일</th>
			</tr>
			<c:forEach items="${smsList}" var="list" varStatus="status">
				<tr>
					<!-- <td><input type="checkbox" /></td> -->
					<td>${pageConfig.totalListCount - (pageConfig.currentPageNo - 1)* boardConfig.LISTPERPAGE - status.index}</td>
					<td>${list.BOARDID}</td>
					<td>${list.GROUPS}</td>
					<td id="userId"><a href="smsDetail.do?SEQ=${list.SEQ}&currentPageNo=${pageConfig.currentPageNo}&${linkURL}" class="listItem">${list.TITLE}</a></td>
					<td>${list.WRITER}</td>
					<td>${list.REGDATE}</td>
				</tr>
			</c:forEach>
			
		</table>
	</div>
	<div class="pagingGrp">
		<a href="smsRegister.do?bid=${param.bid}&currentPageNo=${pageConfig.currentPageNo}&${linkURL}" class="dBtn3 floatRight">SMS 보내기</a>
		<a href="smsList.do?currentPageNo=${pageConfig.currentPageNo - 10}&${linkURL}">이전</a>
		<c:forEach var="i" begin="${pageConfig.startPage}" end="${pageConfig.endPage}">
			<c:if test="${ i == pageConfig.currentPageNo }">
				<strong>${i}</strong>
			</c:if>
			<c:if test="${ i != pageConfig.currentPageNo }">
				<a href="smsList.do?currentPageNo=${i}&${linkURL}">${i}</a>
			</c:if>
		</c:forEach>
		<a href="smsList.do?currentPageNo=${pageConfig.currentPageNo + 10}&${linkURL}">다음</a>
	</div>
