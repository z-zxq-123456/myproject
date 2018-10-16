
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

    if (parent.$("#tbTrailbox").find(".selected").length===1){
        rowData = parent.$('#tbTrailbox').DataTable().rows(".selected").data()[0];
        $("#trailboxId").val(rowData.TRAILBOX_ID).attr("disabled",true);
        $("#branch").val(rowData.BRANCH);
        $("#trailboxType").val(rowData.TRAILBOX_TYPE);
        $("#trailboxStatus").val(rowData.TRAILBOX_STATUS);
        $("#voucherEqualDate").val(rowData.VOUCHER_EQUAL_DATE);
        $("#userId").val(rowData.USER_ID);
        $("#updateDate").val(rowData.UPDATE_DATE);
        $("#trailboxProperty").val(rowData.TRAILBOX_PROPERTY);
        $("#assignUserId").val(rowData.ASSIGN_USER_ID);
        $("#lastUserId").val(rowData.LAST_USER_ID);
        $("#createDate").val(rowData.CREATE_DATE);
        $("#company").val(rowData.COMPANY);
        $("#cashRunDate").val(rowData.CASH_RUN_DATE);
        $("#cashEqualDate").val(rowData.CASH_EQUAL_DATE);
        $("#voucherRunDate").val(rowData.VOUCHER_RUN_DATE);
    }

    $("#tbTrailboxMod").Validform({
        tiptype:2,
        callback:function(form){
            tbTrailboxMod();
            return false;
        }
    });

    $(".select2").select2();
});

function tbTrailboxMod(){
    var paraJson,generalFieldsJson,keyFieldsJson;
    paraJson = {};
    generalFieldsJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateAndInsertForUpdate";
    paraJson.tableName="TB_TRAILBOX";
    keyFieldsJson.TRAILBOX_ID=$("#trailboxId").val();
    generalFieldsJson.BRANCH=$("#branch").val();
    generalFieldsJson.TRAILBOX_TYPE=$("#trailboxType").val();
    generalFieldsJson.TRAILBOX_STATUS=$("#trailboxStatus").val();
    generalFieldsJson.VOUCHER_EQUAL_DATE=$("#voucherEqualDate").val();
    generalFieldsJson.USER_ID=$("#userId").val();
    generalFieldsJson.UPDATE_DATE=$("#updateDate").val();
    generalFieldsJson.TRAILBOX_PROPERTY=$("#trailboxProperty").val();
    generalFieldsJson.ASSIGN_USER_ID=$("#assignUserId").val();
    generalFieldsJson.LAST_USER_ID=$("#lastUserId").val();
    generalFieldsJson.CREATE_DATE=$("#createDate").val();
    generalFieldsJson.COMPANY=$("#company").val();
    generalFieldsJson.CASH_RUN_DATE=$("#cashRunDate").val();
    generalFieldsJson.CASH_EQUAL_DATE=$("#cashEqualDate").val();
    generalFieldsJson.VOUCHER_RUN_DATE=$("#voucherRunDate").val();
    paraJson.key = keyFieldsJson;
    paraJson.general=generalFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.parent.$(".breadcrumb").data("reqNum");
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params,callback_tbTrailboxMod,"json");
}

function callback_tbTrailboxMod(json){
    if (json.success) {
        var dataTable=parent.$("#tbTrailbox").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
        TRAILBOX_ID:$("#trailboxId").val()
        ,BRANCH:$("#branch").val()
        ,TRAILBOX_TYPE:$("#trailboxType").val()
        ,TRAILBOX_STATUS:$("#trailboxStatus").val()
        ,VOUCHER_EQUAL_DATE:$("#voucherEqualDate").val()
        ,USER_ID:$("#userId").val()
        ,UPDATE_DATE:$("#updateDate").val()
        ,TRAILBOX_PROPERTY:$("#trailboxProperty").val()
        ,ASSIGN_USER_ID:$("#assignUserId").val()
        ,LAST_USER_ID:$("#lastUserId").val()
        ,CREATE_DATE:$("#createDate").val()
        ,COMPANY:$("#company").val()
        ,CASH_RUN_DATE:$("#cashRunDate").val()
        ,CASH_EQUAL_DATE:$("#cashEqualDate").val()
        ,VOUCHER_RUN_DATE:$("#voucherRunDate").val()
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function tbTrailboxModCancel(){
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}

