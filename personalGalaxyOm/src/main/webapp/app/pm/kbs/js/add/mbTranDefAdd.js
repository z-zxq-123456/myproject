$(document).ready(function() {
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_CHANNEL&tableCol=CHANNEL,CHANNEL_DESC",
		id: "sourceType",
		async: false
	});

	$("#mbTranDefAdd").Validform({
		tiptype:2,
		callback:function(){
			mbTranDefAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function mbTranDefAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="MB_TRAN_DEF";
    keyFieldsJson.TRAN_TYPE=$("#tranType").val();
    generalFieldsJson.CASH_TRAN=$("#cashTran").val();
    generalFieldsJson.BAL_TYPE_PRIORITY=$("#balTypePriority").val();
    generalFieldsJson.CR_DR_MAINT_IND=$("#crDrMaintInd").val();
    generalFieldsJson.IS_CORRECT=$("#isCorrect").val();
    generalFieldsJson.MULTI_RVS_TRAN_TYPE_IND=$("#multiRvsTranTypeInd").val();
    generalFieldsJson.OTH_TRAN_TYPE=$("#othTranType").val();
    generalFieldsJson.PRINT_TRAN_DESC=$("#printTranDesc").val();
    generalFieldsJson.PROGRAM_ID_GROUP=$("#programIdGroup").val();
    generalFieldsJson.RECALC_ACCT_STOP_PAY=$("#recalcAcctStopPay").val();
    generalFieldsJson.RECALC_RES_AMT=$("#recalcResAmt").val();
    generalFieldsJson.RES_PRIORITY=$("#resPriority").val();
    generalFieldsJson.REVERSAL_TRAN_FLAG=$("#reversalTranFlag").val();
    generalFieldsJson.REVERSAL_TRAN_TYPE=$("#reversalTranType").val();
    generalFieldsJson.SOURCE_TYPE=$("#sourceType").val();
    generalFieldsJson.TRAN_CLASS=$("#tranClass").val();
    generalFieldsJson.TRAN_TIME=$("#tranTime").val();
    generalFieldsJson.TRAN_TIMESTAMP=$("#tranTimestamp").val();
    generalFieldsJson.TRAN_TYPE_DESC=$("#tranTypeDesc").val();
    generalFieldsJson.UPD_TRAILBOX_FLAG=$("#updTrailboxFlag").val();
    generalFieldsJson.AVAILBAL_CALC_TYPE=$("#availbalCalcType").val();
    generalFieldsJson.BALANCE_FLAG=$("#balanceFlag").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_mbTranDefAdd,"json");
}

function callback_mbTranDefAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function mbTranDefAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}