
$(document).ready(function() {
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=FM_CURRENCY&tableCol=CCY,CCY_DESC",
		id: "ccy",
		async: false
	});

	$("#lmTranLimitDefAdd").Validform({
		tiptype:2,
		callback:function(form){
			lmTranLimitDefAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function lmTranLimitDefAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="LM_TRAN_LIMIT_DEF";
		keyFieldsJson.LIMIT_REF=$("#limitRef").val();
		generalFieldsJson.CCY=$("#ccy").val();
		generalFieldsJson.DEAL_FLOW=$("#dealFlow").val();
		generalFieldsJson.EFFECT_DATE=$("#effectDate").val();
		generalFieldsJson.LIMIT_DESC=$("#limitDesc").val();
		generalFieldsJson.LIMIT_TYPE=$("#limitType").val();
		generalFieldsJson.MAX_AMT=$("#maxAmt").val();
		generalFieldsJson.MIN_AMT=$("#minAmt").val();
		generalFieldsJson.STATUS=$("#status").val();
		generalFieldsJson.LIMIT_LEVEL=$("#otherLevel").val();
	    generalFieldsJson.ENABLE_DEFINE=$("#enableDefine").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_lmTranLimitDefAdd,"json");
}

function callback_lmTranLimitDefAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function lmTranLimitDefAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}


