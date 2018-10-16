$(document).ready(function() {
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_CURRENCY&tableCol=CCY,CCY_DESC",
		id: "ccy",
		async: false
	});
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=MB_EVENT_DEFAULT_TYPE&tableCol=EVENT_DEFAULT_TYPE",
		id: "eventType",
		async: false
	});
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=IRL_INT_TYPE&tableCol=INT_TAX_TYPE,INT_TAX_TYPE_DESC",
		id: "intType",
		async: false
	});

	$("#mbStageIntAdd").Validform({
		tiptype:2,
		callback:function(){
			mbStageIntAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function mbStageIntAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="MB_STAGE_INT";
    keyFieldsJson.SEQ_NO=$("#seqNo").val();
    keyFieldsJson.STAGE_CODE=$("#stageCode").val();
    generalFieldsJson.CCY=$("#ccy").val();
    generalFieldsJson.PROD_TYPE=$("#prodType").val();
    generalFieldsJson.ISSUE_YEAR=$("#issueYear").val();
    generalFieldsJson.REAL_RATE=$("#realRate").val();
    generalFieldsJson.INT_CALC_TYPE=$("#intCalcType").val();
    generalFieldsJson.FLOAT_RATE=$("#floatRate").val();
    generalFieldsJson.EVENT_TYPE=$("#eventType").val();
    generalFieldsJson.ACTUAL_RATE=$("#actualRate").val();
    generalFieldsJson.INT_TYPE=$("#intType").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_mbStageIntAdd,"json");
}

function callback_mbStageIntAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function mbStageIntAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}