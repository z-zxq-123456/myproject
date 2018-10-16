
$(document).ready(function() {
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=CIF_CLIENT_STATUS&tableCol=CLIENT_STATUS,CLIENT_STATUS_DESC",
		id: "clientStatus",
		async: false
	});
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
		id: "company",
		async: false
	});

	$("#cifClientStatusAdd").Validform({
		tiptype:2,
		callback:function(form){
			cifClientStatusAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function cifClientStatusAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="CIF_CLIENT_STATUS";
		keyFieldsJson.CLIENT_STATUS=$("#clientStatus").val();
		generalFieldsJson.CLIENT_STATUS_DESC=$("#clientStatusDesc").val();
		generalFieldsJson.BAD_CLIENT_IND=$("#badClientInd").val();
		generalFieldsJson.COMPANY=$("#company").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_cifClientStatusAdd,"json");
}

function callback_cifClientStatusAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function cifClientStatusAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}


