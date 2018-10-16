
$(document).ready(function() {

	$("#glTranMappingAdd").Validform({
		tiptype:2,
		callback:function(form){
			glTranMappingAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function glTranMappingAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="GL_TRAN_MAPPING";
		keyFieldsJson.INDEX_NO=$("#indexNo").val();
		generalFieldsJson.KEY_NAME=$("#keyName").val();
		generalFieldsJson.OBJECT_NAME=$("#objectName").val();
		generalFieldsJson.IS_AMOUNT=$("#isAmount").val();
		generalFieldsJson.KEY_DESC=$("#keyDesc").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_glTranMappingAdd,"json");
}

function callback_glTranMappingAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function glTranMappingAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}


