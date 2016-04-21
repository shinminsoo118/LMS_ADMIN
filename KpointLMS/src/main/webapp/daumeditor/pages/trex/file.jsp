<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Daum에디터 - 파일 첨부</title> 
<script src="${pageContext.request.contextPath}/daumeditor/js/popup.js" type="text/javascript" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/js/lib/jquery-1.11.2.min.js"></script>
<script src="${pageContext.request.contextPath}/js/lib/jquery.form.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/daumeditor/css/popup.css" type="text/css"  charset="utf-8"/>
<script type="text/javascript">
// <![CDATA[
	var _mockdata;
	function done(path, fileName, fileStrName, fileSize, fileSort) {
		console.log(path, fileName, fileStrName, fileSize)
		if (typeof(execAttach) == 'undefined') { //Virtual Function
	        return;
	    }

		var domain = location.href;
		domain.split("//");
		domain = domain[1].substr(0,domain[1].indexOf('/'));
		console.log("path : " + path);
		var url = domain + '${uploadURL}' + path;
		
		_mockdata = {
			//'attachurl': 'http://localhost:8080/KpointLMS_New2/upload/'+fileStrName,
			'attachurl': 'http://localhost:8080/KpointLMS_New2/' + path + "/" +fileStrName,
			'filemime': 'application/pdf',
			'filename': fileName,
			'filesize': fileSize
		};
		execAttach(_mockdata);
		closeWindow();
	}
	
	function fileRegi(){
		execAttach(_mockdata);
		closeWindow();
	}

	function initUploader(){
	    var _opener = PopupUtil.getOpener();
	    if (!_opener) {
	        alert('잘못된 경로로 접근하셨습니다.');
	        return;
	    }
	    
	    var _attacher = getAttacher('file', _opener);
	    registerAction(_attacher);
	}
	
</script>
</head>
<body onload="initUploader();">
<div class="wrapper">
	<div class="header">
		<h1>파일 첨부</h1>
	</div>	
	<div class="body">
		<dl class="alert">
		    <dt>파일 첨부 확인</dt>
		    <dd>
		    	확인을 누르시면 임시 데이터가 파일첨부 됩니다.<br /> 
				인터페이스는 소스를 확인해주세요.
			</dd>
			<dd>
				<c:url value="/common/FileInsert.do?${_csrf.parameterName}=${_csrf.token}" var="actionURL" />
				<form method="post" enctype="multipart/form-data" action="${actionURL}" id="imageUploadForm" name="imageUploadForm">
				    <input type="hidden" name="bid" id="bid" />
				    <input type="hidden" name="BOARDSEQ" id="BOARDSEQ" />
				    <input type="hidden" name="FILEWRITER" id="FILEWRITER" />
				    <input type="file" name="file" id="file">
				    <input type="file" name="file1" id="file1">
				    <input type="submit" value="업로드">
				</form>
			</dd>
		</dl>
	</div>
	<div class="footer">
		<p><a href="#" onclick="closeWindow();" title="닫기" class="close">닫기</a></p>
		<ul>
			<li class="submit"><a href="#" id="fileRegi" title="등록" class="btnlink">등록</a> </li>
			<li class="cancel"><a href="#" onclick="closeWindow();" title="취소" class="btnlink">취소</a></li>
		</ul>
	</div>
</div>
</body>
</html>
<script>
	$(function(){
		var fileList;
		var path;
	    //ajax form submit
	    var openerWin = PopupUtil.getOpener();
	    
	    var bid = openerWin.OpennerValues.bid;
	    var SEQ = openerWin.OpennerValues.SEQ;
	    var WRITER = openerWin.OpennerValues.WRITER;
	    
	    $("#bid").val(bid);
	    $("#BOARDSEQ").val(SEQ);
	    //$("#FILEWRITER").val(fn_random(0,1000000000)+'-'+WRITER);
	    $("#FILEWRITER").val(WRITER);
	    $('#imageUploadForm').ajaxForm({
	    		dataType:'json',
	   
	            beforeSubmit: function (data,form,option) {
	                //validation체크 
	                //막기위해서는 return false를 잡아주면됨
	                console.log("업로드시작");
	                //이미지검사.
	              	
	                return true;
	            },
	            
	            uploadProgress: function(event, position, total, percentComplete){
	            	//진행상황을 나태냄.
	            	console.log("업로드중.", event, position, total, percentComplete)
	            },
	            
	            complete : function(xhr){
	            	console.log("전송완료", xhr.responseText);
	            }, 
	            
	            success: function(response,status){
	                //성공후 서버에서 받은 데이터 처리
	                console.log("업로드 성공!!", response);
	                path = response.path;
	                console.log("path = ", path);
	                fileList = $(response.tempFileList);
	                //console.log("fileList = ", fileList )
	                //var fileSeq;
	                //var fileName;
	                //var fileStrName;
	                //var fileSize;
	                //var fileSort;
	                //fileList.each(function(){
	                //	console.log("this = ",$(this));
	                //	fileSeq = $(this)[0].FILESEQ;
	                //	fileName = $(this)[0].FILENAME;
	                //	fileStrName = $(this)[0].FILESTRNAME;
	                //	fileSize = $(this)[0].FILESIZE;
	                //	console.log(fileName, fileStrName, fileSize, fileSeq);
	                //	done(path, fileName, fileStrName, fileSize, fileSort);
	                //  });
	                alert("파일 업로드가 완료되었습니다.");
	            },
	            error: function(){
	                //에러발생을 위한 code페이지
	                console.log("에러발생");
	            }                               
	        });
	    
	    $("#fileRegi").on("click", function(e){
	    	//console.log("fileList = ", fileList )
            var fileSeq;
            var fileName;
            var fileStrName;
            var fileSize;
            var fileSort;
            fileList.each(function(){
            	console.log("this = ",$(this));
            	fileSeq = $(this)[0].FILESEQ;
            	fileName = $(this)[0].FILENAME;
            	fileStrName = $(this)[0].FILESTRNAME;
            	fileSize = $(this)[0].FILESIZE;
            	console.log(fileName, fileStrName, fileSize, fileSeq);
            	done(path, fileName, fileStrName, fileSize, fileSort);
            });
	    });
	    
	});
	
	//랜덤함수 
	function fn_random(min, max){
		var RandVal = Math.random() * (max- min) + min;
		return Math.floor(RandVal);
	}
	</script>
