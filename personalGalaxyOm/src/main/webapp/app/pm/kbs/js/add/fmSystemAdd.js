
$(document).ready(function() {
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
		id: "company",
		async: false
	});

	$("#fmSystemAdd").Validform({
		tiptype:2,
		callback:function(form){
			fmSystemAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function fmSystemAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="FM_SYSTEM";
		keyFieldsJson.COY_NAME=$("#coyName").val();
		generalFieldsJson.AUTO_CLIENT_GEN=$("#autoClientGen").val();
		generalFieldsJson.SYSTEM_PHASE=$("#systemPhase").val();
		generalFieldsJson.RUN_DATE=$("#runDate").val();
		generalFieldsJson.QUR_END_DATE=$("#qurEndDate").val();
		generalFieldsJson.PRODUCT_30E=$("#product30e").val();
		generalFieldsJson.PROCESS_SPLIT_IND=$("#processSplitInd").val();
		generalFieldsJson.NEXT_RUN_DATE=$("#nextRunDate").val();
		generalFieldsJson.MTH_END_DATE=$("#mthEndDate").val();
		generalFieldsJson.INTER_BRANCH_IND=$("#interBranchInd").val();
		generalFieldsJson.HALF_END_DATE=$("#halfEndDate").val();
		generalFieldsJson.GL_IND=$("#glInd").val();
		generalFieldsJson.COY_SHORT=$("#coyShort").val();
		generalFieldsJson.CONTINUOUS_RUN=$("#continuousRun").val();
		generalFieldsJson.CLIENT_BLOCK_FREQ=$("#clientBlockFreq").val();
		generalFieldsJson.AUTO_LOCK_BL_CLIENT=$("#autoLockBlClient").val();
		generalFieldsJson.AUTO_COLL_GEN=$("#autoCollGen").val();
		generalFieldsJson.YR_END_DATE=$("#yrEndDate").val();
		generalFieldsJson.DEFAULT_BRANCH=$("#defaultBranch").val();
		generalFieldsJson.ALLOW_BACKQRY_DAY=$("#allowBackqryDay").val();
		generalFieldsJson.DEFAULT_PROFIT_CENTRE=$("#defaultProfitCentre").val();
		generalFieldsJson.DEFAULT_RATE_TYPE=$("#defaultRateType").val();
		generalFieldsJson.DEFAULT_RATE_TYPE_LOCAL=$("#defaultRateTypeLocal").val();
		generalFieldsJson.EBH_BRANCH=$("#ebhBranch").val();
		generalFieldsJson.EXCHANGE_RATE_VARIANCE=$("#exchangeRateVariance").val();
		generalFieldsJson.MULTI_CORP_QUERY_ALLOW=$("#multiCorpQueryAllow").val();
		generalFieldsJson.RB_RESTRAINT_TYPE=$("#rbRestraintType").val();
		generalFieldsJson.HEAD_OFFICE_CLIENT=$("#headOfficeClient").val();
		generalFieldsJson.INTERNAL_RATE_CHARGE_FLAG=$("#internalRateChargeFlag").val();
		generalFieldsJson.INTER_BRANCH_ACCT_HO=$("#interBranchAcctHo").val();
		generalFieldsJson.NPV_GAP_TYPE=$("#npvGapType").val();
		generalFieldsJson.IS_DEBUG=$("#isDebug").val();
		generalFieldsJson.IS_ERROR=$("#isError").val();
		generalFieldsJson.DAC_IND=$("#dacInd").val();
		generalFieldsJson.CR_DR_CHECK_FLAG=$("#crDrCheckFlag").val();
		generalFieldsJson.MULTI_CORPORATION_METHOD=$("#multiCorporationMethod").val();
		generalFieldsJson.MULTI_CORPORATION_FLAG=$("#multiCorporationFlag").val();
		generalFieldsJson.REPORT_CCY=$("#reportCcy").val();
		generalFieldsJson.COMPANY=$("#company").val();
		generalFieldsJson.CLIENT_NO_STRUCTURE_TYPE=$("#clientNoStructureType").val();
		generalFieldsJson.MAIN_BRANCH_CODE=$("#mainBranchCode").val();
		generalFieldsJson.CAPITAL_FUNDS=$("#capitalFunds").val();
		generalFieldsJson.BATCH_UNIT=$("#batchUnit").val();
		generalFieldsJson.BATCH_MODULE=$("#batchModule").val();
		generalFieldsJson.BATCH_DEFAULT_USER_ID=$("#batchDefaultUserId").val();
		generalFieldsJson.BATCH_CHECK_FLAG=$("#batchCheckFlag").val();
		generalFieldsJson.BASE_CCY=$("#baseCcy").val();
		generalFieldsJson.LOCAL_CCY=$("#localCcy").val();
		generalFieldsJson.LIMIT_CCY=$("#limitCcy").val();
		generalFieldsJson.LAST_RUN_DATE=$("#lastRunDate").val();
		generalFieldsJson.DEFAULT_CHARGE_RATE_TYPE=$("#defaultChargeRateType").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_fmSystemAdd,"json");
}

function callback_fmSystemAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function fmSystemAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}


