
$(document).ready(function() {
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
		id: "company",
		async: false
	});

	$("#fmSettleMethodAdd").Validform({
		tiptype:2,
		callback:function(form){
			fmSettleMethodAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function fmSettleMethodAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="FM_SETTLE_METHOD";
		keyFieldsJson.SETTLE_METHOD=$("#settleMethod").val();
		keyFieldsJson.PAY_REC=$("#payRec").val();
		generalFieldsJson.DEST_CLIENT_TYPE=$("#destClientType").val();
		generalFieldsJson.MEDIA=$("#media").val();
		generalFieldsJson.SETTLE_METHOD_DESC=$("#settleMethodDesc").val();
		generalFieldsJson.SETTLE_ACCT_TYPE=$("#settleAcctType").val();
		generalFieldsJson.ROUTE=$("#route").val();
		generalFieldsJson.RELEASE_SECURITY=$("#releaseSecurity").val();
		generalFieldsJson.VERIFY_SECURITY=$("#verifySecurity").val();
		generalFieldsJson.FORMAT=$("#format").val();
		generalFieldsJson.CONTACT_TYPE=$("#contactType").val();
		generalFieldsJson.PRINT_MODE=$("#printMode").val();
		generalFieldsJson.COMPANY=$("#company").val();
		generalFieldsJson.IS_CASH=$("#isCash").val();
		generalFieldsJson.DEST_ID=$("#destId").val();
		generalFieldsJson.DP_SETTLE=$("#dpSettle").val();
		generalFieldsJson.DOC_TYPE=$("#docType").val();
		generalFieldsJson.DEST_TYPE=$("#destType").val();
		generalFieldsJson.SENDERS_CONTACT_TYPE=$("#sendersContactType").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_fmSettleMethodAdd,"json");
}

function callback_fmSettleMethodAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function fmSettleMethodAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}


