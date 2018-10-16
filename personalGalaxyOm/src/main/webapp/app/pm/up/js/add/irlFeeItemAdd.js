$(document).ready(function() {
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=IRL_FEE_ITEM&tableCol=FEE_ITEM,FEE_ITEM_DESC",
		id: "feeItem",
		async: false
	});

	$("#irlFeeItemAdd").Validform({
		tiptype:2,
		callback:function(){
			irlFeeItemAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function irlFeeItemAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="IRL_FEE_ITEM";
    keyFieldsJson.FEE_ITEM=$("#feeItem").val();
    generalFieldsJson.FEE_ITEM_DESC=$("#feeItemDesc").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_irlFeeItemAdd,"json");
}

function callback_irlFeeItemAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function irlFeeItemAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}