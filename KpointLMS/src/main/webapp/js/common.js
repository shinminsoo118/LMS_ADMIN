//Null 체크
function isNull(str) {
    if (str == null) return true;
    if (str == "NaN") return true;
    if (new String(str).valueOf() == "undefined") return true;    
    var chkStr = new String(str);
    if( chkStr.valueOf() == "undefined" ) return true;
    if (chkStr == null) return true;    
    if (chkStr.toString().length == 0 ) return true;
    return false; 
}

//폼을 생성하고 해당데이터를 넣어 전송함.
function ComSubmit(paramFormId) {
    this.formId = isNull(paramFormId) == true ? "commonForm" : paramFormId;
    this.url = "";
     
    console.log("formId" , this.formId);
    if(this.formId == "commonForm"){
        $("#commonForm")[0].reset();
    }
     
    this.setUrl = function setUrl(url){
        this.url = url;
    };
     
    this.addParam = function addParam(key, value){
        $("#"+this.formId).append($("<input type='hidden' name='"+key+"' id='"+key+"' value='"+value+"' >"));
    };
     
    this.submit = function submit(){
        var frm = $("#"+this.formId)[0];
        frm.action = this.url;
        frm.method = "post";
        frm.submit();   
    };
}

//날짜을 해당 형식에 맞게 세팅하는 함수.
Date.prototype.format = function(f) {
    if (!this.valueOf()) return " ";
 
    var weekName = ["일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일"];
    var d = this;
     
    return f.replace(/(yyyy|yy|MM|dd|E|hh|mm|ss|a\/p)/gi, function($1) {
        switch ($1) {
            case "yyyy": return d.getFullYear();
            case "yy": return (d.getFullYear() % 1000).zf(2);
            case "MM": return (d.getMonth() + 1).zf(2);
            case "dd": return d.getDate().zf(2);
            case "E": return weekName[d.getDay()];
            case "HH": return d.getHours().zf(2);
            case "hh": return ((h = d.getHours() % 12) ? h : 12).zf(2);
            case "mm": return d.getMinutes().zf(2);
            case "ss": return d.getSeconds().zf(2);
            case "a/p": return d.getHours() < 12 ? "오전" : "오후";
            default: return $1;
        }
    });
};
 
String.prototype.string = function(len){var s = '', i = 0; while (i++ < len) { s += this; } return s;};
String.prototype.zf = function(len){return "0".string(len - this.length) + this;};
Number.prototype.zf = function(len){return this.toString().zf(len);};


//get방식으로 ajax를 요청했을때 처리하는 부분
function fn_getAjax(url, dType, func, data){
	/*
	 * url 요청할 URL 
	 * func ajax부분이 성공했을때 처리할 함수.
	 */
	var getAjax = $.ajax({
		url: url,
		type: 'get',
		data: data,
		contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
		dataType: dType
	});
	
	//ajax 요청한 부분이 성공했을때.
	getAjax.done(function(data){
		func(data);
	});
}

//post방식으로 ajax를 요청했을때 처리하는 부분
function fn_postAjax(formName, url, dType, func, data){
	var formData = $("#"+formName).serialize().replace(/%/g,'%25');
	var postAjax = $.ajax({
		url: url,
		type: 'post',
		data: data,
		contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
		data: formData,
		dataType: dType
	});
	
	//ajax 요청한 부분이 성공했을때.
	postAjax.done(function(data){
		func(data);
	});
}

//기본 변수값을 세팅하는 부분.
//화면이 로딩될때 inc폴더에  common.jsp에서 변수값을 세팅.
function fn_setVariable(cPath, boardId, boardSeq, uRole, uName){
	contextPath = cPath;
	bid = boardId;
	seq = boardSeq;
	adminRole = uRole;
	userName = uName;
}



