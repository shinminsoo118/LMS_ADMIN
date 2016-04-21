<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tile" uri="http://tiles.apache.org/tags-tiles" %>
<script src="${pageContext.request.contextPath}/js/setup/boardsetup.controller.js"></script>
<c:url value="bid=${param.bid}&KEYWORD=${param.KEYWORD}&KEYSTRING=${param.KEYSTRING}" var="linkURL" />	
<script>
	console.log("${param}");
	var linkURL = "${linkURL}";
	//console.log("!!!!!!!!!!!!!!!!!!!!" + linkURL);
	var _csrf = "${_csrf.token}";
	var _csrfParameter = "${_csrf.parameterName}"
	//console.log("_csrf" + _csrf);
	//console.log("_csrfParameter" + _csrfParameter);
	
</script>
<div class="catagoryTitle">
	보드설정리스트
</div>
<div class="searchBox">
	<form id="codeSearchForm" action=boardSetup.do name="codeSearchForm" method="GET">
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
				<select name="KEYWORD" id="KEYWORD" >
					<option value="">==검색조건==</option>
					<option value="BOARDID" ${param.KEYWORD == "BOARDID"  ? "selected" : ""}>아이디</option>
					<option value="BOARDTBL" ${param.KEYWORD == "BOARDTBL"  ? "selected" : ""}>테이블명</option>
					<option value="BOARDNAME" ${param.KEYWORD == "BOARDNAME"  ? "selected" : ""}>테이블이름</option>
				</select>
				<input type="text" size="30" class="tblInput" id="KEYSTRING" name="KEYSTRING" value= ${param.KEYSTRING != "" ? param.KEYSTRING : "" } > 
				</td>
			</tr>
		</table>
		<div class="searchBtnGrp">
			<a href="" class="confirm" id="boardSetSearchBtn">검색</a>
		</div>
	</form>
</div>
<div class="tableBox">
<table class="listTbl">
	<colgroup>
		<col width="5%" />
		<col width="10%" />
		<col width="15%" />
		<col width="" />
		<col width="7%" />
		<col width="7%" />
		<col width="7%" />
		<col width="7%" />
		<col width="7%" />
		<col width="5%" />
	</colgroup>
	<tr>
		<th rowspan="2"><input type="checkbox" /></th>
		<th rowspan="2">아이디</th>
		<th rowspan="2">테이블명</th>
		<th rowspan="2">테이블이름</th>
		<th colspan="5">권한설정</th>
		<th rowspan="2">-</th>
	</tr>
	<tr>
		<th>읽기</th>
		<th>쓰기</th>
		<th>답글</th>
		<th>수정</th>
		<th>삭제</th>
	</tr>
	
	<c:if test="${list.size() != 0 }">
		<c:forEach var="board" items="${list}" varStatus ="st">
			<tr>
				<td><input type="checkbox" /></td>
				<td id="boardId">${board.BOARDID}</td>
				<td id="stIndex">${board.BOARDTBL}<input type="hidden" value="${st.index}"/></td>
				<td class="left"><a href="boardSetupDetail.do?bid=${param.bid}&BOARDID=${board.BOARDID}">${board.BOARDNAME}</a></td>
				<td id="VIEW_${st.index}">
					<script>
						var userRole = '${userRole}';
						$("#VIEW_${st.index}").html(fn_setUserRoleSetting(userRole, "VIEW", '${board.VIEW}'));
					</script>
				</td>
				<td id="REG_${st.index}">
					<script>
						var userRole = '${userRole}';
						$("#REG_${st.index}").html(fn_setUserRoleSetting(userRole, "VIEW", '${board.REG}'));
					</script>
				</td>
				<td id="REPLY_${st.index}">
					<script>
						var userRole = '${userRole}';
						$("#REPLY_${st.index}").html(fn_setUserRoleSetting(userRole, "REPLY", '${board.REPLY}'));
					</script>
				<td id="EDIT_${st.index}">
					<script>
						var userRole = '${userRole}';
						$("#EDIT_${st.index}").html(fn_setUserRoleSetting(userRole, "EDIT", '${board.EDIT}'));
					</script>
				</td>
				<td id="DEL_${st.index}">
					<script>
						var userRole = '${userRole}';
						$("#DEL_${st.index}").html(fn_setUserRoleSetting(userRole, "DEL", '${board.DEL}'));
					</script>
				</td>
				<td class="lastTd">
					<a href="Edit.do?${linkURL}" class="btn cEdit"></a>
				</td>
			</tr>
		</c:forEach>
	</c:if>
</table>
</div>

<div class="pagingGrp">
	<a href="boardSetupRegister.do?bid=${param.bid}&BOARDID=${board.BOARDID}&currentPageNo=${pageConfig.currentPageNo}" class="dBtn3 floatRight">보드등록</a>
	<a href="boardSetup.do?currentPageNo=${pageConfig.currentPageNo - 10}&${linkURL}">이전</a>
	<c:forEach var="i" begin="${pageConfig.startPage}" end="${pageConfig.endPage}">
		<c:if test="${i == pageConfig.currentPageNo }">
			<strong>${i}</strong>
		</c:if>
		<c:if test="${ i != pageConfig.currentPageNo }">
			<a href="boardSetup.do?currentPageNo=${i}&${linkURL}">${i}</a>
		</c:if>
	</c:forEach> 
	<a href="boardSetup.do?currentPageNo=${pageConfig.currentPageNo + 10}&${linkURL}">다음</a>
</div>
