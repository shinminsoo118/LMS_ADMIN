<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tile" uri="http://tiles.apache.org/tags-tiles" %>
<c:url value="bid=${param.bid}&KEYWORD=${param.KEYWORD}&KEYSTRING=${param.KEYSTRING}&" var="linkURL" />
	<div class="catagoryTitle">
		종료된 룸
	</div>	
	<div class="searchBox">
		<form>
		<table class="searchTbl">
			<colgroup>
				<col width="150px" />
				<col width="" />
			</colgroup>
			<tr>
				<th>키워드검색</th>
				<td>
				<select class="board">
					<option value="">== 검색조건 ==</option>
				</select>
				<select>
					<option>== 그룹검색 ==</option>
				</select>
				<input type="text" size="30" class="tblInput" />
				<a href="" class="dBtn4">검색</a>
				</td>
			</tr>
		</table>
		</form>
	</div>
	<div class="tableBox">
		<table class="listTbl">
			<colgroup>
				<col width="5%" />
				<col width="5%" />
				<col width="15%" />
				<col width="40%" />
				<col width="10%" />
				<col width="10%" />
				<col width="10%" />
				<col width="5%" />
			</colgroup>
			<tr>
				<th><input type="checkbox" /></th>
				<th>번호</th>
				<th>수업명</th>
				<th>수업제목</th>
				<th>유저ID</th>
				<th>유저타입</th>
				<th>등록일</th>
				<th>-</th>
			</tr>
			<tr>				
				<td><input type="checkbox" /></td>
				<td>1</td>
				<td>수업명</td>
				<td class="left"><a href="classProcessRoomDetail.do">수업제목</a></td>
				<td>TOP119</td>
				<td>나태균</td>
				<td>2016.03.03</td>		
				<td class="lastTd">
					<a href="" class="btn cEdit"></a>
					<a href="" class="btn cDel" ></a>
				</td>
			</tr>
		</table>
	</div>
<!-- 	<div class="pagingGrp">
		<a href="" class="dBtn3 floatRight">학습룸등록</a>
		<a href="">1</a>
		<a href="">2</a>
		<a href="" class="selected">3</a>
		<a href="">4</a>
		<a href="">5</a>
	</div> -->		
