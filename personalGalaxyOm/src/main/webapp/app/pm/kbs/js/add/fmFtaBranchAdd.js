$(document).ready(function() {

	$("#fmFtaBranchAdd").Validform({
		tiptype:2,
		callback:function(){
			fmFtaBranchAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function fmFtaBranchAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="FM_FTA_BRANCH";
    keyFieldsJson.FTA_CODE=$("#ftaCode").val();
    generalFieldsJson.FTA_DESC=$("#ftaDesc").val();
    generalFieldsJson.FTA_NATURE=$("#ftaNature").val();
    generalFieldsJson.FTA_RATE_TYPE=$("#ftaRateType").val();
    generalFieldsJson.FTA_TYPE=$("#ftaType").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_fmFtaBranchAdd,"json");
}

function callback_fmFtaBranchAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function fmFtaBranchAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}