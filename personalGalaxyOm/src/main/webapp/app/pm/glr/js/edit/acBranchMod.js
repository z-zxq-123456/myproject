
var rowData;
$(document).ready(function() {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_BRANCH&tableCol=BRANCH,BRANCH_NAME",
        id: "settleBranch",
        async: false
    });
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=AC_SUBJECT&tableCol=SUBJECT_CODE,SUBJECT_DESC",
        id: "settleSubject",
        async: false
    });
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=AC_SUBJECT&tableCol=SUBJECT_CODE,SUBJECT_DESC",
        id: "settleSubjectUp",
        async: false
    });

    if (parent.$("#acBranch").find(".selected").length===1){
        rowData = parent.$('#acBranch').DataTable().rows(".selected").data()[0];
        $("#branch").val(rowData.BRANCH).attr("disabled",true);
        $("#settleLevel").val(rowData.SETTLE_LEVEL);
        $("#settleAcctSeq").val(rowData.SETTLE_ACCT_SEQ);
        $("#settleAcctSeqUp").val(rowData.SETTLE_ACCT_SEQ_UP);
        $("#settleBranch").val(rowData.SETTLE_BRANCH);
        $("#settleSubject").val(rowData.SETTLE_SUBJECT);
        $("#settleSubjectUp").val(rowData.SETTLE_SUBJECT_UP);
    }

    $("#acBranchMod").Validform({
        tiptype:2,
        callback:function(form){
            acBranchMod();
            return false;
        }
    });

    $(".select2").select2();
});

function acBranchMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="AC_BRANCH";
    keyFieldsJson.BRANCH=$("#branch").val();
    generalFieldsJson.SETTLE_LEVEL=$("#settleLevel").val();
    generalFieldsJson.SETTLE_ACCT_SEQ=$("#settleAcctSeq").val();
    generalFieldsJson.SETTLE_ACCT_SEQ_UP=$("#settleAcctSeqUp").val();
    generalFieldsJson.SETTLE_BRANCH=$("#settleBranch").val();
    generalFieldsJson.SETTLE_SUBJECT=$("#settleSubject").val();
    generalFieldsJson.SETTLE_SUBJECT_UP=$("#settleSubjectUp").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_acBranchMod,"json");
}

function callback_acBranchMod(json){
    if (json.success) {
        var dataTable=parent.$("#acBranch").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            BRANCH:$("#branch").val(),
            SETTLE_LEVEL:$("#settleLevel").val(),
            SETTLE_ACCT_SEQ:$("#settleAcctSeq").val(),
            SETTLE_ACCT_SEQ_UP:$("#settleAcctSeqUp").val(),
            SETTLE_BRANCH:$("#settleBranch").val(),
            SETTLE_SUBJECT:$("#settleSubject").val(),
            SETTLE_SUBJECT_UP:$("#settleSubjectUp").val(),
            COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function acBranchModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

