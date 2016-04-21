<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:url value="bid=${param.bid}&KEYWORD=${param.KEYWORD}&KEYSTRING=${param.KEYSTRING}&" var="linkURL" />
<div class="tableBox">
	<table class="listTbl">
		<colgroup>
			<col width="5%" />
			<col width="5%" />
			<col width="7%" />
			<col width="" />
			<col width="7%" />
			<col width="7%" />
			<col width="7%" />
			<col width="10%" />
			<col width="10%" />
			<col width="10%" />
			<col width="7%" />
			<col width="5%" />
		</colgroup>
		<tr>
			<th  rowspan="2"><input type="checkbox" /></th>
			<th rowspan="2">번호</th>
			<th rowspan="2">상품코드</th>
			<th rowspan="2">상품명</th>
			<th rowspan="2">상품종류</th>
			<th colspan="5">수업기간</th>
			<th rowspan="2">작성일</th>
			<th rowspan="2">-</th>
		</tr>
		<tr>
			<th>개월</th>
			<th>주중.주말</th>
			<th>주(회수)</th>
			<th>요일</th>
			<th>분</th>
		</tr>
		<c:if test="${list.size() != 0 }">
		<c:forEach var="code" items="${list}" varStatus ="st">
			<c:if test="${detail.SEQ == code.CODESEQ}">
				<tr class="selectedItem">
			</c:if>
			<c:if test="${detail.SEQ != code.CODESEQ}">
				<tr>
			</c:if>
				<td><input type="checkbox" /></td>
				<td>${pageConfig.totalListCount - (pageConfig.currentPageNo - 1)* boardConfig.LISTPERPAGE - st.index}</td>
				<td id="code"><fmt:formatNumber pattern="000000" value="${code.CODE}" /></td>
				<td class="left"><a href="codeEdit.do?CODESEQ=${code.CODESEQ}&currentPageNo=${param.currentPageNo}&${linkURL}">${code.PRONAME}</a> </td>
				<td>${code.PRODUCTSORT}</td>
				<td>${code.PROMONTH}</td>
				<td>${code.PROWEEKEND}</td>
				<td>${code.WEEKREPEAT}</td>
				<td>${code.PROWEEK}</td>
				<td>${code.DAYPERMINUTE}</td>
				<td><fmt:formatDate pattern="yyyy.MM.dd hh:MM:ss" value="${code.REGDATE}" /></td>
				<td class="lastTd">
					<%-- <a href="Edit.do?${linkURL}" class="btn cEdit"></a> --%>
					<a href="javascript:fn_codeDelete('${linkURL}','${code.CODESEQ}','${code.CODE}')" class="btn cDel" ></a>
				</td>
			</tr>
		</c:forEach>
		</c:if>
		<c:if test="${list.size() == 0 }">
			<tr>
				<td colspan="10">등록된 상품코드가 없습니다.
			</tr>
		</c:if>
	</table>
</div>
