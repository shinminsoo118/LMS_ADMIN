<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="catagoryTitle">
	결제정보관리상세
</div>
<div class="tableBox">
<table class="inputTbl2">
	<colgroup>
		<col width="130px" />
		<col width="" />
	</colgroup>
	<tr>
		<th colspan="2">결제정보상세정보</th>
	</tr>
	<tr>
		<td class="left">결제방법</td>
		<td>
			<input type="checkbox"class="tblCheckbox" id="joo4"  /><label for="joo4" class="longLabel">카드결제</label>
			<input type="checkbox"class="tblCheckbox" id="joo1" /><label for="joo1" class="longLabel">무통장입금</label>
			<input type="checkbox"class="tblCheckbox" id="joo2"  /><label for="joo2" class="longLabel">무통장(가상계좌)</label>
			<input type="checkbox"class="tblCheckbox" id="joo3"  /><label for="joo3" class="longLabel">온라인계좌이체</label>
		</td>
	</tr>
	<tr>
		<td class="left">AllatPage :ID</td>
		<td><input type="text" size="30" class="tblInput" /></td>
	</tr>
	<tr>
		<td class="left">AllatPage:Cross-key</td>
		<td>
			<input type="text" size="30" class="tblInput" />
		</td>
	</tr>
	<tr>
		<td class="left">업체주소</td>
		<td>
			<p class="lineP"><input type="text" size="10" class="tblInput" /> <a href="" class="dBtn">주소검색</a></p>
			<p class="lineP"><input type="text" size="50" class="tblInput" /></p> 
		</td>
	</tr>
	<tr>
		<td class="left">무통장계좌</td>
		<td><input type="text" size="30" class="tblInput" /></td>
	</tr>
	<tr>
		<td class="left">가상계좌</td>
		<td>
			<input type="text" size="30" class="tblInput" />
		</td>
	</tr>
	
</table>
</div>
<div class="btnGrp">
	<a href="" class="confirm">확인</a>
	<a href="" class="cancel">취소</a>
</div>