$(document).ready(function() {
	getPkList({
		url: contextPath + "/baseCommon/pklistBase?tableName=CMC_CARD_BIN_INFO&tableCol=BIN_ORDER",
		id: "binOrder",
		async: false
	});

	$("#cmcProductInfoAdd").Validform({
		tiptype:2,
		callback:function(){
			cmcProductInfoAdd();
			return false;
		}
	});

	$(".select2").select2();
});

function cmcProductInfoAdd(){
	var paraJson,generalFieldsJson,keyFieldsJson;
	paraJson = {};
	generalFieldsJson={};
	keyFieldsJson={};
	var url = contextPath+"/baseCommon/updateAndInsertForSave";
	paraJson.tableName="CMC_PRODUCT_INFO";
    keyFieldsJson.CARD_PRODUCT_CODE=$("#cardProductCode").val();
    generalFieldsJson.CARD_PRODUCT_NAME=$("#cardProductName").val();
    generalFieldsJson.BIN_ORDER=$("#binOrder").val();
    generalFieldsJson.PUBLISH_CHANNEL=$("#publishChannel").val();
    generalFieldsJson.CARD_TYPE_CODE=$("#cardTypeCode").val();
    generalFieldsJson.CARD_TYPE_NAME=$("#cardTypeName").val();
    generalFieldsJson.CARD_PRODUCT_TYPE=$("#cardProductType").val();
    generalFieldsJson.CARD_CRDBFLG=$("#cardCrdbflg").val();
    generalFieldsJson.CARD_NO_TYPE=$("#cardNoType").val();
    generalFieldsJson.FEE_LEVEL=$("#feeLevel").val();
    generalFieldsJson.CARD_LEVEL=$("#cardLevel").val();
    generalFieldsJson.ENABLE_FLAG=$("#enableFlag").val();
    generalFieldsJson.ENABLE_DATE=$("#enableDate").val();
    generalFieldsJson.ORDER_FLAG=$("#orderFlag").val();
    generalFieldsJson.BEGIN_SEQ=$("#beginSeq").val();
    generalFieldsJson.END_SEQ=$("#endSeq").val();
    generalFieldsJson.CARD_PHY_SORT=$("#cardPhySort").val();
    generalFieldsJson.ATM_ERR_NUM=$("#atmErrNum").val();
    generalFieldsJson.TOTAL_ERR_NUM=$("#totalErrNum").val();
    generalFieldsJson.CVN_TOT_ERR_NUM=$("#cvnTotErrNum").val();
    generalFieldsJson.CVN2_TOT_ERR_NUM=$("#cvn2TotErrNum").val();
    generalFieldsJson.LAST_TOT_ERR_NUM=$("#lastTotErrNum").val();
    generalFieldsJson.CCY=$("#ccy").val();
    generalFieldsJson.MARK_FLG=$("#markFlg").val();
    generalFieldsJson.ACTIVATE_FEE=$("#activateFee").val();
    generalFieldsJson.MAX_HOLD_NO=$("#maxHoldNo").val();
    generalFieldsJson.PSWD_MARK=$("#pswdMark").val();
    generalFieldsJson.LOST_MARK=$("#lostMark").val();
    generalFieldsJson.EX_DATE=$("#exDate").val();
    generalFieldsJson.SET_FIX_EX_DATE=$("#setFixExDate").val();
    generalFieldsJson.FIX_EX_DATE=$("#fixExDate").val();
    generalFieldsJson.INVALID_DATE=$("#invalidDate").val();
    generalFieldsJson.INTERFACE_PERMIT=$("#interfacePermit").val();
    generalFieldsJson.ISSUE_TARGET=$("#issueTarget").val();
    generalFieldsJson.BEGIN_AMT=$("#beginAmt").val();
    generalFieldsJson.CONVALUE=$("#convalue").val();
    generalFieldsJson.LAST_TRAN_DATE=$("#lastTranDate").val();
    generalFieldsJson.TRAN_TIMESTAMP=$("#tranTimestamp").val();
    generalFieldsJson.TRAN_TIME=$("#tranTime").val();
	paraJson.key = keyFieldsJson;
	paraJson.general=generalFieldsJson;
	paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
	var params = {
		paraJson:JSON.stringify(paraJson)
	};
	sendPostRequest(url,params, callback_cmcProductInfoAdd,"json");
}

function callback_cmcProductInfoAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}

function cmcProductInfoAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}