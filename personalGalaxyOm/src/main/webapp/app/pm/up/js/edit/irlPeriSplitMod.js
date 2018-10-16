var rowData;
$(document).ready(function() {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=IRL_INT_TYPE&tableCol=INT_TAX_TYPE,INT_TAX_TYPE_DESC",
        id: "intType",
        async: false
    });

    if (parent.$("#irlPeriSplit").find(".selected").length===1){
        rowData = parent.$('#irlPeriSplit').DataTable().rows(".selected").data()[0];
        $("#periSeqNo").val(rowData.PERI_SEQ_NO).attr("disabled",true);
        $("#periSplitId").val(rowData.PERI_SPLIT_ID).attr("disabled",true);
        $("#period").val(rowData.PERIOD);
        $("#periodType").val(rowData.PERIOD_TYPE);
        $("#ruleId").val(rowData.RULE_ID);
        $("#recalMethod").val(rowData.RECAL_METHOD);
        $("#periSplitMode").val(rowData.PERI_SPLIT_MODE);
        $("#recalDays").val(rowData.RECAL_DAYS);
        $("#intType").val(rowData.INT_TYPE);
        $("#amtSplitId").val(rowData.AMT_SPLIT_ID);
    }

    $("#irlPeriSplitMod").Validform({
        tiptype:2,
        callback:function(){
            irlPeriSplitMod();
            return false;
        }
    });

    $(".select2").select2();
});

function irlPeriSplitMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="IRL_PERI_SPLIT";
    keyFieldsJson.PERI_SEQ_NO=$("#periSeqNo").val();
    keyFieldsJson.PERI_SPLIT_ID=$("#periSplitId").val();
    generalFieldsJson.PERIOD=$("#period").val();
    generalFieldsJson.PERIOD_TYPE=$("#periodType").val();
    generalFieldsJson.RULE_ID=$("#ruleId").val();
    generalFieldsJson.RECAL_METHOD=$("#recalMethod").val();
    generalFieldsJson.PERI_SPLIT_MODE=$("#periSplitMode").val();
    generalFieldsJson.RECAL_DAYS=$("#recalDays").val();
    generalFieldsJson.INT_TYPE=$("#intType").val();
    generalFieldsJson.AMT_SPLIT_ID=$("#amtSplitId").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_irlPeriSplitMod,"json");
}

function callback_irlPeriSplitMod(json){
    if (json.success) {
        if (parent.$("#irlPeriSplit").find(".selected").length===1){
            rowData = parent.$('#irlPeriSplit').DataTable().rows(".selected").data()[0];
            var columnStatus = rowData.COLUMN_STATUS;
            var dataTable=parent.$("#irlPeriSplit").DataTable();
            dataTable.row(".selected").remove().draw(false);
            dataTable.row.add({
                PERI_SEQ_NO:$("#periSeqNo").val(),
                PERI_SPLIT_ID:$("#periSplitId").val(),
                PERIOD:$("#period").val(),
                PERIOD_TYPE:$("#periodType").val(),
                RULE_ID:$("#ruleId").val(),
                RECAL_METHOD:$("#recalMethod").val(),
                PERI_SPLIT_MODE:$("#periSplitMode").val(),
                RECAL_DAYS:$("#recalDays").val(),
                INT_TYPE:$("#intType").val(),
                AMT_SPLIT_ID:$("#amtSplitId").val(),
                COLUMN_STATUS:columnStatus
            }).draw(false);
            parent.showMsgDuringTime("编辑成功");
        }
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function irlPeriSplitModCancel(){
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}