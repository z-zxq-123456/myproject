$(document).ready(function() {
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_BRANCH&tableCol=BRANCH,BRANCH_NAME",
		id: "branch",
		async: false
	});
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_BRANCH&tableCol=BRANCH,BRANCH_NAME",
		id: "settleBranch",
		async: false
	});

	$("#mbSettleBranchAdd").Validform({
		tiptype:2,
		callback:function(){
			mbSettleBranchAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function mbSettleBranchAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="MB_SETTLE_BRANCH";
    keyFieldsJson.BRANCH=$("#branch").val();
    generalFieldsJson.SETTLE_LEVEL=$("#settleLevel").val();
    generalFieldsJson.SETTLE_BRANCH=$("#settleBranch").val();
    generalFieldsJson.SETTLE_BASE_ACCT=$("#settleBaseAcct").val();
    generalFieldsJson.SETTLE_BASE_ACCT_UP=$("#settleBaseAcctUp").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_mbSettleBranchAdd,"json");
}

function callback_mbSettleBranchAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function mbSettleBranchAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}