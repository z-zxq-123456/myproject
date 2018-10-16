var rowData;
$(document).ready(function() {
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
    if (parent.$("#irlFeePackage").find(".selected").length===1){
        rowData = parent.$('#irlFeePackage').DataTable().rows(".selected").data()[0];
        $("#packageId").val(rowData.PACKAGE_ID).attr("disabled",true);
        $("#effectDate").val(rowData.EFFECT_DATE);
        $("#packageDesc").val(rowData.PACKAGE_DESC);
        $("#packageType").val(rowData.PACKAGE_TYPE);
        $("#packageStatus").val(rowData.PACKAGE_STATUS);
        $("#packageCcy").val(rowData.PACKAGE_CCY);
        $("#endDate").val(rowData.END_DATE);
        $("#packageMode").val(rowData.PACKAGE_MODE);
        $("#processOrder").val(rowData.PROCESS_ORDER);
        $("#settleAmt").val(rowData.SETTLE_AMT);
        $("#processMode").val(rowData.PROCESS_MODE);
        $("#packagePeriodFreq").val(rowData.PACKAGE_PERIOD_FREQ);
        $("#packageNum").val(rowData.PACKAGE_NUM);
        if(rowData.PACKAGE_FEE_TYPE!==undefined){
            $("#packageFeeType").val(rowData.PACKAGE_FEE_TYPE.split(","));
        }
        $("#packageAmt").val(rowData.PACKAGE_AMT);
        $("#nextDealDate").val(rowData.NEXT_DEAL_DATE);
        $("#settleCcy").val(rowData.SETTLE_CCY);
        $("#clientType").val(rowData.CLIENT_TYPE);
        $("#dealDay").val(rowData.DEAL_DAY);
    }

    $("#irlFeePackageMod").Validform({
        tiptype:2,
        callback:function(){
            irlFeePackageMod();
            return false;
        }
    });

    $(".select2").select2();
});

function irlFeePackageMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
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
    if($("#settleAmt").val()!=="")
    generalFieldsJson.SETTLE_AMT=$("#settleAmt").val();
    generalFieldsJson.PROCESS_MODE=$("#processMode").val();
    generalFieldsJson.PACKAGE_PERIOD_FREQ=$("#packagePeriodFreq").val();
    if($("#packageNum").val()!=="")
    generalFieldsJson.PACKAGE_NUM=$("#packageNum").val();
    if($("#packageFeeType").val()!==null) {
        generalFieldsJson.PACKAGE_FEE_TYPE = $("#packageFeeType").val().toString();
    }
    if($("#packageAmt").val()!=="")
    generalFieldsJson.PACKAGE_AMT=$("#packageAmt").val();
    generalFieldsJson.NEXT_DEAL_DATE=$("#nextDealDate").val();
    generalFieldsJson.SETTLE_CCY=$("#settleCcy").val();
    generalFieldsJson.CLIENT_TYPE=$("#clientType").val();
    generalFieldsJson.DEAL_DAY=$("#dealDay").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_irlFeePackageMod,"json");
}

function callback_irlFeePackageMod(json){
    if (json.success) {
        if (parent.$("#irlFeePackage").find(".selected").length===1){
            rowData = parent.$('#irlFeePackage').DataTable().rows(".selected").data()[0];
            var columnStatus = rowData.COLUMN_STATUS;
            var dataTable=parent.$("#irlFeePackage").DataTable();
            dataTable.row(".selected").remove().draw(false);
            dataTable.row.add({
                PACKAGE_ID:$("#packageId").val(),
                EFFECT_DATE:$("#effectDate").val(),
                PACKAGE_DESC:$("#packageDesc").val(),
                PACKAGE_TYPE:$("#packageType").val(),
                PACKAGE_STATUS:$("#packageStatus").val(),
                PACKAGE_CCY:$("#packageCcy").val(),
                END_DATE:$("#endDate").val(),
                PACKAGE_MODE:$("#packageMode").val(),
                PROCESS_ORDER:$("#processOrder").val(),
                SETTLE_AMT:$("#settleAmt").val(),
                PROCESS_MODE:$("#processMode").val(),
                PACKAGE_PERIOD_FREQ:$("#packagePeriodFreq").val(),
                PACKAGE_NUM:$("#packageNum").val(),
                PACKAGE_FEE_TYPE:$("#packageFeeType").val(),
                PACKAGE_AMT:$("#packageAmt").val(),
                NEXT_DEAL_DATE:$("#nextDealDate").val(),
                SETTLE_CCY:$("#settleCcy").val(),
                CLIENT_TYPE:$("#clientType").val(),
                DEAL_DAY:$("#dealDay").val(),
                COLUMN_STATUS:columnStatus
            }).draw(false);
            parent.showMsgDuringTime("编辑成功");
        }
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function irlFeePackageModCancel(){
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}