
var rowData;
$(document).ready(function() {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_CURRENCY&tableCol=CCY,CCY_DESC",
        id: "ccy",
        async: false
    });

    if (parent.$("#fmChargeDefine").find(".selected").length===1){
        rowData = parent.$('#fmChargeDefine').DataTable().rows(".selected").data()[0];
        $("#chargeType").val(rowData.CHARGE_TYPE).attr("disabled",true);
        $("#boClass").val(rowData.BO_CLASS);
        $("#fixRate").val(rowData.FIX_RATE);
        $("#highLimit").val(rowData.HIGH_LIMIT);
        $("#lowLimit").val(rowData.LOW_LIMIT);
        $("#matrixCalc").val(rowData.MATRIX_CALC);
        $("#matrixType").val(rowData.MATRIX_TYPE);
        $("#remarks").val(rowData.REMARKS);
        $("#routineName").val(rowData.ROUTINE_NAME);
        $("#fixAmt").val(rowData.FIX_AMT);
        $("#feeDesc").val(rowData.FEE_DESC);
        $("#convertFlag").val(rowData.CONVERT_FLAG);
        $("#chargeMode").val(rowData.CHARGE_MODE);
        $("#chargeMethod").val(rowData.CHARGE_METHOD);
        $("#ccy").val(rowData.CCY);
        $("#cashEventType").val(rowData.CASH_EVENT_TYPE);
        $("#calcOnAverages").val(rowData.CALC_ON_AVERAGES);
        $("#calcFullUndrawn").val(rowData.CALC_FULL_UNDRAWN);
        $("#transEventType").val(rowData.TRANS_EVENT_TYPE);
    }

    $("#fmChargeDefineMod").Validform({
        tiptype:2,
        callback:function(form){
            fmChargeDefineMod();
            return false;
        }
    });

    $(".select2").select2();
});

function fmChargeDefineMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="FM_CHARGE_DEFINE";
    keyFieldsJson.CHARGE_TYPE=$("#chargeType").val();
    generalFieldsJson.BO_CLASS=$("#boClass").val();
    generalFieldsJson.FIX_RATE=$("#fixRate").val();
    generalFieldsJson.HIGH_LIMIT=$("#highLimit").val();
    generalFieldsJson.LOW_LIMIT=$("#lowLimit").val();
    generalFieldsJson.MATRIX_CALC=$("#matrixCalc").val();
    generalFieldsJson.MATRIX_TYPE=$("#matrixType").val();
    generalFieldsJson.REMARKS=$("#remarks").val();
    generalFieldsJson.ROUTINE_NAME=$("#routineName").val();
    generalFieldsJson.FIX_AMT=$("#fixAmt").val();
    generalFieldsJson.FEE_DESC=$("#feeDesc").val();
    generalFieldsJson.CONVERT_FLAG=$("#convertFlag").val();
    generalFieldsJson.CHARGE_MODE=$("#chargeMode").val();
    generalFieldsJson.CHARGE_METHOD=$("#chargeMethod").val();
    generalFieldsJson.CCY=$("#ccy").val();
    generalFieldsJson.CASH_EVENT_TYPE=$("#cashEventType").val();
    generalFieldsJson.CALC_ON_AVERAGES=$("#calcOnAverages").val();
    generalFieldsJson.CALC_FULL_UNDRAWN=$("#calcFullUndrawn").val();
    generalFieldsJson.TRANS_EVENT_TYPE=$("#transEventType").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_fmChargeDefineMod,"json");
}

function callback_fmChargeDefineMod(json){
    if (json.success) {
        var dataTable=parent.$("#fmChargeDefine").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            CHARGE_TYPE:$("#chargeType").val(),
            BO_CLASS:$("#boClass").val(),
            FIX_RATE:$("#fixRate").val(),
            HIGH_LIMIT:$("#highLimit").val(),
            LOW_LIMIT:$("#lowLimit").val(),
            MATRIX_CALC:$("#matrixCalc").val(),
            MATRIX_TYPE:$("#matrixType").val(),
            REMARKS:$("#remarks").val(),
            ROUTINE_NAME:$("#routineName").val(),
            FIX_AMT:$("#fixAmt").val(),
            FEE_DESC:$("#feeDesc").val(),
            CONVERT_FLAG:$("#convertFlag").val(),
            CHARGE_MODE:$("#chargeMode").val(),
            CHARGE_METHOD:$("#chargeMethod").val(),
            CCY:$("#ccy").val(),
            CASH_EVENT_TYPE:$("#cashEventType").val(),
            CALC_ON_AVERAGES:$("#calcOnAverages").val(),
            CALC_FULL_UNDRAWN:$("#calcFullUndrawn").val(),
            TRANS_EVENT_TYPE:$("#transEventType").val(),
            COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function fmChargeDefineModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

