<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%-- <script src="${pageContext.request.contextPath}/js/lecture/lecture.controller.js"></script> --%>
<c:url value="bid=${param.bid}&STATUS=${param.STATUS}&KEYWORD=${param.KEYWORD}&KEYSTRING=${param.KEYSTRING}" var="linkURL" />
<script>
	var linkURL = "${linkURL}";
	var _csrf = "${_csrf.token}";
	var _csrfParameter = "${_csrf.parameterName}"
	
</script>
<div class="catagoryTitle">
	수강이력 정보리스트
</div>
<div class="searchBox">
	<form id="lectureSearchForm" action=lectureList.do name="lectureSearchForm" method="GET">
	<input type="hidden" name="bid" value="${param.bid}" />
	<input type="hidden" name="type" value="${param.type}" />
	<input type="hidden" name="STATUS" value="${param.STATUS}" />
	<input type="hidden" name="CURSTATUS" value="${param.CURSTATUS}" />
	<table class="searchTbl">
		<colgroup>
			<col width="100px" />
			<col width="40%" />
			<col width="100px" />
			<col width="40%" />
		</colgroup>
		<tr>
			<th>키워드검색</th>
			<td>
				<select name="KEYWORD" id="KEYWORD" >
					<option value="">==검색조건==</option>
					<option value="CURCOURSE" ${param.KEYWORD == "CURCOURSE"  ? "selected" : ""}>교육과정</option>
					<option value="CURPRONAME" ${param.KEYWORD == "CURPRONAME"  ? "selected" : ""}>상품명</option>
					<option value="CURSORT" ${param.KEYWORD == "CURSORT"  ? "selected" : ""}>상품종류</option>
				</select>
				<input type="text" size="30" class="tblInput" id="KEYSTRING" name="KEYSTRING" value= ${param.KEYSTRING != "" ? param.KEYSTRING : "" } > 
			</td>
			<th>그룹</th>
			<td>
				<select>
				<option>== 그룹검색 ==</option>
				</select>
			</td>
		</tr>
		<tr>
			<th>수업기간</th>
			<td colspan="3">
				<input type="text" name="beforeDate" size="20" class="tblInput" value=${param.beforeDate != "" ? param.beforeDate : "" }> <a href="#" class="calendar"></a>
				- 
				<input type="text" name="afterDate" size="20" class="tblInput" value=${param.afterDate != "" ? param.afterDate : "" }> <a href="#" class="calendar"></a>
			</td>
			
		</tr>
	</table>
	<div class="searchBtnGrp">
		<a href="" class="confirm" id="lectureListSearchBtn">검색</a>
	</div>
	</form>
</div>
<div class="tableBox">
<table class="listTbl">
	<colgroup>
		<col width="5%" />
		<col width="5%" />
		<col width="10%" />
		<col width="" />
		<col width="10%" />
		<col width="10%" />
		<col width="7%" />
		<col width="7%" />
		<col width="5%" />
		<col width="5%" />
		<col width="7%" />
		<col width="7%" />
		<col width="5%" />
	</colgroup>
	<tr>
		<th  rowspan="2"><input type="checkbox" /></th>
		<th rowspan="2">번호</th>
		<th rowspan="2">교육과정</th>
		<th rowspan="2">상품명</th>
		<th rowspan="2">기간</th>
		<th rowspan="2">강사</th>
		<th rowspan="2">수업료</th>
		<th rowspan="2">상품종류</th>
		<th rowspan="2">결제여부</th>
		<th colspan="4">수업기간</th>
	<!-- 	<th  rowspan="2">-</th> -->
	</tr>
	<tr>
		<th>주중.주말</th>
		<th>개월</th>
		<th>주</th>
		<th>분</th>
	</tr>
	<c:if test="${clist.size() != 0 }">
		<c:forEach var="cList" items="${clist}" varStatus ="st">
			<tr>
				<td><input type="checkbox" /></td>
				<td>${pageConfig.totalListCount - (pageConfig.currentPageNo - 1)* boardConfig.LISTPERPAGE - st.index}</td>
				<td><div class="courseBox">${cList.CURCOURSE}</div></td>
				<td class="left"><a href="lectureDetail.do?SEQ=${cList.PSEQ}&${linkURL}&currentPageNo=${pageConfig.currentPageNo}">${cList.CURPRONAME}</a></td>
				<td>
					${cList.CURMONTHPERIOD}
				</td>
				<td>${cList.CURTEACHER}</td>
				<td>
					<b class="price">${cList.CURPRICE}원</b><br/>
					<b class="salePrice">${cList.CURSALEPRICE}원(${cList.CURSALERATE}%)</b>
				</td>
				
				<td>${cList.CURSORT}</td>
				<td>${cList.CPAY}</td>
				<td>
					${cList.CURPROWEEKEND}
				</td>
				<td>${cList.CURMONTH}</td>
				<td>
					${cList.CURWEEKPERCOUNT}<br/>
					${cList.CURPROWEEK}
				</td>
				<td>
					${cList.CURPROMINUTE}<br/>
					${cList.CURTIMEPERIOD}
				</td>
			<%-- 	<td class="lastTd">
					<a href="javascript:fn_BoardDelete('${linkURL}')" class="btn cEdit" ></a>
					<a href="javascript:fn_curriculumnDelete('${linkURL}', '${cList.SEQ}','${cList.CURCOURSE}')" class="btn cDel" ></a>
				</td> --%>
			</tr>
		</c:forEach>
	</c:if>
	<c:if test="${clist.size() == 0 && param.STATUS eq 'S'}">
		<td colspan="13">수강 이력이 없습니다.</td>
	</c:if>
	<c:if test="${clist.size() == 0 && param.STATUS eq 'P'}">
		<td colspan="13">결제이력이 없습니다.</td>
	</c:if>
	<c:if test="${clist.size() == 0 && param.STATUS eq 'R'}">
		<td colspan="13">환불이력이 없습니다.</td>
	</c:if>
</table>
</div>
<div class="pagingGrp">
	<a href="lectureList.do?currentPageNo=${pageConfig.currentPageNo - 10}&${linkURL}">이전</a>
	<c:forEach var="i" begin="${pageConfig.startPage}" end="${pageConfig.endPage}">
		<c:if test="${ i == pageConfig.currentPageNo }">
			<strong>${i}</strong>
		</c:if>
		<c:if test="${ i != pageConfig.currentPageNo }">
			<a href="lectureList.do?currentPageNo=${i}&${linkURL}">${i}</a>
		</c:if>
	</c:forEach>
	<a href="lectureList.do?currentPageNo=${pageConfig.currentPageNo + 10}&${linkURL}">다음</a>
</div>
