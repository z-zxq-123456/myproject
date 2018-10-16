$(document).ready(function() {

	$("#cmcCardNoRoleExAdd").Validform({
		tiptype:2,
		callback:function(){
			cmcCardNoRoleExAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function cmcCardNoRoleExAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="CMC_CARD_NO_ROLE_EX";
    keyFieldsJson.PARAM_NAME=$("#paramName").val();
    generalFieldsJson.PARAM_VALUE=$("#paramValue").val();
    generalFieldsJson.EXPAND1=$("#expand1").val();
    generalFieldsJson.EXPAND2=$("#expand2").val();
    generalFieldsJson.EXPAND3=$("#expand3").val();
    generalFieldsJson.EXPAND4=$("#expand4").val();
    generalFieldsJson.EXPAND5=$("#expand5").val();
    generalFieldsJson.EXPAND6=$("#expand6").val();
    generalFieldsJson.EXPAND7=$("#expand7").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_cmcCardNoRoleExAdd,"json");
}

function callback_cmcCardNoRoleExAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function cmcCardNoRoleExAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}