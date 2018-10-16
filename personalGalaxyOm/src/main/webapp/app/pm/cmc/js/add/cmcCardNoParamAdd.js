$(document).ready(function() {
    getPkList({
        url: contextPath + "/cmcRuleNo/getAllForSelect?tableCol=RULE_NO",
        id: "productRuleNo",
        async: false
    });

	$("#cmcCardNoParamAdd").Validform({
		tiptype:2,
		callback:function(){
			cmcCardNoParamAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function cmcCardNoParamAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="CMC_CARD_NO_PARAM";
    keyFieldsJson.PRODUCT_RULE_NO=$("#productRuleNo").val();
    generalFieldsJson.CARD_NUM=$("#cardNum").val();
    generalFieldsJson.FLAG=$("#flag").val();
    generalFieldsJson.THRESHOLD_NUM=$("#thresholdNum").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_cmcCardNoParamAdd,"json");
}

function callback_cmcCardNoParamAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function cmcCardNoParamAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}