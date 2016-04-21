<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:url value="bid=${param.bid}&SEQ=${param.SEQ}&STATUS=${param.STATUS}&KEYWORD=${param.KEYWORD}&KEYSTRING=${param.KEYSTRING}" var="linkURL" />
<c:if test="${param.uStatus == 1 }" >
	<script>
		alert("성공적으로 환불하였습니다.");
		location.href = "lectureList.do?${linkURL}"
	</script>
</c:if>
<c:if test="${param.uStatus == 0 }" >
	<script>
		alert("환불 처리에 실패하였습니다.");
	</script>
</c:if>

<div class="catagoryTitle">
	교육과정 환불정보 상세정보
</div>
<div class="tableBox">
	<table class="inputTbl2">
		<colgroup>
			<col width="150px" />
			<col width="" />
		</colgroup>
		<tr>
			<th colspan="2">교육과정</th>
		</tr>
		<tr>
			<td class="left">상품코드</td>
			<td>${detail.PROCODE}${detail.CURCODE}</td>
		</tr>
		<tr>
			<td class="left">상품명</td>
			<td><b>${detail.CURPRONAME}</b>(${detail.CURCOURSE})</td>
		</tr>
		
		<tr>
			<td class="left">접수기간</td>
			<td>
			${detail.CURREGPERIOD}
			</td>
		</tr>
		<tr>
			<td class="left">수업방법</td>
			<td>
				${detail.CURSORT}
			</td>
		</tr>
		
		
		<tr>
			<td class="left">수업기간(개월)</td>
			<td>
				${detail.CURMONTH}(${detail.CURMONTHPERIOD})
			</td>
		</tr>
		<tr>
			<td class="left">수업시간<br/>(주중.주말)</td>
			<td>
				${detail.CURPROWEEKEND}
			</td>
		<tr>
			<td class="left">수업종류</td>
			<td>
				${detail.CURWEEKPERCOUNT}(${detail.CURPROWEEK})
			</td>
		</tr>
		<tr>
			<td class="left">수업시간(분)</td>
			<td>
			${detail.CURPROMINUTE}(${detail.CURTIMEPERIOD})
			</td>
		</tr>
		
		<tr>
			<td class="left">할인적용</td>
			<td>
				적용(할인율 : ${detail.CURSALERATE}%)
			</td>
		</tr>
		
		<tr>
			<td class="left">가격</td>
			<td>${detail.CURPRICE}원</td>
		</tr>
		
		<tr>
			<td class="left">할인가격</td>
			<td>${detail.CURSALEPRICE}원</td>
		</tr>
		<tr>
			<td class="left">강사선택</td>
			<td>
				${detail.CURTEACHER}
			</td>
		</tr>
		<tr>
			<td class="left">출석여부</td>
			<td>
			<c:if test='${detail.CURATTENDANT == "N" }'>
				체크안함
			</c:if>
			
			<c:if test='${detail.CURATTENDANT != "N" }'>
				체크함
			</c:if>
			
			</td>
		</tr>
		<tr>
			<td class="left">정원</td>
			<td>${detail.CURMEMBERCOUNT} 명</td>
		</tr>
		<tr>
			<td class="left">상태</td>
			<td>
			<c:choose>
				<c:when test='${detail.CURSTATUS == "wait"}'>
					모집중
				</c:when>
				<c:when test='${detail.CURSTATUS == "start"}'>
					진행중
				</c:when>
				<c:otherwise>
					종료
				</c:otherwise>
			</c:choose>
			</td>
		</tr>
		<tr>
			<td class="left">수강,결제여부</td>
			<td>${detail.CPAY} </td>
		</tr>
		<tr>
			<td class="left">수강,경제자</td>
			<td>${detail.CWRITER} </td>
		</tr>
		<tr>
			<td class="left">수강,결제 등록일시</td>
			<td>${detail.CREGDATE}</td>
		</tr>
		<tr>
			<td class="left">내용</td>
			<td>${detail.CONTENT}</td>
		</tr>
	</table>
</div>
	<div class="btnGrp">
		<%-- <a href="lectureRefindUpdate.do?${linkURL}&currentPageNo=${param.currentPageNo}" class="confirm">환불하기</a> --%>
		<a href="refundList.do?${linkURL}&currentPageNo=${param.currentPageNo}" class="cancel">취소</a>
	</div>


		