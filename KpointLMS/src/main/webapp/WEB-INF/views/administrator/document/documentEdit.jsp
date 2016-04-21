<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tile" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="catagoryTitle">
	${boardConfig.BOARDNAME} 수정
</div>
<c:url value="/administrator/document/setDocumentUpdate.do?${_csrf.parameterName}=${_csrf.token}" var="actionURL" />
<form id="updateForm" name="updateForm" method="post" action="${actionURL}" enctype="multipart/form-data">
<input type="hidden" name="SEQ" value="${update.SEQ}" />
<input type="hidden" name="bid" value="${param.bid}" />
<input type="hidden" name="WRITER" value="${username}" />
<input type="hidden" name="currentPageNo" value="${param.currentPageNo}" />
<input type="hidden" name="KEYWORD" value="${param.KEYWORD}" />
<input type="hidden" name="KEYSTRING" value="${param.KEYSTRING}" />
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
<table class="inputTbl">
	<colgroup>
		<col width="120px" />
		<col width="" />
	</colgroup>
	<tr>
		<th>제목</th>
		<td class="left"><input type="text" size="30" name="TITLE" id="TITLE" value="${update.TITLE}" class="tblInput fullSize" /></td>
	</tr>
	<tr>
		<th>작성자</th>
		<td class="left">${username}</td>
	</tr>
	<tr>
		<th>첨부파일</th>
		<td class="left">
			<div class="fileBox">
				
				<c:if test="${fileList.size() != 0 }">
					<c:forEach items="${fileList}" var="filelist">
						<div class="hasFileList">
							${filelist.FILENAME} <a href="FileDelete.do?bid=${param.bid}&FILESEQ=${filelist.FILESEQ}">삭제</a>
						</div>
					</c:forEach>
				</c:if>
				<div class="fileBtnBox" id="fileBtnBox">
					<a href="" class="dBtn4 fileAdd" id="fileAdd">파일추가</a>
				</div>
				<div class="fileListBox" id="fileBoxList">
					<input type="file" size="30" name="file" class="tblInput2 fullSize" />
				</div>
			</div>
		</td>
	</tr>
	
	<tr>
		<th>내용</th>
		<td class="left" id="editorTd">
			<textarea class="fullSize" name="CONTENT" id="CONTENT" style="display:none;">${update.CONTENT}</textarea>
			<tile:insertTemplate template="/daumeditor/editor.jsp" />
			<%-- <form:textarea path="CONTENT" id="CONTENT" class="fullSize" style="display:none" value="${update.CONTENT}" />
			<form:errors path="CONTENT" id="CONTENT" cssClass="error"></form:errors> --%>
		</td>
	</tr>
</table>
<div class="pagingGrp">
	<a href="javascript:saveContent();" class="dBtn3"  >수정하기</a>
	<a href="javascript:history.back();" class="dBtn5">돌아가기</a>
</div>
</form>
<script>
	Editor.modify({
		"content":$tx('CONTENT')
	})
</script>