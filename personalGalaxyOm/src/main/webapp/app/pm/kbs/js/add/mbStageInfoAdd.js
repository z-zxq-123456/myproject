$(document).ready(function() {
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_CURRENCY&tableCol=CCY,CCY_DESC",
		id: "ccy",
		async: false
	});

	$("#mbStageInfoAdd").Validform({
		tiptype:2,
		callback:function(){
			mbStageInfoAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function mbStageInfoAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="MB_STAGE_INFO";
    keyFieldsJson.STAGE_CODE=$("#stageCode").val();
    generalFieldsJson.CCY=$("#ccy").val();
    generalFieldsJson.ISSUE_AMT=$("#issueAmt").val();
    generalFieldsJson.ISSUE_YEAR=$("#issueYear").val();
    generalFieldsJson.PROD_TYPE=$("#prodType").val();
    generalFieldsJson.LAST_CHANGE_DATE=$("#lastChangeDate").val();
    generalFieldsJson.PREV_USED_AMT=$("#prevUsedAmt").val();
    generalFieldsJson.USED_AMT=$("#usedAmt").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_mbStageInfoAdd,"json");
}

function callback_mbStageInfoAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function mbStageInfoAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}