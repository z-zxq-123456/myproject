$(document).ready(function() {

	$("#mbSettleTranMappingAdd").Validform({
		tiptype:2,
		callback:function(){
			mbSettleTranMappingAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function mbSettleTranMappingAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="MB_SETTLE_TRAN_MAPPING";
    keyFieldsJson.ACCT_TYPE=$("#acctType").val();
    keyFieldsJson.PAY_REC_IND=$("#payRecInd").val();
    keyFieldsJson.SETTLE_GL_TYPE=$("#settleGlType").val();
    generalFieldsJson.TRAN_TYPE_LIST=$("#tranTypeList").val();
    generalFieldsJson.COMPANY=$("#company").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_mbSettleTranMappingAdd,"json");
}

function callback_mbSettleTranMappingAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function mbSettleTranMappingAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}