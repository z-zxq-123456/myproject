var rowData;
$(document).ready(function() {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_BRANCH&tableCol=BRANCH,BRANCH_NAME",
        id: "settleBranch",
        async: false
    });

    if (parent.$("#mbSettleBranch").find(".selected").length===1){
        rowData = parent.$('#mbSettleBranch').DataTable().rows(".selected").data()[0];
        $("#branch").val(rowData.BRANCH).attr("disabled",true);
        $("#settleLevel").val(rowData.SETTLE_LEVEL);
        $("#settleBranch").val(rowData.SETTLE_BRANCH);
        $("#settleBaseAcct").val(rowData.SETTLE_BASE_ACCT);
        $("#settleBaseAcctUp").val(rowData.SETTLE_BASE_ACCT_UP);
    }

    $("#mbSettleBranchMod").Validform({
        tiptype:2,
        callback:function(){
            mbSettleBranchMod();
            return false;
        }
    });

    $(".select2").select2();
});

function mbSettleBranchMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="MB_SETTLE_BRANCH";
    keyFieldsJson.BRANCH=$("#branch").val();
    generalFieldsJson.SETTLE_LEVEL=$("#settleLevel").val();
    generalFieldsJson.SETTLE_BRANCH=$("#settleBranch").val();
    generalFieldsJson.SETTLE_BASE_ACCT=$("#settleBaseAcct").val();
    generalFieldsJson.SETTLE_BASE_ACCT_UP=$("#settleBaseAcctUp").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_mbSettleBranchMod,"json");
}

function callback_mbSettleBranchMod(json){
    if (json.success) {
        if (parent.$("#mbSettleBranch").find(".selected").length===1){
            rowData = parent.$('#mbSettleBranch').DataTable().rows(".selected").data()[0];
            var columnStatus = rowData.COLUMN_STATUS;
            var dataTable=parent.$("#mbSettleBranch").DataTable();
            dataTable.row(".selected").remove().draw(false);
            dataTable.row.add({
                BRANCH:$("#branch").val(),
                SETTLE_LEVEL:$("#settleLevel").val(),
                SETTLE_BRANCH:$("#settleBranch").val(),
                SETTLE_BASE_ACCT:$("#settleBaseAcct").val(),
                SETTLE_BASE_ACCT_UP:$("#settleBaseAcctUp").val(),
                COLUMN_STATUS:columnStatus
            }).draw(false);
            parent.showMsgDuringTime("编辑成功");
        }
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function mbSettleBranchModCancel(){
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}