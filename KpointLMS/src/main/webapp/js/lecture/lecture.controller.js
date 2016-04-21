/**
 * member jsp page javascript controller
 */

$(function(){
	// 수강이력
   $("#lectureListSearchBtn").on("click", function(e){
    	e.preventDefault();
		if($("#KEYWORD").val() == undefined || $("#KEYWORD").val() == ""){
			alert("검색조건을 선택해 주세요.");
			$("#KEYWORD").focus();
			return false;
		}else if($("#KEYSTRING").val() == ""){
			alert("검색어를 입력해 주세요.");
			$("#KEYSTRING").focus();
			return false;
		}else{
			$("#lectureSearchForm")[0].submit();
			return true;
		}
    });
	
	
//    $("#profileImgFile").on('change', function(){
//        readURL(this);
//    });
    
//    $("#afterCalendar").on("click",function(){
//    	console.log("calendar");
//    	$("#afterDatepicker").datepicker();
//    });
    
    $(".calendar").on("click",function(){
    	console.log($(this));
    	fn_datepicker($(this));
    });
    
    $("#datepicker").on("click",function(e){
//    	console.log
    });
    
});


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
