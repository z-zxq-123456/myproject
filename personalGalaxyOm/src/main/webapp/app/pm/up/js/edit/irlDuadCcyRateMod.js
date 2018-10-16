
var rowData;
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

    if (parent.$("#irlDuadCcyRate").find(".selected").length===1){
        rowData = parent.$('#irlDuadCcyRate').DataTable().rows(".selected").data()[0];
            $("#branch").val(rowData.BRANCH).attr("disabled",true);
            $("#targetCcy").val(rowData.TARGET_CCY).attr("disabled",true);
            $("#sourceCcy").val(rowData.SOURCE_CCY).attr("disabled",true);
            $("#rateType").val(rowData.RATE_TYPE).attr("disabled",true);
            $("#effectDate").val(rowData.EFFECT_DATE).attr("disabled",true);
            $("#effectTime").val(rowData.EFFECT_TIME).attr("disabled",true);
           $("#exchBuyRate").val(rowData.EXCH_BUY_RATE);
           $("#exchSellRate").val(rowData.EXCH_SELL_RATE);
            $("#quoteType").val(rowData.QUOTE_TYPE);
            $("#middleRate").val(rowData.MIDDLE_RATE);
            $("#centralBankRate").val(rowData.CENTRAL_BANK_RATE);
            $("#notesSellRate").val(rowData.NOTES_SELL_RATE);
            $("#notesBuyRate").val(rowData.NOTES_BUY_RATE);
            $("#company").val(rowData.COMPANY);
            $("#maxFloatRate").val(rowData.MAX_FLOAT_RATE);
    }

    $("#irlDuadCcyRateMod").Validform({
        tiptype:2,
        callback:function(form){
            irlDuadCcyRateMod();
            return false;
        }
    });

    $(".select2").select2();
});

function irlDuadCcyRateMod(){
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
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
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
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_irlDuadCcyRateMod,"json");
}

function callback_irlDuadCcyRateMod(json){
    if (json.success) {
        var dataTable=parent.$("#irlDuadCcyRate").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            BRANCH:$("branch").val(),
            TARGET_CCY:$("targetCcy").val(),
            SOURCE_CCY:$("sourceCcy").val(),
            RATE_TYPE:$("rateType").val(),
            EFFECT_DATE:$("effectDate").val(),
            EFFECT_TIME:$("effectTime").val(),
            EXCH_BUY_RATE:$("exchBuyRate").val(),
            EXCH_SELL_RATE:$("exchSellRate").val(),
            QUOTE_TYPE:$("quoteType").val(),
            MIDDLE_RATE:$("middleRate").val(),
            CENTRAL_BANK_RATE:$("centralBankRate").val(),
            NOTES_BUY_RATE:$("notesBuyRate").val(),
            NOTES_SELL_RATE:$("notesSellRate").val(),
            MAX_FLOAT_RATE:$("maxFloatRate").val(),

            COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function irlDuadCcyRateModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

