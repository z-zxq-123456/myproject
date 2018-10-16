$(document).ready(function() {
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=TB_VOUCHER_DEF&tableCol=DOC_TYPE,DOC_TYPE_DESC",
		id: "docType",
		async: false
	});

	$("#tbVoucherUnitAdd").Validform({
		tiptype:2,
		callback:function(){
			tbVoucherUnitAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function tbVoucherUnitAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="TB_VOUCHER_UNIT";
    keyFieldsJson.DOC_TYPE=$("#docType").val();
    keyFieldsJson.UNIT_CODE=$("#unitCode").val();
    generalFieldsJson.UNIT_BASE=$("#unitBase").val();
    generalFieldsJson.UNIT_DESC=$("#unitDesc").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_tbVoucherUnitAdd,"json");
}

function callback_tbVoucherUnitAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function tbVoucherUnitAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}