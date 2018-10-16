
$(document).ready(function() {
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_BRANCH&tableCol=BRANCH,BRANCH_NAME",
		id: "branch",
		async: false
	});
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
		id: "company",
		async: false
	});

	$("#fmBranchAdd").Validform({
		tiptype:2,
		callback:function(form){
			fmBranchAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function fmBranchAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="FM_BRANCH";
		keyFieldsJson.BRANCH=$("#branch").val();
		generalFieldsJson.COUNTRY=$("#country").val();
		generalFieldsJson.STATE=$("#state").val();
		generalFieldsJson.BRANCH_SHORT=$("#branchShort").val();
		generalFieldsJson.CCY_CTRL_BRANCH=$("#ccyCtrlBranch").val();
		generalFieldsJson.CHEQUE_ISSUING_BRANCH=$("#chequeIssuingBranch").val();
		generalFieldsJson.CITY=$("#city").val();
		generalFieldsJson.CNY_BUSINESS_UNIT=$("#cnyBusinessUnit").val();
		generalFieldsJson.COMPANY=$("#company").val();
		generalFieldsJson.DISTRICT=$("#district").val();
		generalFieldsJson.EOD_IND=$("#eodInd").val();
		generalFieldsJson.FX_ORGAN_CODE=$("#fxOrganCode").val();
		generalFieldsJson.HIERARCHY_CODE=$("#hierarchyCode").val();
		generalFieldsJson.HKD_BUSINESS_UNIT=$("#hkdBusinessUnit").val();
		generalFieldsJson.INTERNAL_CLIENT=$("#internalClient").val();
		generalFieldsJson.IP_ADDR=$("#ipAddr").val();
		generalFieldsJson.LOCAL_CCY=$("#localCcy").val();
		generalFieldsJson.PBOC_FUND_CHECK_FALG=$("#pbocFundCheckFalg").val();
		generalFieldsJson.POSTAL_CODE=$("#postalCode").val();
		generalFieldsJson.PROFIT_CENTRE=$("#profitCentre").val();
		generalFieldsJson.STATUS=$("#status").val();
		generalFieldsJson.SUB_BRANCH_CODE=$("#subBranchCode").val();
		generalFieldsJson.BRANCH_NAME=$("#branchName").val();
		generalFieldsJson.FTA_CODE=$("#ftaCode").val();
		generalFieldsJson.FTA_FLAG=$("#ftaFlag").val();
		generalFieldsJson.TRAN_BR_IND=$("#tranBrInd").val();
		generalFieldsJson.ATTACHED_TO=$("#attachedTo").val();
		generalFieldsJson.VOUCHER_USER_CONTRAL=$("#voucherUserCoutral").val();
		generalFieldsJson.BASE_CCY=$("#baseCcy").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_fmBranchAdd,"json");
}

function callback_fmBranchAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function fmBranchAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);
    }