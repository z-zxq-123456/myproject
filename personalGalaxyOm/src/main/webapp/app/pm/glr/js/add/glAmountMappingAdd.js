
$(document).ready(function() {
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=GL_AMOUNT_TYPE&tableCol=AMOUNT_TYPE,AMOUNT_TYPE_DESC",
		id: "mappingType",
		async: false
	});

	$("#glAmountMappingAdd").Validform({
		tiptype:2,
		callback:function(form){
			glAmountMappingAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function glAmountMappingAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="GL_AMOUNT_MAPPING";
		keyFieldsJson.MAPPING_TYPE=$("#mappingType").val();
		generalFieldsJson.AMOUNT_TYPE=$("#amountType").val();
		generalFieldsJson.MAPPING_NAME=$("#mappingName").val();
		generalFieldsJson.AMOUNT_TYPE_DESC=$("#amountTypeDesc").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_glAmountMappingAdd,"json");
}

function callback_glAmountMappingAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function glAmountMappingAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}


