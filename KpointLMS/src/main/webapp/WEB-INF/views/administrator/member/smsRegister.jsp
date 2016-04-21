<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tile" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!--<c:url value="bid=${param.bid}&type=${param.type}&KEYWORD=${param.KEYWORD}&KEYSTRING=${param.KEYSTRING}&" var="linkURL" />-->
<c:url value="${pageContext.request.contextPath}" var="context" />
<c:if test="${param.msg eq 'SUCCESS'}">
	<script>
		alert("SMS 발송을 완료 하였습니다.전송성공 : ${param.doneCount} 건 , 전송실패 : ${param.failCount} 건");
		location.href= "smsList.do?${linkURL}&currentPageNo=${param.currentPageNo}";
	</script>
</c:if>
<c:if test="${param.msg eq 'NOCACHE'}">
	<script>
		alert("COOL SMS 잔액이 부족합니다. 전송성공 : ${param.doneCount}건 , 전송실패 : ${param.failCount} 건");
	</script>
</c:if>
<c:if test="${param.msg eq 'FAIL'}">
	<script>
		alert("SMS 문자 발송을 실패하였습니다.");
	</script>
</c:if>
<c:if test="${param.msg eq 'NOSEND'}">
	<script>
		alert("SMS 발송한 번호 입력값이 없습니다.발송을 보내는데 실패하였습니다.");
	</script>
</c:if>
<div class="catagoryTitle">
	${boardConfig.BOARDNAME} SMS발송
</div>
<c:url value="/administrator/member/sendSMS.do?${_csrf.parameterName}=${_csrf.token}" var="actionURL" />
<form:form name="regForm" id="regForm"  action="${actionURL}" method="post" enctype="multipart/form-data" accept-charset="utf-8">
<input type="hidden" name="WRITER" value="${username}" />
<input type="hidden" name="bid" value="${param.bid}" />
<input type="hidden" name="currentPageNo" value="${param.currentPageNo}" />
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
<table class="inputTbl">
	<colgroup>
		<col width="120px" />
		<col width="" />
	</colgroup>
	<tr>
		<th>받는사람</th>
		<td class="left">
			<input type="text" name="sendTo" id="sendTo" size="30" id="sendTo" class="tblInput middleSize"  />
			<input type="button" name="button" id="smsPopup" value="주소록" >
		</td>
	</tr>
	<tr>
		<th>제목</th>
		<td class="left">
		<%-- <form:input path="TITLE" type="text" size="30" name="TITLE" id="TITLE" class="tblInput" value="${boardVo.TITLE}" /> --%>
		<input type="text" name="TITLE" size="30" id="TITLE" class="tblInput fullSize"  />
		</td>
	</tr>
	<tr>
		<th>작성자</th>
		<td class="left">
			${username}
		</td>
	</tr>
	<tr>
		<th>내용</th>
		<td class="left" id="editorTd">
			<!-- <textarea class="fullSize" name="CONTENT" id="CONTENT"></textarea> -->
			<textarea name="CONTENT" id="CONTENT" class="tblInput fullSize" ></textarea>
		</td>
	</tr>
</table>
</form:form>
<div class="pagingGrp">
	<a href="" class="dBtn3" id="smsSendBtn" >등록하기</a>
	<a href="smsList.do?bid=sms&currentPageNo=${param.currentPageNo}" class="dBtn5" >목록보기</a>
</div>
	<div class="popWrap" style="display:none;">
		<div id="popLayer" class="indexContent" >
		<div class="catagoryTitle">SMS 주소록</div>
			<form name="form" >
				<div class="popBtn">
					<input type="button" name="button" id="addSmsUser" value="개인" >
					<input type="button" name="button" id="addGroup" value="그룹" >
					<input type="button" name="button" id="checkboxTrue" value="전체선택" >
					<input type="button" name="button" id="checkboxFalse" value="전체해제" >
				</div> 
				<div class ="checkScroll">
				</div>
				<div class="selectBox">
					<div>
						<div class="popBtn center">
							<input type="button" name="button" value="TO추가" onClick="selectChkBox(this.form, 'to');">
							<input type="button" name="button" id="toRemove" value="TO삭제" >
						</div>
						<select name="selectBox" id="selectBox" size="7" multiple ></select>	
						<!-- <select name="selectBoxCC" id="selectBoxCC"  size="7" multiple ></select> -->	
					</div>
				</div>
			</form>
			<div class="pagingGrp">
				<a href="" class="dBtn3" id="addInput">OK</a>
				<a href="" class="dBtn5" id="addressPopupClose">CANCEL</a>
			</div>
		</div>
	</div>
