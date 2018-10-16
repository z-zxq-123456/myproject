var rowData;
$(document).ready(function() {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_PERIOD_FREQ&tableCol=PERIOD_FREQ,PERIOD_FREQ_DESC",
        id: "chargePeriodFreq",
        async: false
    });

    if (parent.$("#mbProdCharge").find(".selected").length===1){
        rowData = parent.$('#mbProdCharge').DataTable().rows(".selected").data()[0];
        $("#feeType").val(rowData.FEE_TYPE).attr("disabled",true);
        $("#prodType").val(rowData.PROD_TYPE).attr("disabled",true);
        $("#nextChargeDate").val(rowData.NEXT_CHARGE_DATE);
        $("#chargePeriodFreq").val(rowData.CHARGE_PERIOD_FREQ);
        $("#chargeDay").val(rowData.CHARGE_DAY);
    }

    $("#mbProdChargeMod").Validform({
        tiptype:2,
        callback:function(){
            mbProdChargeMod();
            return false;
        }
    });

    $(".select2").select2();
});

function mbProdChargeMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="MB_PROD_CHARGE";
    keyFieldsJson.FEE_TYPE=$("#feeType").val();
    keyFieldsJson.PROD_TYPE=$("#prodType").val();
    generalFieldsJson.NEXT_CHARGE_DATE=$("#nextChargeDate").val();
    generalFieldsJson.CHARGE_PERIOD_FREQ=$("#chargePeriodFreq").val();
    generalFieldsJson.CHARGE_DAY=$("#chargeDay").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_mbProdChargeMod,"json");
}

function callback_mbProdChargeMod(json){
    if (json.success) {
        if (parent.$("#mbProdCharge").find(".selected").length===1){
            rowData = parent.$('#mbProdCharge').DataTable().rows(".selected").data()[0];
            var columnStatus = rowData.COLUMN_STATUS;
            var dataTable=parent.$("#mbProdCharge").DataTable();
            dataTable.row(".selected").remove().draw(false);
            dataTable.row.add({
                FEE_TYPE:$("#feeType").val(),
                PROD_TYPE:$("#prodType").val(),
                NEXT_CHARGE_DATE:$("#nextChargeDate").val(),
                CHARGE_PERIOD_FREQ:$("#chargePeriodFreq").val(),
                CHARGE_DAY:$("#chargeDay").val(),
                COLUMN_STATUS:columnStatus
            }).draw(false);
            parent.showMsgDuringTime("编辑成功");
        }
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function mbProdChargeModCancel(){
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}