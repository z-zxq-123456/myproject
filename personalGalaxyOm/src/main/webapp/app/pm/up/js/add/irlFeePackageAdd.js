$(document).ready(function() {
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=IRL_FEE_PACKAGE&tableCol=PACKAGE_ID,PACKAGE_DESC",
		id: "packageId",
		async: false
	});
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_CURRENCY&tableCol=CCY,CCY_DESC",
		id: "packageCcy",
		async: false
	});
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_PERIOD_FREQ&tableCol=PERIOD_FREQ,PERIOD_FREQ_DESC",
		id: "packagePeriodFreq",
		async: false
	});
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=IRL_FEE_TYPE&tableCol=FEE_TYPE,FEE_DESC",
		id: "packageFeeType",
		async: false
	});
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_CURRENCY&tableCol=CCY,CCY_DESC",
		id: "settleCcy",
		async: false
	});
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=CIF_CLIENT_TYPE&tableCol=CLIENT_TYPE,CLIENT_TYPE_DESC",
		id: "clientType",
		async: false
	});
	$('#clientType').append("<option value='ALL'>ALL-全部</option>");
	$("#irlFeePackageAdd").Validform({
		tiptype:2,
		callback:function(){
			irlFeePackageAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function irlFeePackageAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="IRL_FEE_PACKAGE";
    keyFieldsJson.PACKAGE_ID=$("#packageId").val();
    generalFieldsJson.EFFECT_DATE=$("#effectDate").val();
    generalFieldsJson.PACKAGE_DESC=$("#packageDesc").val();
    generalFieldsJson.PACKAGE_TYPE=$("#packageType").val();
    generalFieldsJson.PACKAGE_STATUS=$("#packageStatus").val();
    generalFieldsJson.PACKAGE_CCY=$("#packageCcy").val();
    generalFieldsJson.END_DATE=$("#endDate").val();
    generalFieldsJson.PACKAGE_MODE=$("#packageMode").val();
    generalFieldsJson.PROCESS_ORDER=$("#processOrder").val();
    generalFieldsJson.SETTLE_AMT=$("#settleAmt").val();
    generalFieldsJson.PROCESS_MODE=$("#processMode").val();
    generalFieldsJson.PACKAGE_PERIOD_FREQ=$("#packagePeriodFreq").val();
    generalFieldsJson.PACKAGE_NUM=$("#packageNum").val();
	if($("#packageFeeType").val()!==null) {
		generalFieldsJson.PACKAGE_FEE_TYPE = $("#packageFeeType").val().toString();
	}
    generalFieldsJson.PACKAGE_AMT=$("#packageAmt").val();
    generalFieldsJson.NEXT_DEAL_DATE=$("#nextDealDate").val();
    generalFieldsJson.SETTLE_CCY=$("#settleCcy").val();
	generalFieldsJson.CLIENT_TYPE = $("#clientType").val();
    generalFieldsJson.DEAL_DAY=$("#dealDay").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_irlFeePackageAdd,"json");
}

function callback_irlFeePackageAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function irlFeePackageAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}