<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>			
	<div class="catagoryTitle">
		결제정보관리
	</div>
	<div class="searchBox">
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
				<select>
					<option>== 통합검색 ==</option>
				</select>
				<input type="text" size="30" class="tblInput" />
				</td>
				<th>PG사명</th>
				<td>
					<select>
					<option>== PG사명으로 검색 ==</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>등록일</th>
				<td colspan="3">
					<input type="text" size="20" class="tblInput" /> <a href="" class="calendar"></a>
					- 
					<input type="text" size="20" class="tblInput" /> <a href="" class="calendar"></a>
				</td>
				
			</tr>
		</table>
		<div class="searchBtnGrp">
			<a href="" class="confirm">검색</a>
		</div>	
	</div>
	<div class="tableBox">
		<table class="listTbl">
			<colgroup>
				<col width="5%" />
				<col width="5%" />
				<col width="35%" />
				<col width="10%" />
				<col width="10%" />
				<col width="5%" />
				<col width="5%" />
			</colgroup>
			<tr>
				<th><input type="checkbox" /></th>
				<th>번호</th>
				<th>PG사명</th>
				<th>작성자</th>
				<th>등록일</th>
				<th>조회수</th>
				<th>-</th>
			</tr>
			<tr>
				<td><input type="checkbox" /></td>
				<td>1</td>
				<td><a href="paymentDetail.do">pg</a></td>
				<td>나태균</td>
				<td>2016.03.03</td>
				<td>1</td>				
				<td class="lastTd">
					<a href="Edit.do?${linkURL}" class="btn cEdit"></a>
					<a href="javascript:fn_BoardDelete('${linkURL}')" class="btn cDel" ></a>
				</td>
			</tr>
			
		</table>
	</div>
	<div class="pagingGrp">
		<a href="payment.do" class="dBtn3 floatRight">결제정보등록</a>
		<a href="">1</a>
		<a href="">2</a>
		<a href="" class="selected">3</a>
		<a href="">4</a>
		<a href="">5</a>
	</div>
