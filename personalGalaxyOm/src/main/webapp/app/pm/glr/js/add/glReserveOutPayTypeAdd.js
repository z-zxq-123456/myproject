$(document).ready(function() {

	$("#glReserveOutPayTypeAdd").Validform({
		tiptype:2,
		callback:function(){
			glReserveOutPayTypeAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function glReserveOutPayTypeAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="GL_RESERVE_OUT_PAY_TYPE";
    keyFieldsJson.PAY_TYPE=$("#payType").val();
    generalFieldsJson.ADJUST_ACCT_CLIENT_NO=$("#adjustAcctClientNo").val();
    generalFieldsJson.ADJUST_ACCT_TYPE=$("#adjustAcctType").val();
    generalFieldsJson.PAY_TYPE_DESC=$("#payTypeDesc").val();
    generalFieldsJson.PAY_ACCT_CLIENT_NO=$("#payAcctClientNo").val();
    generalFieldsJson.PAY_ACCT_TYPE=$("#payAcctType").val();
    generalFieldsJson.ADJUST_ACCT_CLIENT_NAME=$("#adjustAcctClientName").val();
    generalFieldsJson.PAY_ACCT_CLIENT_NAME=$("#payAcctClientName").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_glReserveOutPayTypeAdd,"json");
}

function callback_glReserveOutPayTypeAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function glReserveOutPayTypeAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}