
var rowData;
$(document).ready(function() {
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_BRANCH_TBL&tableCol=BRANCH,BRANCH_NAME",
        id: "branch",
        async: false
    });
    getPkList({
        url: contextPath + "/baseCommon/pklistBase?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
        id: "company",
        async: false
    });

    if (parent.$("#fmUser").find(".selected").length===1){
        rowData = parent.$('#fmUser').DataTable().rows(".selected").data()[0];
        $("#userId").val(rowData.USER_ID).attr("disabled",true);
        $("#accountStatus").val(rowData.ACCOUNT_STATUS);
        $("#eodSodEnabled").val(rowData.EOD_SOD_ENABLED);
        $("#userType").val(rowData.USER_TYPE);
        $("#makeDate").val(rowData.MAKE_DATE);
        $("#profitCentre").val(rowData.PROFIT_CENTRE);
        $("#tbook").val(rowData.TBOOK);
        $("#userDesc").val(rowData.USER_DESC);
        $("#acctExec").val(rowData.ACCT_EXEC);
        $("#userLang").val(rowData.USER_LANG);
        $("#userLevel").val(rowData.USER_LEVEL);
        $("#userName").val(rowData.USER_NAME);
        $("#maker").val(rowData.MAKER);
        $("#interBranchInd").val(rowData.INTER_BRANCH_IND);
        $("#branch").val(rowData.BRANCH);
        $("#checker").val(rowData.CHECKER);
        $("#checkDate").val(rowData.CHECK_DATE);
        $("#company").val(rowData.COMPANY);
        $("#department").val(rowData.DEPARTMENT);
        $("#documentId").val(rowData.DOCUMENT_ID);
        $("#documentType").val(rowData.DOCUMENT_TYPE);
        $("#applicationUser").val(rowData.APPLICATION_USER);
        $("#authLevel").val(rowData.AUTH_LEVEL);
    }

    $("#fmUserMod").Validform({
        tiptype:2,
        callback:function(form){
            fmUserMod();
            return false;
        }
    });

    $(".select2").select2();
});

function fmUserMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="FM_USER";
    keyFieldsJson.USER_ID=$("#userId").val();
    generalFieldsJson.ACCOUNT_STATUS=$("#accountStatus").val();
    generalFieldsJson.EOD_SOD_ENABLED=$("#eodSodEnabled").val();
    generalFieldsJson.USER_TYPE=$("#userType").val();
    generalFieldsJson.MAKE_DATE=$("#makeDate").val();
    generalFieldsJson.PROFIT_CENTRE=$("#profitCentre").val();
    generalFieldsJson.TBOOK=$("#tbook").val();
    generalFieldsJson.USER_DESC=$("#userDesc").val();
    generalFieldsJson.ACCT_EXEC=$("#acctExec").val();
    generalFieldsJson.USER_LANG=$("#userLang").val();
    generalFieldsJson.USER_LEVEL=$("#userLevel").val();
    generalFieldsJson.USER_NAME=$("#userName").val();
    generalFieldsJson.MAKER=$("#maker").val();
    generalFieldsJson.INTER_BRANCH_IND=$("#interBranchInd").val();
    generalFieldsJson.BRANCH=$("#branch").val();
    generalFieldsJson.CHECKER=$("#checker").val();
    generalFieldsJson.CHECK_DATE=$("#checkDate").val();
    generalFieldsJson.COMPANY=$("#company").val();
    generalFieldsJson.DEPARTMENT=$("#department").val();
    generalFieldsJson.DOCUMENT_ID=$("#documentId").val();
    generalFieldsJson.DOCUMENT_TYPE=$("#documentType").val();
    generalFieldsJson.APPLICATION_USER=$("#applicationUser").val();
    generalFieldsJson.AUTH_LEVEL=$("#authLevel").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_fmUserMod,"json");
}

function callback_fmUserMod(json){
    if (json.success) {
        var dataTable=parent.$("#fmUser").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            USER_ID:$("#userId").val(),
            ACCOUNT_STATUS:$("#accountStatus").val(),
            EOD_SOD_ENABLED:$("#eodSodEnabled").val(),
            USER_TYPE:$("#userType").val(),
            MAKE_DATE:$("#makeDate").val(),
            PROFIT_CENTRE:$("#profitCentre").val(),
            TBOOK:$("#tbook").val(),
            USER_DESC:$("#userDesc").val(),
            ACCT_EXEC:$("#acctExec").val(),
            USER_LANG:$("#userLang").val(),
            USER_LEVEL:$("#userLevel").val(),
            USER_NAME:$("#userName").val(),
            MAKER:$("#maker").val(),
            INTER_BRANCH_IND:$("#interBranchInd").val(),
            BRANCH:$("#branch").val(),
            CHECKER:$("#checker").val(),
            CHECK_DATE:$("#checkDate").val(),
            COMPANY:$("#company").val(),
            DEPARTMENT:$("#department").val(),
            DOCUMENT_ID:$("#documentId").val(),
            DOCUMENT_TYPE:$("#documentType").val(),
            APPLICATION_USER:$("#applicationUser").val(),
            AUTH_LEVEL:$("#authLevel").val(),
            COLUMN_STATUS:'W'
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function fmUserModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

