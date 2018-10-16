var rowData;
$(document).ready(function() {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_CHANNEL&tableCol=CHANNEL,CHANNEL_DESC",
        id: "sourceType",
        async: false
    });

    if (parent.$("#mbTranDef").find(".selected").length===1){
        rowData = parent.$('#mbTranDef').DataTable().rows(".selected").data()[0];
        $("#tranType").val(rowData.TRAN_TYPE).attr("disabled",true);
        $("#cashTran").val(rowData.CASH_TRAN);
        $("#balTypePriority").val(rowData.BAL_TYPE_PRIORITY);
        $("#crDrMaintInd").val(rowData.CR_DR_MAINT_IND);
        $("#isCorrect").val(rowData.IS_CORRECT);
        $("#multiRvsTranTypeInd").val(rowData.MULTI_RVS_TRAN_TYPE_IND);
        $("#othTranType").val(rowData.OTH_TRAN_TYPE);
        $("#printTranDesc").val(rowData.PRINT_TRAN_DESC);
        $("#programIdGroup").val(rowData.PROGRAM_ID_GROUP);
        $("#recalcAcctStopPay").val(rowData.RECALC_ACCT_STOP_PAY);
        $("#recalcResAmt").val(rowData.RECALC_RES_AMT);
        $("#resPriority").val(rowData.RES_PRIORITY);
        $("#reversalTranFlag").val(rowData.REVERSAL_TRAN_FLAG);
        $("#reversalTranType").val(rowData.REVERSAL_TRAN_TYPE);
        $("#sourceType").val(rowData.SOURCE_TYPE);
        $("#tranClass").val(rowData.TRAN_CLASS);
        $("#tranTime").val(rowData.TRAN_TIME);
        $("#tranTimestamp").val(rowData.TRAN_TIMESTAMP);
        $("#tranTypeDesc").val(rowData.TRAN_TYPE_DESC);
        $("#updTrailboxFlag").val(rowData.UPD_TRAILBOX_FLAG);
        $("#availbalCalcType").val(rowData.AVAILBAL_CALC_TYPE);
        $("#balanceFlag").val(rowData.BALANCE_FLAG);
    }

    $("#mbTranDefMod").Validform({
        tiptype:2,
        callback:function(){
            mbTranDefMod();
            return false;
        }
    });

    $(".select2").select2();
});

function mbTranDefMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="MB_TRAN_DEF";
    keyFieldsJson.TRAN_TYPE=$("#tranType").val();
    generalFieldsJson.CASH_TRAN=$("#cashTran").val();
    generalFieldsJson.BAL_TYPE_PRIORITY=$("#balTypePriority").val();
    generalFieldsJson.CR_DR_MAINT_IND=$("#crDrMaintInd").val();
    generalFieldsJson.IS_CORRECT=$("#isCorrect").val();
    generalFieldsJson.MULTI_RVS_TRAN_TYPE_IND=$("#multiRvsTranTypeInd").val();
    generalFieldsJson.OTH_TRAN_TYPE=$("#othTranType").val();
    generalFieldsJson.PRINT_TRAN_DESC=$("#printTranDesc").val();
    generalFieldsJson.PROGRAM_ID_GROUP=$("#programIdGroup").val();
    generalFieldsJson.RECALC_ACCT_STOP_PAY=$("#recalcAcctStopPay").val();
    generalFieldsJson.RECALC_RES_AMT=$("#recalcResAmt").val();
    generalFieldsJson.RES_PRIORITY=$("#resPriority").val();
    generalFieldsJson.REVERSAL_TRAN_FLAG=$("#reversalTranFlag").val();
    generalFieldsJson.REVERSAL_TRAN_TYPE=$("#reversalTranType").val();
    generalFieldsJson.SOURCE_TYPE=$("#sourceType").val();
    generalFieldsJson.TRAN_CLASS=$("#tranClass").val();
    generalFieldsJson.TRAN_TIME=$("#tranTime").val();
    generalFieldsJson.TRAN_TIMESTAMP=$("#tranTimestamp").val();
    generalFieldsJson.TRAN_TYPE_DESC=$("#tranTypeDesc").val();
    generalFieldsJson.UPD_TRAILBOX_FLAG=$("#updTrailboxFlag").val();
    generalFieldsJson.AVAILBAL_CALC_TYPE=$("#availbalCalcType").val();
    generalFieldsJson.BALANCE_FLAG=$("#balanceFlag").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_mbTranDefMod,"json");
}

function callback_mbTranDefMod(json){
    if (json.success) {
        if (parent.$("#mbTranDef").find(".selected").length===1){
            rowData = parent.$('#mbTranDef').DataTable().rows(".selected").data()[0];
            var columnStatus = rowData.COLUMN_STATUS;
            var dataTable=parent.$("#mbTranDef").DataTable();
            dataTable.row(".selected").remove().draw(false);
            dataTable.row.add({
                TRAN_TYPE:$("#tranType").val(),
                CASH_TRAN:$("#cashTran").val(),
                BAL_TYPE_PRIORITY:$("#balTypePriority").val(),
                CR_DR_MAINT_IND:$("#crDrMaintInd").val(),
                IS_CORRECT:$("#isCorrect").val(),
                MULTI_RVS_TRAN_TYPE_IND:$("#multiRvsTranTypeInd").val(),
                OTH_TRAN_TYPE:$("#othTranType").val(),
                PRINT_TRAN_DESC:$("#printTranDesc").val(),
                PROGRAM_ID_GROUP:$("#programIdGroup").val(),
                RECALC_ACCT_STOP_PAY:$("#recalcAcctStopPay").val(),
                RECALC_RES_AMT:$("#recalcResAmt").val(),
                RES_PRIORITY:$("#resPriority").val(),
                REVERSAL_TRAN_FLAG:$("#reversalTranFlag").val(),
                REVERSAL_TRAN_TYPE:$("#reversalTranType").val(),
                SOURCE_TYPE:$("#sourceType").val(),
                TRAN_CLASS:$("#tranClass").val(),
                TRAN_TIME:$("#tranTime").val(),
                TRAN_TIMESTAMP:$("#tranTimestamp").val(),
                TRAN_TYPE_DESC:$("#tranTypeDesc").val(),
                UPD_TRAILBOX_FLAG:$("#updTrailboxFlag").val(),
                AVAILBAL_CALC_TYPE:$("#availbalCalcType").val(),
                BALANCE_FLAG:$("#balanceFlag").val(),
                COLUMN_STATUS:columnStatus
            }).draw(false);
            parent.showMsgDuringTime("编辑成功");
        }
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function mbTranDefModCancel(){
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}