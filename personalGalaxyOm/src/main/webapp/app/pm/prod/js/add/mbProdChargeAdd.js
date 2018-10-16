$(document).ready(function() {
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=IRL_FEE_TYPE&tableCol=FEE_TYPE,FEE_DESC",
		id: "feeType",
		async: false
	});
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_PERIOD_FREQ&tableCol=PERIOD_FREQ,PERIOD_FREQ_DESC",
		id: "chargePeriodFreq",
		async: false
	});

	$("#mbProdChargeAdd").Validform({
		tiptype:2,
		callback:function(){
			mbProdChargeAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function mbProdChargeAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="MB_PROD_CHARGE";
    keyFieldsJson.FEE_TYPE=$("#feeType").val();
    keyFieldsJson.PROD_TYPE=$("#prodType").val();
    generalFieldsJson.NEXT_CHARGE_DATE=$("#nextChargeDate").val();
    generalFieldsJson.CHARGE_PERIOD_FREQ=$("#chargePeriodFreq").val();
    generalFieldsJson.CHARGE_DAY=$("#chargeDay").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_mbProdChargeAdd,"json");
}

function callback_mbProdChargeAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function mbProdChargeAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}