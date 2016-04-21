var forms;
$(function(){
	//console.log("curriculumn 시작");
	//교육과정 변경했을때 실행되는 부분.
	$("#ProductList").on("change", function(){
		var str;
		$(this).children().each(function(index, item){
			if($(this).prop("selected") == true){
				str = $(this).text();
				return false;
			}
		});
		fn_getProductDetailInfo($(this).val(), str);
	});
	
	$( ".from" ).datepicker({
	      defaultDate: "+1w",
	      changeMonth: false,
	      numberOfMonths: 3,
	      dateFormat:'yy.mm.dd',
	      monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
	      monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
	      dayNames: ['일','월','화','수','목','금','토'],
	      dayNamesShort: ['일','월','화','수','목','금','토'],
	      dayNamesMin: ['일','월','화','수','목','금','토'],
	      showMonthAfterYear: true,
	      yearSuffix: '년',
	      onClose: function( selectedDate ) {
	        $( "#to" ).datepicker( "option", "minDate", selectedDate );
	      }
	    });

	    $( ".to" ).datepicker({
	      defaultDate: "+1w",
	      changeMonth: false,
	      numberOfMonths: 3,
	      dateFormat:'yy.mm.dd',
	      monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
	      monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
	      dayNames: ['일','월','화','수','목','금','토'],
	      dayNamesShort: ['일','월','화','수','목','금','토'],
	      dayNamesMin: ['일','월','화','수','목','금','토'],
	      showMonthAfterYear: true,
	      yearSuffix: '년',
	      onClose: function( selectedDate ) {
	        $( "#from" ).datepicker( "option", "maxDate", selectedDate );
	      }
	  });
	    
	    $("#PriceSaleRate").on("change", function(){
	    	if($(this).val() =="etc"){
	    		$("#EtcSaleRate").prop("disabled",false);
	    		$("#CURSALEPRICE").val($("#CURPRICE").val());
	    		$("#EtcSaleRate").focus();
	    	}else{
	    		var rate = $(this).val();
	    		if(rate == undefined || rate == ""){
	    			$("#CURSALEPRICE").val($("#CURPRICE").val());
	    		}else{
	    			
	    			fn_salePrice($(this).val());
	    		}
	    		$("#EtcSaleRate").val('');
	    		$("#EtcSaleRate").prop("disabled",true);
	    	}
	    });
	    
	    $("#EtcSaleRate").on("keydown", function(e){
	    	if(e.keyCode == 13){
	    		console.log('엔터');
	    		if($(this).val() >= 0 && $(this).val()<= 100){
	    			fn_salePrice($(this).val());
	    		}else{
	    			alert("0과 100사이에 숫자를 입력하셔야 합니다.");
	    			$(this).val('');
	    			$(this).focus();
	    		}
	    	}
	    })
	    
	    //할인율 적용했을때.
	    $("#SaleYes").on("click", function(){
	    	if($("#CURPRICE").val() != "" && $("#CURPRICE").val() != undefined){
	    		$("#PriceSaleRate").prop("disabled", false);
	    	}else{
	    		return false;
	    	}
	    });
	    
	    //할인율 미적용했을때
	    $("#SaleNo").on("click", function(){
	    	$("#PriceSaleRate").prop("disabled", true);
	    	$("#PriceSaleRate option:eq(0)").prop("selected", true);
	    	$("#CURSALEPRICE").val($("#CURPRICE").val());
	    });
	    
	    $("#CURPRICE").on("focusout", function(){
	    	if($(this).val() != "" && $(this).val() != undefined){
	    		var price = numberWithCommas($(this).val());
		    	$(this).val(price);
		    	$("#CURSALEPRICE").val(price);
	    	}else{
	    		$("#CURSALEPRICE").val('');
	    		$("#SaleNo").prop("checked", true);
	    		$("#EtcSaleRate").val('');
	    		$("#EtcSaleRate").prop("disabled", true);
	    		$("#PriceSaleRate").prop("disabled", true);
	    		$("#PriceSaleRate option:eq(0)").prop("selected", true);
	    	}
	    });
	    
	    //폼유효성 검사.
	    $("#curriculumnRegistBtn").on("click", function(e){
	    	e.preventDefault();
	    	console.log("눌렀음.^_^")
	    	fn_curriculumnValidate();
	 
	    });
});

function formCheck(obj, msg){
	var isReturn = false;
	if(obj.val() == "" || obj.val() == undefined){
		alert(msg +"을 입력해 주세요.");
		obj.focus();
		//return false;
		isReturn = false;
	}else{
		isReturn = true;
	}
	//console.log("isReturn",isReturn);
	return isReturn;
}

//경고메세지를 뿌려주는 부분.
function MSG(str, obj){
	alert(str)
	obj.focus();
	return false;
}

//체크박스가 한개라도 체크되어 있는지 체크하는 부분.
function fn_CheckBox(obj, str){
	//console.log(obj);
	var isSelected = false;
	
	for(var i=0; i<obj.length; i++){
		if(obj[i].checked == true){
			isSelected = true;
			//return false;
		};
	}
	if(isSelected == false){
		alert(str + '을 선택해 주세요.');
		obj[0].focus();
	}
	console.log("isSelected = ", isSelected);
	return isSelected;
}


//세일된 값
function fn_salePrice(rate){
	if($("#CURPRICE").val() != "" && $("#CURPRICE").val() != undefined){
		var price = $("#CURPRICE").val().replace(/[^0-9]/g,'');
		var salePrice = price - Math.floor(price*rate/100);
		salePrice = numberWithCommas(salePrice);
		$("#CURSALEPRICE").val(salePrice);
	}
}

function fn_getProductList(mode, procode){
	//console.log("코드리스트 가져오기");
	var url ="productList.do?bid=Curriculumn&mode="+mode+"&procode="+procode;
	var dType ="json";
	fn_getAjax(url, dType,fn_getProductSuccess);
}

function fn_getProductSuccess(data){
	//console.log(data);
	var mode = data.mode;
	var codeList = data.codeList;
	var procode = data.procode;
	console.log(mode, procode);
	var listStr = "<option value=''>== 교육과정을 선택해 주세요. ==</option>";
	var proname;
	for(var i=0; i<codeList.length; i++){
		var isSelected = "";
		if(codeList[i].CODESEQ == procode){
			isSelected = "selected";
			proname = codeList[i].PRONAME;
		}
		listStr += "<option value='"+fn_leadZero(codeList[i].CODESEQ, 6)+"' "+isSelected+">" + codeList[i].PRONAME + "</option>";
	}
	$("#ProductList").html(listStr);
	
	if(mode == "Edit"){
		fn_getProductDetailInfo(procode, proname, 'Edit');
	}
}

//앞자리 0으로 채우기
function fn_leadZero(n, digits){
	var zero = '';
	  n = n.toString();

	  if (n.length < digits) {
	    for (var i = 0; i < digits - n.length; i++)
	      zero += '0';
	  }
	  return zero + n;
}

//상세정보를 가져옴.
function fn_getProductDetailInfo(seq, str, mode){
	if(seq != ""){
		$("#CURCODE").val(seq);
		$("#CURCOURSE").val(str);
		console.log(str);
	}else{
		$("#CURCODE").val('');
		$("#CURCOURSE").val('')
	}
	var url = "ProductDetailInfo.do?bid=Curriculumn&CODESEQ=" + seq +"&mode=" +mode;
	var dType = "json";
	fn_getAjax(url, dType, fn_getProductDetailInfoSuccess);
}

//Select박스에 선택한 교육과정에 맞게 세팅
function fn_getProductDetailInfoSuccess(data){
	console.log("data = ",data);
	
	if(data.message == "error"){
		
	}
	
	var SelectTextArray = [];
	SelectTextArray['PROWEEK'] = "요일선택";
	SelectTextArray['PRODUCTSORT'] = "수업방법";
	SelectTextArray['WEEKREPEAT'] = "수업횟수";
	SelectTextArray['PROMONTH'] = "수업기간";
	SelectTextArray['PROWEEKEND'] = "수업종류";
	SelectTextArray['DAYPERMINUTE'] = "수업시간";
	var j=0;
	for(var key in data){
		console.log(key);
		if(key != "CODE"){
			var tmpArray = data[key].split(",");
			var tmpStr = "<option value=''>== "+SelectTextArray[key]+"을 선택하세요. ==</option>";
			var tmpProWeek = "";
			for(var i=0; i<tmpArray.length; i++){
				if(key != "PROWEEK"){
					tmpStr += "<option value='"+tmpArray[i]+"'>"+tmpArray[i]+"</option>";
				}else{
					tmpProWeek += '<input type="checkbox" name="PROWEEK" class="tblCheckbox" id="yo'+i+'" value="'+tmpArray[i]+'"/><label for="yo'+i+'">'+tmpArray[i]+'</label>';
				}
			}
			
			if(key != "PROWEEK"){
				$("#"+key).html(tmpStr);
				tmpStr="";
			}else{
				$("#"+key).html(tmpProWeek);
				tmpProWeek="";
			}
			j++;
		}
	}

	if(data.mode == 'Edit'){
		fn_curriculumnEditSetting();
	}
}

function fn_getTimes(){
	var timeStr = "";
	for(var i=0; i<24; i++){
		for(var j=0; j<2; j++){
			var hour = fn_timeChange(i);
			var minute = fn_timeChange(j*30);
			var times = hour + ":" + minute;
			timeStr += "<option value='"+times+"'>"+ times +"</option>"; 
		}
	}
	$("#StartTime").append(timeStr);
	$("#FinishTime").append(timeStr);
}

//시간이 10보다 작으면 앞에 0을 붙임.
function fn_timeChange(str){
	if(str >= 0 && str < 10){
		return "0" + str;
	}
	
	if(str >= 10){
		return str;
	}
}

//백단위로 짜르기.
function numberWithCommas(x) {
	return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

//숫자만 입력받기
function InpuOnlyNumber(obj) 
{
    if (event.keyCode >= 48 && event.keyCode <= 57) { //숫자키만 입력
        return true;
    } else {
        event.returnValue = false;
    }
}

function fn_payMent(pay){
	console.log(pay);
	var tempPay = pay.split(",");
	var payStr = "";
	var i=0;
	for(var i = 0; i<tempPay.length; i++){
		payStr += "<input type='radio' name='CURPAY' value='"+tempPay[i]+"' id='pay_"+i+"'/><label for='pay_"+i+"' class='payLabel'>"+tempPay[i]+"</label> ";
	}
	
	$("#payBox").append(payStr);
}

function fn_curriculumnValidate(){
	/*var formID = $("#curriculumnRegistBtn1").parents("form").attr("id");
	console.log(formID)*/
	var domObj = [$("#ProductList"),$("input[name='CURPRONAME']"), $("select[name='CURSORT']"), $("#PROMONTH"), $("#from"), $("#to"),
	              $("#PROWEEKEND"), $("#WEEKREPEAT"), $("input[name='PROWEEK']"), $("#StartTime"), $("#FinishTime"), $("#DAYPERMINUTE"),
	              $("input[name='payMemtMethod']"),$("#CURPRICE"), $("select[name='CURTEACHER']"), $("input[name='CURMEMBERCOUNT']"), $("#fromRegist"), $("#toRegist")];
	var alertMsg = ["교육과정", "상품명","수업방법", "수업기간", "시작월", "종료월", "수업종류", "수업횟수", "요일선택", "시작시간", "종료시간", "수업시간",
	                "결제방식", "가격", "강사", "정원","접수시작기간", "접수종료기간"];
	
	for(var i=0; i<domObj.length; i++){
		if(alertMsg[i] != "요일선택" && alertMsg[i] != "결제방식"){
			//체크박스가 아닐때.
			if(!formCheck(domObj[i], alertMsg[i])){return false};
		}else{
			//체크박스일때.
			if(!fn_CheckBox(domObj[i], alertMsg[i])){return false};
		}
	}
	//console.log($("#"+formID));
	//$("#"+formID)[0].submit();
}

function fn_insertDom(){	
	var insertInput = "";
	var ProductListName;
	var productCode;
	$("#ProductList option").each(function(index, item){
		if($(this).prop("selected") == true){
			ProductListName = $(this).text();
			productCode = $(this).val();
			return false;
		}
	});
	
	/*var ProductListName = $("#ProductList").find("selected").text();
	var productCode = $("#ProductList").val();*/
	var startMonth = $("#from").val();
	var endMonth = $("#to").val();
	var curMonths = startMonth + "~" + endMonth; 
	var startTime = $("#StartTime").val();
	var endTime = $("#FinishTime").val();
	var curTime = startTime + "~" + endTime;
	var weeks;
	var weekArray = [];
	$("input[name='PROWEEK']").each(function(){
		if($(this).prop("checked") == true){
			weekArray.push($(this).val());
		}
	});
	var weeks = weekArray.join();
	
	
	var payMentArray = [];
	$("input[name='payMemtMethod']").each(function(){
		if($(this).prop("checked") == true){
			payMentArray.push($(this).val());
		}
	});
	var payMent = payMentArray.join();
	
	if($("#EtcSaleRate").val() == "" || $("#EtcSaleRate").val() == undefined){
		$("#EtcSaleRate").val($("#PriceSaleRate").val());
	}
	
	var saleRate = $("#EtcSaleRate").val();
	var fromRegist = $("#fromRegist").val();
	var toRegist = $("#toRegist").val();
	var regTime = fromRegist + "~" + toRegist;
	insertInput += "<input type='text' name='bid' value='Curriculumn' />";
	insertInput += "<input type='text' name='PROCODE' value='"+productCode+"' />";
	insertInput += "<input type='text' name='CURCOURSE' value='"+ProductListName+"' />";
	insertInput += "<input type='text' name='CURMONTHPERIOD' value='"+curMonths+"' />";
	insertInput += "<input type='text' name='CURTIMEPERIOD' value='"+curTime+"' />";
	insertInput += "<input type='text' name='CURPROWEEK' value='"+weeks+"' />";
	insertInput += "<input type='text' name='CURPRICESORT' value='"+payMent+"' />";
	insertInput += "<input type='text' name='CURSALERATE' value='"+saleRate+"' />";
	insertInput += "<input type='text' name='CURREGPERIOD' value='"+regTime+"' />";
	
	//$("#"+formID).append(insertInput);
	return insertInput;
	/*forms = $("#"+formID);
	console.log("forms", forms);*/
}

function fn_curriculumnEditSetting(){
	//수업방법.
	fn_selectBoxSelected($("#PRODUCTSORT option"), curSort);
	
	//수업기간
	fn_selectBoxSelected($("#PROMONTH option"), curMonth);
	fn_split(curMonthPeriod, "~", $("#from"), $("#to"));
	
	//수업종류
	fn_selectBoxSelected($("#PROWEEKEND option"), curProWeekend);
	
	//수업회수
	fn_selectBoxSelected($("#WEEKREPEAT option"), curWeekPerCount);
	
	//요일선택
	fn_checkBoxChecked(curProWeek,",",$("input[name='PROWEEK']"))
	
	//수업시간대
	fn_split(curTimePeriod, "~", $("#StartTime"), $("#FinishTime"));
	fn_selectBoxSelected($("#DAYPERMINUTE option"), curProMinute);
	
	//결제방식
	fn_checkBoxChecked(curPriceSort,",",$("input[name='payMemtMethod']"));
	
	//할인적용
	fn_radioBtnChecked($("input[name='CURPRICESALESORT']"), curPriceSaleSort);
	
	//강사선택
	fn_selectBoxSelected($("select[name='CURTEACHER'] option"), curTeacher);
	
	//출석여부
	fn_radioBtnChecked($("input[name='CURATTENDANT']"), curAttendant);
	
	//수업진행여부
	fn_radioBtnChecked($("input[name='CURSTATUS']"), curStatus);
	
	//접수기간
	fn_split(curRegPeriod, "~", $("#fromRegist"), $("#toRegist"));
}

//SelectBox 값에 따라 세팅하기
function fn_selectBoxSelected(obj, val){
	console.log(val);
	obj.each(function(){
		if($(this).val() == val){
			$(this).prop("selected", true);
			return false;
		}
	});
}

//날짜 기간에 따라 값세팅
function fn_split(val, divStr, firstObj, secondObj){
	var tmpValue = val.split(divStr);
	firstObj.val(tmpValue[0]);
	secondObj.val(tmpValue[1]);
}

//Checkbox 값에 따라 세팅
function fn_checkBoxChecked(val, divStr, obj){
	var tmpValue = val.split(divStr);
	obj.each(function(){
		for(var i=0; i<tmpValue.length; i++){
			if(tmpValue[i] == $(this).val()){
				$(this).prop("checked", true);
			}
		}
	});
}

//라디오버튼 값세팅
function fn_radioBtnChecked(obj, val){
	obj.each(function(){
		if($(this).val() == val){
			$(this).prop("checked", true);
			if($(this).attr("id") == "SaleYes"){
				fn_priceSale();
			}
			return false;
		}
	});
}

function fn_priceSale(){
	$("#PriceSaleRate").attr("disabled", false);
	var saleRate;
	if(curSaleRate != '0' && curSaleRate != '5' && curSaleRate != '10' && curSaleRate != '15' && curSaleRate != '20'){
		$("#EtcSaleRate").val(curSaleRate).attr("disabled", false);
		saleRate = "etc";
	}
	fn_selectBoxSelected($("#PriceSaleRate option"), saleRate);
}