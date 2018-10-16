$(document).ready(function() {

	$("#cmcProductLimitAdd").Validform({
		tiptype:2,
		callback:function(){
			cmcProductLimitAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function cmcProductLimitAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="CMC_PRODUCT_LIMIT";
    keyFieldsJson.CARD_PRODUCT_CODE=$("#cardProductCode").val();
    keyFieldsJson.CHANNEL_TYPE=$("#channelType").val();
    generalFieldsJson.CCY=$("#ccy").val();
    keyFieldsJson.PERIOD=$("#period").val();
    generalFieldsJson.CON_LIMIT_AMT=$("#conLimitAmt").val();
    generalFieldsJson.TRAN_IN_LIMIT_AMT=$("#tranInLimitAmt").val();
    generalFieldsJson.TRAN_OUT_LIMIT_AMT=$("#tranOutLimitAmt").val();
    generalFieldsJson.CON_LIMIT_TIME=$("#conLimitTime").val();
    generalFieldsJson.TRAN_IN_LIMIT_TIME=$("#tranInLimitTime").val();
    generalFieldsJson.TRAN_OUT_LIMIT_TIME=$("#tranOutLimitTime").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_cmcProductLimitAdd,"json");
}

function callback_cmcProductLimitAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function cmcProductLimitAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}