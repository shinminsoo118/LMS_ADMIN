<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script>
	console.log('${detail}');
//	console.log('${memberUpdateVo}');
</script>
	<div class="catagoryTitle">
		녹화파일등록
	</div>	
	<table class="inputTbl">
		<colgroup>
			<col width="120px" />
			<col width="20%" />
			<col width="120px" />
			<col width="20%" />
			<col width="120px" />
			<col width="20%" />
		</colgroup>
		<tr>
			<td class="left tblTitle" >
				<span class="viewTitle">제목</span>
			</td>
		</tr>
		<tr>
			<td class="left tblFont">
				<span class="viewWriter">작성자</span><span class="divLine">|</span> 
				조회수 : <span>1</span><span class="divLine">|</span> 
				작성일 : <span>2016.03.03</span><span class="divLine">|</span>
				추천수 : <span>0</span><span class="divLine">|</span>
				비추천수 : <span>0</span><span class="divLine">|</span>
			</td>
		</tr>
		<tr>
			<td class="left tblFont">첨부파일 : 첨부파일이 없습니다.</td>
		</tr>
		<tr>
			<td class="left aContent viewContent">
				내용
				 <div class="recommandBox">
					<a href="" class="recommendation">0</a>
					<a href="" class="norecommendation">0</a>
				 </div>
			</td>
		</tr>
	</table>
	<div class="memoBox">
		<div class="memoListBox"></div>
		<div class="memoInputBox">
			<form method="" action="">
				<div class="right"><a href="" id="CommentWrite">등록</a></div>
				<div class="left"><img src='/KpointLMS_New2/upload/memberIcon/default.png' width=48 height=48></div>
				<div class="center"><textarea id="COMMENT"></textarea></div>
			</form>
		</div>		
	</div>
	<div class="pagingGrp">
		<a href="" class="dBtn3">글쓰기</a>
		<a href="" class="dBtn3" >수정하기</a>
		<a href=""  class="dBtn3">목록보기</a>
		<a href="" class="dBtn5">삭제하기</a>
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
				<td>제목</td>
				<td>나태균</td>
				<td>2016.03.03</td>
				<td>1</td>				
				<td class="lastTd">
					<a href="" class="btn cEdit"></a>
					<a href="" class="btn cDel" ></a>
				</td>
			</tr>
			<tr class="selectedItem">				
				<td><input type="checkbox" /></td>
				<td>1</td>
				<td>ALL</td>
				<td>제목</td>
				<td>나태균</td>
				<td>2016.03.03</td>
				<td>1</td>				
				<td class="lastTd">
					<a href="" class="btn cEdit"></a>
					<a href="" class="btn cDel" ></a>
				</td>
			</tr>
			<tr>				
				<td><input type="checkbox" /></td>
				<td>1</td>
				<td>ALL</td>
				<td>제목</td>
				<td>나태균</td>
				<td>2016.03.03</td>
				<td>1</td>				
				<td class="lastTd">
					<a href="" class="btn cEdit"></a>
					<a href="" class="btn cDel" ></a>
				</td>
			</tr>
			<tr>				
				<td><input type="checkbox" /></td>
				<td>1</td>
				<td>ALL</td>
				<td>제목</td>
				<td>나태균</td>
				<td>2016.03.03</td>
				<td>1</td>				
				<td class="lastTd">
					<a href="" class="btn cEdit"></a>
					<a href="" class="btn cDel" ></a>
				</td>
			</tr>
			<tr>				
				<td><input type="checkbox" /></td>
				<td>1</td>
				<td>ALL</td>
				<td>제목</td>
				<td>나태균</td>
				<td>2016.03.03</td>
				<td>1</td>				
				<td class="lastTd">
					<a href="" class="btn cEdit"></a>
					<a href="" class="btn cDel" ></a>
				</td>
			</tr>
			<tr>				
				<td><input type="checkbox" /></td>
				<td>1</td>
				<td>ALL</td>
				<td>제목</td>
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
		<a href="">1</a>
		<a href="">2</a>
		<a href="" class="selected">3</a>
		<a href="">4</a>
		<a href="">5</a>
	</div>


		