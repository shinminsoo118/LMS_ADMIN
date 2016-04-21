<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tile" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:url value="bid=${param.bid}&SEQ=${param.SEQ}&CURSTATUS=${param.CURSTATUS}&KEYWORD=${param.KEYWORD}&KEYSTRING=${param.KEYSTRING}" var="linkURL" />
	<div class="catagoryTitle">
		교육과정등록
	</div>
<c:url value="/administrator/curriculumn/curriculumnUpdate.do?${_csrf.parameterName}=${_csrf.token}" var="actionURL" />
<form id="curriculumnRegistForm" name="curriculumnRegistForm" method="post" action="${actionURL}">
<input type="hidden" name="SEQ" value="${param.SEQ}" />
<input type="hidden" name="KEYWORD" value="${param.KEYWORD}" />
<input type="hidden" name="KEYSTRING" value="${param.KEYSTRING}" />
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
<div class="tableBox">
	
	<table class="inputTbl2">
		<colgroup>
			<col width="150px" />
			<col width="" />
		</colgroup>
		<tr>
			<th colspan="2">교육과정수정 </th>
		</tr>
		
		<tr>
			<td class="left">교육과정</td>
			<td>
				<select class="middleSelect" id="ProductList">
					<option value="">== 교육과정을 선택해 주세요. ==</option>
				</select>
			</td>
		</tr>
		<tr>
			<td class="left">상품명</td>
			<td><input type="text" name="CURPRONAME" size="52" class="tblInput" value="${detail.CURPRONAME}"/></td>
		</tr>
		<tr>
			<td class="left">수업방법</td>
			<td>
				<select class="middleSelect" id="PRODUCTSORT" name="CURSORT">
					<option value="">== 수업방법을 선택해 주세요. ==</option>	
				</select>
			</td>
		</tr>
		
		
		<tr>
			<td class="left" rowspan="2">수업기간</td>
			<td>
				<select class="middleSelect" id="PROMONTH"  name="CURMONTH">
					<option value="">== 수업기간을 선택해 주세요. ==</option>	
				</select>
			</td>
		</tr>
		
		<tr>
			<td>
				<input type="text" size="20" class="tblInput from" id="from"/> <!-- <a href="" class="calendar"></a> -->
				~
				<input type="text" size="20" class="tblInput to" id="to" /> <!-- <a href="" class="calendar"></a> -->
			</td>
		</tr>
		
		<tr>
			<td class="left">수업종류</td>
			<td>
				<select class="middleSelect" id="PROWEEKEND"  name="CURPROWEEKEND">
					<option value="">== 수업종류을 선택해 주세요. ==</option>	
				</select>
			</td>
		</tr>
		<tr>
			<td class="left">수업횟수</td>
			<td>
				<select class="middleSelect" id="WEEKREPEAT"  name="CURWEEKPERCOUNT">
					<option value="">== 수업횟수을 선택해 주세요. ==</option>	
				</select>
			</td>
		</tr>
		<tr>
			<td class="left">요일선택</td>
			<td id="PROWEEK">
			</td>
		</tr>
		<tr>
			<td class="left">수업시간대</td>
			<td>
				<select class="smallSelect"  name="StartTime" id="StartTime">
					<option value="">==시작시간==</option>	
				</select>
				~
				<select class="smallSelect" name="FinishTime" id="FinishTime">
					<option value="">==종료시간==</option>	
				</select>
			</td>
		</tr>
		<tr>
			<td class="left">수업시간</td>
			<td>
				<select class="middleSelect" id="DAYPERMINUTE"  name="CURPROMINUTE">
					<option value="">== 수업시간을 선택해 주세요. ==</option>	
				</select>
			</td>
		</tr>
		
		<tr>
			<td class="left">결제방식</td>
			<td>
				<input type="checkbox" name="payMemtMethod" class="tblCheckbox" id="joo4"  value="카드결제"/><label for="joo4" class="longLabel">카드결제</label>
				<input type="checkbox" name="payMemtMethod" class="tblCheckbox" id="joo1" value="무통장입금"/><label for="joo1" class="longLabel">무통장입금</label>
				<input type="checkbox" name="payMemtMethod" class="tblCheckbox" id="joo2"  value="무통장(가상계좌)"/><label for="joo2" class="longLabel">무통장(가상계좌)</label>
				<input type="checkbox" name="payMemtMethod" class="tblCheckbox" id="joo3"  value="온라인계좌이체"/><label for="joo3" class="longLabel">온라인계좌이체</label>
			</td>
		</tr>
		<tr>
			<td class="left">가격</td>
			<td><input type="text" size="20" class="tblInput" name="CURPRICE" onkeyPress="InpuOnlyNumber(this);" id="CURPRICE" value="${detail.CURPRICE}"/> 원 (,없이 숫자만 입력)</td>
		</tr>
		<tr>
			<td class="left">할인적용</td>
			<td>
				<input type="radio" name="CURPRICESALESORT" value="Y" id="SaleYes" /><label for="SaleYes"> 적용</label>
				<input type="radio" name="CURPRICESALESORT" value="N" checked id="SaleNo" /><label for="SaleNo"> 미적용</label>
			</td>
		</tr>
		<tr>
			<td class="left">할인율</td>
			<td>
				<select class="smallSelect" id="PriceSaleRate" disabled>
					<option value="0">==할인율==</option>	
					<option value="5">5%</option>
					<option value="10">10%</option>
					<option value="15">15%</option>
					<option value="20">20%</option>
					<option value="etc">기타</option>
				</select>
				<input type="text" size="2" name="EtcSaleRate" id="EtcSaleRate" class="tblInput" disabled onkeyPress="InpuOnlyNumber(this);" min=0 max=3 />%
			</td>
		</tr>
		
		
		<tr>
			<td class="left">할인가격</td>
			<td><input type="text" size="20" class="tblInput" name="CURSALEPRICE" id="CURSALEPRICE" readonly  value="${detail.CURSALEPRICE}" /> 원</td>
		</tr>
		<tr>
			<td class="left">강사선택</td>
			<td>
				<select class="middleSelect" name="CURTEACHER">
					<option value="">== 강사를 선택해 주세요. ==</option>
					<option value="David's Kim Welet">David's Kim Welet </option>	
				</select>
			</td>
		</tr>
		<tr>
			<td class="left">출석여부</td>
			<td>
				<input type="radio" name="CURATTENDANT" value="Y" id="AttendantYes"/><label for="AttendantYes"> 적용</label>
				<input type="radio" name="CURATTENDANT" value="N" checked id="AttendantNo"/><label for="AttendantNo"> 미적용</label>
			</td>
		</tr>
		<tr>
			<td class="left">정원</td>
			<td><input type="text" size="5" name="CURMEMBERCOUNT" class="tblInput"  onkeypress="InpuOnlyNumber(this);"  value="${detail.CURMEMBERCOUNT}"/> 명</td>
		</tr>
		<tr>
			<td class="left">수업진형여부</td>
			<td>
				<input type="radio" name="CURSTATUS" value="wait" checked id="wait" /><label for="wait"> 개설중</label>
				<input type="radio" name="CURSTATUS" value="start" id="start"/><label for="start"> 진행중</label>
				<input type="radio" name="CURSTATUS" value="finish" id="finish"/><label for="finish"> 종료</label>
			</td>
		</tr>
		<tr>
			<td class="left">접수기간</td>
			<td>
				<input type="text" size="20" class="tblInput from" id="fromRegist"/> <!-- <a href="" class="calendar"></a> -->
				~
				<input type="text" size="20" class="tblInput to" id="toRegist" /> <!-- <a href="" class="calendar"></a> -->
			</td>
		</tr>
		<tr>
			<td class="left">수업상세설명</td>
			<td>
				<textarea class="fullSize" name="CONTENT" id="CONTENT" style="display:none;">${detail.CONTENT}</textarea>
				<tile:insertTemplate template="/daumeditor/editor.jsp" />
			</td>
		</tr>
	</table>
</div>
	<div class="btnGrp">
		<a href="javascript:saveContent();" class="confirm" id="curriculumnRegistBtn1">확인</a>
		<a href="curriculumnDetail.do?${linkURL}&currentPageNo=${param.currentPageNo}" class="cancel">취소</a>
	</div>
</form>
<script>
	//변수값들.
	var curSort = '${detail.CURSORT}';
	var curMonth = '${detail.CURMONTH}';
	var curMonthPeriod = '${detail.CURMONTHPERIOD}';
	var curProWeekend = '${detail.CURPROWEEKEND}';
	var curProWeek = '${detail.CURPROWEEK}';
	var curWeekPerCount = '${detail.CURWEEKPERCOUNT}';
	var curTimePeriod = '${detail.CURTIMEPERIOD}';
	var curProMinute = '${detail.CURPROMINUTE}';
	var curPriceSort = '${detail.CURPRICESORT}';
	var curPriceSaleSort = '${detail.CURPRICESALESORT}';
	var curSaleRate = '${detail.CURSALERATE}';
	var curAttendant = '${detail.CURATTENDANT}';
	var curTeacher = "${detail.CURTEACHER}";
	var curStatus = '${detail.CURSTATUS}';
	var curRegPeriod = '${detail.CURREGPERIOD}';
	
	fn_getProductList('Edit','${detail.PROCODE}');
	fn_getTimes();//시간세팅.
	Editor.modify({
		"content":$tx('CONTENT')
	});
	getEditFile();
</script>
		