
$(document).ready(function() {

	$("#glEventTypeAdd").Validform({
		tiptype:2,
		callback:function(form){
			glEventTypeAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function glEventTypeAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="GL_EVENT_TYPE";
		keyFieldsJson.EVENT_TYPE=$("#eventType").val();
		generalFieldsJson.EVENT_TYPE_DESC=$("#eventTypeDesc").val();
		generalFieldsJson.CR_DR=$("#crDr").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_glEventTypeAdd,"json");
}

function callback_glEventTypeAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function glEventTypeAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}


