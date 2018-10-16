
$(document).ready(function() {

	$("#mbExchangeTranTypeAdd").Validform({
		tiptype:2,
		callback:function(form){
			mbExchangeTranTypeAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function mbExchangeTranTypeAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="MB_EXCHANGE_TRAN_TYPE";
		keyFieldsJson.TRAN_TYPE=$("#tranType").val();
		generalFieldsJson.EX_TYPE=$("#exType").val();
		generalFieldsJson.OP_TYPE=$("#opType").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_mbExchangeTranTypeAdd,"json");
}

function callback_mbExchangeTranTypeAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function mbExchangeTranTypeAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}


