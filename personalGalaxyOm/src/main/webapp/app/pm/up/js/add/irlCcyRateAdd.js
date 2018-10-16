$(document).ready(function() {
			getPkList({
				url: contextPath + "/baseCommon/pklistBase?tableName=FM_BRANCH&tableCol=BRANCH,BRANCH_NAME",
				id: "branch",
				async: false
			});
			getPkList({
				url: contextPath + "/baseCommon/pklistBase?tableName=FM_CURRENCY&tableCol=CCY,CCY_DESC",
				id: "ccy",
				async: false
			});
			getPkList({
				url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
				id: "company",
				async: false
			});
			getPkList({
            	url: contextPath + "/baseCommon/pklistBase?tableName=IRL_EXCHANGE_TYPE&tableCol=RATE_TYPE,RATE_TYPE_DESC",
            	id: "rateType",
            	async: false
            });

	$("#irlCcyRateAdd").Validform({
		tiptype:2,
		callback:function(form){
			irlCcyRateAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function irlCcyRateAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="IRL_CCY_RATE";
		keyFieldsJson.BRANCH=$("#branch").val();
		keyFieldsJson.CCY=$("#ccy").val();
		keyFieldsJson.EFFECT_DATE=$("#effectDate").val();
		keyFieldsJson.EFFECT_TIME=$("#effectTime").val();
		keyFieldsJson.RATE_TYPE=$("#rateType").val();
		generalFieldsJson.EXCH_BUY_RATE=$("#exchBuyRate").val();
		generalFieldsJson.CENTRAL_BANK_RATE=$("#centralBankRate").val();
		generalFieldsJson.MAX_FLOAT_RATE=$("#maxFloatRate").val();
		generalFieldsJson.MIDDLE_RATE=$("#middleRate").val();
		generalFieldsJson.NOTES_BUY_RATE=$("#notesBuyRate").val();
		generalFieldsJson.NOTES_SELL_RATE=$("#notesSellRate").val();
		generalFieldsJson.QUOTE_TYPE=$("#quoteType").val();
		generalFieldsJson.EXCH_SELL_RATE=$("#exchSellRate").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_irlCcyRateAdd,"json");
}

function callback_irlCcyRateAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function irlCcyRateAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

