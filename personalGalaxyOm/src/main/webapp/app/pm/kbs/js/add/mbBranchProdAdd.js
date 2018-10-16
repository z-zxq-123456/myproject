$(document).ready(function() {
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_BRANCH&tableCol=BRANCH,BRANCH_NAME",
		id: "branch",
		async: false
	});

	$("#mbBranchProdAdd").Validform({
		tiptype:2,
		callback:function(){
			mbBranchProdAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function mbBranchProdAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="MB_BRANCH_PROD";
    keyFieldsJson.BRANCH=$("#branch").val();
    keyFieldsJson.PROD_TYPE=$("#prodType").val();
    generalFieldsJson.PROD_DESC=$("#prodDesc").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_mbBranchProdAdd,"json");
}

function callback_mbBranchProdAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function mbBranchProdAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}