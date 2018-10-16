$(document).ready(function() {
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=CMC_CARD_BIN_INFO&tableCol=BIN_ORDER",
		id: "binOrder",
		async: false
	});

	$("#cmcCardBinInfoAdd").Validform({
		tiptype:2,
		callback:function(){
			cmcCardBinInfoAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function cmcCardBinInfoAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="CMC_CARD_BIN_INFO";
    keyFieldsJson.BIN_ORDER=$("#binOrder").val();
    generalFieldsJson.BIN=$("#bin").val();
    generalFieldsJson.BIN_LENGTH=$("#binLength").val();
    generalFieldsJson.DELAY_FLAG=$("#delayFlag").val();
    generalFieldsJson.LAST_TIME=$("#lastTime").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_cmcCardBinInfoAdd,"json");
}

function callback_cmcCardBinInfoAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function cmcCardBinInfoAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}