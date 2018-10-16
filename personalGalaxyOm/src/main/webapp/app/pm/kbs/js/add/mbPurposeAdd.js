
$(document).ready(function() {

	$("#mbPurposeAdd").Validform({
		tiptype:2,
		callback:function(form){
			mbPurposeAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function mbPurposeAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="MB_PURPOSE";
		keyFieldsJson.PURPOSE=$("#purpose").val();
		generalFieldsJson.PURPOSE_DESC=$("#purposeDesc").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_mbPurposeAdd,"json");
}

function callback_mbPurposeAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function mbPurposeAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}


