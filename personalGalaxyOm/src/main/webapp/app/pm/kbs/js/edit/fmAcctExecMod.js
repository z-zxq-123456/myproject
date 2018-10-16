
var rowData;
$(document).ready(function() {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_BRANCH&tableCol=BRANCH,BRANCH_NAME",
        id: "branch",
        async: false
    });
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
        id: "company",
        async: false
    });

    if (parent.$("#fmAcctExec").find(".selected").length===1){
        rowData = parent.$('#fmAcctExec').DataTable().rows(".selected").data()[0];
        $("#acctExec").val(rowData.ACCT_EXEC).attr("disabled",true);
        $("#acctExecName").val(rowData.ACCT_EXEC_NAME);
        $("#collatMgrInd").val(rowData.COLLAT_MGR_IND);
        $("#branch").val(rowData.BRANCH);
        $("#company").val(rowData.COMPANY);
        $("#manager").val(rowData.MANAGER);
        $("#profitSegment").val(rowData.PROFIT_SEGMENT);
    }

    $("#fmAcctExecMod").Validform({
        tiptype:2,
        callback:function(form){
            fmAcctExecMod();
            return false;
        }
    });

    $(".select2").select2();
});

function fmAcctExecMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="FM_ACCT_EXEC";
    keyFieldsJson.ACCT_EXEC=$("#acctExec").val();
    generalFieldsJson.ACCT_EXEC_NAME=$("#acctExecName").val();
    generalFieldsJson.COLLAT_MGR_IND=$("#collatMgrInd").val();
    generalFieldsJson.BRANCH=$("#branch").val();
    generalFieldsJson.COMPANY=$("#company").val();
    generalFieldsJson.MANAGER=$("#manager").val();
    generalFieldsJson.PROFIT_SEGMENT=$("#profitSegment").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_fmAcctExecMod,"json");
}

function callback_fmAcctExecMod(json){
    if (json.success) {
        var dataTable=parent.$("#fmAcctExec").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            ACCT_EXEC:$("#acctExec").val(),
            ACCT_EXEC_NAME:$("#acctExecName").val(),
            COLLAT_MGR_IND:$("#collatMgrInd").val(),
            BRANCH:$("#branch").val(),
            COMPANY:$("#company").val(),
            MANAGER:$("#manager").val(),
            PROFIT_SEGMENT:$("#profitSegment").val(),
            COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function fmAcctExecModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

