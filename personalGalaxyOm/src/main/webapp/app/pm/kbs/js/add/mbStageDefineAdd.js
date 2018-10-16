$(document).ready(function() {
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_CURRENCY&tableCol=CCY,CCY_DESC",
		id: "ccy",
		async: false
	});

	$("#mbStageDefineAdd").Validform({
		tiptype:2,
		callback:function(){
			mbStageDefineAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function mbStageDefineAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="MB_STAGE_DEFINE";
    keyFieldsJson.STAGE_CODE=$("#stageCode").val();
    generalFieldsJson.ISSUE_YEAR=$("#issueYear").val();
    generalFieldsJson.ISSUE_START_DATE=$("#issueStartDate").val();
    generalFieldsJson.PROD_TYPE=$("#prodType").val();
    generalFieldsJson.ISSUE_END_DATE=$("#issueEndDate").val();
    generalFieldsJson.ISSUE_AMT=$("#issueAmt").val();
    generalFieldsJson.CCY=$("#ccy").val();
    generalFieldsJson.STAGE_CODE_DESC=$("#stageCodeDesc").val();
    generalFieldsJson.TERM=$("#term").val();
    generalFieldsJson.TERM_TYPE=$("#termType").val();
    generalFieldsJson.TRANSFER_FLAG=$("#transferFlag").val();
    generalFieldsJson.TRAN_BRANCH=$("#tranBranch").val();
    generalFieldsJson.TRAN_DATE=$("#tranDate").val();
    generalFieldsJson.SALE_TYPE=$("#saleType").val();
    generalFieldsJson.RESET_INT_FREQ=$("#resetIntFreq").val();
    generalFieldsJson.PRE_WITHDRAW_FLAG=$("#preWithdrawFlag").val();
    generalFieldsJson.PAY_INT_TYPE=$("#payIntType").val();
    generalFieldsJson.PART_WITHDRAW_NUM=$("#partWithdrawNum").val();
    generalFieldsJson.INT_CALC_TYPE=$("#intCalcType").val();
    generalFieldsJson.USER_ID=$("#userId").val();
    generalFieldsJson.GET_INT_FREQ=$("#getIntFreq").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_mbStageDefineAdd,"json");
}

function callback_mbStageDefineAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function mbStageDefineAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}