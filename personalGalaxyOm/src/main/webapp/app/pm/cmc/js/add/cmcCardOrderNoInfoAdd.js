$(document).ready(function() {
	
	getPkList({
        url: contextPath + "/cmcRuleNo/getAllForSelect?tableCol=RULE_NO",
        id: "productRuleNo",
        async: false
    });

	$("#cmcCardOrderNoInfoAdd").Validform({
		tiptype:2,
		callback:function(){
			cmcCardOrderNoInfoAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function cmcCardOrderNoInfoAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="CMC_CARD_ORDER_NO_INFO";
    keyFieldsJson.PRODUCT_RULE_NO=$("#productRuleNo").val();
    generalFieldsJson.CARD_NO_BEGIN=$("#cardNoBegin").val();
    generalFieldsJson.CARD_NO_END=$("#cardNoEnd").val();
    generalFieldsJson.CARD_NO_NOW=$("#cardNoNow").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_cmcCardOrderNoInfoAdd,"json");
}

function callback_cmcCardOrderNoInfoAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function cmcCardOrderNoInfoAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}