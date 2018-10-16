
$(document).ready(function() {
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
		id: "company",
		async: false
	});

	$("#fmBankHierarchyAdd").Validform({
		tiptype:2,
		callback:function(form){
			fmBankHierarchyAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function fmBankHierarchyAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="FM_BANK_HIERARCHY";
		keyFieldsJson.HIERARCHY_CODE=$("#hierarchyCode").val();
		generalFieldsJson.HIERARCHY_NAME=$("#hierarchyName").val();
		generalFieldsJson.HIERARCHY_LEVEL=$("#hierarchyLevel").val();
		generalFieldsJson.COMPANY=$("#company").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_fmBankHierarchyAdd,"json");
}

function callback_fmBankHierarchyAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function fmBankHierarchyAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}


