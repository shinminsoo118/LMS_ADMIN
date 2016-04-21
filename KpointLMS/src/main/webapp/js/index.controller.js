var prevLayer;
$(function(){
	////////////////// INDEX 관련부분 //////////////////////////
	init();
	//index화면의 메뉴를 클릭했을때 실행되는 부분
	$("#indexMenu li a").on("click", function(e){
		e.preventDefault();
		var linkURL = $(this).attr("href");
		console.log(linkURL);
		if(linkURL == "login" || linkURL == "join"){
			hideLayer();
			viewLayer(linkURL);
		}else if(linkURL == undefined || linkURL == ""){
			//$(this).attr("href", "./administrator/index.htm");
			window.location.href = "index.do?error=1";
			return true;
		}
	});
	
	 //로그인 취소 버튼을 누렀을때
	$("#LoginCancel").on("click", function(e){	
		e.preventDefault();
		$("#login").fadeOut(500);
		$("#dim").fadeOut();
	});
	
	//회원가입폼 유효성 검사
//	$("#Register").on("click", function(){
//		
//		var RegexEmail = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/i; //이메일 요휴성검사
//		var RegexName = /^[가-힣]{2,4}$/; //이름 유효성 검사 2~4자 사이
//		var RegexId = /^[a-z0-9_-]{4,20}$/; //아이디 유효성 검사
//		var RegexTel = /^[0-9_-]{8,20}$/; //전화번호 유효성 검사
//		
//		console.log("값체크aa 1122= ? ",$.trim($("#regid").val()), RegexId.test($.trim($("#regid").val())));
//		if(!RegexId.test($.trim($("#regid").val()))){
//			alert("아이디를 입력해 주세요.");
//			$("#regid").focus();
//			return false;
//		}else if($("#regname").val() == ""){
//			alert("이름을 입력해 주세요.");
//			$("#regname").focus();
//			return false;
//		}else if($("#regpw").val() == ""){
//			alert("비밀번호를 입력해 주세요.");
//			$("#regpw").focus();
//			return false;
//		}else if(!RegexTel.test($.trim($("#regtel").val())) ){
//			alert("핸드폰번호를 정확히 입력해 주세요.");
//			$("#regtel").focus();
//			return false;
//		}else if(!RegexEmail.test($.trim($("#regemail").val()))){
//			alert("이메일주소를 정확히 입력해 주세요.");
//			$("#regemail").focus();
//			return false;
//		}else{
//			$("#registerForm")[0].submit();
//			return false;
//		}
//	});
	
	$("#Register").on("click", function(){
		
		var result = 0;
		
		$(".indexList .regi_input").each(function( key,i) {
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
			$("#registerForm").submit();
			console.log($("#registerForm").attr("action"));
			return false;
		}
		
	});
	
/*	$(".indexList .regi_input").on("mouseover", function(){
		$(this).css("background-color","");
	})*/
	
	//회원가입 취소를 눌렀을때.
	$("#RegisterCancel").on("click", function(e){
		e.preventDefault();
		$("#join").fadeOut(500);
		$("#dim").fadeOut();
	});
});

//로그인 관련 부분 체크
function loginCheck(){
	if($("#userid").val() == ""){
		alert("아이디를 입력해 주세요.");
		$("#userid").focus();
		return false;
	}else if($("#userpw").val() == ""){
		alert("비밀번호를 입력해 주세요.");
		$("#userpw").focus();
		return false;
	}else{
		return true;
	}
}

//페이지 초기화 함수.
function init(){
	$("#dim").hide();
	$("#login").hide();
	$("#join").hide();
	console.log($(".joinTitle").children().children().children().hasClass("error"),"aaaa");
	if($(".joinTitle").children().children().children().hasClass("error")){
		viewLayer('join');
	}
}

//index창의 모든 레이어를 숨김.
function hideLayer(){
	console.log("prevLayer = " + prevLayer);
	if(prevLayer != undefined){
		$("#"+prevLayer).fadeOut(500);
		$("#dim").fadeOut(500);
	}
}
//해당항목에 해당하는 레이어 보임.
function viewLayer(str){
	console.log(str);
	$("#dim").fadeIn(500, function(){
		$("#"+str).fadeIn(500, function(){
			$("input[type='text']").placeholder();
		});
		prevLayer = str;
	});
}

function viewLogin(){
	console.log("에러메세지..");
	$(window).on("load", function(){
		$("#dim").css({"display":"block"});
		$("#login").css({"display":"block"});
	})
	
}
//////////////////INDEX 관련부분 //////////////////////////