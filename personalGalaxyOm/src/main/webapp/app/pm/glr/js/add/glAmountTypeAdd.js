
$(document).ready(function() {

	$("#glAmountTypeAdd").Validform({
		tiptype:2,
		callback:function(form){
			glAmountTypeAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function glAmountTypeAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="GL_AMOUNT_TYPE";
		keyFieldsJson.AMOUNT_TYPE=$("#amountType").val();
		generalFieldsJson.AMOUNT_TYPE_DESC=$("#amountTypeDesc").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_glAmountTypeAdd,"json");
}

function callback_glAmountTypeAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function glAmountTypeAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}


