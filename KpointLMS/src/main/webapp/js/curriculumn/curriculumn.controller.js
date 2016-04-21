/**
 * member jsp page javascript controller
 */

$(function(){
	// 상품코드 관리
	$("#curriculumnListSearchBtn").on("click",function(e){
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
			$("#curriculumnSearchForm")[0].submit();
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
    
    $("#courseInsertBtn").on("click", function(e){
    	e.preventDefault();
    	$("#courseRegistForm")[0].submit();
    });
    
});


function fn_curriculumnDelete(obj, seq, curProName){
	var str = curProName+" 교육과정을 삭제하시겠습니까?";
	console.log(obj, seq);
//	return false;
	if(confirm(str)){
		location.href = "curriculumnDelete.do?"+ obj +"&SEQ="+seq;
	}
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

