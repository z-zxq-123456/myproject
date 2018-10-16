
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

	$("#fmUserAdd").Validform({
		tiptype:2,
		callback:function(form){
			fmUserAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function fmUserAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="FM_USER";
		keyFieldsJson.USER_ID=$("#userId").val();
		generalFieldsJson.EOD_SOD_ENABLED=$("#eodSodEnabled").val();
		generalFieldsJson.ACCOUNT_STATUS=$("#accountStatus").val();
		generalFieldsJson.USER_TYPE=$("#userType").val();
		generalFieldsJson.CHECK_DATE=$("#checkDate").val();
		generalFieldsJson.COMPANY=$("#company").val();
		generalFieldsJson.DEPARTMENT=$("#department").val();
		generalFieldsJson.DOCUMENT_ID=$("#documentId").val();
		generalFieldsJson.DOCUMENT_TYPE=$("#documentType").val();
		generalFieldsJson.INTER_BRANCH_IND=$("#interBranchInd").val();
		generalFieldsJson.MAKER=$("#maker").val();
		generalFieldsJson.MAKE_DATE=$("#makeDate").val();
		generalFieldsJson.PROFIT_CENTRE=$("#profitCentre").val();
		generalFieldsJson.TBOOK=$("#tbook").val();
		generalFieldsJson.USER_DESC=$("#userDesc").val();
		generalFieldsJson.USER_LANG=$("#userLang").val();
		generalFieldsJson.USER_LEVEL=$("#userLevel").val();
		generalFieldsJson.USER_NAME=$("#userName").val();
		generalFieldsJson.CHECKER=$("#checker").val();
		generalFieldsJson.BRANCH=$("#branch").val();
		generalFieldsJson.AUTH_LEVEL=$("#authLevel").val();
		generalFieldsJson.APPLICATION_USER=$("#applicationUser").val();
		generalFieldsJson.ACCT_EXEC=$("#acctExec").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_fmUserAdd,"json");
}

function callback_fmUserAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function fmUserAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}


