
$(document).ready(function() {
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_CURRENCY&tableCol=CCY,CCY_DESC",
		id: "ccy",
		async: false
	});

	$("#fmCurrencyAttachAdd").Validform({
		tiptype:2,
		callback:function(form){
			fmCurrencyAttachAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function fmCurrencyAttachAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="FM_CURRENCY_ATTACH";
		generalFieldsJson.CCY=$("#ccy").val();
		generalFieldsJson.COUNTRY=$("#country").val();
		generalFieldsJson.SPREAD_POINT_TYPE=$("#spreadPointType").val();
		generalFieldsJson.RATE_TYPE_RELATED=$("#rateTypeRelated").val();
		generalFieldsJson.SPREAD_POINT=$("#spreadPoint").val();
		generalFieldsJson.RATE_TYPE=$("#rateType").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_fmCurrencyAttachAdd,"json");
}

function callback_fmCurrencyAttachAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function fmCurrencyAttachAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}


