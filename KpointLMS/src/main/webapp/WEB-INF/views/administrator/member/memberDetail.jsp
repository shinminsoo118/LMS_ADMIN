<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script>
	console.log('${result}');
	console.log('${memberUpdateVo}');
</script>
	<div class="catagoryTitle">
		사용자정보상세보기
	</div>
<div class="tableBox">
	<table class="inputTbl2">
		<colgroup>
			<col width="150px" />
			<col width="" />
		</colgroup>
		<tr>
			<th colspan="2">사용자정보</th>
		</tr>
		<tr>
			<td class="left">아이디</td>
			<td>${result.USERID }</td>
		</tr>
		<tr>
			<td class="left">이름</td>
			<td>${result.USERNAME }</td>
		</tr>
		<tr>
			<td class="left">성별</td>
			<td>남자</td>
		<tr>
			<td class="left">E-MAIL</td>
			<td>${result.USEREMAIL }</td>
		</tr>
		<tr>
			<td class="left">전화번호</td>
			<td>${result.USERTEL }</td>
		</tr>
		
		<tr>
			<td class="left">회원등급</td>
			<td>${result.USERROLE }</td>
		</tr>
		
		<tr>
			<td class="left">학습그룹</td>
			<td>학습 그룹이 없습니다.</td>
		</tr>
		<tr>
			<td class="left">회원가입일</td>
			<td>${result.USERREGDATE }</td>
		</tr>
		<tr>
			<td class="left">주소</td>
			<td>${result.USERADDR }</td>
		</tr>
		<tr>
			<td class="left">유저이미지</td>
			<td><img id="profileImg" src='${pageContext.request.contextPath}/upload/memberIcon/${result.USERIMG }'></td>
		</tr>
	</table>
</div>
	<div class="btnGrp">
		<a href="memberList.do?bid=Member&type=${param.type }&currentPageNo=${param.currentPageNo}" id="memberConfirm" class="confirm">목록으로</a>
		<a href="openMemberUpdate.do?USERID=${result.USERID }&type=${param.type }" class="update">수정하기</a>
	</div>


		