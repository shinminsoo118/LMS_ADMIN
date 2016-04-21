$(function(){
	    //ajax form submit
    var openerWin = PopupUtil.getOpener();
    
    var bid = openerWin.OpennerValues.bid;
    var SEQ = openerWin.OpennerValues.SEQ;
    var WRITER = openerWin.OpennerValues.WRITER;
    
    console.log(bid, SEQ, WRITER);
    
    $("#bid").val(bid);
    $("#BOARDSEQ").val(SEQ);
    $("#FILEWRITER").val(fn_random(0,1000000000)+'-'+WRITER);
    console.log("#######################################");
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
                var path = response.path;
                console.log("path = ", path);
                var fileList = $(response.tempFileList);
                console.log("fileList = ", fileList )
                var fileSeq;
                var fileName;
                var fileStrName;
                var fileSize;
                
                fileList.each(function(){
                	console.log("this = ",$(this));
                	fileSeq = $(this)[0].FILESEQ;
                	fileName = $(this)[0].FILENAME;
                	fileStrName = $(this)[0].FILESTRNAME;
                	fileSize = $(this)[0].FILESIZE;
                	console.log(fileName, fileStrName, fileSize, fileSeq);
                	done(path, fileName, fileStrName, fileSize);
                });
            },
            error: function(){
                //에러발생을 위한 code페이지
                console.log("에러발생");
            }                               
        });
});

//랜덤함수 
function fn_random(min, max){
	var RandVal = Math.random() * (max- min) + min;
	return Math.floor(RandVal);
}