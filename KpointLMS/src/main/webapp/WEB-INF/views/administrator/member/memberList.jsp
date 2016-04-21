<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="${pageContext.request.contextPath}/js/member/controller.js"></script>
<!--<c:url value="bid=${param.bid}&type=${param.type}&KEYWORD=${param.KEYWORD}&KEYSTRING=${param.KEYSTRING}&" var="linkURL" />-->

<script>
	console.log("${param}");
	var linkURL = "${linkURL}";
	console.log("!!!!!!!!!!!!!!!!!!!!" + linkURL);
	var _csrf = "${_csrf.token}";
	var _csrfParameter = "${_csrf.parameterName}"
</script>

<div class="catagoryTitle">
	회원리스트
</div>
<div class="searchBox">
	<form id="memberSearchForm" action="memberList.do" name="memberSearchForm" method="GET">
	<input type="hidden" name="bid" value="${param.bid}" />
	<input type="hidden" name="type" value="${param.type}" />
	<table class="searchTbl">
		<colgroup>
			<col width="100px" />
			<col width="" />
	<%-- 		<col width="100px" />
			<col width="40%" /> --%>
		</colgroup>
		<tr>
			<th>키워드검색</th>
			<td>
			<select name="KEYWORD" id="KEYWORD">
				<option value="">==검색조건==</option>
				<option value="USERID" ${param.KEYWORD == "USERID"  ? "selected" : ""}>아이디</option>
				<option value="USERNAME" ${param.KEYWORD == "USERNAME"  ? "selected" : ""}>이름</option>
				<option value="USEREMAIL" ${param.KEYWORD == "USEREMAIL"  ? "selected" : ""}>E-MAIL</option>
			</select>
			<input type="text" size="30" class="tblInput" name="KEYSTRING" value= ${param.KEYSTRING != "" ? param.KEYSTRING : "" } >
			</td>
		<!-- 	<th>그룹</th>
			<td>
				<select name="GROUP" id="GROUP">
					<option value="">==등급검색==</option>
					<option value="COMMON">일반등급 </option>
				</select>
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
		<a href="#this" class="confirm" id="memberListSearchBtn">검색</a>
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
		<th>아이디</th>
		<th>이름</th>
		<th>E-MAIL</th>
		<th>전화번호</th>
		<th>회원등급</th>
		<th>학습그룹</th>
		<th>회원가입일</th>
		<th>-</th>
	</tr>
	<c:forEach items="${userList}" var="list" varStatus="status">
		<tr>
			<!-- <td><input type="checkbox" /></td> -->
			<td>${list.COUNT}</td>
			<td id="userId"><a href="memberDetail.do?USERID=${list.USERID }&type=${param.type }&currentPageNo=${pageConfig.currentPageNo}">${list.USERID}</a></td>
			<td>${list.USERNAME}</td>
			<td>${list.USEREMAIL}</td>
			<td>${list.USERTEL}</td>
			<td>
				<select id="roleSelect">
					<c:if test="${list.USERROLE eq 'ROLE_STUDENT'}">	
						<option value="ROLE_STUDENT" selected>일반등급</option>
					</c:if>
					<c:if test="${list.USERROLE ne 'ROLE_STUDENT'}">	
						<option value="ROLE_STUDENT">일반등급</option>
					</c:if>
					<c:if test="${list.USERROLE eq 'ROLE_ADMIN'}">	
						<option value="ROLE_ADMIN" selected>관리자등급</option>
					</c:if>
					<c:if test="${list.USERROLE ne 'ROLE_ADMIN'}">	
						<option value="ROLE_ADMIN">관리자등급</option>
					</c:if>
					<c:if test="${list.USERROLE eq 'ROLE_SUPERADMIN'}">	
						<option value="ROLE_SUPERADMIN" selected>슈퍼관리자등급</option>
					</c:if>
					<c:if test="${list.USERROLE ne 'ROLE_SUPERADMIN'}">	
						<option value="ROLE_SUPERADMIN">슈퍼관리자등급</option>
					</c:if>
				</select>
			</td>
			<td>
				<select>
					<option>일반등급</option>
				</select>
			<td>${list.USERREGDATE}</td>
			<td class="lastTd">
				<a href="#" class="btn mEdit"></a>
				<a href="#" class="btn mDel" ></a>
			</td>
		</tr>
	</c:forEach>
</table>
</div>

<div class="pagingGrp">
	<a href="memberList.do?currentPageNo=${pageConfig.currentPageNo - 10}&${linkURL}">이전</a>
	<c:forEach var="i" begin="${pageConfig.startPage}" end="${pageConfig.endPage}">
		<c:if test="${ i == pageConfig.currentPageNo }">
			<strong>${i}</strong>
		</c:if>
		<c:if test="${ i != pageConfig.currentPageNo }">
			<a href="memberList.do?currentPageNo=${i}&${linkURL}">${i}</a>
		</c:if>
	</c:forEach>
	<a href="memberList.do?currentPageNo=${pageConfig.currentPageNo + 10}&${linkURL}">다음</a>
</div> 