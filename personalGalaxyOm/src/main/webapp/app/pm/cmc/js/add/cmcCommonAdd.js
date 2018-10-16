$(document).ready(function() {

	$("#cmcCommonAdd").Validform({
		tiptype:2,
		callback:function(){
			cmcCommonAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function cmcCommonAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="CMC_COMMON";
    keyFieldsJson.PARAM_NAME=$("#paramName").val();
    generalFieldsJson.PARAM_DESC=$("#paramDesc").val();
    generalFieldsJson.PARAM_ORDER=$("#paramOrder").val();
    generalFieldsJson.PARAM_VALUE=$("#paramValue").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_cmcCommonAdd,"json");
}

function callback_cmcCommonAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function cmcCommonAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}