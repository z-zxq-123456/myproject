
$(document).ready(function() {

	$("#mbEventDefaultTypeAdd").Validform({
		tiptype:2,
		callback:function(form){
			mbEventDefaultTypeAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function mbEventDefaultTypeAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="MB_EVENT_DEFAULT_TYPE";
		keyFieldsJson.EVENT_DEFAULT_TYPE=$("#eventDefaultType").val();
		generalFieldsJson.EVENT_DEFAULT_DESC=$("#eventDefaultDesc").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_mbEventDefaultTypeAdd,"json");
}

function callback_mbEventDefaultTypeAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function mbEventDefaultTypeAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}


