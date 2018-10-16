
$(document).ready(function() {
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_BRANCH&tableCol=BRANCH,BRANCH_NAME",
		id: "branch",
		async: false
	});
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=AC_SUBJECT&tableCol=SUBJECT_CODE,SUBJECT_DESC",
		id: "settleSubjectUp",
		async: false
	});
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=AC_SUBJECT&tableCol=SUBJECT_CODE,SUBJECT_DESC",
		id: "settleSubject",
		async: false
	});
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_BRANCH&tableCol=BRANCH,BRANCH_NAME",
		id: "settleBranch",
		async: false
	});

	$("#acBranchAdd").Validform({
		tiptype:2,
		callback:function(form){
			acBranchAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function acBranchAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="AC_BRANCH";
		keyFieldsJson.BRANCH=$("#branch").val();
		generalFieldsJson.SETTLE_LEVEL=$("#settleLevel").val();
		generalFieldsJson.SETTLE_SUBJECT_UP=$("#settleSubjectUp").val();
		generalFieldsJson.SETTLE_SUBJECT=$("#settleSubject").val();
		generalFieldsJson.SETTLE_ACCT_SEQ_UP=$("#settleAcctSeqUp").val();
		generalFieldsJson.SETTLE_ACCT_SEQ=$("#settleAcctSeq").val();
		generalFieldsJson.SETTLE_BRANCH=$("#settleBranch").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_acBranchAdd,"json");
}

function callback_acBranchAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function acBranchAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}


