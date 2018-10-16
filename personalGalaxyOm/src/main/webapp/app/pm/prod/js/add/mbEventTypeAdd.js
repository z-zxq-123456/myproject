
$(document).ready(function() {
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=MB_EVENT_DEFAULT_TYPE&tableCol=EVENT_DEFAULT_TYPE",
		id: "eventType",
		async: false
	});
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=MB_EVENT_CLASS&tableCol=EVENT_CLASS",
		id: "eventClass",
		async: false
	});
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
		id: "company",
		async: false
	});
	$("#mbEventTypeAdd").Validform({
		tiptype:2,
		callback:function(form){
			mbEventTypeAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function mbEventTypeAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="MB_EVENT_TYPE";
		keyFieldsJson.EVENT_TYPE=$("#eventType").val();
		generalFieldsJson.EVENT_CLASS=$("#eventClass").val();
		generalFieldsJson.EVENT_DESC=$("#eventDesc").val();
		generalFieldsJson.IS_STANDARD=$("#isStandard").val();
		generalFieldsJson.PROCESS_METHOD=$("#processMethod").val();
		generalFieldsJson.STATUS=$("#status").val();
		generalFieldsJson.COMPANY=$("#company").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_mbEventTypeAdd,"json");
}

function callback_mbEventTypeAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function mbEventTypeAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}


