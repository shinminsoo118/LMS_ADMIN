<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tile" uri="http://tiles.apache.org/tags-tiles" %>
 <c:url value="bid=${param.bid}&SEQ=${param.SEQ}&KEYWORD=${param.KEYWORD}&KEYSTRING=${param.KEYSTRING}" var="linkURL" />

 <!-- 페이지 로딩했을때 뎃글목록을 가져오는 부분 -->
 <script>
 	$(function(){
 		//글상세보기를 했을때 상세보기 글에 해당하는 코멘트 항목을 가져옴.
 		fn_getCommentList(bid, seq);
 	})
 </script>
<div class="catagoryTitle">
	${boardConfig.BOARDNAME} 상세보기 
</div>
<div class="tableBox">
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
			<span class="viewTitle">${detail.TITLE}</span>
		</td>
	</tr>
	<tr>
		<td class="left tblFont">
			<span class="viewWriter">${detail.USERNAME}</span><span class="divLine">|</span> 
			조회수 : <span>${detail.HIT}</span><span class="divLine">|</span> 
			작성일 : <span><fmt:formatDate pattern="yyyy.MM.dd HH:mm:ss" value="${detail.REGDATE}" /></span><span class="divLine">|</span>
			<c:if test='${ boardConfig.ISRECOMMANDATION == "Y" }'>
				추천수 : <span id="recommandationTxt">${detail.RECOMMANDATION}</span><span class="divLine">|</span>
			</c:if>
			<c:if test='${ boardConfig.ISNORECOMMANDATION == "Y" }'>
				비추천수 : <span id="noRecommandationTxt">${detail.NORECOMMANDATION}</span>
			</c:if>
		</td>
	</tr>
	<c:choose>
		<c:when test="${fileList.size() != 0}">
			<tr>
				<td class="left tdFiles">
					<span class="fileTxt">첨부파일 : </span>
					<div class="fileListBox1">
						<c:forEach var="flist" items="${fileList}">
							<a href="${pageContext.request.contextPath}/common/Download.do?FILESEQ=${flist.FILESEQ}">${flist.FILENAME} (<fmt:formatNumber value="${flist.FILESIZE}" pattern="#,###.#" /> KB)</a><br/>
						</c:forEach>
					</div>
				</td>
			</tr>
		</c:when>
		<c:otherwise>
			<tr>
				<td class="left tdFiles">
					<span class="fileTxt">첨부파일 : </span>
					<div class="fileListBox">
						첨부파일이 없습니다.
					</div>
				</td>
			</tr>
		</c:otherwise>
	</c:choose>
	<tr>
		<td class="left aContent viewContent">
			 <%-- ${fn:replace(detail.CONTENT, lf ,"<br/>")} --%>
			 ${detail.CONTENT}
			 <div class="recommandBox">
			 	<c:if test='${ boardConfig.ISRECOMMANDATION == "Y" }'>
			 		<a href="javascript:fn_Recommandation('Recommandation.do','${param.bid}','${param.SEQ}','${detail.RECOMMANDATION}');" class="recommendation" id="recommandationBtn">${detail.RECOMMANDATION}</a>
			 	</c:if>
			 	<c:if test='${ boardConfig.ISNORECOMMANDATION == "Y" }'>
			 		<a href="javascript:fn_NoRecommandation('NoRecommandation.do','${param.bid}','${param.SEQ}','${detail.NORECOMMANDATION}');" class="norecommendation" id="noRecommandationBtn">${detail.NORECOMMANDATION}</a>
			 	</c:if>
			 </div>
		</td>
	</tr>
</table>
</div>

<c:if test='${ boardConfig.ISCOMMENT == "Y" }'>
	<div class="memoBox">
		<div class="memoListBox" id="commentListBox"></div>
		<div class="memoInputBox">
			<form id="CommentForm" name="CommentForm" method="POST" action="CommentInsert.do">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				<input type="hidden" name="bid" id="bid" value="${param.bid}" />
				<input type="hidden" name="BOARDSEQ" id="BOARDSEQ" value="${param.SEQ}" />
				<input type="hidden" name="WRITER" id="WRITER" value="${username}" />
				<div class="right"><a href="" id="CommentWrite">등록</a></div>
				<sec:authentication property="principal.userImg" var="userImg"/>
				<div class="left"><img src='<c:url value="/upload/memberIcon/" />${userImg}' width=48 height=48></div>
				<div class="center"><textarea name="COMMENT" id="COMMENT"></textarea></div>
			</form>
		</div>
		
	</div>
</c:if>

<%-- <sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERADMIN')" var="adminRole" />
<sec:authentication property="principal.username" var="username"/> --%>

<div class="pagingGrp">
	<a href="documentReg.do?bid=${param.bid}"  class="dBtn3">글쓰기</a>
	<c:if test="${username == detail.WRITER || adminRole == true}">		
		<a href="getDocumentUpdate.do?currentPageNo=${pageConfig.currentPageNo}&${linkURL}" class="dBtn3" >수정하기</a>
	</c:if>
	<a href="documentList.do?currentPageNo=${pageConfig.currentPageNo}&${linkURL}"  class="dBtn3">목록보기</a>
	<c:if test="${username == detail.WRITER || adminRole == true}">		
		<a href="javascript:fn_BoardDelete('${linkURL}&GROUPID=${detail.GROUPID}&DEPTH=${detail.DEPTH}&ORDERINDEX=${detail.ORDERINDEX}&BOARDSEQ=${detail.SEQ}')" class="dBtn5">삭제하기</a>
	</c:if>
</div>

<tile:insertTemplate template="ListTemplate.jsp" />

<div class="pagingGrp">
	<a href="documentDetail.do?currentPageNo=${pageConfig.currentPageNo - 10}&${linkURL}">이전</a>
	<c:forEach var="i" begin="${pageConfig.startPage}" end="${pageConfig.endPage}">
		<c:if test="${ i == pageConfig.currentPageNo }">
			<strong>${i}</strong>
		</c:if>
		<c:if test="${ i != pageConfig.currentPageNo }">
			<a href="documentDetail.do?currentPageNo=${i}&${linkURL}">${i}</a>
		</c:if>
	</c:forEach>
	<a href="documentDetail.do?currentPageNo=${pageConfig.currentPageNo + 10}&${linkURL}">다음</a>
</div> 