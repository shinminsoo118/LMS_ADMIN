<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tile" uri="http://tiles.apache.org/tags-tiles" %>
<c:url value="bid=${param.bid}&KEYWORD=${param.KEYWORD}&KEYSTRING=${param.KEYSTRING}&" var="linkURL" />
	<div class="catagoryTitle">
		녹화파일목록
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
				<col width="45%" />
				<col width="10%" />
				<col width="10%" />
				<col width="5%" />
				<col width="5%" />
			</colgroup>
			<tr>
				<th><input type="checkbox" /></th>
				<th>번호</th>
				<th>그룹</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>조회수</th>
				<th>-</th>
			</tr>
			<tr>				
				<td><input type="checkbox" /></td>
				<td>1</td>
				<td>ALL</td>
				<td><a href="recordingDetail.do">제목</a></td>
				<td>나태균</td>
				<td>2016.03.03</td>
				<td>1</td>				
				<td class="lastTd">
					<a href="" class="btn cEdit"></a>
					<a href="" class="btn cDel" ></a>
				</td>
			</tr>
		</table>
	</div>
	<div class="pagingGrp">
		<a href="recordingRegister.do" class="dBtn3 floatRight">녹화파일등록</a>
		<a href="">1</a>
		<a href="">2</a>
		<a href="" class="selected">3</a>
		<a href="">4</a>
		<a href="">5</a>
	</div>	
