
$(document).ready(function() {
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=MB_EVENT_DEFAULT_TYPE&tableCol=EVENT_DEFAULT_TYPE",
		id: "eventType",
		async: false
	});

	$("#mbEventPartAdd").Validform({
		tiptype:2,
		callback:function(form){
			mbEventPartAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function mbEventPartAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="MB_EVENT_PART";
		keyFieldsJson.ASSEMBLE_ID=$("#assembleId").val();
		keyFieldsJson.ATTR_KEY=$("#attrKey").val();
		keyFieldsJson.EVENT_TYPE=$("#eventType").val();
		generalFieldsJson.ATTR_VALUE=$("#attrValue").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_mbEventPartAdd,"json");
}

function callback_mbEventPartAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function mbEventPartAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}


