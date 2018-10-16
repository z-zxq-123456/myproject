
$(document).ready(function() {
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=GL_EVENT_MAPPING&tableCol=EVENT_TYPE,EVENT_TYPE_DESC",
		id: "tranEventType",
		async: false
	});
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=CIF_CLIENT_TYPE&tableCol=CLIENT_TYPE,CLIENT_TYPE_DESC",
		id: "clientType",
		async: false
	});
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_CURRENCY&tableCol=CCY,CCY_DESC",
		id: "ccy",
		async: false
	});
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=MB_TRAN_DEF&tableCol=TRAN_TYPE,TRAN_TYPE_DESC",
		id: "tranType",
		async: false
	});
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=IRL_PROD_TYPE&tableCol=PROD_TYPE,PROD_TYPE_DESC",
		id: "prodType",
		async: false
	});

	$("#glProdRuleAdd").Validform({
		tiptype:2,
		callback:function(form){
			glProdRuleAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function glProdRuleAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="GL_PROD_RULE";
	paraJson.systemId="GLR";
		keyFieldsJson.TRAN_EVENT_TYPE=$("#tranEventType").val();
		keyFieldsJson.CLIENT_TYPE=$("#clientType").val();
		keyFieldsJson.ACCOUNTING_STATUS=$("#accountingStatus").val();
		keyFieldsJson.CCY=$("#ccy").val();
		keyFieldsJson.SYS_NAME=$("#sysName").val();
		keyFieldsJson.TRAN_TYPE=$("#tranType").val();
		keyFieldsJson.PROD_TYPE=$("#prodType").val();
		keyFieldsJson.SOURCE_TYPE=$("#sourceType").val();
		generalFieldsJson.ACCOUNTING_NO=$("#accountingNo").val();
		generalFieldsJson.ACCOUNTING_DESC=$("#accountingDesc").val();
		generalFieldsJson.CUSTOM_RULE=$("#customRule").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_glProdRuleAdd,"json");
}

function callback_glProdRuleAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function glProdRuleAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}