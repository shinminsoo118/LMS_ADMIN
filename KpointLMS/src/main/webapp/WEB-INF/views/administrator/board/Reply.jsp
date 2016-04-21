<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tile" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="catagoryTitle">
	${boardConfig.BOARDNAME}
</div>

<c:url value="/administrator/board/ReplyInsert.do" var="actionURL" />
<form:form id="replyForm" name="replyForm" commandName="boardVo" action="${actionURL}"  method="post">
	<input type="hidden" name="WRITER" value="${username}" />
	<input type="hidden" name="bid" value="${param.bid}" />
	<input type="hidden" name="GROUPLIST" value="${param.GROUPLIST}" />
	<input type="hidden" name="GROUPID" value="${param.GROUPID}" />
	<input type="hidden" name="DEPTH" value="${param.DEPTH}" />
	<input type="hidden" name="ORDERINDEX" value="${param.ORDERINDEX}" />
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
<table class="inputTbl">
	<colgroup>
		<col width="120px" />
		<col width="" />
	</colgroup>
	
	<tr>
		<th>제목</th>
		<td class="left">
		<form:input path="TITLE" size="30" class="tblInput fullSize" value="${boardVo.TITLE}" />
		<form:errors path="TITLE" cssClass="error"></form:errors>
		</td>
	</tr>
	<tr>
		<th>작성자</th>
		<td class="left">${username}</td>
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
			<form:textarea path="CONTENT" class="fullSize" style="display:none"/>
			<form:errors path="CONTENT" cssClass="error"></form:errors>
		</td>	
	</tr>
</table>
<div class="pagingGrp">
	<a href="javascript:saveContent();" class="dBtn3" >답글쓰기</a>
	<a href="javascript:history.back();"  class="dBtn5"  >목록보기</a>
</div>
</form:form>
<script>
	Editor.modify({
		"content":$tx('CONTENT')
	})
</script>