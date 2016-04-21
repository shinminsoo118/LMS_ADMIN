<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="catagoryTitle">
	상품코드관리
</div>
<table>
	<colgroup>
		<col width="100px" />
		<col width="" />
	</colgroup>
	<tr>
		<th colspan="2">상품코드등록</th>
	</tr>
	<tr>
		<td class="left">상품코드</td>
		<td>000001</td>
	</tr>
	<tr>
		<td class="left">상품명</td>
		<td><input type="text" size="30" class="tblInput" /></td>
	</tr>
	<tr>
		<td class="left">구분</td>
		<td>
			<input type="radio" name="lecType" checked /> <label>주중</label>
			<input type="radio" name="lecType" /> <label>주말</label>
		</td>
	</tr>
	<tr>
		<td class="left">주</td>
		<td>
			<input type="checkbox"class="tblCheckbox" id="joo1" /><label for="joo1">주1회</label>
			<input type="checkbox"class="tblCheckbox" id="joo2"  /><label for="joo2">주2회</label>
			<input type="checkbox"class="tblCheckbox" id="joo3"  /><label for="joo3">주3회</label>
			<input type="checkbox"class="tblCheckbox" id="joo4"  /><label for="joo4">주4회</label>
			<input type="checkbox"class="tblCheckbox" id="joo5"  /><label for="joo5">주5회</label>
		</td>
	</tr>
	<tr>
		<td class="left">분</td>
		<td>
			<input type="checkbox" name="" class="tblCheckbox" id="bun10" /><label for="bun10">10분</label>
			<input type="checkbox" name="" class="tblCheckbox"  id="bun15" /><label for="bun15">15분</label>
			<input type="checkbox" name="" class="tblCheckbox"  id="bun20" /><label for="bun20">20분</label>
			<input type="checkbox" name="" class="tblCheckbox"  id="bun25" /><label for="bun25">25분</label>
			<input type="checkbox" name="" class="tblCheckbox"  id="bun30" /><label for="bun30">30분</label>
			<input type="checkbox" name="" class="tblCheckbox"  id="bun40" /><label for="bun40">40분</label>
			<input type="checkbox" name="" class="tblCheckbox"  id="bun50" /><label for="bun50">50분</label>
			<input type="checkbox" name="" class="tblCheckbox"  id="bun60" /><label for="bun60">60분</label>
			<input type="checkbox" name="" class="tblCheckbox"  id="bun80" /><label for="bun80">80분</label>
			<input type="checkbox" name="" class="tblCheckbox"  id="bun100" /><label for="bun100">100분</label>
			<input type="checkbox" name="" class="tblCheckbox"  id="bun120" /><label for="bun120">120분</label>
			<input type="checkbox" name="" class="tblCheckbox"  id="bun180" /><label for="bun180">180분</label>
			<input type="checkbox" name="" class="tblCheckbox"  id="bun240" /><label for="bun240">240분</label>
			
		</td>
	</tr>
	<tr>
		<td class="left">개월</td>
		<td>
			<input type="checkbox" name="" class="tblCheckbox" id="month1" /><label for="month1">1개월</label>
			<input type="checkbox" name="" class="tblCheckbox" id="month12" /><label for="month2">2개월</label>
			<input type="checkbox" name="" class="tblCheckbox"  id="month3"/><label for="month3">3개월</label> 
			<input type="checkbox" name="" class="tblCheckbox"  id="month6"/><label for="month6">6개월</label> 
			<input type="checkbox" name="" class="tblCheckbox"  id="month12"/><label for="month12">12개월</label>
			
		</td>
	</tr>
	<tr>
		<td class="left">분류</td>
		<td>
			<input type="radio" name="lecType" checked /> <label>화상</label>
			<input type="radio" name="lecType" /> <label>녹화</label>
		</td>
	</tr>
</table>
<div class="btnGrp">
	<a href="" class="confirm">확인</a>
	<a href="" class="cancel">취소</a>
</div>
