/**
 * member jsp page javascript controller
 */

$(function(){
	// 상품코드 관리
	$("#boardSetSearchBtn").on("click",function(e){
		e.preventDefault();
		
//		return false;
		if($("#KEYWORD").val() == undefined || $("#KEYWORD").val() == ""){
			alert("검색조건을 선택해 주세요.");
			$("#KEYWORD").focus();
			return false;
		}else if($("#KEYSTRING").val() == ""){
			alert("검색어를 입력해 주세요.");
			$("#KEYSTRING").focus();
			return false;
		}else{
			$("#codeSearchForm")[0].submit();
			return true;
		}
	});
	
	
    $(".calendar").on("click",function(){
    	console.log($(this));
    	fn_datepicker($(this));
    });
    
    $("#datepicker").on("click",function(e){
//    	console.log
    });
    
	$(".cEdit").on("click",function(e){
		e.preventDefault();
		fn_boardSetupUpdate($(this));
	});
	
    $("#boardDetailUpdate").on("click", function(e){
    	e.preventDefault();
    	
    	var result = 0;
		
		$(".inputTbl2 .tblInput").each(function( key,i) {
			if($(this).attr("type") == "checkbox"){
				if($(this).prop("checked") == true){
					$(this).val('Y');
				}else{
					$(this).val('N');
				}
			}else{
				console.log(" i" , i);
				var validCheck = 1;
				result = common_valid.validFunc($(this).val(), $(this).attr('pattern'), validCheck);
				if(result == 0){
					$(this).focus();
					$(this).val('');
					return false;
				}
				
				if($(this).attr('pattern') == "10"){
					$(".inputTbl2 .smallSelect").each(function( key,i) {
						var validCheck = 1;
						result = common_valid.validSelectFunc($(this).val(), validCheck);
						if(result == 0){
							$(this).focus();
							$(this).val('');
							return false;
						}
					});	
				}
			}
			
		});
		
		if(result == 0){
			return false;
		}else{
			var formData = $("#updateForm").serialize();
			console.log("formData", formData);
			fn_boardSetupDetailUpdate(formData);
//			$("#regForm").submit();
//			console.log($("#regForm").attr("action"));
//			return false;
		}
		
		
    });
    
    $("#boardSetupInsertBtn").on("click", function(e){
    	e.preventDefault();
    	
    	var result = 0;
    	
		$(".inputTbl2 .tblInput").each(function( key,i) {
			inputCheck = 0;
			console.log(key,i);
			if($(this).attr("type") == "checkbox"){
				if($(this).prop("checked") == true){
					$(this).val('Y');
				}else{
					$(this).val('N');
				}
			}else{
				var validCheck = 1;
				result = common_valid.validFunc($(this).val(), $(this).attr('pattern'), validCheck);
				if(result == 0){
					$(this).focus();
					$(this).val('');
					return false;
				}

			}
			
		});
		
//		$(".inputTbl2 .smallSelect").each(function( key,i) {
//			console.log("smallSelect" , i);
//			console.log($(this).val());
//			var validCheckSelect = 1;
//			result = common_valid.validSelectFunc($(this).val(), validCheckSelect);
//			console.log(result);
//			if(result == 0){
//				console.log("!!!!!!!!!!!!!!!");
//				$(this).focus();
//				$(this).val('');
//				return false;
//			}
//		});
		
		if(result == 0){
			return false;
		}else{
			var formData = $("#regiForm").serialize();
			console.log("regiForm", formData);
			fn_boardSetupInsert(formData);
//			$("#regForm").submit();
//			console.log($("#regForm").attr("action"));
//			return false;
		}
		
    });
    
});


function fn_codeDelete(obj, seq, code){
	var str = code+" 해당 상품코드를 삭제하시겠습니까?";
	console.log(obj, seq);
//	return false;
	if(confirm(str)){
		location.href = "codeDelete.do?"+ obj +"&CODESEQ="+seq;
	}
}


function fn_memberUpdate(){
	$("#memberUpdateForm")[0].submit();
}

function fn_datepicker(obj){
	if(!$(obj).next().hasClass("datepicker")){
		$(".datepicker").remove();
		var calendarHtml = "<div class='datepicker' id='datepicker'></div>";
		$(obj).after(calendarHtml);
		$("#datepicker").datepicker({
			onSelect : function(dateText,inst){
				$(obj).prev().val("");
				console.log(dateText,inst);
				//var resultDate = dateText.replace(/\//g,"-");
				var splitStr = dateText.split("/");
				var resultDate = splitStr[2]+"-"+splitStr[0]+"-"+splitStr[1];
				$(obj).prev().val(resultDate);
			}
		});
		$("#datepicker").css({"position": "absolute","left":$(obj).prev().offset().left+"px"});
	}
	else{
		$(".datepicker").remove();
	}
}


function fn_boardSetupDetailUpdate(obj){
	var bid = $("#BOARDID").val();
	
	if($("#ISREPLY").val() == "N"){
		obj += "&ISREPLY=" + $("#ISREPLY").val()
	}
	if($("#ISCOMMENT").val() == "N"){
		obj += "&ISCOMMENT=" + $("#ISCOMMENT").val()
	}
	if($("#ISRECOMMANDATION").val() == "N"){
		obj += "&ISRECOMMANDATION=" + $("#ISRECOMMANDATION").val()
	}
	if($("#ISNORECOMMANDATION").val() == "N"){
		obj += "&ISNORECOMMANDATION=" + $("#ISNORECOMMANDATION").val()
	}
//		console.log("obj" + obj);
//		return false;
	var ajaxDone = $.ajax({
		type : 'POST',
		url : "boardSetupDetailUpdate.do?"+_csrfParameter+"="+_csrf + "&" + linkURL + "&BOARDID="+ bid,
//		url : "boardSetupDetailUpdate.do?",
		dataType : 'json',
		data : obj,
		contentType : 'application/x-www-form-urlencoded; charset=UTF-8',
		error : function(request,status,error){
			console.log(request,status,error)
		}
	});
	
	ajaxDone.done(function(data,status){
		console.log("data", data);
		if(data.status =="success"){
			alert(bid + "상세설정을 수정하였습니다.");
		}
//		location.href = "boardSetup.do?bid=Config";
	});
}

function fn_boardSetupInsert(obj){
//	var bid = $("#BOARDID").val();
	
	if($("#ISREPLY").val() == "N"){
		obj += "&ISREPLY=" + $("#ISREPLY").val()
	}
	if($("#ISCOMMENT").val() == "N"){
		obj += "&ISCOMMENT=" + $("#ISCOMMENT").val()
	}
	if($("#ISRECOMMANDATION").val() == "N"){
		obj += "&ISRECOMMANDATION=" + $("#ISRECOMMANDATION").val()
	}
	if($("#ISNORECOMMANDATION").val() == "N"){
		obj += "&ISNORECOMMANDATION=" + $("#ISNORECOMMANDATION").val()
	}
//		console.log("obj" + obj);
//		return false;
	var ajaxDone = $.ajax({
		type : 'POST',
		url : "boardSetupInsert.do?"+_csrfParameter+"="+_csrf + "&" + linkURL,
//		url : "boardSetupDetailUpdate.do?",
		dataType : 'json',
		data : obj,
		contentType : 'application/x-www-form-urlencoded; charset=UTF-8',
		error : function(request,status,error){
			console.log(request,status,error)
		}
	});
	
	ajaxDone.done(function(data,status){
		console.log("data", data);
		if(data.status =="success"){
			alert($("#BOARDTBLNEW").val() + "신규 등록하였습니다.");
//			return false;
			location.href = "boardSetup.do?bid=Config&currentPageNo="+data.currentPageNo;
		}else if(data.status =="fail"){
			alert("보드 아이디와 테이블이 중복입니다.");
		}
//		location.href = "boardSetup.do?bid=Config";
	});
}

function fn_boardSetupUpdate(obj){
	
	var stIndex = $(obj).parents('tr').children('#stIndex').children('input').val();
	var boardId = $(obj).parents('tr').children('#boardId').text();
	if(!confirm(boardId+" 의 권한 설정을 변경 하시겠습니까?")){
		return;
	}
	console.log(boardId);
	
	var VIEW = $(obj).parents('tr').children('#VIEW_' + stIndex).children('#VIEW').val();
	var REG = $(obj).parents('tr').children('#REG_' + stIndex).children('#VIEW').val();
	var REPLY = $(obj).parents('tr').children('#REPLY_' + stIndex).children('#REPLY').val();
	var EDIT = $(obj).parents('tr').children('#EDIT_' + stIndex).children('#EDIT').val();
	var DEL = $(obj).parents('tr').children('#DEL_' + stIndex).children('#DEL').val();
	var data = {
			setBoardId : boardId,
			setView : VIEW,
			setReg : REG,
			setReply : REPLY,
			setEdit : EDIT,
			setDel : DEL
	}
	
	var ajaxDone = $.ajax({
		type : 'POST',
		url : "boardSetupRoleUpdate.do?"+_csrfParameter+"="+_csrf + "&" + linkURL,
		dataType : 'json',
		data : data,
		contentType : 'application/x-www-form-urlencoded; charset=UTF-8',
		error : function(request,status,error){
			console.log(request,status,error)
		}
	});
	
	ajaxDone.done(function(data,status){
		alert(boardId + "의 권한등급이 변경 되었습니다");
		location.href = "boardSetup.do?bid=Config";
	});
}

//function readURL(input) {
//    if (input.files && input.files[0]) {
//    var reader = new FileReader();
//
//    reader.onload = function (e) {
//            $('#profileImg').attr('src', e.target.result);
//        }
//
//      reader.readAsDataURL(input.files[0]);
//      $("#profileImgFileName").val(input.files[0].name);
//    }
//}
