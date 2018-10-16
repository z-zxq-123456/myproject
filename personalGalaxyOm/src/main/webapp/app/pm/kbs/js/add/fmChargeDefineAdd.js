
$(document).ready(function() {
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_CURRENCY&tableCol=CCY,CCY_DESC",
		id: "ccy",
		async: false
	});

	$("#fmChargeDefineAdd").Validform({
		tiptype:2,
		callback:function(form){
			fmChargeDefineAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function fmChargeDefineAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="FM_CHARGE_DEFINE";
		keyFieldsJson.CHARGE_TYPE=$("#chargeType").val();
		generalFieldsJson.TRANS_EVENT_TYPE=$("#transEventType").val();
		generalFieldsJson.CALC_ON_AVERAGES=$("#calcOnAverages").val();
		generalFieldsJson.CASH_EVENT_TYPE=$("#cashEventType").val();
		generalFieldsJson.CCY=$("#ccy").val();
		generalFieldsJson.CHARGE_METHOD=$("#chargeMethod").val();
		generalFieldsJson.CHARGE_MODE=$("#chargeMode").val();
		generalFieldsJson.CONVERT_FLAG=$("#convertFlag").val();
		generalFieldsJson.FEE_DESC=$("#feeDesc").val();
		generalFieldsJson.FIX_AMT=$("#fixAmt").val();
		generalFieldsJson.FIX_RATE=$("#fixRate").val();
		generalFieldsJson.HIGH_LIMIT=$("#highLimit").val();
		generalFieldsJson.LOW_LIMIT=$("#lowLimit").val();
		generalFieldsJson.MATRIX_CALC=$("#matrixCalc").val();
		generalFieldsJson.MATRIX_TYPE=$("#matrixType").val();
		generalFieldsJson.REMARKS=$("#remarks").val();
		generalFieldsJson.ROUTINE_NAME=$("#routineName").val();
		generalFieldsJson.BO_CLASS=$("#boClass").val();
		generalFieldsJson.CALC_FULL_UNDRAWN=$("#calcFullUndrawn").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_fmChargeDefineAdd,"json");
}

function callback_fmChargeDefineAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function fmChargeDefineAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}


