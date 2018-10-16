
$(document).ready(function() {

	$("#glCcyRuleAdd").Validform({
		tiptype:2,
		callback:function(form){
			glCcyRuleAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function glCcyRuleAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="GL_CCY_RULE";
		keyFieldsJson.BUY_CCY=$("#buyCcy").val();
		keyFieldsJson.SELL_CCY=$("#sellCcy").val();
		keyFieldsJson.EVENT_TYPE=$("#eventType").val();
		generalFieldsJson.ACCOUNTING_NO=$("#accountingNo").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_glCcyRuleAdd,"json");
}

function callback_glCcyRuleAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function glCcyRuleAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}


