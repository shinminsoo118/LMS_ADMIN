<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tile" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:url value="bid=${param.bid}&KEYWORD=${param.KEYWORD}&KEYSTRING=${param.KEYSTRING}" var="linkURL" />

<c:if test="${param.msg eq 'SUCCESS'}">
	<script>
		alert("상품코드을 등록을 완료하였습니다.");
		location.href= "codeList.do?${linkURL}&currentPageNo=${param.currentPageNo}";
	</script>
</c:if>
<c:if test="${param.msg eq 'FAIL'}">
	<script>
		alert("다시 등록해주세요.");
	</script>
</c:if>
<div class="catagoryTitle">
	관리자등록
</div>
<div class="catagoryTitle">
	상품코드관리
</div>
<div class="tableBox">
<c:url value="/administrator/setup/codeRegisterInsert.do" var="actionURL" />
<form action="${actionURL}" name="registForm" id="registForm" method="POST">
	<input type="hidden" name="bid" id="bid" value="${param.bid}" />
	<input type="hidden" name="currentPageNo" value="${param.currentPageNo}" />
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	<table class="inputTbl2">
		<colgroup>
			<col width="150px" />
			<col width="" />
		</colgroup>
		<tr>
			<th colspan="2">상품코드등록</th>
		</tr>
		<tr>
			<td class="left">상품명</td>
			<td><input type="text" name="PRONAME" id="PRONAME" size="70" class="tblInput" /></td>
		</tr>
		<tr>
			<td class="left">수업방식</td>
			<td>
				<input type="checkbox" name="PRODUCTSORT" class="tblCheckbox" id="method1" value="화상" /><label for="method1">화상</label>
				<input type="checkbox" name="PRODUCTSORT" class="tblCheckbox" id="method2" value="녹화" /><label for="method2">녹화</label>
				<input type="checkbox" name="PRODUCTSORT" class="tblCheckbox"  id="method3" value="화상+녹화" /><label for="method3" style="width:120px;">화상+녹화</label> 
			</td>
		</tr>
		<tr>
			<td class="left">수업시간(개월)</td>
			<td>
				<input type="checkbox" name="PROMONTH" class="tblCheckbox" id="month1" value="1개월" /><label for="month1">1개월</label>
				<input type="checkbox" name="PROMONTH" class="tblCheckbox" id="month2" value="2개월" /><label for="month2">2개월</label>
				<input type="checkbox" name="PROMONTH" class="tblCheckbox"  id="month3" value="3개월" /><label for="month3">3개월</label> 
				<input type="checkbox" name="PROMONTH" class="tblCheckbox"  id="month6" value="6개월" /><label for="month6">6개월</label> 
				<input type="checkbox" name="PROMONTH" class="tblCheckbox"  id="month12" value="12개월" /><label for="month12">12개월</label>
				
			</td>
		</tr>
		<tr>
			<td class="left">수업시간(주중.주말)</td>
			<td>
				<input type="checkbox" name="PROWEEKEND" class="tblCheckbox" id="se1"  value="주중"/><label for="se1">주중</label>
				<input type="checkbox" name="PROWEEKEND" class="tblCheckbox" id="se2"   value="주말"/><label for="se2">주말</label>
			</td>
		</tr>
		<tr>
			<td class="left">수업시간(회수)</td>
			<td>
				<input type="checkbox"  name="WEEKREPEAT" class="tblCheckbox" id="joo1" value="주1회"/><label for="joo1">주1회</label>
				<input type="checkbox"  name="WEEKREPEAT" class="tblCheckbox" id="joo2" value="주2회"/><label for="joo2">주2회</label>
				<input type="checkbox"  name="WEEKREPEAT" class="tblCheckbox" id="joo3" value="주3회"/><label for="joo3">주3회</label>
				<input type="checkbox"  name="WEEKREPEAT" class="tblCheckbox" id="joo4" value="주4회"/><label for="joo4">주4회</label>
				<input type="checkbox"  name="WEEKREPEAT" class="tblCheckbox" id="joo5" value="주5회"/><label for="joo5">주5회</label>
			</td>
		</tr>
		<tr>
			<td class="left">수업시간(요일)</td>
			<td>
				<input type="checkbox" name="PROWEEK" class="tblCheckbox" id="yo1"  value="월"/><label for="yo1">월</label>
				<input type="checkbox" name="PROWEEK" class="tblCheckbox" id="yo2"  value="화"/><label for="yo2">화</label>
				<input type="checkbox" name="PROWEEK" class="tblCheckbox" id="yo3"  value="수"/><label for="yo3">수</label>
				<input type="checkbox" name="PROWEEK" class="tblCheckbox" id="yo4"  value="목"/><label for="yo4">목</label>
				<input type="checkbox" name="PROWEEK" class="tblCheckbox" id="yo5"  value="금" /><label for="yo5">금</label>
				<input type="checkbox" name="PROWEEK" class="tblCheckbox" id="yo6"  value="토"/><label for="yo6">토</label>
				<input type="checkbox" name="PROWEEK" class="tblCheckbox" id="yo7"  value="일"/><label for="yo7">일</label>
			</td>
		</tr>
		<tr>
			<td class="left">수업시간(분)</td>
			<td>
				<input type="checkbox" name="DAYPERMINUTE" class="tblCheckbox" id="bun10"  value="10분"/><label for="bun10">10분</label>
				<input type="checkbox" name="DAYPERMINUTE" class="tblCheckbox"  id="bun15" value="15분"/><label for="bun15">15분</label>
				<input type="checkbox" name="DAYPERMINUTE" class="tblCheckbox"  id="bun20" value="20분"/><label for="bun20">20분</label>
				<input type="checkbox" name="DAYPERMINUTE" class="tblCheckbox"  id="bun25" value="25분"/><label for="bun25">25분</label>
				<input type="checkbox" name="DAYPERMINUTE" class="tblCheckbox"  id="bun30" value="30분"/><label for="bun30">30분</label>
				<input type="checkbox" name="DAYPERMINUTE" class="tblCheckbox"  id="bun40" value="40분"/><label for="bun40">40분</label>
				<input type="checkbox" name="DAYPERMINUTE" class="tblCheckbox"  id="bun50" value="50분"/><label for="bun50">50분</label>
				<input type="checkbox" name="DAYPERMINUTE" class="tblCheckbox"  id="bun60" value="60분"/><label for="bun60">60분</label>
				<input type="checkbox" name="DAYPERMINUTE" class="tblCheckbox"  id="bun80" value="80분"/><label for="bun80">80분</label>
				<input type="checkbox" name="DAYPERMINUTE" class="tblCheckbox"  id="bun100" value="100분"/><label for="bun100">100분</label>
				<input type="checkbox" name="DAYPERMINUTE" class="tblCheckbox"  id="bun120" value="120분"/><label for="bun120">120분</label>
				<input type="checkbox" name="DAYPERMINUTE" class="tblCheckbox"  id="bun180" value="180분"/><label for="bun180">180분</label>
				<input type="checkbox" name="DAYPERMINUTE" class="tblCheckbox"  id="bun240" value="240분"/><label for="bun240">240분</label>
				
			</td>
		</tr>
	</table>
</form>
</div>
<div class="btnGrp">
	<a href="" class="confirm" id="CodeRegist">확인</a>
	<a href="codeList.do?${linkURL}&currentPageNo=${param.currentPageNo}" class="cancel">취소</a>
</div>
