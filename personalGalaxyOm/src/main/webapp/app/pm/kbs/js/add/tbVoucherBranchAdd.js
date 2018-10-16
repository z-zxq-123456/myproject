
$(document).ready(function() {
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_BRANCH&tableCol=BRANCH,BRANCH_NAME",
		id: "branch",
		async: false
	});
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=TB_VOUCHER_DEF&tableCol=DOC_TYPE,DOC_TYPE_DESC",
		id: "docType",
		async: false
	});

	$("#tbVoucherBranchAdd").Validform({
		tiptype:2,
		callback:function(form){
			tbVoucherBranchAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function tbVoucherBranchAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="TB_VOUCHER_BRANCH";
		keyFieldsJson.BRANCH=$("#branch").val();
		keyFieldsJson.DOC_TYPE=$("#docType").val();
		generalFieldsJson.COMPANY=$("#company").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_tbVoucherBranchAdd,"json");
}

function callback_tbVoucherBranchAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function tbVoucherBranchAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}


