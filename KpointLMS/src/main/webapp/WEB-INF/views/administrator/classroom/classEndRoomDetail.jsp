<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tile" uri="http://tiles.apache.org/tags-tiles" %>
<c:url value="bid=${param.bid}&KEYWORD=${param.KEYWORD}&KEYSTRING=${param.KEYSTRING}&" var="linkURL" />
	<div class="catagoryTitle">
		진행중인 룸 상세보기
	</div>	
	<div class="tableBox">
	<form>
		<table class="inputTbl2">
			<colgroup>
				<col width="150px" />
				<col width="" />
			</colgroup>
			<tr>
				<th colspan="2">수업명</th>
			</tr>
			<tr>
				<td class="left">수업제목</td>
				<td><input type="text" size="70" class="tblInput" /></td>
			</tr>
			<tr>
				<td class="left">유저ID</td>
				<td><input type="text" size="70" class="tblInput" /></td>
			</tr>
			<tr>
				<td class="left">참석자</td>
				<td><input type="text" size="70" class="tblInput" /></td>
			</tr>
			<tr>
				<td class="left">유저명</td>
				<td><input type="text" size="70" class="tblInput" /></td>
			</tr>
			<tr>
				<td class="left">유저타입</td>
				<td><input type="text" size="70" class="tblInput" /></td>
			</tr>
			<tr>
				<td class="left">디바이스</td>
				<td><input type="text" size="70" class="tblInput" /></td>
			</tr>
			<tr>
				<td class="left">룸상태</td>
				<td><input type="text" size="70" class="tblInput" /></td>
			</tr>
			<tr>
				<td class="left">등록일</td>
				<td><input type="text" size="70" class="tblInput" /></td>
			</tr>
		</table>
	</form>
	</div>
	<div class="btnGrp">
		<a href="" class="confirm">확인</a>
		<a href="" class="cancel">취소</a>
	</div>
