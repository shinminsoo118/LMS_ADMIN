<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tile" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:url value="bid=${param.bid}&SEQ=${param.SEQ}&CURSTATUS=${param.CURSTATUS}&KEYWORD=${param.KEYWORD}&KEYSTRING=${param.KEYSTRING}" var="linkURL" />
<script src="${pageContext.request.contextPath}/js/curriculumn/curriculumn.controller.js"></script>
<script>console.log('${linkURL}');</script>
<c:if test="${param.resStatus eq 'success'}">
	<script>
		alert("수강 신청을 완료하였습니다.");
		location.href= "curriculumnList.do?${linkURL}&currentPageNo=${param.currentPageNo}";
	</script>
</c:if>
<c:if test="${param.resStatus eq 'fail'}">
	<script>alert("수강 신청인원이 초과하였습니다. 신청할 수 없습니다.");location.href= "curriculumnList.do?${linkURL}&currentPageNo=${param.currentPageNo}";alert("curriculumnList.do?${linkURL}&currentPageNo=${param.currentPageNo}");</script>
</c:if>

<c:url value="bid=${param.bid}&SEQ=${param.SEQ}&KEYWORD=${param.KEYWORD}&KEYSTRING=${param.KEYSTRING}" var="linkURL" />
	<div class="catagoryTitle">
		교육과정상세보기
	</div>
<c:url value="/administrator/curriculumn/courseInsert.do?${_csrf.parameterName}=${_csrf.token}" var="actionURL" />
<form id="courseRegistForm" name="courseRegistForm" method="post" action="${actionURL}" enctype="multipart/form-data">
<input type="hidden" name="WRITER" value="${username}" />
<input type="hidden" name="bid" value="${param.bid}" />
<input type="hidden" name="CURSEQ" value="${param.SEQ}" />
<input type="hidden" name="CURCODE" value="${detail.CURCODE}" />
<input type="hidden" name="CURSTATUS" value="${param.CURSTATUS}" />
<input type="hidden" name="currentPageNo" value="${param.currentPageNo}" />
<input type="hidden" name="CURPRICESORT" value="${detail.CURPRICESORT}" />
<input type="hidden" name="CURMEMBERCOUNT" value="${detail.CURMEMBERCOUNT}" />
<input type="hidden" name="CURPRICE" value="${detail.CURPRICE}" />
<input type="hidden" name="CURTEACHER" value="${detail.CURTEACHER}" />
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
<div class="tableBox">
	<table class="inputTbl2">
		<colgroup>
			<col width="150px" />
			<col width="" />
		</colgroup>
		<tr>
			<th colspan="2">교육과정</th>
		</tr>
		
		<tr>
			<td class="left">상품명</td>
			<td><b>${detail.CURPRONAME}</b>(${detail.CURCOURSE})</td>
		</tr>
		
		
		<tr>
			<td class="left">수업</td>
			<td>
				${detail.CURSORT}
			</td>
		</tr>
		
		
		<tr>
			<td class="left">수업기간(개월)</td>
			<td>
				${detail.CURMONTH}(${detail.CURMONTHPERIOD})
			</td>
		</tr>
		
		<tr>
			<td class="left">수업시간</td>
			<td>${detail.CURPROWEEKEND}, ${detail.CURWEEKPERCOUNT}(${detail.CURPROWEEK}), ${detail.CURPROMINUTE}(${detail.CURTIMEPERIOD})</td>
		</tr>
		
		<tr>
			<td class="left">가격</td>
			<td><span class="price">${detail.CURPRICE}원</span> <b class="salePrice">${detail.CURSALEPRICE}원</b>(${detail.CURSALERATE}%)</td>
		</tr>
		
		
		<tr>
			<td class="left">강사</td>
			<td>
				${detail.CURTEACHER}
			</td>
		</tr>
		<tr>
			<td class="left">결제방식</td>
			<td id="payBox">
				
				<script>
					var pay = '${detail.CURPRICESORT}';
					fn_payMent(pay);
				</script>
			</td>
		</tr>
		
	</table>
</div>
	<div class="btnGrp">
		<a href="" class="confirm" id="courseInsertBtn">확인</a>
		<a href="curriculumnDetail.do?${linkURL}&CURSTATUS=${param.CURSTATUS}&currentPageNo=${param.currentPageNo}" class="cancel">취소</a>
	</div>
</form>
		