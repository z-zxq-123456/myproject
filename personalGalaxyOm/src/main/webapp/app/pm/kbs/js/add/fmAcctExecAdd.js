
$(document).ready(function() {
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
		id: "company",
		async: false
	});
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_BRANCH&tableCol=BRANCH,BRANCH_NAME",
		id: "branch",
		async: false
	});

	$("#fmAcctExecAdd").Validform({
		tiptype:2,
		callback:function(form){
			fmAcctExecAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function fmAcctExecAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="FM_ACCT_EXEC";
		keyFieldsJson.ACCT_EXEC=$("#acctExec").val();
		generalFieldsJson.ACCT_EXEC_NAME=$("#acctExecName").val();
		generalFieldsJson.COLLAT_MGR_IND=$("#collatMgrInd").val();
		generalFieldsJson.PROFIT_SEGMENT=$("#profitSegment").val();
		generalFieldsJson.COMPANY=$("#company").val();
		generalFieldsJson.MANAGER=$("#manager").val();
		generalFieldsJson.BRANCH=$("#branch").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_fmAcctExecAdd,"json");
}

function callback_fmAcctExecAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function fmAcctExecAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}


