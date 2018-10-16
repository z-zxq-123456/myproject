$(document).ready(function() {
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=CIF_CLIENT_TYPE&tableCol=CLIENT_TYPE,CLIENT_TYPE_DESC",
		id: "clientType",
		async: false
	});

	$("#mbCommissionParameterAdd").Validform({
		tiptype:2,
		callback:function(){
			mbCommissionParameterAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function mbCommissionParameterAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="MB_COMMISSION_PARAMETER";
    keyFieldsJson.CLIENT_TYPE=$("#clientType").val();
    keyFieldsJson.PROGRAM_ID=$("#programId").val();
    generalFieldsJson.STATUS=$("#status").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_mbCommissionParameterAdd,"json");
}

function callback_mbCommissionParameterAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function mbCommissionParameterAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}