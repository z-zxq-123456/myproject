
var rowData;
$(document).ready(function() {
            getPkList({
                url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
                id: "company",
                async: false
            });

    if (parent.$("#irlCcyRate").find(".selected").length===1){
        rowData = parent.$('#irlCcyRate').DataTable().rows(".selected").data()[0];
            $("#branch").val(rowData.BRANCH).attr("disabled",true);
            $("#ccy").val(rowData.CCY).attr("disabled",true);
            $("#effectDate").val(rowData.EFFECT_DATE).attr("disabled",true);
            $("#effectTime").val(rowData.EFFECT_TIME).attr("disabled",true);
            $("#rateType").val(rowData.RATE_TYPE).attr("disabled",true);
            $("#exchBuyRate").val(rowData.EXCH_BUY_RATE);
            $("#centralBankRate").val(rowData.CENTRAL_BANK_RATE);
            $("#company").val(rowData.COMPANY);
            $("#maxFloatRate").val(rowData.MAX_FLOAT_RATE);
            $("#middleRate").val(rowData.MIDDLE_RATE);
            $("#notesBuyRate").val(rowData.NOTES_BUY_RATE);
            $("#notesSellRate").val(rowData.NOTES_SELL_RATE);
            $("#quoteType").val(rowData.QUOTE_TYPE);
            $("#exchSellRate").val(rowData.EXCH_SELL_RATE);
    }

    $("#irlCcyRateMod").Validform({
        tiptype:2,
        callback:function(form){
            irlCcyRateMod();
            return false;
        }
    });

    $(".select2").select2();
});

function irlCcyRateMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
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
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_irlCcyRateMod,"json");
}

function callback_irlCcyRateMod(json){
    if (json.success) {

        var dataTable=parent.$("#irlCcyRate").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            BRANCH:$("branch").val(),
            CCY:$("ccy").val(),
            EFFECT_DATE:$("effectDate").val(),
            EFFECT_TIME:$("effectTime").val(),
            RATE_TYPE:$("rateType").val(),
            EXCH_BUY_RATE:$("exchBuyRate").val(),
            CENTRAL_BANK_RATE:$("centralBankRate").val(),
            MAX_FLOAT_RATE:$("maxFloatRate").val(),
            MIDDLE_RATE:$("middleRate").val(),
            NOTES_BUY_RATE:$("notesBuyRate").val(),
            NOTES_SELL_RATE:$("notesSellRate").val(),
            QUOTE_TYPE:$("quoteType").val(),
            EXCH_SELL_RATE:$("exchSellRate").val(),
            COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function irlCcyRateModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

