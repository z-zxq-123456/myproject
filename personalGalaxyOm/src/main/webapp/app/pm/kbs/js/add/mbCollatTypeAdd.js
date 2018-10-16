$(document).ready(function() {

	$("#mbCollatTypeAdd").Validform({
		tiptype:2,
		callback:function(){
			mbCollatTypeAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function mbCollatTypeAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="MB_COLLAT_TYPE";
    keyFieldsJson.COLLAT_TYPE=$("#collatType").val();
    generalFieldsJson.COLLAT_TYPE_DESC=$("#collatTypeDesc").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_mbCollatTypeAdd,"json");
}

function callback_mbCollatTypeAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function mbCollatTypeAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}