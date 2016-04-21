<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--<c:url value="bid=${param.bid}&KEYWORD=${param.KEYWORD}&KEYSTRING=${param.KEYSTRING}&" var="linkURL" />-->
<script>
	console.log('${detail}');
//	console.log('${memberUpdateVo}');
</script>
	<div class="catagoryTitle">
		메일발송 상세보기
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
			<td class="left">번호</td>
			<td>${detail.SEQ } </td>
		</tr>
		<tr>
			<td class="left">브드ID</td>
			<td>${detail.BOARDID }</td>
		</tr>
			<td class="left">GROUPS</td>
			<td>${detail.GROUPS }</td>
		</tr>
		<tr>
			<td class="left">작성자</td>
			<td>${detail.WRITER }</td>
		</tr>
		
		<tr>
			<td class="left">내용</td>
			<td>${detail.CONTENT }</td>
		</tr>
		
		<tr>
			<td class="left">그룹ID</td>
			<td>${detail.GROUPID }</td>
		</tr>
		<tr>
			<td class="left">받는 사람</td>
			<td>${detail.SENDTO }</td>
		</tr>
		<tr>
			<td class="left">참조하는 사람</td>
			<td>${detail.SENDCC }</td>
		</tr>
		<tr>
			<td class="left">등록일</td>
			<td>${detail.REGDATE }</td>
		</tr>
		<tr>
			<td class="left">참조하는 사람</td>
			<td>${detail.SENDCC }</td>
		</tr>
<%-- 		<tr>
			<td class="left">유저이미지</td>
			<td><img id="profileImg" src='${pageContext.request.contextPath}/upload/memberIcon/${result.USERIMG }'></td>
		</tr> --%>
	</table>
</div>
	<div class="btnGrp">
		<a href="mailList.do?bid=Mail&currentPageNo=${param.currentPageNo}" id="memberConfirm" class="confirm">목록으로</a>
	</div>


		