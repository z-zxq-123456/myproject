
$(document).ready(function() {
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=MB_EVENT_DEFAULT_TYPE&tableCol=EVENT_DEFAULT_TYPE",
		id: "eventType",
		async: false
	});

	$("#mbEventPartRelationAdd").Validform({
		tiptype:2,
		callback:function(form){
			mbEventPartRelationAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function mbEventPartRelationAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="MB_EVENT_PART_RELATION";
		keyFieldsJson.ASSEMBLE_ID=$("#assembleId").val();
		keyFieldsJson.ASSEMBLE_TYPE=$("#assembleType").val();
		keyFieldsJson.EVENT_TYPE=$("#eventType").val();
		generalFieldsJson.PART_DESC=$("#partDesc").val();
		generalFieldsJson.STATUS=$("#status").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_mbEventPartRelationAdd,"json");
}

function callback_mbEventPartRelationAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function mbEventPartRelationAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}


