<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tile" uri="http://tiles.apache.org/tags-tiles" %>
<c:url value="bid=${param.bid}&KEYWORD=${param.KEYWORD}&KEYSTRING=${param.KEYSTRING}&" var="linkURL" />
	<div class="catagoryTitle">
		업로드설정
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
				<th>보디ID</th>
				<th>업로드 내용</th>
				<th>서브 패스</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>-</th>
			</tr>
			<tr>				
				<td><input type="checkbox" /></td>
				<td>1</td>
				<td>보디ID</td>
				<td>내용</td>
				<td>서브 패스</td>
				<td>나태균</td>
				<td>2016.03.03</td>		
				<td class="lastTd">
					<a href="" class="btn cEdit"></a>
					<a href="" class="btn cDel" ></a>
				</td>
			</tr>
			<tr>				
				<td><input type="checkbox" /></td>
				<td>1</td>
				<td>보디ID</td>
				<td>내용</td>
				<td>서브 패스</td>
				<td>나태균</td>
				<td>2016.03.03</td>		
				<td class="lastTd">
					<a href="" class="btn cEdit"></a>
					<a href="" class="btn cDel" ></a>
				</td>
			</tr>
			<tr>				
				<td><input type="checkbox" /></td>
				<td>1</td>
				<td>보디ID</td>
				<td>내용</td>
				<td>서브 패스</td>
				<td>나태균</td>
				<td>2016.03.03</td>		
				<td class="lastTd">
					<a href="" class="btn cEdit"></a>
					<a href="" class="btn cDel" ></a>
				</td>
			</tr>
			<tr>				
				<td><input type="checkbox" /></td>
				<td>1</td>
				<td>보디ID</td>
				<td>내용</td>
				<td>서브 패스</td>
				<td>나태균</td>
				<td>2016.03.03</td>		
				<td class="lastTd">
					<a href="" class="btn cEdit"></a>
					<a href="" class="btn cDel" ></a>
				</td>
			</tr>
			<tr>				
				<td><input type="checkbox" /></td>
				<td>1</td>
				<td>보디ID</td>
				<td>내용</td>
				<td>서브 패스</td>
				<td>나태균</td>
				<td>2016.03.03</td>		
				<td class="lastTd">
					<a href="" class="btn cEdit"></a>
					<a href="" class="btn cDel" ></a>
				</td>
			</tr>
			<tr>				
				<td><input type="checkbox" /></td>
				<td>1</td>
				<td>보디ID</td>
				<td>내용</td>
				<td>서브 패스</td>
				<td>나태균</td>
				<td>2016.03.03</td>		
				<td class="lastTd">
					<a href="" class="btn cEdit"></a>
					<a href="" class="btn cDel" ></a>
				</td>
			</tr>
		</table>
	</div>
	<div class="pagingGrp">
		<a href="uploadSettingRegister.do" class="dBtn3 floatRight">녹화파일등록</a>
		<a href="">1</a>
		<a href="">2</a>
		<a href="" class="selected">3</a>
		<a href="">4</a>
		<a href="">5</a>
	</div>		
