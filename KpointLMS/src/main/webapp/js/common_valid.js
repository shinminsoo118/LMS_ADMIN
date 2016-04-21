var common_valid = {
//	action : "action",
//	enctype : "enctype",
	patterns  : [/^[0-9]+$/,											 //	num 0
	             /^[가-힣]{2,4}$/,										 //	han 1
	             /^[a-zA-Z]+$/,											 //	eng 2
	             /^[0-9a-zA-Z]+$/,										 //	eng_num 3
	             /^[_a-zA-Z0-9-]+@[._a-zA-Z0-9-]+.[a-zA-Z]+$/,			 //	email 4
	             /^[.a-zA-Z0-9-]+.[a-zA-Z]+$/,							 //	url 5
	             /.+(.jpg|.gif|.bmp|.png|.jpeg)+$/gi,					 //	img 6
	             /^[a-z0-9_-]{4,20}$/,									 //아이디 유효성 검사 7
	             /^[0-9_-]{8,20}$/,										 //전화번호 유효성 검사 8
	             /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{6,16}$/, // password 9
	             /^[가-힣,.]{2,20}$/,										 //	han 10자 10
	             /^[a-zA-Z_]+$/,										 //	eng_ 11
	             ],
	message   : ["숫자만 입력할 수 있습니다.","한글과 4자이하로만 입력할 수 있습니다.",									
	             "영문만 입력할 수 있습니다.","영문/숫자만 입력할 수 있습니다.",								
	             "EMAIN형식에 맞지 않습니다.","URI형식과 맞지 않습니다.",					
	             "IMG형식과 맞지 않습니다.","아이디를 다시 입력해 주세요.",
	             "핸드폰번호를 다시 입력해 주세요.","영문,숫자,특문을 조합하세요.패스워드를 다시 입력해 주세요."
	             ,"한글과 20자이하로만 입력할 수 있습니다."
	             ],
	nullMsg : "입력한 값이 없습니다. 다시 입력하세요.",
	
	validFunc : function(str, patternType, validCheck){
		if(str == null || str == "" || str.length == 0){
			alert(this.nullMsg);
			validCheck = 0;
			return validCheck;
		}
		
		if(!str.match(this.patterns[patternType])){
			alert(this.message[patternType]);
			validCheck = 0;
			return validCheck;
		}
		
		return validCheck;
	},
	validSelectFunc : function(str, validCheck){
	if(str == null || str == "" || str.length == 0){
		alert(this.nullMsg);
		validCheck = 0;
		return validCheck;
	}
	
	return validCheck;
}
};

var common_util = {
		
}
