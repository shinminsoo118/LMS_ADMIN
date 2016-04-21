/////////////// 기본변수값 설정 //////////////////////////
/*
 * 해당변수의 초기값 설정은 /inc/common에서 설정.
 */
var contextPath; //ContextPath경로
var bid; //게시판 아이디
var seq; //글번호
var adminRole; //admin 권한여부
var userName;  //해당글의 작성자.
/////////////////////////////////////////////////////////

$(function(){
	//***************** 게시판 부분 ********************//
	//글목록에서 검색하기 버튼을 눌렀을때.
	$("#BoardSearch").on("click", function(e){
		e.preventDefault();
		console.log($("#KEYWORD").val())
		if($("#KEYWORD").val() == undefined || $("#KEYWORD").val() == ""){
			alert("검색조건을 선택해 주세요.");
			$("#KEYWORD").focus();
			return false;
		}else if($("#KEYSTRING").val() == ""){
			alert("검색어를 입력해 주세요.");
			$("#KEYSTRING").focus();
			return false;
		}else{
			$("#boardSearchForm")[0].submit();
			return true;
		}
	});
	
	$("#DocSearch").on("click", function(e){
		e.preventDefault();
		console.log($("#KEYWORD").val())
		if($("#KEYWORD").val() == undefined || $("#KEYWORD").val() == ""){
			alert("검색조건을 선택해 주세요.");
			$("#KEYWORD").focus();
			return false;
		}else if($("#KEYSTRING").val() == ""){
			alert("검색어를 입력해 주세요.");
			$("#KEYSTRING").focus();
			return false;
		}else{
			$("#boardSearchForm")[0].submit();
			return true;
		}
	});
	
	//삭제하기버튼을 눌렀을때
	$("#Delete").on("click", function(e){
		e.preventDefault();
		var bid = $(this).attr("bid");
		var seq = $(this).attr("seq");
		var contextPath = $(this).attr("href");
		fn_BoardDelete(contextPath, seq, bid);
	});
	
	//파일업로드 input추가
	$("#fileAdd").on("click", function(e){
		e.preventDefault();
		var fileCount = $("input[type='file']").length;
		fn_FileAdd(fileCount);
	});
	
	//파일업로드 input삭제
	$(document).on("click", ".fileDel", function(e){
		e.preventDefault();
		var index = $(this).attr("href");
		console.log(index);
		$("#fileDel_"+index).remove();
		$("input[name='file_"+index+"']").remove();
	});
	
	//코멘트 글남기기
	$("#CommentWrite").on("click", function(e){
		e.preventDefault();
		if($("#COMMENT").val() == ""){
			alert("코멘트를 작성하셔야 합니다.");
			$("#COMMENT").focus();
		}else{
			fn_CommentInsert();
		}
	});
	
	//글수정페이지에서 파일삭제를 눌렀을때.
	$(".hasFileList a").on("click", function(e){
		e.preventDefault();
		var fileURL = $(this).attr("href");
		var obj = $(this).parent();
		var msg = "해당파일을 삭제하시겠습니까?";
		if(confirm(msg) == true){
			fn_FileDelete(fileURL, obj);
		}
	});
	
	//코멘트에서 답글, 수정하기 버튼을 눌렀을때.
	$(document).on("click", "a.cWrite, a.cEdit", function(e){
		e.preventDefault();
		var writeType = $(this).attr("class");
		var seq = $(this).attr("href");
		var cId = $(this).attr("cId");
		var cDepth = $(this).attr("cDepth");
		var cIndex = $(this).attr("cIndex");
		fn_CommentEdit(seq, writeType, cId, cDepth, cIndex);
	});
	
	//코멘트 답글을 수정하는 부분
	$(document).on("click", "#cEdit", function(e){
		e.preventDefault();
		//console.log("답글수정")
		if($("#RECOMMENT").val() == ""){
			alert("내용을 입력하셔야 합니다.");
			$("#RECOMMENT").focus();
			return false;
		}else{
			var seq = $("#CommentUpdateForm input[name='SEQ']").val();
			fn_CommentUpdate(seq);
		}
	});
	
	//코멘트 답글을 새로 등록하는 부분
	$(document).on("click", "#cReply", function(e){
		e.preventDefault();
		if($("#RECOMMENT").val() == ""){
			alert("내용을 입력하셔야 합니다.");
			$("#RECOMMENT").focus();
			return false;
		}else{
			fn_CommentReplyInsert();
		}
	});
	
	//코멘트 삭제버튼을 눌렀을때.
	$(document).on("click",".cDel", function(e){
		e.preventDefault();
		var bid = $("#bid").val();
		var bSeq = $(this).attr("cseq");
		var cSeq = $(this).attr("href");
		var groupId = $(this).attr("cid");
		var depth  = $(this).attr("cdepth");
		var orderIndex = $(this).attr("cindex");
		fn_CommentDelete(bid, bSeq, cSeq, groupId, depth, orderIndex);
	});
	
	
	//***************** 게시판 부분 ********************//
});

////////////////////// 관련함수들 ////////////////////////
//******************* 게시판 부분 **********************//

//상세페이지에서 삭제버튼을 눌렀을때.
function fn_BoardDelete(linkURL){
	console.log(linkURL);
	var str = "해당항목을 삭제하시겠습니까?";
	if(confirm(str) == true){
		location.href="Delete.do?"+linkURL;
	}
}

function fn_FileAdd(count){
	console.log("fileCount = "+count);
	var inputStr = "<input type='file' size='30' name='file_" + count + "' class='tblInput2 fullSize'>";
	var inputBtn = "<a href='"+count+"' class='dBtn4 fileDel' id='fileDel_"+count+"'>파일삭제</a>";
	$("#fileBoxList").append(inputStr);
	$("#fileBtnBox").append(inputBtn);
}

//파일을 삭제하는 함수
function fn_FileDelete(fileURL, obj){
	var fileDelete = $.ajax({
		url: fileURL,
		contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		dataType: 'text'
	});
	fileDelete.done(function(){
		obj.remove();
	});
}

/*
 * fn_getAjax함수와 fn_postAjax 함수는 
 * 전역으로 사용하기 위해 /js/common.js 폴더에 선언
 */

//코멘트 리스트를 가져오는 함수.
function fn_getCommentList(bid, seq){
	var url = "CommentList.do?bid="+bid+"&SEQ="+seq;
	var dType = "json";
	fn_getAjax(url, dType, fn_getCommentListSuccess);
}

//코멘트 리스트가 성공했을때 실행되는 함수.
function fn_getCommentListSuccess(data){
	var getData = data;
	var Str = "";
	$(data).each(function(index, item){
		var regDate = new Date(this.REGDATE).format("yyyy.MM.ss hh:dd:ss");
		var comment = this.COMMENT.replace(/\n/g, "<br />");
		Str += '<dl class="memoList" data-value="'+this.SEQ+'">';
		Str += '<dd class="center">';
		Str += '<div class="commentBox" style="padding-left:'+(this.DEPTH* 20)+'px;">';
		
		if(this.DEPTH != 0){
			Str += '<span class="commentArraow" style="left:'+(this.DEPTH-1)*20+'px"></span>';
		}
		
		Str += '<div class="commentList">';
		Str += '<div class="commentIcon"><img src="/KpointLMS_New2/upload/memberIcon/'+this.USERIMG+'" width="50" height="50"></div>';
		Str += '<div class="commentContent">';
		Str += '<div class="commentWriterBox"><span class="writer" writer="'+this.WRITER+'">'+this.USERNAME+'</span> <span class="regDate">'+regDate+'</span>';
		/*Str += '<div class="recommendBtn">';*/
		Str += '<div class="memoGrp">';
		Str += '<a href="'+this.SEQ+'" cseq="'+this.BOARDSEQ+'" cId ="'+this.GROUPID+'" cDepth="'+this.DEPTH+'" cIndex="'+this.ORDERINDEX+'" class="cWrite" title="답글쓰기"></a>';
		
		//관리자나 Super관리자 또는 글을 쓴 사람만이 삭제,수정버튼이 보이도록 설정.
		if((this.WRITER == userName) || (adminRole == true)){
			Str += '<a href="'+this.SEQ+'" class="cEdit" title="수정하기"></a>';
			Str += '<a href="'+this.SEQ+'" cseq="'+this.BOARDSEQ+'" cId ="'+this.GROUPID+'" cDepth="'+this.DEPTH+'" cIndex="'+this.ORDERINDEX+'" class="cDel"  title="삭제하기"></a>';
		}
		
		Str += '</div>';
		/*Str += '</div>';*/
		Str += '<div class="commentContents">'+comment+'</div>';
		Str += '</div>';
		/*Str += '<a href="" class="recommendation">120</a><a href="" class="norecommendation">30</a>';*/
		Str += '</div>';
		Str += '</div>';
		Str += '</div>';
		Str += '</dd>';
		Str += '</dl>';
	});
	
	$("#commentListBox").html(Str);
}

//코멘트 새글을 입력하는 함수.
function fn_CommentInsert(){
	var formName = "CommentForm";
	var url = "CommentInsert.do";
	var dType = "json";
	fn_postAjax(formName, url, dType, fn_CommentInsertSuccess);
}

//코멘트 새글입력이 성공했을때 실행되는 함수
function fn_CommentInsertSuccess(data){
	$("#COMMENT").val('');
	fn_getCommentList(data.bid, data.seq)
}

//코멘트 답글을 새로 입력하는 부분.
function fn_CommentReplyInsert(){
	var formName = "CommentReplyForm";
	var url = "CommentReplyInsert.do";
	var dType = "json";
	fn_postAjax(formName, url, dType, fn_CommentReplyInsertSuccess);
}

//코멘트 답글을 새로 입력한 부분이 성공했을때 실행되는 함수
function fn_CommentReplyInsertSuccess(data){
	$("#RECOMMENT").val('');
	fn_getCommentList(data.bid, data.seq)
}

//코멘트 수정하기, 답글달기 버튼을 눌렀을때 폼이 생성되고 해당값을 세팅 하는 함수.
function fn_CommentEdit(seq, writeType, cId, cDepth, cIndex){
	
	var bid = $("#bid").val();
	var bseq = $("#BOARDSEQ").val();
	var token = $("input[name='_csrf']").val();
	var formName;
	
	$("#CommentBox").remove();
	var writeText = "";
	var btnText = "";
	if(writeType == "cWrite"){
		writeText = "답글쓰기";
		btnText = "답글";
		writeId = "cReply";
		formName = "CommentReplyForm";
	}else{ 
		writeText = "수정하기";
		btnText = "수정";
		writeId = "cEdit";
		formName = "CommentUpdateForm";	
	}

	var Str="";
	Str += "<div id='CommentBox'>";
	Str += "<p><strong>"+writeText+"</strong></p>";
	Str += "<div class='commentInputBox' id='CommentInputBox'>";
	Str += "<a href='' class='commentEdit' id='"+writeId+"' >"+btnText+"</a>";
	Str += "<div class='inputBoxGrp'>";
	Str += "<form name='"+formName+"' id='"+formName+"'>";
	if(writeType == "cWrite"){
		//코멘트 답글일 경우.
		Str += "<input type='hidden' name='bid' value='"+bid+"' />";
		Str += "<input type='hidden' name='BOARDSEQ' value='"+bseq+"' />";
		Str += "<input type='hidden' name='GROUPID' value='"+cId+"' />";
		Str += "<input type='hidden' name='DEPTH' value='"+cDepth+"' />";
		Str += "<input type='hidden' name='ORDERINDEX' value='"+cIndex+"' />";
		Str += "<input type='hidden' name='_csrf' value='"+token+"' />";
	}else{
		//코맨트 수정사항일 경우
		Str += "<input type='hidden' name='bid' value='"+bid+"' />";
		Str += "<input type='hidden' name='SEQ' value='"+seq+"' />";
		Str += "<input type='hidden' name='_csrf' value='"+token+"' />";
	}
	
	Str += "<textarea id='RECOMMENT' name='RECOMMENT'></textarea></div>";
	Str += "</form>";
	Str += "</div>";
	Str += "</div>";
	Str += "</div>";
	
	$(".memoList").each(function(index, item){
		var itemIndex = $(this).attr("data-value");
		if(itemIndex == seq){
			$(this).append(Str);
			return false;
		}
	});
	$("#RECOMMENT").focus();
	
	//코멘트 수정하기 버튼을 눌렀을때 해당값은 textarea에 넣음.
	if(writeType == "cEdit"){
		fn_CommentReplyEdit(bid, seq);
	}
}

//코멘트 수정하는 함수
function fn_CommentUpdate(){
	var formName = "CommentUpdateForm";
	var url = "CommentUpdate.do";
	var dType = "json";
	fn_postAjax(formName, url, dType, fn_CommentUpdateSuccess);
}

//코멘트 수정이 성공했을때 실행되는 함수.
function fn_CommentUpdateSuccess(data){
	$("#RECOMMENT").val('');
	$("#CommentBox").slideUp('500');
	var comment = data.COMMENT;
	comment = comment.replace(/\n/g, "<br/>");
	$("#commentListBox dl").each(function(){
		var dataValue = $(this).attr("data-value");
		if(dataValue == data.seq){
			console.log(comment);
			$(this).find(".commentContents").html(comment);
			return false;
		}
	});
}

//코멘트를 삭제하는 함수
function fn_CommentDelete(bid, bSeq, cSeq, groupId, depth, orderIndex){
	var msg = "댓글을 정말로 삭제하시겠습니까?";
	var CommentDel;
	if(confirm(msg) == true){
		var url = "CommentDel.do?bid="+bid+"&BOARDID="+bid+"&BOARDSEQ="+bSeq+"&SEQ="+cSeq+"&GROUPID="+groupId+"&DEPTH="+depth+"&ORDERINDEX="+orderIndex;
		var dType = "json";
		fn_getAjax(url, dType, fn_CommentDeleteSuccess);
	}
}
//코멘트 삭제가 성공했을때 실행되는 함수.
function fn_CommentDeleteSuccess(data){
	fn_getCommentList(data.bid, data.bSeq);
}

//코멘트 수정을 위하여 불러오는 함수
function fn_CommentReplyEdit(bid, seq){
	var url = "getCommentView.do?bid="+bid+"&SEQ="+seq;
	var dataType = "json";
	fn_getAjax(url, dataType, fn_ComentReplyEditSuccess);
}

//코멘트 수정하기 위해 불러온 데이터가 성공했을때 실행되는 함수.
function fn_ComentReplyEditSuccess(data){
	$("#RECOMMENT").val(data.COMMENT);
}

//추천수를 올리는 부분
function fn_Recommandation(url, bid, seq, count){
	var data = {
		'bid' : bid,
		'SEQ' : seq,
		'RECOMMANDATION' :count
	};
	
	fn_getAjax(url, 'json', fn_RecomandationDone, data)
}

function fn_RecomandationDone(data){
	console.log(data);
	$("#recommandationTxt").text(data.reCount);
	$("#recommandationBtn").text(data.reCount);
}

//비추천수를 올리는 부분
function fn_NoRecommandation(url, bid, seq, count){
	var data = {
		'bid' : bid,
		'SEQ' : seq,
		'NORECOMMANDATION' :count
	};
	
	fn_getAjax(url, 'json', fn_NoRecomandationDone, data)
}

function fn_NoRecomandationDone(data){
	console.log(data);
	$("#noRecommandationTxt").text(data.noReCount);
	$("#noRecommandationBtn").text(data.noReCount);
}

/*//기본 변수값을 세팅하는 부분.
//화면이 로딩될때 inc폴더에  common.jsp에서 변수값을 세팅.
function fn_setVariable(cPath, boardId, boardSeq, uRole, uName){
	contextPath = cPath;
	bid = boardId;
	seq = boardSeq;
	adminRole = uRole;
	userName = uName;
}*/

/////////////////// JQuery Validator ////////////////////
//******************* 게시판 부분 **********************//
function formCheck(){
/*
$("#regForm").validate({
    //validation이 끝난 이후의 submit 직전 추가 작업할 부분
    //onfocusout: false,
    //규칙
	submitHandler : function(form){
		var f = confirm("글을 등록하시겠습니까?");
        if(f){
        	return true;
        } else {
            return false;
        }
	},
    rules: {
       TITLE: { required : true, minlength : 2},
       CONTENT: { required : true, minlength : 2 },
    },
    //규칙체크 실패시 출력될 메시지
    messages : {
        TITLE: {  
           required : "제목은 입력하세요.", 
           minlength : "최소 {0}글자이상이어야 합니다."
        },
        
        CONTENT: { 
           required : "글내용은 입력하세요.",
           minlength : "최소 {0}글자이상이어야 합니다."
        }
    }
});
*/
}
 //******************* 게시판 부분 **********************//

