$(document).ready(function() {
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=MB_EVENT_TYPE&tableCol=EVENT_TYPE,EVENT_DESC",
		id: "eventType",
		async: false
	});
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_CURRENCY&tableCol=CCY,CCY_DESC",
		id: "amtTypeList",
		async: false
	});

	$("#mbSettleAcctMappingAdd").Validform({
		tiptype:2,
		callback:function(){
			mbSettleAcctMappingAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function mbSettleAcctMappingAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="MB_SETTLE_ACCT_MAPPING";
    keyFieldsJson.EVENT_TYPE=$("#eventType").val();
    keyFieldsJson.SETTLE_ACCT_CLASS=$("#settleAcctClass").val();
    generalFieldsJson.PAY_REC_IND=$("#payRecInd").val();
	if($("#amtTypeList").val()!==null) {
		generalFieldsJson.AMT_TYPE_LIST = $("#amtTypeList").val().toString();
	}
    generalFieldsJson.COMPANY=$("#company").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_mbSettleAcctMappingAdd,"json");
}

function callback_mbSettleAcctMappingAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function mbSettleAcctMappingAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}