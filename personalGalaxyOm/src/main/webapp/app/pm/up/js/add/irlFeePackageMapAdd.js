$(document).ready(function() {
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=IRL_FEE_PACKAGE&tableCol=PACKAGE_ID,PACKAGE_DESC",
		id: "packageId",
		async: false
	});
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=IRL_FEE_TYPE&tableCol=FEE_TYPE,FEE_DESC",
		id: "feeType",
		async: false
	});

	$("#irlFeePackageMapAdd").Validform({
		tiptype:2,
		callback:function(){
			irlFeePackageMapAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function irlFeePackageMapAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="IRL_FEE_PACKAGE_MAP";
    keyFieldsJson.PACKAGE_ID=$("#packageId").val();
    keyFieldsJson.FEE_TYPE=$("#feeType").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_irlFeePackageMapAdd,"json");
}

function callback_irlFeePackageMapAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function irlFeePackageMapAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}