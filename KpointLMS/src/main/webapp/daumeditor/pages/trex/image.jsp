<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Daum에디터 - 이미지 첨부</title> 
<script src="${pageContext.request.contextPath}/daumeditor/js/popup.js" type="text/javascript" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/js/lib/jquery-1.11.2.min.js"></script>
<script src="${pageContext.request.contextPath}/js/lib/jquery.form.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/daumeditor/css/popup.css" type="text/css" />
<c:url value="/" var = "uploadURL" />
<script type="text/javascript">
	
// <![CDATA[
	var _mockdata;
	function done(path, fileName, fileStrName, fileSize, fileWriter) {
		console.log(execAttach);
		
		if (typeof(execAttach) == 'undefined') { //Virtual Function
			return;
	    }
		
		var domain = location.href;
		console.log("domain : " + domain);
		domain.split("//");
		console.log("domain[0]",domain[0]);
		console.log(domain[1]);
		console.log(domain[2]);
		console.log(domain[3]);
		domain = domain[1].substr(0,domain[1].indexOf('/'));
		 
		var url = domain + '${uploadURL}' + path;
		console.log("url : " + url);
		_mockdata = {
			'imageurl': 'http://localhost:8080/KpointLMS_New2/' + path + "/" +fileStrName,
			'filename': fileName,
			'filesize': fileSize,
			'imagealign': 'C',
			'originalurl': url + '/' + fileStrName,
			'thumburl': url + '/' + fileStrName,
			'fileWriter': fileWriter,
			'fileStrName': fileStrName
		};
		//console.log(_mockdata);
	//	execAttach(_mockdata);
		//closeWindow();
		alert("이미지 업로드가 완료되었습니다.!");
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
	    
	    var _attacher = getAttacher('image', _opener);
	    registerAction(_attacher);
	}
// ]]>

	
</script>
</head>
<body onload="initUploader();">
<div class="wrapper">
	<div class="header">
		<h1>사진 첨부</h1>
	</div>	
	<div class="body">
	
		<dl class="alert">
		    <dt>사진 첨부 확인</dt>
		    <dd>
		    	확인을 누르시면 임시 데이터가 사진첨부 됩니다. <a href="" id="addFile">파일추가</a> <a href="" id="delFile">파일삭제</a><br/>
			</dd>
			 <!-- <input id="fileData" name="fileData" type="file" onchange="fileUploadPreview(this, 'preView')" />
     		 <div id="preView" class="preView" title="이미지미리보기"></div> -->
			<dd>
				<c:url value="/common/FileInsert.do?${_csrf.parameterName}=${_csrf.token}" var="actionURL" />
				<form method="post" enctype="multipart/form-data" action="${actionURL}" id="imageUploadForm" name="imageUploadForm">
				    <div id="fileBox">
				    	<input type="hidden" name="bid" id="bid" />
				    	<input type="hidden" name="BOARDSEQ" id="BOARDSEQ" />
				    	<input type="hidden" name="FILEWRITER" id="FILEWRITER" />
				    	<input type="file" name="file" id="file">
				  	</div>
				    
				    <input type="submit" value="파일업로드" class="fileUpload"/>
				</form>
				
			</dd>
		</dl>
	</div>
	<div class="footer">
		<p><a href="#" onclick="closeWindow();" title="닫기" class="close">닫기</a></p>
		<ul>
			<li class="submit"><a href="#" onclick="fileRegi();" title="등록" class="btnlink">등록</a> </li>
			<li class="cancel"><a href="#" onclick="closeWindow();" title="취소" class="btnlink">취소</a></li>
		</ul>
	</div>
</div>
</body>
</html>
<script>
	$(function(){
	    //ajax form submit
	    var openerWin = PopupUtil.getOpener();
	    var bid = openerWin.OpennerValues.bid;
	    var SEQ = openerWin.OpennerValues.SEQ;
	    var WRITER = openerWin.OpennerValues.WRITER;
	    var imageCount = openerWin.OpennerValues.imageCount;
	    var fileCount = openerWin.OpennerValues.fileCount;
	    
	    
	    $("#bid").val(bid);
	    $("#BOARDSEQ").val(SEQ);
	    $("#FILEWRITER").val(WRITER);
	    console.log(bid);
	    console.log(SEQ);
	    console.log(WRITER);
	    
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
	            	console.log("업로드중.", event, position, total, percentComplete);
	            	$("#tx_upload_progress").show();
	            	$("#tx_upload_progress div").text(percentComplete+"%");
	            },
	            complete : function(xhr){
	            	console.log("전송완료", xhr.responseText);
	            	$("#tx_upload_progress").hide();
	            	$("#tx_upload_progress div").text("0%");
	            }, 
	            
	            success: function(response,status){
	                //성공후 서버에서 받은 데이터 처리
	                console.log("업로드 성공!!", response);
	                var path = response.path;
	                console.log("path = ", path);
	                var fileList = $(response.tempFileList);
	                console.log("fileList = ", fileList )
	                var fileSeq;
	                var fileName;
	                var fileStrName;
	                var fileSize;
	                var fileWriter;
	                fileList.each(function(){
	                	console.log("this = ",$(this));
	                	fileSeq = $(this)[0].FILESEQ;
	                	fileName = $(this)[0].FILENAME;
	                	fileStrName = $(this)[0].FILESTRNAME;
	                	fileSize = $(this)[0].FILESIZE;
	                	fileWriter = $(this)[0].FILEWRITER;
	                	console.log(fileName, fileStrName, fileSize, fileSeq, fileWriter);
	                	done(path, fileName, fileStrName, fileSize, fileWriter, fileWriter);
	                });	             
	            },
	            error: function(){
	                //에러발생을 위한 code페이지
	                console.log("에러발생");
	            }                               
	        });
	
	    $("#addFile").on("click", function(e){
			e.preventDefault();
			var imageFileCount = $("input[type='file']").length;
			console.log("개수@@ ", $("input[type='file']").length);
			imageFileCount++;
			
			if(imageFileCount > 1){
				$("#delFile").css("display","inline-block");
			}
			
			if(imageFileCount <= imageCount){
				var obj = "<input type='file' name='file_"+imageFileCount+"' id='file_"+imageFileCount+"' />";
				$("#fileBox").append(obj);
			}else{
				var str = "파일제한 "+imageCount+"개수입니다.";
				console.log("로그..");
				alert(str);
				return false;
			}
		});
	    
	    $("#delFile").on("click", function(e){
	    	e.preventDefault();
	    	var imageFileCount = $("input[type='file']").length;
			console.log($("input[type='file']").length);
			if(imageFileCount > 1){
				$("#file_"+imageFileCount).remove();	
			}
			
			console.log(imageFileCount);
			if(imageFileCount <= 2){
				$("#delFile").css("display","none");
			}
	    });
	});

	</script>