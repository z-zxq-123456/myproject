$(document).ready(function() {
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=CIF_CLIENT_TYPE&tableCol=CLIENT_TYPE,CLIENT_TYPE_DESC",
		id: "clientTypeMe",
		async: false
	});
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=MB_PROD_TYPE&tableCol=PROD_TYPE,PROD_DESC",
		id: "prodTypeMut",
		async: false
	});
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=MB_PROD_TYPE&tableCol=PROD_TYPE,PROD_DESC",
		id: "prodTypeExclusive",
		async: false
	});

	$("#prodMutualExclusiveAdd").Validform({
		tiptype:2,
		callback:function(){
			prodMutualExclusiveAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function prodMutualExclusiveAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="PROD_MUTUAL_EXCLUSIVE";
    keyFieldsJson.CLIENT_TYPE_ME=$("#clientTypeMe").val();
    generalFieldsJson.PROD_TYPE_MUT=$("#prodTypeMut").val();
    generalFieldsJson.PROD_TYPE_EXCLUSIVE=$("#prodTypeExclusive").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_prodMutualExclusiveAdd,"json");
}

function callback_prodMutualExclusiveAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function prodMutualExclusiveAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}