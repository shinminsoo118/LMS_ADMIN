<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tile" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!--<c:url value="bid=${param.bid}&type=${param.type}&KEYWORD=${param.KEYWORD}&KEYSTRING=${param.KEYSTRING}&" var="linkURL" />-->
<c:url value="${pageContext.request.contextPath}" var="context" />
<c:if test="${param.retVal eq 'cancel'}">
	<script>alert("50건이상 보낼수 없습니다.")</script>
</c:if>
<div class="catagoryTitle">
	${boardConfig.BOARDNAME} 메일발송
</div>
<c:url value="/administrator/member/mailSend.do?${_csrf.parameterName}=${_csrf.token}" var="actionURL" />
<form:form name="regForm" id="regForm"  action="${actionURL}" method="post" enctype="multipart/form-data" accept-charset="utf-8">
<input type="hidden" name="WRITER" value="${username}" />
<input type="hidden" name="bid" value="${param.bid}" />
<input type="hidden" name="type" value="${param.type}" />
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
			<input type="text" name="sendTo" id="sendTo" size="30" id="sendTo" class="tblInput middleSize" value="" />
			<input type="button" name="button" id="addressPopup" value="주소록" >
		</td>
	</tr>
	<tr>
		<th>참조 </th>
		<td class="left">
			<input type="text" name="sendCC" id="sendCC" size="30" id="sendCC" class="tblInput fullSize" value="" />
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
	<!-- <tr>
		<th>첨부파일</th>
		<td class="left">
			<div class="fileBox">
				<div class="fileBtnBox" id="fileBtnBox">
					<a href="" class="dBtn4 fileAdd" id="fileAdd">파일추가</a>
				</div>
				<div class="fileListBox" id="fileBoxList">
					<input type="file" size="30" name="file" class="tblInput2 fullSize" />
				</div>
			</div>
		</td>
	</tr> -->
	
	<tr>
		<th>내용</th>
		<td class="left" id="editorTd">
			<!-- <textarea class="fullSize" name="CONTENT" id="CONTENT"></textarea> -->
			<tile:insertTemplate template="/daumeditor/editor.jsp" />
			<textarea name="CONTENT" id="CONTENT" class="fullSize" style="display:none"></textarea>
		</td>
	</tr>
</table>
</form:form>
<div class="pagingGrp">
	<a href="javascript:saveContent();" class="dBtn3"  >등록하기</a>
	<a href="mailList.do?bid=Mail&currentPageNo=${param.currentPageNo}" class="dBtn5" >목록보기</a>
</div>
<%-- 	<div id="popLayer" class="indexContent" style="display:none;">
		<form name="form" >
			<input type="button" name="button" id="addUser" value="개인" >
			<input type="button" name="button" id="addGroup" value="그룹" >
			<input type="button" name="button" id="checkboxTrue" value="전체선택" >
			<input type="button" name="button" id="checkboxFalse" value="전체해제" > 
			<div class ="checkScroll">
			</div>
			<input type="button" name="button" value="TO추가" onClick="selectChkBox(this.form, 'to');">
			<input type="button" name="button" id="toRemove" value="TO삭제" >                     
			<input type="button" name="button" value="CC추가" onClick="selectChkBox(this.form, 'cc');">
			<input type="button" name="button" id="ccRemove" value="CC삭제" >
			<br><br>
			<select name="selectBox" id="selectBox" style="width:200px;height:100px;" size="7" multiple ></select>
			<select name="selectBoxCC" id="selectBoxCC" style="width:200px;height:100px;" size="7" multiple ></select>
		</form>
		<div class="indexBtnGrp">
			<input type="button" name="button" id="addInput" value="OK" >
			<input type="button" name="button" id="addressPopupClose" value="CANCEL" >
		</div>
	
	</div> --%>
	<div class="popWrap" style="display:none;">
		<div id="popLayer" class="indexContent">
			<div class="catagoryTitle">메일 주소록</div>
			<form name="form">
				<div class="popBtn">
					<input type="button" name="button" id="addUser" class="selected" value="개인" >
					<input type="button" name="button" id="addGroup" value="그룹" >
					<input type="button" name="button" id="checkboxTrue" value="전체선택" >
					<input type="button" name="button" id="checkboxFalse" value="전체해제" > 
				</div>
				<div class="checkScroll">
					<input name="checkbox" type="checkbox" value="체크박스1">체크박스1<br>
					<input name="checkbox" type="checkbox" value="체크박스2">체크박스2<br>
					<input name="checkbox" type="checkbox" value="체크박스3">체크박스3<br>
					<input name="checkbox" type="checkbox" value="체크박스4">체크박스4<br>
					<input name="checkbox" type="checkbox" value="체크박스5">체크박스5<br>
					<input name="checkbox" type="checkbox" value="체크박스6">체크박스6<br>
					<input name="checkbox" type="checkbox" value="체크박스7">체크박스7<br>
				</div>			
				<div class="selectBox">
					<div class="toBox">
						<div class="popBtn center">
							<input type="button" name="button" value="TO추가" onClick="selectChkBox(this.form, 'to');">
							<input type="button" name="button" id="toRemove" value="TO삭제" >
						</div>
						<select name="selectBox" id="selectBox" size="7" multiple ></select>		
					</div>
					<div class="ccBox">
						<div class="popBtn center">
							<input type="button" name="button" value="CC추가" onClick="selectChkBox(this.form, 'cc');">
							<input type="button" name="button" id="ccRemove" value="CC삭제" >
						</div>
						<select name="selectBoxCC" id="selectBoxCC" size="7" multiple ></select>
					</div>
				</div>
			</form>
			<div class="pagingGrp">
				<a href="" class="dBtn3" id="addInput">OK</a>
				<a href="" class="dBtn5" id="addressPopupClose">CANCEL</a>
			</div>	
		</div>
	</div>

<%-- </form> --%>
<script>
	Editor.modify({
		"content":$tx('CONTENT')
	})
</script>

<script>
	
</script>