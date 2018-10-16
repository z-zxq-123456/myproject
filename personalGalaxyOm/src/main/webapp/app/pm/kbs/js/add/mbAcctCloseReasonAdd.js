$(document).ready(function() {

	$("#mbAcctCloseReasonAdd").Validform({
		tiptype:2,
		callback:function(){
			mbAcctCloseReasonAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function mbAcctCloseReasonAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="MB_ACCT_CLOSE_REASON";
    keyFieldsJson.REASON_CODE=$("#reasonCode").val();
    generalFieldsJson.REASON_CODE_DESC=$("#reasonCodeDesc").val();
    generalFieldsJson.COMPANY=$("#company").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_mbAcctCloseReasonAdd,"json");
}

function callback_mbAcctCloseReasonAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function mbAcctCloseReasonAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}