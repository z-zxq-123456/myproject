var rowData;
$(document).ready(function() {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_CURRENCY&tableCol=CCY,CCY_DESC",
        id: "ccy",
        async: false
    });

    if (parent.$("#mbStageInfo").find(".selected").length===1){
        rowData = parent.$('#mbStageInfo').DataTable().rows(".selected").data()[0];
        $("#stageCode").val(rowData.STAGE_CODE).attr("disabled",true);
        $("#ccy").val(rowData.CCY);
        $("#issueAmt").val(rowData.ISSUE_AMT);
        $("#issueYear").val(rowData.ISSUE_YEAR);
        $("#prodType").val(rowData.PROD_TYPE);
        $("#lastChangeDate").val(rowData.LAST_CHANGE_DATE);
        $("#prevUsedAmt").val(rowData.PREV_USED_AMT);
        $("#usedAmt").val(rowData.USED_AMT);
    }

    $("#mbStageInfoMod").Validform({
        tiptype:2,
        callback:function(){
            mbStageInfoMod();
            return false;
        }
    });

    $(".select2").select2();
});

function mbStageInfoMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="MB_STAGE_INFO";
    keyFieldsJson.STAGE_CODE=$("#stageCode").val();
    generalFieldsJson.CCY=$("#ccy").val();
    generalFieldsJson.ISSUE_AMT=$("#issueAmt").val();
    generalFieldsJson.ISSUE_YEAR=$("#issueYear").val();
    generalFieldsJson.PROD_TYPE=$("#prodType").val();
    generalFieldsJson.LAST_CHANGE_DATE=$("#lastChangeDate").val();
    generalFieldsJson.PREV_USED_AMT=$("#prevUsedAmt").val();
    generalFieldsJson.USED_AMT=$("#usedAmt").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_mbStageInfoMod,"json");
}

function callback_mbStageInfoMod(json){
    if (json.success) {
        if (parent.$("#mbStageInfo").find(".selected").length===1){
            rowData = parent.$('#mbStageInfo').DataTable().rows(".selected").data()[0];
            var columnStatus = rowData.COLUMN_STATUS;
            var dataTable=parent.$("#mbStageInfo").DataTable();
            dataTable.row(".selected").remove().draw(false);
            dataTable.row.add({
                STAGE_CODE:$("#stageCode").val(),
                CCY:$("#ccy").val(),
                ISSUE_AMT:$("#issueAmt").val(),
                ISSUE_YEAR:$("#issueYear").val(),
                PROD_TYPE:$("#prodType").val(),
                LAST_CHANGE_DATE:$("#lastChangeDate").val(),
                PREV_USED_AMT:$("#prevUsedAmt").val(),
                USED_AMT:$("#usedAmt").val(),
                COLUMN_STATUS:columnStatus
            }).draw(false);
            parent.showMsgDuringTime("编辑成功");
        }
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function mbStageInfoModCancel(){
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}