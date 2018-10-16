var rowData;
$(document).ready(function() {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_CURRENCY&tableCol=CCY,CCY_DESC",
        id: "ccy",
        async: false
    });
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=MB_EVENT_DEFAULT_TYPE&tableCol=EVENT_DEFAULT_TYPE",
        id: "eventType",
        async: false
    });
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=IRL_INT_TYPE&tableCol=INT_TAX_TYPE,INT_TAX_TYPE_DESC",
        id: "intType",
        async: false
    });

    if (parent.$("#mbStageInt").find(".selected").length===1){
        rowData = parent.$('#mbStageInt').DataTable().rows(".selected").data()[0];
        $("#seqNo").val(rowData.SEQ_NO).attr("disabled",true);
        $("#stageCode").val(rowData.STAGE_CODE).attr("disabled",true);
        $("#ccy").val(rowData.CCY);
        $("#prodType").val(rowData.PROD_TYPE);
        $("#issueYear").val(rowData.ISSUE_YEAR);
        $("#realRate").val(rowData.REAL_RATE);
        $("#intCalcType").val(rowData.INT_CALC_TYPE);
        $("#floatRate").val(rowData.FLOAT_RATE);
        $("#eventType").val(rowData.EVENT_TYPE);
        $("#actualRate").val(rowData.ACTUAL_RATE);
        $("#intType").val(rowData.INT_TYPE);
    }

    $("#mbStageIntMod").Validform({
        tiptype:2,
        callback:function(){
            mbStageIntMod();
            return false;
        }
    });

    $(".select2").select2();
});

function mbStageIntMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="MB_STAGE_INT";
    keyFieldsJson.SEQ_NO=$("#seqNo").val();
    keyFieldsJson.STAGE_CODE=$("#stageCode").val();
    generalFieldsJson.CCY=$("#ccy").val();
    generalFieldsJson.PROD_TYPE=$("#prodType").val();
    generalFieldsJson.ISSUE_YEAR=$("#issueYear").val();
    generalFieldsJson.REAL_RATE=$("#realRate").val();
    generalFieldsJson.INT_CALC_TYPE=$("#intCalcType").val();
    generalFieldsJson.FLOAT_RATE=$("#floatRate").val();
    generalFieldsJson.EVENT_TYPE=$("#eventType").val();
    generalFieldsJson.ACTUAL_RATE=$("#actualRate").val();
    generalFieldsJson.INT_TYPE=$("#intType").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_mbStageIntMod,"json");
}

function callback_mbStageIntMod(json){
    if (json.success) {
        if (parent.$("#mbStageInt").find(".selected").length===1){
            rowData = parent.$('#mbStageInt').DataTable().rows(".selected").data()[0];
            var columnStatus = rowData.COLUMN_STATUS;
            var dataTable=parent.$("#mbStageInt").DataTable();
            dataTable.row(".selected").remove().draw(false);
            dataTable.row.add({
                SEQ_NO:$("#seqNo").val(),
                STAGE_CODE:$("#stageCode").val(),
                CCY:$("#ccy").val(),
                PROD_TYPE:$("#prodType").val(),
                ISSUE_YEAR:$("#issueYear").val(),
                REAL_RATE:$("#realRate").val(),
                INT_CALC_TYPE:$("#intCalcType").val(),
                FLOAT_RATE:$("#floatRate").val(),
                EVENT_TYPE:$("#eventType").val(),
                ACTUAL_RATE:$("#actualRate").val(),
                INT_TYPE:$("#intType").val(),
                COLUMN_STATUS:columnStatus
            }).draw(false);
            parent.showMsgDuringTime("编辑成功");
        }
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function mbStageIntModCancel(){
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}