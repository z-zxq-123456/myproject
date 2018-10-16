
$(document).ready(function() {
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=GL_EVENT_TYPE&tableCol=EVENT_TYPE,EVENT_TYPE_DESC",
		id: "eventType",
		async: false
	});

	$("#glEventMappingAdd").Validform({
		tiptype:2,
		callback:function(form){
			glEventMappingAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function glEventMappingAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="GL_EVENT_MAPPING";
		keyFieldsJson.MAPPING_TYPE=$("#mappingType").val();
		generalFieldsJson.EVENT_TYPE=$("#eventType").val();
		generalFieldsJson.MAPPING_NAME=$("#mappingName").val();
		generalFieldsJson.EVENT_TYPE_DESC=$("#eventTypeDesc").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_glEventMappingAdd,"json");
}

function callback_glEventMappingAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function glEventMappingAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}


