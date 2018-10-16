
$(document).ready(function() {
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
		id: "company",
		async: false
	});

	$("#fmExternalBranchAdd").Validform({
		tiptype:2,
		callback:function(form){
			fmExternalBranchAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function fmExternalBranchAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="FM_EXTERNAL_BRANCH";
		keyFieldsJson.BANK_CODE=$("#bankCode").val();
		keyFieldsJson.BRANCH_CODE=$("#branchCode").val();
		generalFieldsJson.COUNTRY=$("#country").val();
		generalFieldsJson.COMPANY=$("#company").val();
		generalFieldsJson.STATE=$("#state").val();
		generalFieldsJson.BRANCH_NAME=$("#branchName").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_fmExternalBranchAdd,"json");
}

function callback_fmExternalBranchAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function fmExternalBranchAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}


