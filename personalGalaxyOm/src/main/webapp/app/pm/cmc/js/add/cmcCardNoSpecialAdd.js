$(document).ready(function() {

	$("#cmcCardNoSpecialAdd").Validform({
		tiptype:2,
		callback:function(){
			cmcCardNoSpecialAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function cmcCardNoSpecialAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="CMC_CARD_NO_SPECIAL";
    keyFieldsJson.ORDER_NO=$("#orderNo").val();
    keyFieldsJson.PRODUCT_RULE_NO=$("#productRuleNo").val();
    generalFieldsJson.STATUS=$("#status").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_cmcCardNoSpecialAdd,"json");
}

function callback_cmcCardNoSpecialAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function cmcCardNoSpecialAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}