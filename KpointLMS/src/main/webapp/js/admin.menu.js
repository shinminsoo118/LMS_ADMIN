var url="/KpointLMS_New2/";

function adminLogo(){
	location.href = url + "administrator/index.do";
}

function logOut(){
	location.href = url + "index.do";
}

function adminMenu01(num){
	if(num == 1){
		location.href = url + "administrator/setup/adminList.do?bid=Member";
	}else if(num == 2){
		location.href = url + "administrator/setup/codeList.do?bid=Procode";
	}else if(num == 3){
		location.href = url + "administrator/setup/paymentList.do?bid=Payment";
	}else if(num == 4){
		location.href = url + "administrator/setup/boardSetup.do?bid=Config";
	}else if(num == 5){
		location.href = url + "administrator/setup/etcSetup.do";
	}
}
function adminMenu02(num){
	if(num == 1){
		location.href = url + "administrator/member/memberList.do?bid=Member&type=Student";
	}else if(num == 2){
		location.href = url + "administrator/member/memberList.do?bid=Member&type=Admin";
	}else if(num == 3){
		location.href = url + "administrator/member/mailList.do?bid=Mail";
	}else if(num == 4){
		location.href = url + "administrator/member/smsList.do?bid=sms";
	}
}
function adminMenu03(num){
	if(num == 1){
		location.href = url + "administrator/lecture/lectureList.do?bid=Sign&STATUS=S";
	}else if(num == 2){
		location.href = url + "administrator/lecture/priceHistoryList.do?bid=Sign&STATUS=P";
	}else if(num == 3){
		location.href = url + "administrator/lecture/refundList.do?bid=Sign&STATUS=R";
	}
	
}
function adminMenu04(num){
	if(num == 1){
		location.href = url + "administrator/curriculumn/curriculumnList.do?bid=Curriculumn&CURSTATUS=start";
	}else if(num == 2){
		location.href = url + "administrator/curriculumn/curriculumnList.do?bid=Curriculumn&CURSTATUS=wait";
	}else if(num == 3){
		location.href = url + "administrator/curriculumn/curriculumnList.do?bid=Curriculumn&CURSTATUS=finish";
	}else if(num == 4){
		location.href = url + "administrator/curriculumn/curriculumnRegister.do?bid=Curriculumn";
	}
}
function adminMenu05(num){
	if(num == 1){
		location.href = url + "administrator/document/documentList.do?bid=Document";
	}else if(num == 2){
		location.href = url + "administrator/document/recordingList.do?bid=Record";
	}else if(num == 3){
		location.href = url + "administrator/document/uploadSettingList.do?bid=Record";
	}
}
function adminMenu06(num){
	if(num == 1){
		location.href = url + "administrator/classroom/classProcessRoomList.do?bid=ClassRomm";
	}else if(num == 2){
		location.href = url + "administrator/classroom/classEndRoomList.do?bid=ClassRomm";
	}
}
function adminMenu07(num){
	if(num == 1){
		location.href = url + "administrator/board/List.do?bid=Notice";
	}else if(num == 2){
		location.href = url + "administrator/board/List.do?bid=Faq";
	}else if(num == 3){
		location.href = url + "administrator/board/List.do?bid=Board";
	}else if(num == 4){
		location.href = url + "administrator/board/List.do?bid=Oneandone";
	}
}
function adminMenu08(num){
	if(num == 1){
		location.href = url + "administrator/plan/planList.do";
	}else if(num == 2){
		location.href = url + "administrator/plan/code_register.do";
	}
}
function adminMenu09(num){
	if(num == 1){
		location.href = url + "administrator/statistics/connectList.do";
	}else if(num == 2){
		location.href = url + "administrator/statistics/code_register.do";
	}else if(num == 3){
		location.href = url + "administrator/statistics/paymentList.do";
	}else if(num == 4){
		location.href = url + "administrator/statistics/code_register.do";
	}else if(num == 5){
		location.href = url + "administrator/statistics/code_register.do";
	}
}