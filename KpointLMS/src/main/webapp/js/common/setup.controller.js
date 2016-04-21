var CheckBoxArrayValue = [];
var CheckBoxName = [];
$(function(){
	//console.log("시작");
	$("#CodeRegist").on("click", function(e){
		e.preventDefault();
		if($("#PRONAME").val() == ""){
			MSG("상품명을 입력해 주세요.", $("#PRONAME"));
		}else if(fn_CheckBox($("input[name='PRODUCTSORT']")) == false){
			MSG("수업방식을 선택해 주세요.",$("input[name='PRODUCTSORT']")[0]);
		}else if(fn_CheckBox($("input[name='PROMONTH']")) == false){
			MSG("수업시간(개월)을 선택해 주세요.",$("input[name='MONTH']")[0]);
		}else if(fn_CheckBox($("input[name='PROWEEKEND']")) == false){
			MSG("수업시간(주중.주말)을 선택해 주세요.",$("input[name='PROWEEKEND']")[0]);
		}else if(fn_CheckBox($("input[name='WEEKREPEAT']")) == false){
			MSG("수업시간(회수)을 선택해 주세요.",$("input[name='WEEKREPEAT']")[0]);
		}else if(fn_CheckBox($("input[name='PROWEEK']")) == false){
			MSG("수업시간(요일)을 선택해 주세요.",$("input[name='WEEK']")[0]);
		}else if(fn_CheckBox($("input[name='DAYPERMINUTE']")) == false){
			MSG("수업시간(분)을 선택해 주세요.",$("input[name='DAYPERMINUTE']")[0]);
		}else{
			
			//$("#codeRegistForm").submit();
			//return true;
			var form = $("<form>");
			var actionURL = $("#registForm").attr("action");
			form.attr("name","CodeRegisterForm").attr("id","CodeRegisterForm").attr("action",actionURL).attr("method", "POST");
			console.log(form);
			var tempName;
			
			$("input").each(function(index, item){
				//console.log(item.name);
				if(item.name != tempName){
					CheckBoxName.push(item.name);
					
					if($(this).attr("type") == "checkbox"){
						fn_CheckboxValue($("input[name='"+item.name+"']"));
					}else{
						fn_InputValue($("input[name='"+item.name+"']"));
					}
					
					tempName = item.name;
				}
			});
			
			var InputStr = "";
			for(var i=0; i<CheckBoxName.length; i++){
				InputStr += "<input type='text' name='"+CheckBoxName[i]+"' id='"+CheckBoxName[i]+"' value='"+CheckBoxArrayValue[i]+"' />";
			}
			
			form.append(InputStr);
			form.submit();
		}
	});
	
	
});

//경고메세지를 뿌려주는 부분.
function MSG(str, obj){
	alert(str)
	obj.focus();
	return false;
}

//체크박스가 한개라도 체크되어 있는지 체크하는 부분.
function fn_CheckBox(obj){
	//console.log(obj);
	var isCheck = false;
	//console.log(obj.length);
	obj.each(function(){
		if($(this).prop("checked") == true){
			isCheck = true;
			return false;
		}
	});
	
	return isCheck;
}

//input[type='text']값을 입력.
function fn_InputValue(obj){
	CheckBoxArrayValue.push(obj.val());
}

//체크박스 값을 입력.
function fn_CheckboxValue(obj){
	var checkboxArray = [];
	obj.each(function(index, item){
		if($(this).prop("checked") == true){
			checkboxArray.push($(this).val());
		}
	});
	CheckBoxArrayValue.push(checkboxArray.join());
}

//받이온 데이터를 페이지에 세팅
function fn_CheckBoxSetting(arrayList){
	console.log(arrayList.length);
	for(key in arrayList){
		fn_ViewCheckBox(key, arrayList[key]);
	}
}

function fn_ViewCheckBox(key, item){
	var tempArray = item.split(",");
	var checkLength = $("input[name='"+key+"']").length;
	for(var i=0; i<tempArray.length; i++){
		for(var j=0; j<checkLength; j++){
			var items = $("input[name='"+key+"']")[j];
			
			if(items.value == tempArray[i]){
				items.checked = true;
			}
		}
		
	}
}

//보드상세설정 부분 세팅
function fn_settingBoardConfig(list, view, reg, edit, reply, comment, del, isReply, isComment, isRecommandation, isNoRecommandation, tableList, selectTableList, userRole){
	//테이블이름을 세팅하는 부분
	tableListArray = cutString(tableList, ",", "[", "]");
	isUseArray = [];
	isUseArray['ISREPLY'] = isReply;
	isUseArray['ISCOMMENT'] = isComment;
	isUseArray['ISRECOMMANDATION'] = isRecommandation;
	isUseArray['ISNORECOMMANDATION'] = isNoRecommandation;
	var tableSelect = "";
	for(var i=0; i<tableListArray.length; i++){
		var tmpValue = cutString(tableListArray[i], "=", "{", "}")[1];
		if(selectTableList == tmpValue){
			tableSelect += "<option value='"+tmpValue+"' selected>"+tmpValue+"</option>";
		}else{
			tableSelect += "<option value='"+tmpValue+"'>"+tmpValue+"</option>";
		}
		
	}
	$("#selectTableList").html(tableSelect);
	
	//사용여부를 세팅하는 부분
	for(var key in isUseArray){
		if(isUseArray[key] == 'Y'){
			$("#"+key).prop("checked",true);
		}
	}
	
	//권한설정을 세팅하는 부분
	$("#VIEWS").html(fn_setUserRoleSetting(userRole, "VIEW", view));
	$("#REGS").html(fn_setUserRoleSetting(userRole, "REG", reg));
	$("#EDITS").html(fn_setUserRoleSetting(userRole, "EDIT", edit));
	$("#COMMENTS").html(fn_setUserRoleSetting(userRole, "COMMENT", comment));
	$("#DELS").html(fn_setUserRoleSetting(userRole, "DEL", del));
	$("#REPLYS").html(fn_setUserRoleSetting(userRole, "REPLY", reply));
	$("select:not(#selectTableList)").addClass("smallSelect");
}

//해당문자를 주어진문자에 맞추어서 split하고 replace함.
function fn_setUserRoleSetting(userRole, category, categoryValue){
	var strList = cutString(userRole, ",", "[", "]");
	var SelectStr = "<select name='"+category+"' id='"+category+"'>";
	for(var i=0; i<strList.length; i++){
		tmpStr = cutString(strList[i],"=", "{", "}")[1];
		var selectTxt;
		if(tmpStr == categoryValue){
			SelectStr += "<option value='"+tmpStr+"' selected>"+hangulRoleText(tmpStr)+"</option>";
		}else{
			SelectStr += "<option value='"+tmpStr+"'>"+hangulRoleText(tmpStr)+"</option>";
		}
	}
	SelectStr += "</select>";
	return SelectStr;
}

function cutString(str, splitStr, cutStr1, cutStr2){
	var tmpStr;
	if(cutStr1 != undefined){
		tmpStr = str.replace(cutStr1,"");
	}
	
	if(cutStr2 != undefined){
		tmpStr = tmpStr.replace(cutStr2,"");
	}
	
	return splitArray(tmpStr, splitStr);
}

function splitArray(str, splitStr){
	var tmpArray = str.split(splitStr);
	return tmpArray;
}

function hangulRoleText(str){
	var hangulStr;
	switch(str){
		case "ROLE_USER":
			hangulStr = "일반학생";
			break;
		case "ROLE_STUDENT":
			hangulStr = "등록학생";
			break;
		case "ROLE_TEACHER":
			hangulStr = "선생님";
			break;
		case "ROLE_ADMIN":
			hangulStr = "관리자";
			break;
		case "ROLE_SUPERADMIN":
			hangulStr = "최고관리자";
			break;
	}
	return hangulStr;
}