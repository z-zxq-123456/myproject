
$(document).ready(function() {
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_CURRENCY&tableCol=CCY,CCY_DESC",
		id: "ccy",
		async: false
	});
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
		id: "company",
		async: false
	});

	$("#fmCcyHolidayAdd").Validform({
		tiptype:2,
		callback:function(form){
			fmCcyHolidayAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function fmCcyHolidayAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="FM_CCY_HOLIDAY";
		keyFieldsJson.HOLIDAY_DATE=$("#holidayDate").val();
		generalFieldsJson.HOLIDAY_DESC=$("#holidayDesc").val();
		generalFieldsJson.APPLY_IND=$("#applyInd").val();
		generalFieldsJson.HOLIDAY_TYPE=$("#holidayType").val();
		keyFieldsJson.CCY=$("#ccy").val();
		generalFieldsJson.COMPANY=$("#company").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_fmCcyHolidayAdd,"json");
}

function callback_fmCcyHolidayAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function fmCcyHolidayAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}


