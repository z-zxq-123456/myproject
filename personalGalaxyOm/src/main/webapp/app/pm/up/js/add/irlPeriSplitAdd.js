$(document).ready(function() {
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=IRL_INT_TYPE&tableCol=INT_TAX_TYPE,INT_TAX_TYPE_DESC",
		id: "intType",
		async: false
	});

	$("#irlPeriSplitAdd").Validform({
		tiptype:2,
		callback:function(){
			irlPeriSplitAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function irlPeriSplitAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="IRL_PERI_SPLIT";
    keyFieldsJson.PERI_SEQ_NO=$("#periSeqNo").val();
    keyFieldsJson.PERI_SPLIT_ID=$("#periSplitId").val();
    generalFieldsJson.PERIOD=$("#period").val();
    generalFieldsJson.PERIOD_TYPE=$("#periodType").val();
    generalFieldsJson.RULE_ID=$("#ruleId").val();
    generalFieldsJson.RECAL_METHOD=$("#recalMethod").val();
    generalFieldsJson.PERI_SPLIT_MODE=$("#periSplitMode").val();
    generalFieldsJson.RECAL_DAYS=$("#recalDays").val();
    generalFieldsJson.INT_TYPE=$("#intType").val();
    generalFieldsJson.AMT_SPLIT_ID=$("#amtSplitId").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_irlPeriSplitAdd,"json");
}

function callback_irlPeriSplitAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function irlPeriSplitAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}