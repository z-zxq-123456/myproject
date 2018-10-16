
$(document).ready(function() {
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=AC_SUBJECT&tableCol=SUBJECT_CODE,SUBJECT_DESC",
		id: "intReceivable",
		async: false
	});
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_PERIOD_FREQ&tableCol=PERIOD_FREQ,PERIOD_FREQ_DESC",
		id: "taxFreq",
		async: false
	});
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=AC_SUBJECT&tableCol=SUBJECT_CODE,SUBJECT_DESC",
		id: "suspAssetAcct",
		async: false
	});
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=AC_SUBJECT&tableCol=SUBJECT_CODE,SUBJECT_DESC",
		id: "suspContAsset",
		async: false
	});
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=AC_SUBJECT&tableCol=SUBJECT_CODE,SUBJECT_DESC",
		id: "suspContLiab",
		async: false
	});
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=AC_SUBJECT&tableCol=SUBJECT_CODE,SUBJECT_DESC",
		id: "osCloseBal",
		async: false
	});
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=AC_SUBJECT&tableCol=SUBJECT_CODE,SUBJECT_DESC",
		id: "suspSettleLiab",
		async: false
	});
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=AC_SUBJECT&tableCol=SUBJECT_CODE,SUBJECT_DESC",
		id: "retainEarnings",
		async: false
	});
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=AC_SUBJECT&tableCol=SUBJECT_CODE,SUBJECT_DESC",
		id: "intPayable",
		async: false
	});
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=AC_SUBJECT&tableCol=SUBJECT_CODE,SUBJECT_DESC",
		id: "interAcctgBk",
		async: false
	});
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=AC_SUBJECT&tableCol=SUBJECT_CODE,SUBJECT_DESC",
		id: "suspSettleAsset",
		async: false
	});
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=AC_SUBJECT&tableCol=SUBJECT_CODE,SUBJECT_DESC",
		id: "glCashCode",
		async: false
	});
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=IRL_EXCHANGE_TYPE&tableCol=RATE_TYPE,RATE_TYPE_DESC",
		id: "defaultRateType",
		async: false
	});
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_BRANCH&tableCol=BRANCH,BRANCH_NAME",
		id: "defaultBranch",
		async: false
	});
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=AC_SUBJECT&tableCol=SUBJECT_CODE,SUBJECT_DESC",
		id: "suspLiabAcct",
		async: false
	});

	$("#glSystemAdd").Validform({
		tiptype:2,
		callback:function(form){
			glSystemAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function glSystemAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="GL_SYSTEM";
		keyFieldsJson.SEQ_NO=$("#seqNo").val();
		generalFieldsJson.TWO_BOOK=$("#twoBook").val();
		generalFieldsJson.ACCT_HIST_PERIOD_NO=$("#acctHistPeriodNo").val();
		generalFieldsJson.AUTO_INT_ACCT_CREATION=$("#autoIntAcctCreation").val();
		generalFieldsJson.TAX_ACCT_TYPE=$("#taxAcctType").val();
		generalFieldsJson.RUN_DATE=$("#runDate").val();
		generalFieldsJson.REVAL_BY_POST=$("#revalByPost").val();
		generalFieldsJson.PROFIT_CENTRE=$("#profitCentre").val();
		generalFieldsJson.OS_CLOSE_IND=$("#osCloseInd").val();
		generalFieldsJson.NEXT_RUN_DATE=$("#nextRunDate").val();
		generalFieldsJson.MUTI_SETTLE_MODE=$("#mutiSettleMode").val();
		generalFieldsJson.MAX_FUTUREDATE_DAY=$("#maxFuturedateDay").val();
		generalFieldsJson.MAX_BACKDATE_DAY=$("#maxBackdateDay").val();
		generalFieldsJson.LAST_RUN_DATE=$("#lastRunDate").val();
		generalFieldsJson.IS_BATCH_FINISHED=$("#isBatchFinished").val();
		generalFieldsJson.INT_RECEIVABLE=$("#intReceivable").val();
		generalFieldsJson.INT_ACCT_TYPE=$("#intAcctType").val();
		generalFieldsJson.HIST_PERIOD_TYPE=$("#histPeriodType").val();
		generalFieldsJson.HIST_PERIOD_NO=$("#histPeriodNo").val();
		generalFieldsJson.COMPANY=$("#company").val();
		generalFieldsJson.TAX_FREQ=$("#taxFreq").val();
		generalFieldsJson.BASE_CCY=$("#baseCcy").val();
		generalFieldsJson.AUTO_REF=$("#autoRef").val();
		generalFieldsJson.SUSP_ASSET_ACCT=$("#suspAssetAcct").val();
		generalFieldsJson.MULTISETTLE_IND=$("#multisettleInd").val();
		generalFieldsJson.SUSP_CONT_ASSET=$("#suspContAsset").val();
		generalFieldsJson.NEXT_CYCLE_DATE=$("#nextCycleDate").val();
		generalFieldsJson.SUSP_CONT_LIAB=$("#suspContLiab").val();
		generalFieldsJson.OS_CLOSE_BAL=$("#osCloseBal").val();
		generalFieldsJson.LAST_POSTING_RETENTION_DATE=$("#lastPostingRetentionDate").val();
		generalFieldsJson.SUSP_SETTLE_LIAB=$("#suspSettleLiab").val();
		generalFieldsJson.RETAIN_EARNINGS=$("#retainEarnings").val();
		generalFieldsJson.INT_PAYABLE=$("#intPayable").val();
		generalFieldsJson.REF_PREFIX=$("#refPrefix").val();
		generalFieldsJson.INTER_ACCTG_BK=$("#interAcctgBk").val();
		generalFieldsJson.SUSP_SETTLE_ASSET=$("#suspSettleAsset").val();
		generalFieldsJson.PREV_CYCLE_DATE=$("#prevCycleDate").val();
		generalFieldsJson.GL_CASH_CODE=$("#glCashCode").val();
		generalFieldsJson.DEFAULT_RATE_TYPE=$("#defaultRateType").val();
		generalFieldsJson.DEFAULT_CHARGE_RATE_TYPE=$("#defaultChargeRateType").val();
		generalFieldsJson.DEFAULT_BRANCH=$("#defaultBranch").val();
		generalFieldsJson.SUSP_LIAB_ACCT=$("#suspLiabAcct").val();
		generalFieldsJson.CCY_CTRL_ACCT=$("#ccyCtrlAcct").val();
		generalFieldsJson.BO_IND=$("#boInd").val();
		generalFieldsJson.BATCH_CONS_IND=$("#batchConsInd").val();
		generalFieldsJson.LOCAL_CCY=$("#localCcy").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_glSystemAdd,"json");
}

function callback_glSystemAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function glSystemAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}


