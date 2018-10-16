$(document).ready(function() {
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=IRL_INT_TYPE&tableCol=INT_TAX_TYPE,INT_TAX_TYPE_DESC",
		id: "intType",
		async: false
	});

	$("#irlAmtSplitAdd").Validform({
		tiptype:2,
		callback:function(){
			irlAmtSplitAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function irlAmtSplitAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="IRL_AMT_SPLIT";
    keyFieldsJson.AMT_SEQ_NO=$("#amtSeqNo").val();
    keyFieldsJson.AMT_SPLIT_ID=$("#amtSplitId").val();
    generalFieldsJson.AMT_SPLIT_MODE=$("#amtSplitMode").val();
    generalFieldsJson.INT_TYPE=$("#intType").val();
    generalFieldsJson.PERI_SPLIT_ID=$("#periSplitId").val();
    generalFieldsJson.RULE_ID=$("#ruleId").val();
    generalFieldsJson.SPLIT_AMT=$("#splitAmt").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_irlAmtSplitAdd,"json");
}

function callback_irlAmtSplitAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function irlAmtSplitAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}