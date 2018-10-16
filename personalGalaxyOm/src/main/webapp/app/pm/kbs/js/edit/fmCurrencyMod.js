
var rowData;
$(document).ready(function() {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
        id: "company",
        async: false
    });

    if (parent.$("#fmCurrency").find(".selected").length===1){
        rowData = parent.$('#fmCurrency').DataTable().rows(".selected").data()[0];
        $("#ccy").val(rowData.CCY).attr("disabled",true);
        $("#roundTrunc").val(rowData.ROUND_TRUNC);
        $("#integerDesc").val(rowData.INTEGER_DESC);
        $("#deciPlaces").val(rowData.DECI_PLACES);
        $("#ccyDesc").val(rowData.CCY_DESC);
        $("#ccyIntCode").val(rowData.CCY_INT_CODE);
        $("#weekend1").val(rowData.WEEKEND_1);
        $("#spotDate").val(rowData.SPOT_DATE);
        $("#quoteType").val(rowData.QUOTE_TYPE);
        $("#positionLimit").val(rowData.POSITION_LIMIT);
        $("#payAdviceDays").val(rowData.PAY_ADVICE_DAYS);
        $("#ccyGroup").val(rowData.CCY_GROUP);
        $("#fixingDays").val(rowData.FIXING_DAYS);
        $("#ccyGroupCode").val(rowData.CCY_GROUP_CODE);
        $("#deciDesc").val(rowData.DECI_DESC);
        $("#dayBasis").val(rowData.DAY_BASIS);
        $("#company").val(rowData.COMPANY);
        $("#ccyLibol").val(rowData.CCY_LIBOL);
        $("#weekend2").val(rowData.WEEKEND_2);
    }

    $("#fmCurrencyMod").Validform({
        tiptype:2,
        callback:function(form){
            fmCurrencyMod();
            return false;
        }
    });

    $(".select2").select2();
});

function fmCurrencyMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="FM_CURRENCY";
    keyFieldsJson.CCY=$("#ccy").val();
    generalFieldsJson.ROUND_TRUNC=$("#roundTrunc").val();
    generalFieldsJson.INTEGER_DESC=$("#integerDesc").val();
    generalFieldsJson.DECI_PLACES=$("#deciPlaces").val();
    generalFieldsJson.CCY_DESC=$("#ccyDesc").val();
    generalFieldsJson.CCY_INT_CODE=$("#ccyIntCode").val();
    generalFieldsJson.WEEKEND_1=$("#weekend1").val();
    generalFieldsJson.SPOT_DATE=$("#spotDate").val();
    generalFieldsJson.QUOTE_TYPE=$("#quoteType").val();
    //generalFieldsJson.POSITION_LIMIT=$("#positionLimit").val();
    generalFieldsJson.PAY_ADVICE_DAYS=$("#payAdviceDays").val();
    generalFieldsJson.CCY_GROUP=$("#ccyGroup").val();
    generalFieldsJson.FIXING_DAYS=$("#fixingDays").val();
    generalFieldsJson.CCY_GROUP_CODE=$("#ccyGroupCode").val();
    generalFieldsJson.DECI_DESC=$("#deciDesc").val();
    generalFieldsJson.DAY_BASIS=$("#dayBasis").val();
    generalFieldsJson.COMPANY=$("#company").val();
    //generalFieldsJson.CCY_LIBOL=$("#ccyLibol").val();
    generalFieldsJson.WEEKEND_2=$("#weekend2").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_fmCurrencyMod,"json");
}

function callback_fmCurrencyMod(json){
    if (json.success) {
        var dataTable=parent.$("#fmCurrency").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            CCY:$("#ccy").val(),
            ROUND_TRUNC:$("#roundTrunc").val(),
            INTEGER_DESC:$("#integerDesc").val(),
            DECI_PLACES:$("#deciPlaces").val(),
            CCY_DESC:$("#ccyDesc").val(),
            CCY_INT_CODE:$("#ccyIntCode").val(),
            WEEKEND_1:$("#weekend1").val(),
            SPOT_DATE:$("#spotDate").val(),
            QUOTE_TYPE:$("#quoteType").val(),
            POSITION_LIMIT:$("#positionLimit").val(),
            PAY_ADVICE_DAYS:$("#payAdviceDays").val(),
            CCY_GROUP:$("#ccyGroup").val(),
            FIXING_DAYS:$("#fixingDays").val(),
            CCY_GROUP_CODE:$("#ccyGroupCode").val(),
            DECI_DESC:$("#deciDesc").val(),
            DAY_BASIS:$("#dayBasis").val(),
            COMPANY:$("#company").val(),
            //CCY_LIBOL:$("#ccyLibol").val(),
            WEEKEND_2:$("#weekend2").val(),
            COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function fmCurrencyModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

