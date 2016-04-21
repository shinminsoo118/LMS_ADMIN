<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:url value="bid=${param.bid}&KEYWORD=${param.KEYWORD}&KEYSTRING=${param.KEYSTRING}&" var="linkURL" />
<div class="tableBox">
	<table class="listTbl">
		<colgroup>
			<col width="5%" />
			<col width="5%" />
			<col width="15%" />
			<col width="" />
			<col width="10%" />
			<col width="10%" />
			<col width="8%" />
			<col width="5%" />
		</colgroup>
		<tr>
			<th><input type="checkbox" /></th>
			<th>번호</th>
			<th>그룹</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회수</th>
			<th>-</th>
		</tr>
			<c:if test="${list.size() != 0 }">
				<c:forEach var="board" items="${list}" varStatus ="st">
					<c:if test="${detail.SEQ == board.SEQ}">
						<tr class="selectedItem">
					</c:if>
					<c:if test="${detail.SEQ != board.SEQ}">
						<tr>
					</c:if>
						<td><input type="checkbox" /></td>
						<td>
							${pageConfig.totalListCount - (pageConfig.currentPageNo - 1)* boardConfig.LISTPERPAGE - st.index}
						</td>
						<td>${board.GROUPS}</td>
						<td class="left">
							<%-- ${board.GROUPID } b${board.DEPTH } c${board.REORDER } --%>
							
							<a href="documentDetail.do?SEQ=${board.SEQ}&currentPageNo=${param.currentPageNo}&${linkURL}" class="listItem">
							
							<c:if test="${board.DEPTH > 0}">
								<%-- <c:forEach begin="1" end="${board.DEPTH}">
									&nbsp;&nbsp;=>
									
								</c:forEach> --%>
								<span class="replyIcon" style="margin-left:${board.DEPTH * 15}px"></span>
							</c:if>
							${board.TITLE}
								<c:if test="${ board.COMMENTCNT != 0 }">
								<span class="commentCnt">(${board.COMMENTCNT})</span>
								</c:if>
							</a>
						</td>
						<td>${board.USERNAME}</td>
						<td><fmt:formatDate pattern="yyyy.MM.dd" value="${board.REGDATE}" /></td>
						<td>${board.HIT}</td>
						<td class="lastTd">
							<a href="Edit.do?${linkURL}" class="btn cEdit"></a>
							<a href="javascript:fn_BoardDelete('${linkURL}')" class="btn cDel" ></a>
						</td>
					</tr>
				</c:forEach>
			</c:if>
		<c:if test="${list.size() == 0 }">
		<tr>
			<td colspan="7" class="center">
				목록이 존재하지 않습니다.
			</td>
		</tr>
		</c:if>
	</table>
</div>

