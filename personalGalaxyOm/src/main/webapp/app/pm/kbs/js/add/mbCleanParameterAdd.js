$(document).ready(function() {
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_PERIOD_FREQ&tableCol=PERIOD_FREQ,PERIOD_FREQ_DESC",
		id: "periodFreq",
		async: false
	});
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_CURRENCY&tableCol=CCY,CCY_DESC",
		id: "ccy",
		async: false
	});

	$("#mbCleanParameterAdd").Validform({
		tiptype:2,
		callback:function(){
			mbCleanParameterAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function mbCleanParameterAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="MB_CLEAN_PARAMETER";
    keyFieldsJson.SEQ_NO=$("#seqNo").val();
    generalFieldsJson.ACCT_STATUS=$("#acctStatus").val();
    generalFieldsJson.TERM_PERIOD=$("#termPeriod").val();
    generalFieldsJson.STATUS=$("#status").val();
    generalFieldsJson.START_TIME=$("#startTime").val();
    generalFieldsJson.PROD_TYPE=$("#prodType").val();
    generalFieldsJson.PERIOD_FREQ_TYPE=$("#periodFreqType").val();
    generalFieldsJson.PERIOD_FREQ=$("#periodFreq").val();
    generalFieldsJson.NEXT_CLEAN_DATE=$("#nextCleanDate").val();
    generalFieldsJson.LAST_CLEAN_DATE=$("#lastCleanDate").val();
    generalFieldsJson.END_TIME=$("#endTime").val();
    generalFieldsJson.CLEAN_TYPE=$("#cleanType").val();
    generalFieldsJson.CCY=$("#ccy").val();
    generalFieldsJson.BALANCE=$("#balance").val();
    generalFieldsJson.AGREEMENT_TYPE=$("#agreementType").val();
    generalFieldsJson.ACCT_TYPE=$("#acctType").val();
    generalFieldsJson.TERM_TYPE=$("#termType").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_mbCleanParameterAdd,"json");
}

function callback_mbCleanParameterAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function mbCleanParameterAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}