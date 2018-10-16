var rowData;
$(document).ready(function() {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_PERIOD_FREQ&tableCol=PERIOD_FREQ,PERIOD_FREQ_DESC",
        id: "periodFreq",
        async: false
    });
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_CURRENCY&tableCol=CCY,CCY_DESC",
        id: "ccy",
        async: false
    });

    if (parent.$("#mbCleanParameter").find(".selected").length===1){
        rowData = parent.$('#mbCleanParameter').DataTable().rows(".selected").data()[0];
        $("#seqNo").val(rowData.SEQ_NO).attr("disabled",true);
        $("#acctStatus").val(rowData.ACCT_STATUS);
        $("#termPeriod").val(rowData.TERM_PERIOD);
        $("#status").val(rowData.STATUS);
        $("#startTime").val(rowData.START_TIME);
        $("#prodType").val(rowData.PROD_TYPE);
        $("#periodFreqType").val(rowData.PERIOD_FREQ_TYPE);
        $("#periodFreq").val(rowData.PERIOD_FREQ);
        $("#nextCleanDate").val(rowData.NEXT_CLEAN_DATE);
        $("#lastCleanDate").val(rowData.LAST_CLEAN_DATE);
        $("#endTime").val(rowData.END_TIME);
        $("#cleanType").val(rowData.CLEAN_TYPE);
        $("#ccy").val(rowData.CCY);
        $("#balance").val(rowData.BALANCE);
        $("#agreementType").val(rowData.AGREEMENT_TYPE);
        $("#acctType").val(rowData.ACCT_TYPE);
        $("#termType").val(rowData.TERM_TYPE);
    }

    $("#mbCleanParameterMod").Validform({
        tiptype:2,
        callback:function(){
            mbCleanParameterMod();
            return false;
        }
    });

    $(".select2").select2();
});

function mbCleanParameterMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="MB_CLEAN_PARAMETER";
    keyFieldsJson.SEQ_NO=$("#seqNo").val();
    generalFieldsJson.ACCT_STATUS=$("#acctStatus").val();
    generalFieldsJson.TERM_PERIOD=$("#termPeriod").val();
    generalFieldsJson.STATUS=$("#status").val();
    generalFieldsJson.START_TIME=$("#startTime").val();
    generalFieldsJson.PROD_TYPE=$("#prodType").val();
    generalFieldsJson.PERIOD_FREQ_TYPE=$("#periodFreqType").val();
    generalFieldsJson.PERIOD_FREQ=$("#periodFreq").val();
    generalFieldsJson.NEXT_CLEAN_DATE=$("#nextCleanDate").val();
    generalFieldsJson.LAST_CLEAN_DATE=$("#lastCleanDate").val();
    generalFieldsJson.END_TIME=$("#endTime").val();
    generalFieldsJson.CLEAN_TYPE=$("#cleanType").val();
    generalFieldsJson.CCY=$("#ccy").val();
    generalFieldsJson.BALANCE=$("#balance").val();
    generalFieldsJson.AGREEMENT_TYPE=$("#agreementType").val();
    generalFieldsJson.ACCT_TYPE=$("#acctType").val();
    generalFieldsJson.TERM_TYPE=$("#termType").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_mbCleanParameterMod,"json");
}

function callback_mbCleanParameterMod(json){
    if (json.success) {
        if (parent.$("#mbCleanParameter").find(".selected").length===1){
            rowData = parent.$('#mbCleanParameter').DataTable().rows(".selected").data()[0];
            var columnStatus = rowData.COLUMN_STATUS;
            var dataTable=parent.$("#mbCleanParameter").DataTable();
            dataTable.row(".selected").remove().draw(false);
            dataTable.row.add({
                SEQ_NO:$("#seqNo").val(),
                ACCT_STATUS:$("#acctStatus").val(),
                TERM_PERIOD:$("#termPeriod").val(),
                STATUS:$("#status").val(),
                START_TIME:$("#startTime").val(),
                PROD_TYPE:$("#prodType").val(),
                PERIOD_FREQ_TYPE:$("#periodFreqType").val(),
                PERIOD_FREQ:$("#periodFreq").val(),
                NEXT_CLEAN_DATE:$("#nextCleanDate").val(),
                LAST_CLEAN_DATE:$("#lastCleanDate").val(),
                END_TIME:$("#endTime").val(),
                CLEAN_TYPE:$("#cleanType").val(),
                CCY:$("#ccy").val(),
                BALANCE:$("#balance").val(),
                AGREEMENT_TYPE:$("#agreementType").val(),
                ACCT_TYPE:$("#acctType").val(),
                TERM_TYPE:$("#termType").val(),
                COLUMN_STATUS:columnStatus
            }).draw(false);
            parent.showMsgDuringTime("编辑成功");
        }
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function mbCleanParameterModCancel(){
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}