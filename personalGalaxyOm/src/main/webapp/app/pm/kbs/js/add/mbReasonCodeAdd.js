
$(document).ready(function() {

	$("#mbReasonCodeAdd").Validform({
		tiptype:2,
		callback:function(form){
			mbReasonCodeAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function mbReasonCodeAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="MB_REASON_CODE";
		keyFieldsJson.REASON_CODE=$("#reasonCode").val();
		generalFieldsJson.REASON_CODE_DESC=$("#reasonCodeDesc").val();
		generalFieldsJson.SOURCE_MODE=$("#sourceMode").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_mbReasonCodeAdd,"json");
}

function callback_mbReasonCodeAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function mbReasonCodeAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}


