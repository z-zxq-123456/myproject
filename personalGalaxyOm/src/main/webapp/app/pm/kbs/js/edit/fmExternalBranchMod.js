
var rowData;
$(document).ready(function() {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
        id: "company",
        async: false
    });

    if (parent.$("#fmExternalBranch").find(".selected").length===1){
        rowData = parent.$('#fmExternalBranch').DataTable().rows(".selected").data()[0];
        $("#bankCode").val(rowData.BANK_CODE).attr("disabled",true);
        $("#branchCode").val(rowData.BRANCH_CODE).attr("disabled",true);
        $("#country").val(rowData.COUNTRY);
        $("#company").val(rowData.COMPANY);
        $("#state").val(rowData.STATE);
        $("#branchName").val(rowData.BRANCH_NAME);
    }

    $("#fmExternalBranchMod").Validform({
        tiptype:2,
        callback:function(form){
            fmExternalBranchMod();
            return false;
        }
    });

    $(".select2").select2();
});

function fmExternalBranchMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="FM_EXTERNAL_BRANCH";
    keyFieldsJson.BANK_CODE=$("#bankCode").val();
    keyFieldsJson.BRANCH_CODE=$("#branchCode").val();
    generalFieldsJson.COUNTRY=$("#country").val();
    generalFieldsJson.COMPANY=$("#company").val();
    generalFieldsJson.STATE=$("#state").val();
    generalFieldsJson.BRANCH_NAME=$("#branchName").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_fmExternalBranchMod,"json");
}

function callback_fmExternalBranchMod(json){
    if (json.success) {
        var dataTable=parent.$("#fmExternalBranch").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
        BANK_CODE:$("#bankCode").val()
        ,BRANCH_CODE:$("#branchCode").val()
        ,COUNTRY:$("#country").val()
        ,COMPANY:$("#company").val()
        ,STATE:$("#state").val()
        ,BRANCH_NAME:$("#branchName").val()
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function fmExternalBranchModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

