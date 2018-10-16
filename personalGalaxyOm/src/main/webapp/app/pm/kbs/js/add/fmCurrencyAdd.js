
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

	$("#fmCurrencyAdd").Validform({
		tiptype:2,
		callback:function(form){
			fmCurrencyAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function fmCurrencyAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="FM_CURRENCY";
		keyFieldsJson.CCY=$("#ccy").val();
		generalFieldsJson.CCY_DESC=$("#ccyDesc").val();
		generalFieldsJson.ROUND_TRUNC=$("#roundTrunc").val();
		generalFieldsJson.INTEGER_DESC=$("#integerDesc").val();
		generalFieldsJson.DECI_PLACES=$("#deciPlaces").val();
		//generalFieldsJson.CCY_LIBOL=$("#ccyLibol").val();
		generalFieldsJson.COMPANY=$("#company").val();
		generalFieldsJson.DAY_BASIS=$("#dayBasis").val();
		generalFieldsJson.DECI_DESC=$("#deciDesc").val();
		generalFieldsJson.WEEKEND_1=$("#weekend1").val();
		generalFieldsJson.FIXING_DAYS=$("#fixingDays").val();
		generalFieldsJson.WEEKEND_2=$("#weekend2").val();
		generalFieldsJson.PAY_ADVICE_DAYS=$("#payAdviceDays").val();
		generalFieldsJson.POSITION_LIMIT=$("#positionLimit").val();
		generalFieldsJson.QUOTE_TYPE=$("#quoteType").val();
		generalFieldsJson.CCY_INT_CODE=$("#ccyIntCode").val();
		generalFieldsJson.CCY_GROUP_CODE=$("#ccyGroupCode").val();
		generalFieldsJson.CCY_GROUP=$("#ccyGroup").val();
		generalFieldsJson.SPOT_DATE=$("#spotDate").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_fmCurrencyAdd,"json");
}

function callback_fmCurrencyAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function fmCurrencyAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}


