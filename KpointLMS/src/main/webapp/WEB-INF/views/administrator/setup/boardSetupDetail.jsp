<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tile" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script src="${pageContext.request.contextPath}/js/setup/boardsetup.controller.js"></script>
<script type="text/javascript"> 
	console.log("${param}");
	var linkURL = "${linkURL}";
	console.log("!!!!!!!!!!!!!!!!!!!!" + linkURL);
	var _csrf = "${_csrf.token}";
	var _csrfParameter = "${_csrf.parameterName}"
</script>
<div class="catagoryTitle">
	보드상세설정파일
</div>
<c:url value="/administrator/setup/boardSetupDetailUpdate.do" var="actionURL" />
<form:form name="updateForm" id="updateForm"  action="${actionURL}" method="post" enctype="multipart/form-data" accept-charset="utf-8">
<input type="hidden" name="WRITER" value="${username}" />
<input type="hidden" name="bid" value="${param.bid}" />
<%-- <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> --%>
<div class="tableBox">
<table class="inputTbl2">
	<colgroup>
		<col width="150px" />
		<col width="40%" />
		<col width="150px" />
		<col width="" />
	</colgroup>
	<tr>
		<th colspan="4">보드세부설정파일</th>
	</tr>
	<tr>
		<td class="left">보드아이디</td>
		<td><input type="text" size="15" name="BOARDID" id="BOARDID" class="tblInput" pattern="2" value="${detail.BOARDID}"  disabled /></td>
		<td class="left">테이블명</td>
		<td>
			<select id="selectTableList" class="smallSelect" disabled >
				
			</select>
		</td>
	</tr>
	<tr>
		<td class="left">보드제목</td>
		<td colspan="3"><input type="text" size="50" name="BOARDNAME"  pattern="10" class="tblInput" value="${detail.BOARDNAME}" /></td>
	</tr>
	<tr>
		<td class="left">사용여부</td>
		<td colspan="3"> 
			답글 - <input type="checkbox"  class="tblInput" name="ISREPLY" id="ISREPLY" value="N" />사용
			&nbsp;&nbsp; 코멘트 - <input type="checkbox"  class="tblInput" name="ISCOMMENT" id="ISCOMMENT" value="N"/>사용
			&nbsp;&nbsp; 추천 - <input type="checkbox"   class="tblInput" name="ISRECOMMANDATION" id="ISRECOMMANDATION" value="N"/>사용
			&nbsp;&nbsp; 비추천 - <input type="checkbox" class="tblInput"  name="ISNORECOMMANDATION" id="ISNORECOMMANDATION" value="N"  />사용
		</td>
	</tr>
	<tr>
		<td class="left" rowspan="2">권한설정</td>
		<td colspan="3">
			글읽기 - <span id="VIEWS"></span>
			&nbsp;&nbsp; 글쓰기 - <span id="REGS"></span>
			&nbsp;&nbsp; 답변글 - <span id="REPLYS"></span>
		</td>
	</tr>
	<tr>
		<td colspan="3">
			글수정 - <span id="EDITS"></span>
			&nbsp;&nbsp; 글삭제 - <span id="DELS"></span>
			&nbsp;&nbsp; 코멘트 - <span id="COMMENTS"></span>
			
		</td>
	</tr>
	
	<tr>
		<td class="left">글제목 글자수</td>
		<td colspan="3">
			<input type="text" size="3" name="TITLELENGTH" class="tblInput" pattern="0" value="${detail.TITLELENGTH}" />
			목록에서의 제목 글자수. (잘리는 글은 ... 로 표시)
		</td>
	</tr>
	<tr>
		<td class="left">페이지당 목록수</td>
		<td colspan="3">
			<input type="text" size="3" name="LISTPERPAGE" class="tblInput" pattern="0" value="${detail.LISTPERPAGE}" />
			한페이지에 나타날 목록수
		</td>
	</tr>
	<tr>
		<td class="left">페이지수</td>
		<td colspan="3">
			<input type="text" size="3" name="PAGEPERBLOCK" class="tblInput" pattern="0" value="${detail.PAGEPERBLOCK}" />
			한페이지에 나타날 페이수
		</td>
	</tr>
</table>
</div>
<div class="btnGrp">
	<a href="" class="confirm" id="boardDetailUpdate">확인</a>
	<a href="javascript:history.back()" class="cancel">취소</a>
</div>
</form:form>
<script>
	var list = '${detail.LIST}';
	var view = '${detail.VIEW}';
	var reg = '${detail.REG}';
	var edit = '${detail.EDIT}';
	var reply = '${detail.REPLY}';
	var comment = '${detail.COMMENT}';
	var del = '${detail.DEL}';
	var isReply = '${detail.ISREPLY}';
	var isComment = '${detail.ISCOMMENT}';
	var isRecommandation = '${detail.ISRECOMMANDATION}';
	var isNoRecommandation = '${detail.ISNORECOMMANDATION}';
	var tableList = '${tableList}';
	var selectTableList = '${detail.BOARDTBL}';
	var userRole = '${userRole}';
	fn_settingBoardConfig(list, view, reg, edit, reply, comment, del, isReply, isComment, isRecommandation, isNoRecommandation, tableList, selectTableList, userRole);
</script>
