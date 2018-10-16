$(document).ready(function() {
			getPkList({
				url: contextPath + "/baseCommon/pklistBase?tableName=FM_BRANCH&tableCol=BRANCH,BRANCH_NAME",
				id: "branch",
				async: false
			});
			getPkList({
				url: contextPath + "/baseCommon/pklistBase?tableName=FM_CURRENCY&tableCol=CCY,CCY_DESC",
				id: "targetCcy",
				async: false
			});
			getPkList({
				url: contextPath + "/baseCommon/pklistBase?tableName=FM_CURRENCY&tableCol=CCY,CCY_DESC",
				id: "sourceCcy",
				async: false
			});
			getPkList({
				url: contextPath + "/baseCommon/pklistBase?tableName=IRL_EXCHANGE_TYPE&tableCol=RATE_TYPE,RATE_TYPE_DESC",
				id: "rateType",
				async: false
			});
			getPkList({
				url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
				id: "company",
				async: false
			});

	$("#irlDuadCcyRateAdd").Validform({
		tiptype:2,
		callback:function(form){
			irlDuadCcyRateAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function irlDuadCcyRateAdd(){
	var sourceCcy,targetCcy;
	sourceCcy=$("#sourceCcy").val();
	targetCcy=$("#targetCcy").val();
	if (sourceCcy ===targetCcy) {
		showMsg("源币种与目标币种不能相同！");
		return;
	}
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="IRL_DUAD_CCY_RATE";
		keyFieldsJson.BRANCH=$("#branch").val();
		keyFieldsJson.TARGET_CCY=$("#targetCcy").val();
		keyFieldsJson.SOURCE_CCY=$("#sourceCcy").val();
		keyFieldsJson.RATE_TYPE=$("#rateType").val();
		keyFieldsJson.EFFECT_DATE=$("#effectDate").val();
		keyFieldsJson.EFFECT_TIME=$("#effectTime").val();
		generalFieldsJson.EXCH_BUY_RATE=$("#exchBuyRate").val();
		generalFieldsJson.EXCH_SELL_RATE=$("#exchSellRate").val();
		generalFieldsJson.QUOTE_TYPE=$("#quoteType").val();
		generalFieldsJson.MIDDLE_RATE=$("#middleRate").val();
		generalFieldsJson.CENTRAL_BANK_RATE=$("#centralBankRate").val();
		generalFieldsJson.NOTES_SELL_RATE=$("#notesSellRate").val();
		generalFieldsJson.NOTES_BUY_RATE=$("#notesBuyRate").val();
		generalFieldsJson.MAX_FLOAT_RATE=$("#maxFloatRate").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_irlDuadCcyRateAdd,"json");
}

function callback_irlDuadCcyRateAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function irlDuadCcyRateAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}


