/**
 * member jsp page javascript controller
 */

$(function(){
	console.log("controller load");
	
	// 관리자관리
	$("#adminListSearchBtn").on("click",function(e){
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
			$("#adminSearchForm")[0].submit();
			return true;
		}
//		$("#memberSearchForm")[0].submit();
	});
	
	// 
	
	$(".mDel").on("click",function(){
		fn_memberDelete($(this));
	});
	
	$(".mEdit").on("click",function(){
		fn_memberRoleUpdate($(this));
	});
	
    $("#profileImgFile").on('change', function(){
        readURL(this);
    });
    
    $("#afterCalendar").on("click",function(){
    	console.log("calendar");
    	$("#afterDatepicker").datepicker();
    });
    
    $(".calendar").on("click",function(){
    	console.log($(this));
    	fn_datepicker($(this));
    });
    
    $("#datepicker").on("click",function(e){
    	console.log
    });
    
    $("#adminRegister").on("click", function(e){
    	e.preventDefault();
    	
    	var result = 0;
		
		$(".inputTbl2 .tblInput").each(function( key,i) {
			var validCheck = 1;
			result = common_valid.validFunc($(this).val(), $(this).attr('pattern'), validCheck);
			if(result == 0){
				$(this).focus();
				$(this).val('');
				return false;
			}
		});
		
		if(result == 0){
			return false;
		}else{
			$("#regForm").submit();
			console.log($("#regForm").attr("action"));
//			return false;
		}
    });
    
    $("#USEREMAIL").focusout(function(e){
    	var formData = $("#regForm").serialize();
    	console.log("formData" , formData);
    	var ajaxDone = $.ajax({
    		type : 'POST',
    		url : "getMemberEmailCheck.do",
    		data : formData,
    		dataType : 'json',
    		contentType : 'application/x-www-form-urlencoded; charset=UTF-8',
    		error : function(request,status,error){
    			console.log(request,status,error)
    		}
    	});
    	
    	ajaxDone.done(function(data,status){
    		console.log("data", data);
    		if(data.status == "fail"){
    			alert("이메일이 중복입니다. 다시 입력하세요.");
    			$("#USEREMAIL").val('');
    		}
    	});
    });

});

function fn_memberDelete(obj){
	var userId = $(obj).parents('tr').children('#userId').text();
	var str = userId+" 계정을 삭제 하시겠습니까?";
	console.log(userId,linkURL);
	if(confirm(str)){
		location.href = "memberDelete.do?USERID="+userId;
	}
}

function fn_memberRoleUpdate(obj){
	
	var userId = $(obj).parents('tr').children('#userId').text();
	
	if(!confirm(userId+" 님의 회원등급을 변경 하시겠습니까?")){
		return;
	}
	
	var userRole = $(obj).parents('tr').children('td').children('#roleSelect').val();
	console.log(userId,userRole);
	var data = {
			userId : userId,
			userRole : userRole,
	}
	
	var ajaxDone = $.ajax({
		type : 'POST',
		url : "memberRoleUpdate.do?"+_csrfParameter+"="+_csrf,
		dataType : 'json',
		data : data,
		contentType : 'application/x-www-form-urlencoded; charset=UTF-8',
		error : function(request,status,error){
			console.log(request,status,error)
		}
	});
	
	ajaxDone.done(function(data,status){
		//var result = JSON.stringify(data);
		//console.log("success data : " + JSON.stringify(data) + " ,status : " + status);
		//console.log(data.test);
		alert(userId + "님의 회원등급이 변경 되었습니다");
		location.href = "adminList.do?bid=Member";
	});
	//location.href = "memberDetail.do?USERID="+userId;
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
				console.log(splitStr);
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

function readURL(input) {
    if (input.files && input.files[0]) {
    var reader = new FileReader();

    reader.onload = function (e) {
            $('#profileImg').attr('src', e.target.result);
        }

      reader.readAsDataURL(input.files[0]);
      $("#profileImgFileName").val(input.files[0].name);
    }
}
