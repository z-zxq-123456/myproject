var rowData;
$(document).ready(function() {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=IRL_INT_TYPE&tableCol=INT_TAX_TYPE,INT_TAX_TYPE_DESC",
        id: "intType",
        async: false
    });

    if (parent.$("#irlAmtSplit").find(".selected").length===1){
        rowData = parent.$('#irlAmtSplit').DataTable().rows(".selected").data()[0];
        $("#amtSeqNo").val(rowData.AMT_SEQ_NO).attr("disabled",true);
        $("#amtSplitId").val(rowData.AMT_SPLIT_ID).attr("disabled",true);
        $("#amtSplitMode").val(rowData.AMT_SPLIT_MODE);
        $("#intType").val(rowData.INT_TYPE);
        $("#periSplitId").val(rowData.PERI_SPLIT_ID);
        $("#ruleId").val(rowData.RULE_ID);
        $("#splitAmt").val(rowData.SPLIT_AMT);
    }

    $("#irlAmtSplitMod").Validform({
        tiptype:2,
        callback:function(){
            irlAmtSplitMod();
            return false;
        }
    });

    $(".select2").select2();
});

function irlAmtSplitMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="IRL_AMT_SPLIT";
    keyFieldsJson.AMT_SEQ_NO=$("#amtSeqNo").val();
    keyFieldsJson.AMT_SPLIT_ID=$("#amtSplitId").val();
    generalFieldsJson.AMT_SPLIT_MODE=$("#amtSplitMode").val();
    generalFieldsJson.INT_TYPE=$("#intType").val();
    generalFieldsJson.PERI_SPLIT_ID=$("#periSplitId").val();
    generalFieldsJson.RULE_ID=$("#ruleId").val();
    generalFieldsJson.SPLIT_AMT=$("#splitAmt").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_irlAmtSplitMod,"json");
}

function callback_irlAmtSplitMod(json){
    if (json.success) {
        if (parent.$("#irlAmtSplit").find(".selected").length===1){
            rowData = parent.$('#irlAmtSplit').DataTable().rows(".selected").data()[0];
            var columnStatus = rowData.COLUMN_STATUS;
            var dataTable=parent.$("#irlAmtSplit").DataTable();
            dataTable.row(".selected").remove().draw(false);
            dataTable.row.add({
                AMT_SEQ_NO:$("#amtSeqNo").val(),
                AMT_SPLIT_ID:$("#amtSplitId").val(),
                AMT_SPLIT_MODE:$("#amtSplitMode").val(),
                INT_TYPE:$("#intType").val(),
                PERI_SPLIT_ID:$("#periSplitId").val(),
                RULE_ID:$("#ruleId").val(),
                SPLIT_AMT:$("#splitAmt").val(),
                COLUMN_STATUS:columnStatus
            }).draw(false);
            parent.showMsgDuringTime("编辑成功");
        }
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function irlAmtSplitModCancel(){
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}