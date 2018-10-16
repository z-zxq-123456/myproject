$(document).ready(function() {

	$("#fmBankAdd").Validform({
		tiptype:2,
		callback:function(){
			fmBankAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function fmBankAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="FM_BANK";
    keyFieldsJson.BANK_CODE=$("#bankCode").val();
    generalFieldsJson.BANK_NAME=$("#bankName").val();
    generalFieldsJson.BANK_TYPE=$("#bankType").val();
    generalFieldsJson.STATUS=$("#status").val();
    generalFieldsJson.COMPANY=$("#company").val();
    generalFieldsJson.BANK_ID=$("#bankId").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_fmBankAdd,"json");
}

function callback_fmBankAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function fmBankAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}